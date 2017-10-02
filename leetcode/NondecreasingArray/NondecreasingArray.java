/*
Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.

Note: The n belongs to [1, 10,000].

idea:
not much, a so weird problem

modification to record how many modification times
increase modification condition is a[i-1] > a[i]
meanwhile, really modify the array with two cases, raise or lower
*/
class NondecreasingArray {
    public boolean checkPossibility(int[] nums) {
        int modification = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                if (modification++ >= 1) {
                    return false;
                }
                // two modification cases:
                // 1. lower a[i - 1]
                // 2. raise a[i]
                if (i < 2 || nums[i - 2] < nums[i]) {
                    nums[i - 1] = nums[i]; // decrease
                } else {
                    nums[i] = nums[i - 1]; // increase
                }
            }
        }
        return true;   
    }
}