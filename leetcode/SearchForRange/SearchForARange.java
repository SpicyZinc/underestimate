/*
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4]

idea:
1. typical binary search to find leftmost index, then while() to find the rightmost
2. binary search variation, search for the leftmost index and rightmost index
3. LinkedList peekFirst() and peekLast()

1. direct method
go through the array, find all target values and save index into a LinkedList,
then peekFirst() and peekLast() to get the positions 
I did not use binary search which is wrong

2. binary search
searchLeftMost r = m - 1;
searchRightMost l = m + 1;

Sorted array, binary search, O(logN)

this version of binary search is to return index of biggest number less than target

use binary search twice:
one for find position of target-1, position plus 1 is necessary
one for find position of target

note:
not typical binary search
it looks like to find the right most boundary

*/

import java.util.*;

public class SearchForARange {
    public static void main(String[] args) {
        SearchForARange eg = new SearchForARange();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] idx = eg.searchRange(nums, 8);
        for (int i : idx) {
            System.out.println(i);
        }
    }
    // method 1
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        int pos = binarySearch(nums, target);
        if (pos == -1) {
            return result;
        }
        int leftMost = pos;
        int rightMost = pos;
        while (leftMost >= 0 && nums[leftMost] == target) {
            leftMost--;
        }
        while (rightMost < nums.length && nums[rightMost] == target) {
            rightMost++;
        }
        result[0] = leftMost + 1;
        result[1] = rightMost - 1;
        return result;
    }
    // typical binary search, not guarantee that found index is the leftmost
    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            }
            else if (nums[mid] > target) {
                right = mid - 1;
            }
            else {
                return mid;
            }
        }

        return -1;
    }

    // method 2
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
		// target - 1 is very smart, plus 1
        res[0] = binarySearchForRightMost(nums, target - 1) + 1;
        res[1] = binarySearchForRightMost(nums, target);
		
        if (res[1] == -1 || nums[res[1]] != target) {
            res[0] = -1;
            res[1] = -1;
        }
        return res;
    }
	// binary search implementation for the right most position of the target
	public int binarySearchForRightMost(int A[], int target) {
        int l = 0;
        int r = A.length - 1;
        int idx = -1;

        while (l <= r) {
			int m = l + (r - l) / 2;
            if (A[m] > target) {
                r = m - 1;
            }
            else {
                idx = m;
                l = m + 1;
            }
        }

        return idx;
    }

    // method 3
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[2];
        ret[0] = searchForLeftMost(nums, target);
        ret[1] = searchForRightMost(nums, target);

        return ret;
    }

    private int searchForLeftMost(int[] A, int target) {
        int l = 0;
        int r = A.length - 1;
        int idx = -1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (A[m] == target) {
                if (m == 0) return m;
                if (A[m - 1] == target) {
                    r = m - 1; 
                } 
                else {
                    return m;
                }
            }
            else if (A[m] > target) {
                r = m - 1;
            }
            else {
                l = m + 1;  
            }
        }
        // this one not working as I expected
        // while (l <= r) {
        //     int mid = l + (r - l) / 2;
        //     if (A[mid] >= target) {
        //         idx = mid;
        //         r = mid - 1;
        //     }
        //     else {
        //         l = mid + 1;
        //     }
        // }

        return idx;
    }

    private int searchForRightMost(int[] A, int target) {
        int l = 0;
        int r = A.length - 1;
        int idx = -1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (A[m] == target) {
                if (m == A.length - 1) return m;
                if (A[m + 1] == target) {
                    l = m + 1; 
                } 
                else {
                    return m;
                }
            }
            else if (A[m] > target) {
                r = m - 1;
            }
            else {
                l = m + 1;  
            }
        }

        // while (l <= r) {
        //     int m = l + (r - l) / 2;
        //     if (A[m] > target) {
        //         r = m - 1;
        //     }
        //     else {
        //         idx = m;
        //         l = m + 1;
        //     }
        // }

        return idx;
    }

    // method 4
    public int[] searchRange(int[] nums, int target) {
        // note, not List interface
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                list.add(i);
            }
        }
        int[] res = new int[2];
        if (list.size() > 1) {
            res[0] = list.peekFirst();
            res[1] = list.peekLast();
        }
        else if (list.size() == 1) {
            res[0] = res[1] = list.peekFirst();
        }
        else {
            res[0] = res[1] = -1;
        }
        
        return res;
    }
}