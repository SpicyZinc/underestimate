/*
Given two strings, find the longest common subsequence (LCS).
Your code should return the length of LCS.

Example
For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.
For "ABCD" and "EACB", the LCS is "AC", return 2.

idea:
typical 双序列DP
dp[i][j] first i chars in A and first j chars in B common subsequence's length
*/

public class LongestCommonSubsequence {
	public int longestCommonSubsequence(String A, String B) {
	    int m = A.length();
	    int n = B.length();
	    
	    int[][] dp = new int[m + 1][n + 1];
	    
	    for (int i = 0; i <= m; i++) {
	        dp[i][0] = 0;
	    }
	    
	    for (int j = 0; j <= m; j++) {
	        dp[0][j] = 0;
	    }
	    
	    for (int i = 1; i <= m; i++) {
	        for (int j = 1; j <= n; j++) {
	            char a = A.charAt(i - 1);
	            char b = B.charAt(j - 1);
	            dp[i][j] = a == b ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
	        }
	    }
	    
	    return dp[m][n];
	}    
}
