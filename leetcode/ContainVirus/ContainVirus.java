/*
A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.

The world is modeled as a 2-D array of cells, where 0 represents uninfected cells, and 1 represents cells contaminated with the virus.
A wall (and only one wall) can be installed between any two 4-directionally adjacent cells, on the shared boundary.

Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall. Resources are limited.
Each day, you can install walls around only one region
-- the affected area (continuous block of infected cells) that threatens the most uninfected cells the following night. There will never be a tie.
Can you save the day? If so, what is the number of walls required? If not, and the world becomes fully infected, return the number of walls used.

Example 1:
Input: grid = 
[[0,1,0,0,0,0,0,1],
 [0,1,0,0,0,0,0,1],
 [0,0,0,0,0,0,0,1],
 [0,0,0,0,0,0,0,0]]
Output: 10
Explanation:
There are 2 contaminated regions.
On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:
[[0,1,0,0,0,0,1,1],
 [0,1,0,0,0,0,1,1],
 [0,0,0,0,0,0,1,1],
 [0,0,0,0,0,0,0,1]]
On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.

Example 2:
Input: grid = 
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output: 4
Explanation: Even though there is only one cell saved, there are 4 walls built.
Notice that walls are only built on the shared boundary of two different cells.

Example 3:
Input: grid = 
[[1,1,1,0,0,0,0,0,0],
 [1,0,1,0,1,1,1,1,1],
 [1,1,1,0,0,0,0,0,0]]
Output: 13
Explanation: The region on the left only builds two new walls.

Note:
The number of rows and columns of grid will each be in the range [1, 50].
Each grid[i][j] will be either 0 or 1.
Throughout the described process, there is always a contiguous viral region that will infect strictly more uncontaminated squares in the next round.

idea:
http://zxi.mytechroad.com/blog/searching/leetcode-749-contain-virus/
https://blog.csdn.net/jackzhang_123/article/details/78899190
*/

class ContainVirus {
	public int containVirus(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int totalWalls = 0;

		// every day
		while (true) {
			boolean[] visited = new boolean[m * n];
			List<Integer> maxVirusBlock = new ArrayList<>();
			List<Set<Integer>> nexts = new ArrayList<>();

			int maxBlockIdx = -1;
			int blockWalls = -1;

			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					int key = i * n + j;

					if (grid[i][j] != 1 || visited[key]) {
						continue;
					}
					// for each virus cell 1, find virus block size by dfs
					// after dfs(), these 3 values get populated
					List<Integer> virusBlock = new ArrayList<Integer>();
					Set<Integer> nextAffectedCells = new HashSet<Integer>();
					int[] walls = {0};

					dfs(grid, i, j, visited, virusBlock, nextAffectedCells, walls);
					if (nextAffectedCells.size() == 0) {
						continue;
					}
					// 刚开始 或者 更新 为最大
					// greedy
					if (nexts.isEmpty() || nextAffectedCells.size() > nexts.get(maxBlockIdx).size()) {
						maxVirusBlock = virusBlock;
						maxBlockIdx = nexts.size();
						blockWalls = walls[0];
					}
					nexts.add(nextAffectedCells);
				}
            }
            
            // no infectable area
            if (nexts.isEmpty()) {
                break;
            }

            totalWalls += blockWalls;

            // loop through all possible affected cells
            for (int i = 0; i < nexts.size(); i++) {
                if (i == maxBlockIdx) {
                    for (int key : maxVirusBlock) {
                        int x = key / n;
                        int y = key % n;
                        // virtually marked as wall built, blocked
                        grid[x][y] = 2;
                    }
                } else {
                    for (int key : nexts.get(i)) {
                        int x = key / n;
                        int y = key % n;
                        // continue spreading virus
                        grid[x][y] = 1;
                    }
                }
            }
        }

		return totalWalls;
	}

	// dfs get some virus area, get potential affected area, and corresponding number of walls needed
	private void dfs(
		int[][] grid,
		int x,
		int y,
		boolean[] visited,
		List<Integer> current,
		Set<Integer> next,
		int[] walls
	) {
		int[][] directions = {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0}
		};

		int m = grid.length;
		int n = grid[0].length;

		if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 2) {
			return;
		}

		int key = x * n + y;

		// need one wall
		// 能传染过病毒来 说明是一个需要堵的 so wall++
		if (grid[x][y] == 0) {
			walls[0]++;
			next.add(key);
			return;
		}

		if (visited[key]) {
			return;
		}

		visited[key] = true;
		current.add(key);
		for (int[] dir : directions) {
			int newX = x + dir[0];
			int newY = y + dir[1];
			dfs(grid, newX, newY, visited, current, next, walls);
		}
	}
}

