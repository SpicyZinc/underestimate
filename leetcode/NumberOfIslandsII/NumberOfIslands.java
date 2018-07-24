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
		List<Integer> result = eg.numIslands2(m,n,positions);
		System.out.println(result);
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

	// 07/24/2018
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        
        int[][] directions = new int[][] {
            {0, 1},
            {0, -1},
            {-1, 0},
            {1, 0}
        };
        
        int[] roots = new int[m * n];
        Arrays.fill(roots, -1);
        int islandCnt = 0;
        
        
        for (int[] position : positions) {
            int x = position[0];
            int y = position[1];
            
            int index = x * n + y;
            roots[index] = index;
            
            islandCnt++;
            for (int[] dir : directions) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];
                
                int nextIndex = nextX * n + nextY;
                
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && roots[nextIndex] != -1) {
                    int rootIdx = getRoot(roots, nextIndex);
                    if (index != rootIdx) {
                        // note, roots[index], not roots[nextIndex]
                        roots[index] = rootIdx;
                        index = rootIdx;

                        islandCnt--;
                    }
                }
            }
            result.add(islandCnt);
        }
        
        return result;
    }
    
    private int getRoot(int[] roots, int i) {
        while (roots[i] != i) {
            i = roots[i];
        }
        
        return i;
    }
}
