/*
Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map,
compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:
Given the following 3x6 height map:
[
	[1,4,3,1,3,2],
	[3,2,1,3,2,4],
	[2,3,3,2,3,1]
]
Return 4.
https://leetcode.com/static/images/problemset/rainwater_fill.png
The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.
After the rain, water are trapped between the blocks. The total volume of water trapped is 4.

idea:
note: bfs
https://www.cnblogs.com/grandyang/p/5928987.html

maintain a priorityQueue, first put all four sides elements into it.
while this queue is not empty, pick the shortest height h, compare it with 4 elements around it and not visited
if h > any 1 of 4, put water in, which means add this amount of water to final result, put it into queue, mark h visited
if h < any 1 of 4, put it into queue, mark h visited

create a cell class to wrap x, y and height, and declare comparator
of course, directions = {{1,0}, {0,1}, {-1,0}, {0,-1}}
*/

class Solution {

    class Cell {
        int x;
        int y;
        int h;
        public Cell(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    public int trapRainWater(int[][] heightMap) {
		if (heightMap.length == 0 || heightMap[0].length == 0 || heightMap == null) {
			return 0;
		}

		PriorityQueue<Cell> queue = new PriorityQueue<Cell>(1, new Comparator<Cell>() {
			@Override
			public int compare(Cell a, Cell b) {
				return a.h - b.h;
			}
		});
		int m = heightMap.length;
		int n = heightMap[0].length;
		boolean[][] visited = new boolean[m][n];

		// first put four sides of the matrix into queue
		for (int i = 0; i < m; i++) {
			visited[i][0] = true;
			visited[i][n - 1] = true;
			queue.offer(new Cell(i, 0, heightMap[i][0]));
			queue.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
		}
		for (int j = 0; j < n; j++) {
			visited[0][j] = true;
			visited[m - 1][j] = true;
			queue.offer(new Cell(0, j, heightMap[0][j]));
			queue.offer(new Cell(m - 1, j, heightMap[m - 1][j]));
		}

		// start bfs
		int result = 0;
		int[][] directions = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};
		while (!queue.isEmpty()) {
			Cell cell = queue.poll();
			for (int[] direction : directions) {
				int nextX = cell.x + direction[0];
				int nextY = cell.y + direction[1];
				if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY]) {
					if (cell.h > heightMap[nextX][nextY]) {
						result += (cell.h - heightMap[nextX][nextY]);
					}
					visited[nextX][nextY] = true;
					queue.offer(new Cell(nextX, nextY, Math.max(heightMap[nextX][nextY], cell.h)));
				}
			}
		}

		return result;
    }
}
