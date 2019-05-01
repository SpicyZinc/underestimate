/*
In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.
These characters divide the square into contiguous regions.
(Note that backslash characters are escaped, so a \ is represented as "\\".)

Return the number of regions.

Example 1:
Input:
[
  " /",
  "/ "
]
Output: 2
Explanation: The 2x2 grid is as follows:
https://assets.leetcode.com/uploads/2018/12/15/1.png

Example 2:
Input:
[
  " /",
  "  "
]
Output: 1
Explanation: The 2x2 grid is as follows:
https://assets.leetcode.com/uploads/2018/12/15/2.png

Example 3:
Input:
[
  "\\/",
  "/\\"
]
Output: 4
Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
The 2x2 grid is as follows:
https://assets.leetcode.com/uploads/2018/12/15/3.png

Example 4:
Input:
[
  "/\\",
  "\\/"
]
Output: 5
Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)
The 2x2 grid is as follows:
https://assets.leetcode.com/uploads/2018/12/15/4.png

Example 5:
Input:
[
  "//",
  "/ "
]
Output: 3
Explanation: The 2x2 grid is as follows:
https://assets.leetcode.com/uploads/2018/12/15/5.png

Note:
1 <= grid.length == grid[0].length <= 30
grid[i][j] is either '/', '\', or ' '.

idea:
https://assets.leetcode.com/users/votrubac/image_1544935075.png

First mark the boundary by painting [n * 3][m * 3] grid,
then use the algorithm count number of island (leetcode200) using either bfs or dfs
Using a 3X3 array to represent '/' or '\'

Then it turns into count of islands problem
*/

class RegionsCutBySlashes {
	public int regionsBySlashes(String[] grid) {
		int m = grid.length;
		int n = grid[0].length();

		int cnt = 0;
		int[][] g = new int[m * 3][n * 3];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i].charAt(j) == '/') {
					int ii = i * 3;
					int jj = j * 3;
					for (int k = 0; k < 3; k++) {
						g[ii + k][jj + 3 - 1 - k] = 1;
					}
				} else if (grid[i].charAt(j) == '\\') {
					int ii = i * 3;
					int jj = j * 3;
					for (int k = 0; k < 3; k++) {
						g[ii + k][jj + k] = 1;
					}
				}
			}
		}

		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[i].length; j++) {
				if (g[i][j] == 0) {
					dfs(g, i, j);
					cnt++;
				}
			}
		}

		return cnt;
	}
	// dfs
	public void dfs(int[][] g, int i, int j) {
		int[][] directions = {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0}
		};
		int m = g.length;
		int n = g[0].length;

		if (i < 0 || i >= m || j < 0 || j >= n || g[i][j] == 1) {
			return;
		}

		g[i][j] = 1;
		for (int[] dir : directions) {
			dfs(g, i + dir[0], j + dir[1]);
		}
	}
}