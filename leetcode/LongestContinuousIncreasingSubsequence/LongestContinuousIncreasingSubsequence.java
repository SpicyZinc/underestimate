/*
Given an unsorted array of integers, find the length of longest continuous increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.

Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1. 

Note: Length of the array will not exceed 10,000.

idea:
发现比前面大的就update maxLen
否则就 reset len = 1
*/

class LongestContinuousIncreasingSubsequence {
	public int findLengthOfLCIS(int[] nums) {
		int n = nums.length;
		int maxLen = 0;
		int len = 0;

		for (int i = 0; i < n; i++) {
			if (i == 0 || nums[i - 1] < nums[i]) {
				len++;
				maxLen = Math.max(maxLen, len);
			} else {
				len = 1;
			}
		}

		return maxLen;
	}
}