/*
In a N x N grid representing a field of cherries, each cell is one of three possible integers.

0 means the cell is empty, so you can pass through;
1 means the cell contains a cherry, that you can pick up and pass through;
-1 means the cell contains a thorn that blocks your way.

Your task is to collect maximum number of cherries possible by following the rules below:
Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.

Example 1:
Input: grid =
[[0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]]
Output: 5
Explanation: 
The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.

Note:
grid is an N by N 2D array, with 1 <= N <= 50.
Each grid[i][j] is an integer in the set {-1, 0, 1}.
It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.

idea:
another one: https://leetcode.com/problems/cherry-pickup/discuss/165218/Java-O(N3)-DP-solution-w-specific-explanation
two persons A and B
simultaneously pick
x1 + y1 = x2 + y2
memo[x1][y1][y2]

*/

class CherryPick {
	public int cherryPickup(int[][] grid) {
		int n = grid.length;
		int[][][] memo = new int[n][n][n];

		for (int[][] layer : memo) {
			for (int[] row : layer) {
				Arrays.fill(row, Integer.MIN_VALUE);
			}
		}

		return Math.max(0, dfs(0, 0, 0, grid, memo));
	}

	public int dfs(int r1, int c1, int c2, int[][] grid, int[][][] memo) {
		int n = grid.length;
		// r1 + c1 = r2 + c2
		int r2 = r1 + c1 - c2;

		if (r1 >= n || r2 >= n || c1 >= n || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1) {
			return Integer.MIN_VALUE;
		} else if (r1 == n - 1 && c1 == n - 1) {
			// note this case
			return grid[r1][c1];
		} else if (memo[r1][c1][c2] != Integer.MIN_VALUE) {
			return memo[r1][c1][c2];
		} else {
			int cnt = grid[r1][c1];
			if (c1 != c2) {
				cnt += grid[r2][c2];
			}

			// 4 cases
			// A down, B down
			// A down, B right
			// A right, B down
			// A right, B right
			// find max among 4 values
			int case1 = dfs(r1 + 1, c1, c2, grid, memo);
			int case2 = dfs(r1 + 1, c1, c2 + 1, grid, memo);
			int case3 = dfs(r1, c1 + 1, c2, grid, memo);
			int case4 = dfs(r1, c1 + 1, c2 + 1, grid, memo);

			cnt += Math.max(Math.max(case1, case2), Math.max(case3, case4));
			memo[r1][c1][c2] = cnt;

			return cnt;
		}
	}
}