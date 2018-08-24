/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical).
You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands.
An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.

Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.

idea:
take self as anchored
dfs, visited, one shape as one path
note, need to know and remember why (i - r0) * n * 2, 用多余的一倍去中和 minus column
otherwise duplicates sum generated but different location, e.g. as in main method
*/
import java.util.*;

class NumberOfDistinctIslands {
	public static void main(String[] args) {
		NumberOfDistinctIslands eg = new NumberOfDistinctIslands();
		int[][] grid = {
			// {1, 1, 0, 0, 0},
			// {1, 0, 0, 0, 0},
			// {0, 0, 0, 1, 1},
			// {0, 0, 0, 1, 0},

			// {1,1,0,1,1},
			// {1,0,0,0,0},
			// {0,0,0,0,1},
			// {1,1,0,1,1},

			{1,1,1,1},
			{1,0,1,0},
			{0,0,0,0},
			{0,1,1,1},
			{1,1,0,1}

		};

		int cnt = eg.numDistinctIslands(grid);
		System.out.println(cnt);
	}

	public int numDistinctIslands(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        Set<Set<Integer>> shapes = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Set<Integer> shape = new HashSet<Integer>();
                dfs(grid, i, j, i, j, shape, visited);

                if (!shape.isEmpty()) {
                	// if there are duplicates, set shapes will filter off
                    shapes.add(shape);
                }
            }
        }

        return shapes.size();
    }

	public void dfs(int[][] grid, int i, int j, int r0, int c0, Set<Integer> shape, boolean[][] visited) {
		int m = grid.length;
		int n = grid[0].length;

		int[][] directions = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1},
		};

		if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1 && !visited[i][j]) {
			visited[i][j] = true;

			shape.add((i - r0) * n * 2 + (j - c0));

            for (int[] dir : directions) {
            	int newRow = i + dir[0];
            	int newCol = j + dir[1];
            	dfs(grid, newRow, newCol, r0, c0, shape, visited);
            }
        }
    }

    // self better method with encoded string
    public int numDistinctIslands(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		boolean[][] visited = new boolean[m][n];
		Set<Set<String>> shapes = new HashSet<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				Set<String> islandShape = new HashSet<>();
				dfs(grid, i, j, i, j, islandShape, visited);
				if (!islandShape.isEmpty()) {
					shapes.add(islandShape);
				}
			}
		}

        return shapes.size();
    }

	public void dfs(int[][] grid, int i, int j, int r0, int c0, Set<String> islandShape, boolean[][] visited) {
		int m = grid.length;
		int n = grid[0].length;

		int[][] directions = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1},
		};

		if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1 && !visited[i][j]) {
			visited[i][j] = true;

			islandShape.add((i - r0) + "-" + (j - c0));

			for (int[] dir : directions) {
				int newRow = i + dir[0];
				int newCol = j + dir[1];
				dfs(grid, newRow, newCol, r0, c0, islandShape, visited);
			}
        }
    }
}