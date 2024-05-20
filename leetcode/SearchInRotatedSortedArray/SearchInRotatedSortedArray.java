/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
assume no duplicate exists in the array.
(0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2)

You are given a target value to search. 
If found in the array return its index, otherwise return -1.

idea:
remember to use binary search, this is the critical test point
One thing to note: while (start <= end) 

since the sorted array is only rotated once beforehand, 
binary divide the array, then either left part is sorted or right part is sorted
find the sorted part,
and determine new partial array (by redefining 'start' or 'end') in which to search the target,
then eliminate the rest of the array

keep apply binary search on the unsorted half part because it is another rotated sorted array

new:
recursion is for sure, binary search
pivot binary search in the rotated array
first find pivot helper findPivot()
if -1, direct binary search
respectively apply binary search around pivot
*/

public class SearchInRotatedSortedArray {
    // Sat Apr 27 00:47:22 2024
    // divide and conquer
    public int binarySearch(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;

        // case where the array isn't rotated ( since the questions says "*the array is possibly rotated* )
        if (nums[0] < nums[n-1]) {
            return binarySearch(nums, 0, n - 1, target);
        }

        while (l <= r) {
            int mid = (l + r) / 2;
            if (target == nums[mid]) {
                return mid;
            }

            // to verify if *mid* is the "inflection point".
            if (mid + 1 < n && nums[mid + 1] <= nums[mid]) {
                // if target is to the left of the inflection point, perform binary search on this sorted section
                if (target <= nums[n - 1]) {
                    return binarySearch(nums, mid + 1, n - 1,target);    
                } else { // if target is to the right of the inflection point, perform binary search on this sorted section
                    return binarySearch(nums, 0, mid - 1, target);
                }
            } else if (nums[mid] > nums[n - 1]) { // update the indices to find the inflection point
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;  
    }


    // linear time O(n)
    public int search(int[] A, int target) {
        for (int i = 0; i < A.length; i++) {            
            if (A[i] == target) {
                return i;
            }
        }

        return -1;
    }
    // Sun Jul 14 02:04:07 2019
    public int search(int[] A, int target) {
        int start = 0;
        int end = A.length - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (A[mid] == target) {
                return mid;
            }

            // right part is sorted
            if (A[start] > A[mid]) {
                // 关键是update start or end with mid
                // find where the target is
                if (A[mid + 1] <= target && target <= A[end]) { // in sorted right part
                    start = mid + 1;
                } else { // in unsorted left part
                    end = mid - 1;
                }
            } else { // left part is sorted
                if (A[start] <= target && target <= A[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        
        return -1;
    }
    // also work
    public int search(int[] A, int target) {
        int left = 0;
        int right = A.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (A[mid] == target) {
                return mid;
            }
            
            if (A[mid] > A[left]) { // left sorted
                if (A[left] <= target && target <= A[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // right sorted
                if (A[mid] <= target && target <= A[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    // 注意区别 也是对的
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            // happen to find target, return early
            if (nums[mid] == target) {
                return mid;
            }
            // left part is sorted
            if (nums[mid] > nums[end]) {
                if (nums[start] <= target && target <= nums[mid]) { // target in the sorted left part
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else { // right part is sorted
                if (nums[mid] <= target && target <= nums[end]) { // target in the sorted right part
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        
        return -1;
    }

    // search in a rotated once array, recursion
    public int search(int[] nums, int target) {
        int n = nums.length;
        int pivot = findPivot(nums, 0, n - 1);
        if (pivot == -1) { // sorted array, direct binary search
            return binarySearch(nums, target, 0, n - 1);
        }
        if (nums[pivot] == target) {
            return pivot;
        }

        if (nums[0] > target) {
            return binarySearch(nums, target, pivot + 1, n - 1);
        } else {
            return binarySearch(nums, target, 0, pivot - 1);
        }
    }
    // the standard binary search, recursive
    public int binarySearch(int[] a, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (a[mid] == target) {
            return mid;
        }
        if (a[mid] > target) {
            return binarySearch(a, target, start, mid - 1);
        } else {
            return binarySearch(a, target, mid + 1, end);
        }
    }

    public int findPivot(int[] a, int start, int end) {
        if (start > end) {
            return -1;
        }
        if (start == end) {
            return start; 
        }

        int mid = start + (end - start) / 2;
        if (mid < end && a[mid] > a[mid + 1]) {
            return mid;
        }
        if (mid > start && a[mid-1] > a[mid]) {
            return mid - 1;
        }
        if (a[start] >= a[mid]) {
            return findPivot(a, start, mid - 1);
        } else {
            return findPivot(a, mid + 1, end);
        }
    }

    // the standard binary search, iterative
    public int bs(int[] a, int x, int start, int end) {
        if (a.length == 0) {
            return -1;
        }
 
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (a[mid] == x) {
                return mid;
            } else if (a[mid] > x) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
 
        return -1;
    }

    // watermark's solution, get confused
    public int search(int[] A, int target) {
        assert(A != null);
        int start = 0;
        int end = A.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (A[mid] == target) return mid;
            if (A[mid] > target && (A[end] > A[mid] || A[end] < target) || A[mid] < target && A[end] > A[mid] && A[end] < target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }
}