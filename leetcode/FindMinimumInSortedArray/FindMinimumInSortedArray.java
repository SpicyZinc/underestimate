/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
You may assume no duplicate exists in the array.

idea:
binary search
note: the minimum element's previous one is bigger than the minimum, which breaks the fact that the array is sorted
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
        // nums already sorted
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
            } else {
                end = mid;
            }
        }
        
        return nums[start];
    }
    // recursion
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }
    
    public int findMin(int[] nums, int start, int end) {
        // two cases:
        // if two pointers are equal, no matter which one is picked
        // if value at start < one at end, the array is sorted, so 1st element is min
        if (start == end || nums[start] < nums[end]) {
            return nums[start];
        }
        // if start + 1 == end, means only two elements
        if (start + 1 == end) {
            return nums[end];
        }
        
        int mid = start + (end - start) / 2;
        return nums[mid] > nums[end] ? findMin(nums, mid, end) : findMin(nums, start, mid);
    }
}