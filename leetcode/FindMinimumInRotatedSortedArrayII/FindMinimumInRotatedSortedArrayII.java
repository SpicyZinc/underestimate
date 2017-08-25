/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?
Would this affect the run-time complexity? How and why?
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.
The array may contain duplicates.

idea:
1. {2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 1, 2} and {2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2}
worse case 时间复杂度升到O(n)

2. recursion:
https://segmentfault.com/a/1190000003488815
*/

public class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            if (nums[l] < nums[r]) {
                return nums[l];
            }
            int m = l + (r - l) / 2;
            if (nums[l] > nums[m]) {
                r = m;
            } 
            else if (nums[l] < nums[m]) {
                l = m + 1;
            } 
            else {
                if (nums[l] == nums[r]) {
                    l++;
                    r--;
                } 
                else {
                    // not very understand
                    l = m + 1;
                }
            }
        }

        return nums[l];
    }
    // recursion
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }
    public int findMin(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int right = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        int mid = start + (end - start) / 2;
        if (nums[mid] >= nums[end]) {
            right = findMin(nums, mid + 1, end);
        }
        if (nums[mid] <= nums[end]) {
            left = findMin(nums, start, mid); 
        }
        return Math.min(left, right);
    }
}