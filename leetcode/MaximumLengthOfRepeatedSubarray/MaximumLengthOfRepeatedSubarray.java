/*
Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].

Note:
1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100

idea:
dp[i][j] represents the length of common subarray (substring) in first i numbers in A and first j numbers in B 
https://www.cnblogs.com/grandyang/p/7801533.html
*/

import java.util.*;

class MaximumLengthOfRepeatedSubarray {
	public static void main(String[] args) {
		MaximumLengthOfRepeatedSubarray eg = new MaximumLengthOfRepeatedSubarray();
		int[] A = {70,39,25,40,7};
		int[] B = {52,20,67,5,31};
		int len = eg.findLength(A, B);
		System.out.println(len);
	}
	// Fri May 10 00:25:18 2019
	public int findLength(int[] A, int[] B) {
		int m = A.length;
		int n = B.length;
		
		int maxLen = 0;
		int[][] dp = new int[m + 1][n + 1];
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
				maxLen = Math.max(maxLen, dp[i][j]);
			}
		}
		
		return maxLen;
	}

	public int findLength(int[] A, int[] B) {
		int m = A.length;
		int n = B.length;
		int[][] dp = new int[m + 1][n + 1];

		int maxLen = 0;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
				maxLen = Math.max(maxLen, dp[i][j]);
			}
		}

		return maxLen;
	}
}