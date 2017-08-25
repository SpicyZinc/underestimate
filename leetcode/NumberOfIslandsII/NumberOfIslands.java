/*
A 2d grid map of m rows and n columns is initially filled with water.
We may perform an addLand operation which turns the water at position (row, col) into a land.
Given a list of positions to operate, count the number of islands after each addLand operation.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

idea:
I don't know what is union-find, it uses union-find
note: roots[roots[roots[roots[c]]]] ... until roots[c] = c;
best explanation: https://discuss.leetcode.com/topic/29613/easiest-java-solution-with-explanations/2

*/

import java.util.*;

class NumberOfIslands {
	public static void main(String[] args) {
		NumberOfIslands eg = new NumberOfIslands();
		int m = 3;
		int n = 3;
		int[][] positions = {{0,0}, {0,2}, {0,1}, {1,1}};
		List<Integer> result = eg.numIslands2(m,n,positions);
		System.out.println(result);
	}

	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> result = new ArrayList<Integer>();

		int[] roots = new int[m * n];
		Arrays.fill(roots, -1);

		int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
		int cnt = 0;
		for (int[] position : positions) {
			cnt++;

			int x = position[0];
			int y = position[1];
			int insertIndex = x * n + y;
			roots[insertIndex] = insertIndex;

			for (int[] dir : directions) {
				int nextX = x + dir[0];
				int nextY = y + dir[1];
				int neighborIndex = nextX * n + nextY;
				// roots[neighborIndex] != -1 means it is land, visited before, now needs to update
				if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && roots[neighborIndex] != -1) {
					int rootIdx = getRootIdx(roots, neighborIndex);
					// should equal, why because it is current position's 4 neighbors, belonging to the same island, should have the same parent
					// if not, need to update
					// always use the i == roots[i] in roots to update the current insertIndex's parent root
					if (insertIndex != rootIdx) {
						roots[insertIndex] = rootIdx; // insertIndex's parent is rootIdx
						insertIndex = rootIdx;
						cnt--;
					}
				}
			}

			for (int root : roots) {
				System.out.printf("%2s,", root);
			}
			System.out.println();

			result.add(cnt);
		}

		return result;
	}

	// find root index
	// always return i = roots[i] case, the very root
	private int getRootIdx(int[] roots, int i) {
		while (roots[i] != i) {
			i = roots[i];
		}

		return i;
	}
}
