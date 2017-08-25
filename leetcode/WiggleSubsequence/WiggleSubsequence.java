/*
A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative.
The first difference (if one exists) may be either positive or negative.
A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. 
In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence.
A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

Examples:
Input: [1,7,4,9,2,5]
Output: 6
The entire sequence is a wiggle sequence.

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

Input: [1,2,3,4,5,6,7,8,9]
Output: 2
Follow up:
Can you do it in O(n) time?

idea:
https://www.hrwhisper.me/leetcode-wiggle-subsequence/

greedy algorithm
keep looking for turning point,
once found one, while loop to another turning point
in summary, find the maximum and minimum to not lost the optimal solution

*/
public class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) {
        	return 0;
        }
		int n = nums.length;
		int ans = 1;
		for (int i = 1, j = 0; i < n; j = i, i++) {
			if (nums[j] < nums[i]) {
				ans++;
				while (i + 1 < n && nums[i] <= nums[i + 1]) {
					i++;
				}
			}
			else if (nums[j] > nums[i]) {
				ans++;
				while (i + 1 < n && nums[i] >= nums[i + 1]) {
					i++;
				}
			}
		}
		return ans;
    }

    // another method DP like LIS longest increase subsequence length, not working so far
    public int wiggleMaxLength(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length();
		int[] dp = new int[n];
		int[] sign = new int[n];
		int max = 1;

		for (int i = 1; i < n; i++) {
			dp[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (dp[j] + 1 > dp[i] && (sign[j] > 0 && nums[i] < nums[i] || sign[j] < 0 && nums[i] > nums[j] || sign[j] == 0)) {
					sign[i] = nums[i] > nums[j] ? 1 : -1;
					dp[i] = dp[j] + 1;
				}
				max = Math.max(max, dp[i]);
			}
		}
		return max;
    }
}










