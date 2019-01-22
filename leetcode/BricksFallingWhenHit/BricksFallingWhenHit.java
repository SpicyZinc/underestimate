/*
We have a grid of 1s and 0s; the 1s in a cell represent bricks.
A brick will not drop if and only if it is directly connected to the top of the grid,
or at least one of its (4-way) adjacent bricks will not drop.

We will do some erasures sequentially.
Each time we want to do the erasure at the location (i, j),
the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.

Return an array representing the number of bricks that will drop after each erasure in sequence.

Example 1:
Input: 
grid = [[1,0,0,0],[1,1,1,0]]
hits = [[1,0]]
Output: [2]
Explanation: 
If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.

Example 2:
Input: 
grid = [[1,0,0,0],[1,1,0,0]]
hits = [[1,1],[1,0]]
Output: [0,0]
Explanation: 
When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move.
So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.
 
Note:
The number of rows and columns in the grid will be in the range [1, 200].
The number of erasures will not exceed the area of the grid.
It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
An erasure may refer to a location with no brick - if it does, no bricks drop.

idea:
https://www.cnblogs.com/grandyang/p/9362777.html
反着想
把要去的砖头都去掉 -1
从边上top row 开始 dfs() four directions and grid[][] == 1的 这些是始终不可能掉的
再把砖头倒着一个个加上 加上后的砖一定得是1 而且它的四周必须是 noDrop里包含的
这样差值就是了 别忘-1 本身去掉的砖头不能算
*/

import java.util.*;

class BricksFallingWhenHit {
	public static int[][] directions = {
		{0, 1},
		{0, -1},
		{1, 0},
		{-1, 0}
	};

	public int[] hitBricks(int[][] grid, int[][] hits) {
		int k = hits.length;
		int[] result = new int[k];

		int m = grid.length;
		int n = grid[0].length;

		// 把要去的砖头都先去掉
		for (int[] hit : hits) {
			int x = hit[0];
			int y = hit[1];

			grid[x][y] -= 1;
		}

		Set<Integer> noDrop = new HashSet<>();
		// top of grid will NOT drop
		// after this, noDrop is populated
		for (int i = 0; i < n; i++) {
			if (grid[0][i] == 1) {
				dfs(grid, 0, i, noDrop);
			}
		}

		for (int i = k - 1; i >= 0; i--) {
			int oldSize = noDrop.size();

			int x = hits[i][0];
			int y = hits[i][1];

			// 因为这个brick又放上了可能它作为bridge 其他的bricks也不掉了 
			// 原来是砖头
			if (++grid[x][y] != 1) {
				continue;
			}
			
			// note, noDrop contains current (x,y) 的四个邻居 or top row
			// then okay to dfs which is to add noDrop set
			if (x - 1 >= 0 && noDrop.contains((x - 1) * n + y) ||
				x + 1 < m && noDrop.contains((x + 1) * n + y) ||
				y - 1 >= 0 && noDrop.contains(x * n + y - 1) ||
				y + 1 < n && noDrop.contains(x * n + y + 1) ||
				x == 0) {

				dfs(grid, x, y, noDrop);

				result[i] = noDrop.size() - oldSize - 1; // 减1的原因是去掉的砖头不算掉落的砖头数中
			}
		}

		return result;
	}
	// 如果我们先把要去掉的所有砖头都先去掉
	// 遍历所有相连的砖头就是最终还剩下的砖头
	public void dfs(int[][] grid, int i, int j, Set<Integer> noDrop) {

		int m = grid.length;
		int n = grid[0].length;

		if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1 || noDrop.contains(i * n + j)) {
			return;
		}

		noDrop.add(i * n + j);

		for (int[] dir : directions) {
			int newX = i + dir[0];
			int newY = j + dir[1];
			dfs(grid, newX, newY, noDrop);
		}
	}
}