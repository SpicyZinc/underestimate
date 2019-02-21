/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
Your algorithm's runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1].

Example 1: 
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2: 
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

idea:
1. direct method
go through the array, find all target values and save index into a LinkedList,
then peekFirst() and peekLast() to get the positions 

2. use binary search twice:
binarySearchForRightMost() get right most position of target

one for find position of target - 1, position plus 1 is necessary
one for find position of target

或者 用一般的 binary search 先找到一个target的 position
然后再向两边延伸while 找

3. two binary search methods
searchForLeftMost r = m - 1;
searchForRightMost l = m + 1;
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
    // direct method
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
        } else if (list.size() == 1) {
            res[0] = res[1] = list.peekFirst();
        } else {
            res[0] = res[1] = -1;
        }
        
        return res;
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
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
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
            } else {
                idx = m;
                l = m + 1;
            }
        }

        return idx;
    }

    // method 3
    public int[] searchRange(int[] nums, int target) {
        int left = searchForLeftMost(nums, target);
        int right = searchForRightMost(nums, target);
        
        return new int[] {left, right};
    }
    
    private int searchForLeftMost(int[] A, int target) {
        int left = 0;
        int right = A.length - 1;
        
        int idx = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (A[mid] < target) {
                left = mid + 1;
            } else if (A[mid] > target) {
                right = mid - 1;
            } else {
                if (mid == 0) {
                    return mid;
                }
                if (A[mid - 1] == target) {
                    right = mid - 1;
                } else {
                    return mid;
                }
            }
        }
        
        return idx;
    }
    
    private int searchForRightMost(int[] A, int target) {
        int left = 0;
        int right = A.length - 1;
        
        int idx = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (A[mid] < target) {
                left = mid + 1;
            } else if (A[mid] > target) {
                right = mid - 1;
            } else {
                if (mid == A.length - 1) {
                    return mid;
                }
                if (A[mid + 1] == target) {
                    left = mid + 1;
                } else {
                    return mid;
                }
            }
        }
        
        return idx;
    }
}