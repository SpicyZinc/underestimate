/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
You may assume no duplicate exists in the array.

idea:
binary search
note: the minimum element's previous one is bigger than the minimum, which breaks the sorted array fact
*/
public class FindMinimumInSortedArray {
    public int findMin(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int start = 0;
        int end = nums.length - 1;
        if (nums[start] < nums[end]) {
            return nums[start];
        }
        // good example [3, 1, 2]
        while (start < end) {
            if (start + 1 == end) {
                return nums[end];
            }
            int mid = start  + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        return nums[start];
        // also works
        // return Math.min(nums[start], nums[end]);
    }

    // recursion
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }
    public int findMin(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int mid = start + (end - start) / 2;
        if (nums[end] > nums[mid]) {
            return findMin(nums, start, mid);
        }
        else {
            return findMin(nums, mid + 1, end);
        }
    }
    // self recursion same logic as iteration
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }
    public int findMin(int[] nums, int start, int end) {
        if (start == end || nums[start] < nums[end]) {
            return nums[start];
        } 
        if (start + 1 == end) {
            return nums[end];
        }
        int mid = start + (end - start) / 2;
        if (nums[mid] > nums[end]) {
            return findMin(nums, mid, end);
        }
        else {
            return findMin(nums, start, mid);
        }
    }
}