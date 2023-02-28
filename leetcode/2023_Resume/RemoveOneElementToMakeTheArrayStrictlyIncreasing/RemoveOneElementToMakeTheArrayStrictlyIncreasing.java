/*
Given a 0-indexed integer array nums, return true if it can be made strictly increasing after removing exactly one element, or false otherwise. If the array is already strictly increasing, return true.
The array nums is strictly increasing if nums[i - 1] < nums[i] for each index (1 <= i < nums.length).

Example 1:
Input: nums = [1,2,10,5,7]
Output: true
Explanation: By removing 10 at index 2 from nums, it becomes [1,2,5,7].
[1,2,5,7] is strictly increasing, so return true.

Example 2:
Input: nums = [2,3,1,2]
Output: false
Explanation:
[3,1,2] is the result of removing the element at index 0.
[2,1,2] is the result of removing the element at index 1.
[2,3,2] is the result of removing the element at index 2.
[2,3,1] is the result of removing the element at index 3.
No resulting array is strictly increasing, so return false.

Example 3:
Input: nums = [1,1,1]
Output: false
Explanation: The result of removing any element is [1,1].
[1,1] is not strictly increasing, so return false.
 

Constraints:
2 <= nums.length <= 1000
1 <= nums[i] <= 1000

idea:
4 cases
peak index at start or end
peak at middle, two cases
*/
class RemoveOneElementToMakeTheArrayStrictlyIncreasing {
    public boolean canBeIncreasing(int[] nums) {
        int peakIndex = 0;
        int peakCount = 0;
        int size = nums.length;

        for (int i = 0; i < size - 1; i++) {
            // Drop, so peak appears
            if (nums[i] >= nums[i + 1]) {
                peakCount++;
                peakIndex = i;
            }
        }

        if (peakCount > 1) {
            return false;
        } 

        if (peakIndex == 0 || peakIndex == size - 2) {
            return true;
        } else if (nums[peakIndex + 1] > nums[peakIndex - 1] || nums[peakIndex + 2] > nums[peakIndex]) {
            return true;
        } else {
            return false;
        }
    }
}
