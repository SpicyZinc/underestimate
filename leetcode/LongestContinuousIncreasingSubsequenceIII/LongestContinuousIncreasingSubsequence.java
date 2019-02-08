/*
Give you an integer matrix (with row size n, column size m), find the longest increasing continuous subsequence in this matrix.
(The definition of the longest increasing continuous subsequence here can start at any row or column and go up/down/right/left any direction).

Example
Given a matrix:
[
  [1 ,2 ,3 ,4 ,5],
  [16,17,24,23,6],
  [15,18,25,22,7],
  [14,19,20,21,8],
  [13,12,11,10,9]
]
return 25

Challenge
O(nm) time and memory.

idea:
dp[i][j] represents starting from matrix[i][j] longest increasing subsequence
memoization 
*/

public class LongestContinuousIncreasingSubsequence {
	public int longestContinuousIncreasingSubsequence2(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0 || matrix == null) {
			return 0;
		}
		
		int m = matrix.length;
		int n = matrix[0].length;

		int maxLen = 0;
		int[][] dp = new int[m][n];
		// initialization to -1 as flag
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = -1;
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dfs(matrix, i, j, dp);
				maxLen = Math.max(maxLen, dp[i][j]);
			}
		}

		return maxLen;
	}

	public void dfs(int[][] matrix, int x, int y, int[][] dp) {
		if (dp[x][y] != -1) {
			return;
		}

		int[][] directions = {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0},
		};

		int m = matrix.length;
		int n = matrix[0].length;

		dp[x][y] = 1;
		
		for (int[] dir : directions) {
			int nextX = x + dir[0];
			int nextY = y + dir[1];

			if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
				if (matrix[nextX][nextY] > matrix[x][y]) {
					dfs(matrix, nextX, nextY, dp);
					dp[x][y] = Math.max(dp[x][y], dp[nextX][nextY] + 1);
				}
			}
		}
	}
}

