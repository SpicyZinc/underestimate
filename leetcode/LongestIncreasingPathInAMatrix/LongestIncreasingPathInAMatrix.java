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
		int[][] memo = new int[m][n];
		int max = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 以每个点作为起点 求最长的 increasing path
				max = Math.max(max, dfs(matrix, i, j, memo));
			}
		}

		return max;
	}

	private int dfs(int[][] matrix, int x, int y, int[][] memo) {
		int m = matrix.length;
		int n = matrix[0].length;

		if (memo[x][y] != 0) {
			return memo[x][y];
		}

		for (int[] direction : directions) {
			int nextX = x + direction[0];
			int nextY = y + direction[1];

			if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && matrix[x][y] < matrix[nextX][nextY]) {
				memo[x][y] = Math.max(memo[x][y], dfs(matrix, nextX, nextY, memo));
			}
		}
		// 增加了1步
		memo[x][y] += 1;
		return memo[x][y];
	}
}
