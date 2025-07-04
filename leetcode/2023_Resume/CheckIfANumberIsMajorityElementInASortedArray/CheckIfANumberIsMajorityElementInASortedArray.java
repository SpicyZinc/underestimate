/*
Given an integer array nums sorted in non-decreasing order and an integer target, return true if target is a majority element, or false otherwise.
A majority element in an array nums is an element that appears more than nums.length / 2 times in the array.

Example 1:
Input: nums = [2,4,5,5,5,5,5,6,6], target = 5
Output: true
Explanation: The value 5 appears 5 times and the length of the array is 9.
Thus, 5 is a majority element because 5 > 9/2 is true.

Example 2:
Input: nums = [10,100,101,101], target = 101
Output: false
Explanation: The value 101 appears 2 times and the length of the array is 4.
Thus, 101 is not a majority element because 2 > 4/2 is false.


Constraints:
1 <= nums.length <= 1000
1 <= nums[i], target <= 10^9
nums is sorted in non-decreasing order.

idea:
binary search
binary find the 1st occurrence index of the target
*/

class CheckIfANumberIsMajorityElementInASortedArray {
    // binary find the 1st occurrence index of the target
    private int binarySearchFirstIndex(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        
        int low = 0;
        int high = nums.length - 1;
        
        while (low < high) {
            int mid = low + (high - low)/2;
            
            if (nums[mid] >= target)
                high = mid;
            else
                low = mid+1;
        }
        
        return nums[low] == target ? low : -1;
    }
    
    
    public boolean isMajorityElement(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        
        int first = binarySearchFirstIndex(nums, target);
        // Target not in nums
        if (first == -1)
            return false;
        
        int lastPos = first + nums.length / 2;
        
        return lastPos < nums.length && nums[lastPos] == target;
    }
}
