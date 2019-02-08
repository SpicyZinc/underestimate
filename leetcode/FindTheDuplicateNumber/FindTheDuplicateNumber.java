/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
prove that at least one duplicate number must exist.
Assume that there is only one duplicate number, find the duplicate one.

Example 1:
Input: [1,3,4,2,2]
Output: 2

Example 2:
Input: [3,1,3,4,2]
Output: 3

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n^2).
There is only one duplicate number in the array, but it could be repeated more than once.

idea:
1. sort
2. slow and fast pointer
*/

public class FindTheDuplicateNumber {
	public int findDuplicate(int[] nums) {
		Arrays.sort(nums);
		for (int i = 1; i <= nums.length; i++) {
			if (nums[i - 1] == nums[i]) {
				return nums[i];
			}
		}

		return -1;
	}

    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
            	break;
            }
        }

        int duplicate = 0;
        while (duplicate != slow) {
            duplicate = nums[duplicate];
            slow = nums[slow];
        }

        return duplicate;
    }
}