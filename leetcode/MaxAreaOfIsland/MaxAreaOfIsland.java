/*
Given a non-empty 2D array grid of 0's and 1's,
an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical).

You may assume all four edges of the grid are surrounded by water.
Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6.
Note the answer is not 11, because the island must be connected 4-directionally.

Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.

Note: The length of each dimension in the given grid does not exceed 50.

idea:
dfs
https://www.cnblogs.com/grandyang/p/7712724.html
set grid[i][j] = 2 as a mark for being visited
note, when visited or not land, return
grid[i][j] == 2 || grid[i][j] == 0
*/

class MaxAreaOfIsland {
	// Thu May  2 01:33:55 2019
	public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        }
        
        return max;
    }
    
    public int dfs(int[][] grid, int i, int j) {
    	// 这个cell 就是1 island 面积为1
        int area = 1;
        
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] directions = new int[][] {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0}
		};
        // visited before
        // not land
        // return number of islands as 0
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 2 || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 2;
        
        for (int[] dir : directions) {
            int nextX = i + dir[0];
            int nextY = j + dir[1];
            
            area += dfs(grid, nextX, nextY);
        }
        
        return area;
    }

	public int maxAreaOfIsland(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

        int maxArea = 0;
		int[] area = new int[1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					dfs(grid, i, j, area);
                    maxArea = Math.max(maxArea, area[0]);
                    area[0] = 0;
				}
			}
		}

		return maxArea;
	}

	public void dfs(int[][] grid, int i, int j, int[] area) {
		int m = grid.length;
		int n = grid[0].length;
        
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 2 || grid[i][j] == 0) {
            return;
        }

		int[][] directions = new int[][] {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0}
		};

        grid[i][j] = 2;
        area[0]++;

		for (int[] dir : directions) {
			int nextX = i + dir[0];
			int nextY = j + dir[1];

            dfs(grid, nextX, nextY, area);
		}
	}

	// dfs() return value
	public int maxAreaOfIsland(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

        int maxArea = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					int area = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);
				}
			}
		}

		return maxArea;
	}

	public int dfs(int[][] grid, int i, int j) {
        int[][] directions = new int[][] {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0}
		};
        
		int m = grid.length;
		int n = grid[0].length;
        
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 2 || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 2;
        int area = 1;
		for (int[] dir : directions) {
			int nextX = i + dir[0];
			int nextY = j + dir[1];
            area += dfs(grid, nextX, nextY);
		}
        
        return area;
	}
}