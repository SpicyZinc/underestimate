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
find leftMost
*/

class CheckIfANumberIsMajorityElementInASortedArray {
    public boolean isMajorityElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int n = nums.length;
        int first = binarySearchLeftmost(nums, target);

        // If target is not found or not enough space for majority
        if (first == -1 || first + n / 2 >= n) return false;

        return nums[first + n / 2] == target;
    }

    // binary find the 1st occurrence index of the target
    private int binarySearchLeftmost(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left)/2;
            
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[left] == target ? left : -1;
    }
}
