/*
A 2d grid map of m rows and n columns is initially filled with water.
We may perform an addLand operation which turns the water at position (row, col) into a land.
Given a list of positions to operate, count the number of islands after each addLand operation.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
0 0 0
0 0 0
0 0 0

Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
1 0 0
0 0 0   Number of islands = 1
0 0 0

Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
1 1 0
0 0 0   Number of islands = 1
0 0 0

Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
1 1 0
0 0 1   Number of islands = 2
0 0 0

Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
1 1 0
0 0 1   Number of islands = 3
0 1 0

We return the result as an array: [1, 1, 2, 3]

idea:
good article about union-find
https://blog.csdn.net/dm_vincent/article/details/7655764

note, ids[ids[ids[ids[root]]]] ... till ids[root] = root
底层仍然是[] 但是是 tree 结构
*/

import java.util.*;

class NumberOfIslands {
	public static void main(String[] args) {
		NumberOfIslands eg = new NumberOfIslands();
		int m = 3;
		int n = 3;
		// int[][] positions = {{0,0}, {0,2}, {0,1}, {1,1}};
		int[][] positions = {{0,0}, {0,1}, {1,2}, {2,1}};

		m = 1;
		n = 2;
		positions = new int[][] {{0,1}, {0,0}};

		List<Integer> result = eg.numIslands2(m, n, positions);

		System.out.println(result);
	}
	// Tue May 14 01:34:42 2019
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> result = new ArrayList<>();

		if (positions == null || positions.length == 0 ) {
			return result;
		}

		// initialization
		int[] f = new int[m * n];
		for (int i = 0; i < m * n; i++) {
			f[i] = i;
		}
		
		int[][] island = new int[m][n];
		int islandsCnt = 0;

		int[][] directions = {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0},
		};

		for (int[] position : positions) {
			int x = position[0];
			int y = position[1];
			
			if (island[x][y] != 1) {
				islandsCnt++;
				
				island[x][y] = 1;
				
				int id = x * n + y;
				
				for (int[] dir : directions) {
					int nextX = x + dir[0];
					int nextY = y + dir[1];
					
					if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && island[nextX][nextY] == 1) {
						int nextId = nextX * n + nextY;

						int fId = find(id, f);
						int fNextId = find(nextId, f);
						// id四周的点 have to union()
						if (fId != fNextId) {
							// note, who assign who
							f[fId] = fNextId;
							islandsCnt--;
						}
					}
				}
			}
			
			result.add(islandsCnt);
		}
		
		return result;
	}
	
	public int find(int i, int[] f) {
		int j = i;

		while (j != f[j]) {
			j = f[j];
		}

		while (i != j) {
			int fi = f[i];
			f[i] = j;
			i = fi;
		}
		
		return i;
	}


	// 01/28/2019
	// lintcode version
	public List<Integer> numIslands2(int n, int m, Point[] operators) {
		List<Integer> result = new ArrayList<>();
		if (operators == null || operators.length == 0) {
			return result;
		}
		
		// initialization
		int[] f = new int[n * m];
		for (int i = 0; i < n * m; i++) {
			f[i] = i;
		}

		int[][] island = new int[n][m];
		int islandsCnt = 0;
		
		int[][] directions = {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0},
		};
		
		for (Point operator : operators) {
			int x = operator.x;
			int y = operator.y;
			
			if (island[x][y] != 1) {
				islandsCnt++;
				
				island[x][y] = 1;
				
				int id = x * m + y;
				
				for (int[] dir : directions) {
					int nextX = x + dir[0];
					int nextY = y + dir[1];
					
					if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && island[nextX][nextY] == 1) {
						int nextId = nextX * m + nextY;
						
						int fId = find(id, f);
						int fNextId = find(nextId, f);
						// id四周的点 have to union()
						if (fId != fNextId) {
							f[fId] = fNextId;
							islandsCnt--;
						}
					}
				}
			}
			
			result.add(islandsCnt);
		}
		
		return result;
	}
	
	public int find(int i, int[] f) {
		int j = i;
		while (j != f[j]) {
			j = f[j];
		}
		
		while (i != j) {
			int fi = f[i];
			f[i] = j;
			i = fi;
		}
		
		return i;
	}

	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> result = new ArrayList<Integer>();

		int[] roots = new int[m * n];
		Arrays.fill(roots, -1);

		int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
		int islandCnt = 0;

		for (int[] position : positions) {
			// add one more land, assume it is not connected, one more island
			islandCnt++;

			int x = position[0];
			int y = position[1];
			int index = x * n + y;
			// itself is itself's parent
			roots[index] = index;

			for (int[] dir : directions) {
				int nextX = x + dir[0];
				int nextY = y + dir[1];
				int nextIndex = nextX * n + nextY;
				// roots[nextIndex] != -1 means it is land, visited before, now needs to update
				if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && roots[nextIndex] != -1) {
					int rootIdx = getRoot(roots, nextIndex);
					// should equal, why because it is current position's 4 neighbors,
					// belonging to the same island, should have the same parent
					// if not, need to update
					// always use the i == roots[i] in roots to update the current index's parent root
					if (index != rootIdx) {
						roots[index] = rootIdx; // index's parent is rootIdx
						index = rootIdx;
						islandCnt--;
					}
				}
			}
			result.add(islandCnt);
		}

		return result;
	}

	// find root index
	// always return i = roots[i] case, the very root
	private int getRoot(int[] roots, int i) {
		while (roots[i] != i) {
			i = roots[i];
		}

		return i;
	}
}
