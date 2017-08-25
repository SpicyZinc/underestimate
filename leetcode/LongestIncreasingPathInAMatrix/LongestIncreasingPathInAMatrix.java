/*
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down.
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:
nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:
nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

idea:
recursion on every point in the matrix, then DP to memorization
dp[i][j] staring at (i,j) == the length of the longest increasing path

recursion return as long as dp[x][y] != 0
*/

public class LongestIncreasingPathInAMatrix {
	int[][] directions = new int[][] {
		{0, 1},
		{1, 0},
		{0, -1},
		{-1, 0}
	};

    public int longestIncreasingPath(int[][] matrix) {
		if (matrix.length == 0 || matrix == null) {
			return 0;
		}

		int m = matrix.length;
		int n = matrix[0].length;
		int[][] dp = new int[m][n];
		int max = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				max = Math.max(max, dfs(matrix, i, j, m, n, dp));
			}
		}

		return max;
    }

    private int dfs(int[][] matrix, int x, int y, int m, int n, int[][] dp) {
    	if (dp[x][y] != 0) {
    		return dp[x][y];
    	}

    	for (int[] direction : directions) {
    		int nextX = x + direction[0];
    		int nextY = y + direction[1];
    		if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && matrix[x][y] < matrix[nextX][nextY]) {
    			dp[x][y] = Math.max(dp[x][y], dfs(matrix, nextX, nextY, m, n, dp));
    		}
    	}

    	return ++dp[x][y];
    }
}
