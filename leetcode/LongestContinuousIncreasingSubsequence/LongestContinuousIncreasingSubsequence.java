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
I think it is longest continuous increasing substring
take each element as start of substring, this substring could be the longest, so update the maxLen

*/

class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
    	if (nums.length == 0 || nums == null) {
    		return 0;
    	}
		int maxLen = 1;
		for (int i = 0; i < nums.length; i++) {
			int right = i;
			while (right < nums.length - 1) {
				if (nums[right] < nums[right + 1]) {
					right++;
				} else {
					break;
				}
			}
			maxLen = Math.max(maxLen, right - i + 1);
		}
		return maxLen;      
    }
    // I want to do this way, but did not implement it
    public int findLengthOfLCIS(int[] nums) {
		int maxLen = 0;
		int cnt = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i == 0 || nums[i - 1] < nums[i]) {
				maxLen = Math.max(maxLen, ++cnt);
			} else {
				cnt = 1;
			}
		}
		return maxLen;
	}
}