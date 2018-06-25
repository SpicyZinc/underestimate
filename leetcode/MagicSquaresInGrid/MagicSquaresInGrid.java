/*
A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column,
and both diagonals all have the same sum.
Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).

Example 1:
Input: [[4,3,8,4],
        [9,5,1,9],
        [2,7,6,2]]
Output: 1
Explanation: 
The following subgrid is a 3 x 3 magic square:
438
951
276
while this one is not:
384
519
762

In total, there is only one magic square inside the given grid.
Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
0 <= grid[i][j] <= 15

idea:
brute force, no explanation

because 0 <= grid[i][j] <= 15,
*/

class MagicSquaresInGrid {
	public static void main(String[] args) {
		int[][] grid = {
			{4,3,8},
			{9,5,1},
			{2,7,6}
		};
		MagicSquaresInGrid eg = new MagicSquaresInGrid();
		int cnt = eg.numMagicSquaresInside(grid);

		System.out.println(cnt);
	}

	public int numMagicSquaresInside(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		int cnt = 0;
		for (int i = 0; i < m - 2; i++) {
			for (int j = 0; j < n - 2; j++) {
				if (isMagicSquare(grid, i, j)) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	public boolean isMagicSquare(int[][] grid, int x, int y) {
		int m = grid.length;
		int n = grid[0].length;

		// check if all cell are unique
		int[] count = new int[16];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int newX = x + i;
				int newY = y + j;
				int val = grid[newX][newY];
				count[val]++;
			}
		}
		for (int i = 1; i <= 9; i++) {
			if (count[i] != 1) {
				return false;
			}
		}

		// check each row, column, and diagonal
		return 
			grid[x][y] + grid[x][y + 1] + grid[x][y + 2] == 15 &&
			grid[x + 1][y] + grid[x + 1][y + 1] + grid[x + 1][y + 2] == 15 &&
			grid[x + 2][y] + grid[x + 2][y + 1] + grid[x + 2][y + 2] == 15 &&
			grid[x][y] + grid[x + 1][y] + grid[x + 2][y] == 15 &&
			grid[x][y + 1] + grid[x + 1][y + 1] + grid[x + 2][y + 1] == 15 &&
			grid[x][y + 2] + grid[x + 1][y + 2] + grid[x + 2][y + 2] == 15 &&
			grid[x][y] + grid[x + 1][y + 1] + grid[x + 2][y + 2] == 15 &&
			grid[x][y + 2] + grid[x + 1][y + 1] + grid[x + 2][y] == 15;
	}
}