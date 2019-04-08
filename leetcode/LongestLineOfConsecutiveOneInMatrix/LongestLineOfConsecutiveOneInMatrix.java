/*
Given a 01 matrix M, find the longest line of consecutive one in the matrix.
The line could be horizontal, vertical, diagonal or anti-diagonal.

Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3

Hint: The number of elements in the given matrix will not exceed 10,000.

idea:
1. DFS
2. iteration, i < m + n - 1
diagonal: x = i - j, y = j,
anti-diagonal: x = m - 1 - i + j, y = j
https://www.cnblogs.com/grandyang/p/6900866.html
*/

public class LongestLineOfConsecutiveOneInMatrix {
	public static void main(String[] args) {
		LongestLineOfConsecutiveOneInMatrix eg = new LongestLineOfConsecutiveOneInMatrix();
		int[][] matrix = new int[][] {
			{0,1,1,0},
			{0,1,1,0},
			{0,0,0,1}
		};

		int cnt = eg.longestLine(matrix);
		System.out.println(cnt);
	}

	public int longestLine(int[][] M) {
		if (M == null || M.length == 0 || M[0].length == 0) {
			return 0;
		}

		int m = M.length;
		int n = M[0].length;

		int cnt = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (M[i][j] == 1) {
					cnt = Math.max(cnt, getMax(M, i, j));
				}
			}
		}

		return cnt;
	}

	public int getMax(int[][] M, int x, int y) {
		int maxLen = 1;

		int [][] directions = new int[][] {
			{1,0}, {0,1}, {1,1}, {1,-1}
		};

		int m = M.length;
		int n = M[0].length;

		for (int[] dir : directions) {
			int cnt = 1;

			int newX = x + dir[0];
			int newY = y + dir[1];

			while (newX >= 0 && newX < m && newY >= 0 && newY < n && M[newX][newY] == 1) {
				// different from regular dfs, newX = newX + dir[0], not newX = x + dir[0]
				// also not continue dfs(), it is while
				newX += dir[0];
				newY += dir[1];
				
				cnt++;
			}

			maxLen = Math.max(maxLen, cnt);
		}

		return maxLen;
	}

	// iteration
	public int longestLine(int[][] M) {
		if (M == null || M.length == 0 || M[0].length == 0) {
			return 0;
		}

		int max = 0;
		int m = M.length;
		int n = M[0].length;

		// Check row
		for (int i = 0; i < m; i++) {
			int cnt = 0;
			for (int j = 0; j < n; j++) {
				if (M[i][j] == 1) {
					max = Math.max(max, ++cnt);
				} else {
					cnt = 0;
				}
			}
		}
		// Check column
		for (int j = 0; j < n; j++) {
			int cnt = 0;
			for (int i = 0; i < m; i++) {
				if (M[i][j] == 1) {
					max = Math.max(max, ++cnt);
				}
				else {
					cnt = 0;
				}
			}
		}

		for (int i = 0; i < m + n - 1; i++) {
			int cnt1 = 0;
			int cnt2 = 0;
			for (int j = i; j >= 0; j--) {
				// Check diagonal
				if (i - j < m && j < n) {
					if (M[i - j][j] == 1) {
						max = Math.max(max, ++cnt1);
					} else {
						cnt1 = 0;
					} 
				}
				// Check anti-diagonal
				if (m - 1 - i + j >= 0 && m - 1 - i + j < m && j < n ) {
					if (M[m - 1 - i + j][j] == 1) {
						max = Math.max(max, ++cnt2);
					} else {
						cnt2 = 0;
					} 
				}
			}
		}

		return max;
	}
}