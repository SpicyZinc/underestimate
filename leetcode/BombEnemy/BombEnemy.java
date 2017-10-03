/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)

idea:
the most direct thought
*/
class BombEnemy {
	public static void main(String[] args) {
		BombEnemy eg = new BombEnemy();
		char[][] grid = {
			{'0', 'E', '0', '0'},
			{'E', '0', 'W', 'E'},
			{'0', 'E', '0', '0'},
		};
		int kills = eg.maxKilledEnemies(grid);
		System.out.println(kills);
	}

	public int maxKilledEnemies(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int m = grid.length;
		int n = grid[0].length;
		int[][] max = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '0') {
					int kills = 0;

					// i not change, j - 1 and j + 1
					for (int k = j + 1; k < n; k++) {
						if (grid[i][k] == 'E') {
							kills++;
						} else if (grid[i][k] == 'W') {
							break;
						}
					}
					for (int k = j - 1; k >= 0; k--) {
						if (grid[i][k] == 'E') {
							kills++;
						} else if (grid[i][k] == 'W') {
							break;
						}
					}

					// j not change, i - 1 and i + 1
					for (int k = i + 1; k < m; k++) {
						if (grid[k][j] == 'E') {
							kills++;
						} else if (grid[k][j] == 'W') {
							break;
						}
					}
					for (int k = i - 1; k >= 0; k--) {
						if (grid[k][j] == 'E') {
							kills++;
						} else if (grid[k][j] == 'W') {
							break;
						}
					}

					max[i][j] = kills;
				}
			}
		}

		int maxKill = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				maxKill = Math.max(maxKill, max[i][j]);
			}
		}

		return maxKill;
	}
}