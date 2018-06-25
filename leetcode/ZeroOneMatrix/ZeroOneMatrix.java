/*
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1.

Example 1: 
Input:
0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0

Example 2: 
Input:
0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1

Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.

idea:
first initialize those not 0 cell to integer max so meaning it is the maximum distance
BFS with 0 cell, go to 4 directions, like DP, since it is neighbor, so plus one
push those updated 4 directions to queue,
then from there, 其他的cell 要经过它们来到0 cell, 而它们就已经需要某个距离了, so plus 1 again
https://blog.csdn.net/fuxuemingzhu/article/details/70052981

*/
class ZeroOneMatrix {
	public int[][] updateMatrix(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		Queue<int[]> queue = new LinkedList<int[]>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					queue.offer(new int[] {i, j});
				} else {
					// set non-zero cell to be max integer as a mark
					matrix[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		int[][] directions = new int[][] {
			{0, 1}, {0, -1}, {1, 0}, {-1, 0}
		};

		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
			int x = cell[0];
			int y = cell[1];

			for (int[] dir : directions) {
				int newX = x + dir[0];
				int newY = y + dir[1];
				if (newX >= 0 && newX < m && newY >= 0 && newY < n && matrix[x][y] + 1 < matrix[newX][newY]) {
					queue.add(new int[] {newX, newY});
					matrix[newX][newY] = matrix[x][y] + 1;
				}
			}
		}

		return matrix;
	}
}