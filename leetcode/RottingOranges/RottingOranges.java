/*
In a given grid, each cell can have one of three values:
the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

Example 1:
https://assets.leetcode.com/uploads/2019/02/16/oranges.png
Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:
Input: [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 
Note:
1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.

idea:
quintessential BFS
首先 用 rotten positions to populate queue
记住 size 内for loop 可以有效地 清空 当前的 rotten
不与将要感染的 oranges 混合
while() 一次 增加一天
*/

class RottingOranges {
	public int orangesRotting(int[][] grid) {
		if (grid.length == 0 || grid[0].length == 0) {
			return 0;
		}

		int[][] directions = new int[][] {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0},
		};

		int m = grid.length;
		int n = grid[0].length;
		// queue to keep some day rotten oranges
		Queue<int[]> queue = new LinkedList<>();
		int freshCnt = 0;

		// populate 1st day rotten oranges
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 2) {
					queue.offer(new int[] {i, j});
				} else if (grid[i][j] == 1) {
					freshCnt++;
				}
			}
		}
		// freshCnt = 0, no need to spread the rotten disease, just return 
		if (freshCnt == 0) {
			return 0;
		}

		int days = 0;
		while (!queue.isEmpty()) {
			days++;
			// some day, all rotten oranges positions
			// make sure it is one day
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] rotten = queue.poll();

				for (int[] dir : directions) {
					int nextX = rotten[0] + dir[0];
					int nextY = rotten[1] + dir[1];

					if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == 1) {
						// got contagious to turn into rotten
						grid[nextX][nextY] = 2;
						// add to the rotten queue
						queue.offer(new int[] {nextX, nextY});
						freshCnt--;
					}
				}
			}
		}
		// still have fresh ones, impossible so -1; otherwise return days - 1
		return freshCnt > 0 ? -1 : days - 1;
	}
}
