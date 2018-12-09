/*
In a given 2D binary array A, there are two islands.
(An island is a 4-directionally connected group of 1s not connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
Return the smallest number of 0s that must be flipped.
(It is guaranteed that the answer is at least 1.)

Example 1:
Input: [[0,1],[1,0]]
Output: 1

Example 2:
Input: [[0,1,0],[0,0,0],[0,0,1]]
Output: 2

Example 3:
Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1

Note:
1 <= A.length = A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1

idea:
dfs to find an island, visited array used
then dfs to find the another island, smallest number of flip from 0 to 1
*/

class ShortestBridge {
	public int shortestBridge(int[][] A) {
		int m = A.length;
		int n = A[0].length;
		boolean[][] visited = new boolean[m][n];

		int[][] directions =  {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
		};

		Queue<int[]> queue = new LinkedList<>();
		boolean found = false;
		// 1. dfs to find an island, mark it in `visited`
		for (int i = 0; i < m; i++) {
			if (found) {
				break;
			}
			for (int j = 0; j < n; j++) {
				if (A[i][j] == 1) {
					dfs(A, visited, queue, i, j, directions);
					found = true;
					break;
				}
			}
		}
        // 2. bfs to expand this island
        // bfs guarantees fewest flips from 1 to 0
		int step = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
            // go through 4 directions, which walks 1 step
            // 这是最近的走法
			for (int k = 0; k < size; k++) {
				int[] current = queue.poll();
				for (int[] dir : directions) {
					int nextX = current[0] + dir[0];
					int nextY = current[1] + dir[1];
					if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY]) {
						if (A[nextX][nextY] == 1) {
							return step;
						}
						queue.offer(new int[] {nextX, nextY});
						visited[nextX][nextY] = true;
					}
				}
			}
			step++;
		}

		return -1;
	}
	// dfs to find an island, actually mark corresponding (i, j) visited
	private void dfs(
		int[][] A,
		boolean[][] visited,
		Queue<int[]> queue,
		int i,
		int j,
		int[][] directions
	) {
		int m = A.length;
		int n = A[0].length;

		if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j] || A[i][j] == 0) {
			return;
		}

		visited[i][j] = true;
		queue.offer(new int[] {i, j});
		for (int[] dir : directions) {
			int nextX = i + dir[0];
			int nextY = j + dir[1];
			dfs(A, visited, queue, nextX, nextY, directions);
		}
	}
}