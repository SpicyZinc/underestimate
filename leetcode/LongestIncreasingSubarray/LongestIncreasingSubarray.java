/*
Given an array containing n numbers.
The problem is to find the length of the longest contiguous subarray
such that every element in the subarray is strictly greater than its previous element in the same subarray.

Time Complexity should be O(n).

idea:

*/

class LongestIncreasingSubarray {
	public static void main(String[] args) {
		LongestIncreasingSubarray eg = new LongestIncreasingSubarray();
		int[] nums = {5, 6, 3, 5, 7, 8, 9, 1, 2};
		// int[] nums = {12, 13, 1, 5, 4, 7, 8, 10, 10, 11};
		int max = eg.getLIS(nums);

		System.out.println(max);
	}
	// Sun Jun 23 16:57:41 2019
	public int getLIS(int[] nums) {
		int n = nums.length;
		int maxLen = 1;
		int left = 0;

		for (int i = 1; i < n; i++) {
			if (nums[i - 1] >= nums[i]) {
				maxLen = Math.max(maxLen, i - left);
				left = i;
			}
		}

		return maxLen;
	}

	// O(n^2) not necessary
	public int getLIS(int[] nums) {
		int n = nums.length;
		int maxLen = 1;

		for (int left = 0; left < n; left++) {
			int right = left + 1;
			while (right < n && nums[right - 1] < nums[right]) {
				maxLen = Math.max(maxLen, right - left + 1);
				right++;
			}
		}

		return maxLen;
	}
}