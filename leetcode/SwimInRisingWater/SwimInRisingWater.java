/*
On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
Now rain starts to fall. At time t, the depth of the water everywhere is t.
You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t.
You can swim infinite distance in zero time.
Of course, you must stay within the boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

Example 1:
Input: [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.

Example 2:
Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation:
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

The final route is marked in bold.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

Note:
2 <= N <= 50.
grid[i][j] is a permutation of [0, ..., N*N - 1].

idea: BFS
*/

class SwimInRisingWater {
	class Pair {
		int index;
		int height;

		public Pair(int index, int height) {
			this.index = index;
			this.height = height;
		}
	}

	public int swimInWater(int[][] grid) {
        if (grid.length == 0 || grid == null) {
            return 0;
        }
        
		int result = 0;
		int n = grid.length;
		Set<Integer> visited = new HashSet<Integer>();

		int[][] directions = new int[][] {
			{0, -1}, {-1, 0}, {0, 1}, {1, 0}
		};

		PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>() {
			@Override
			public int compare(Pair a, Pair b) {
				return a.height - b.height;
			}
		});

		pq.add(new Pair(0, grid[0][0]));
        visited.add(0);

		while (!pq.isEmpty()) {
			Pair lowest = pq.poll();
			int i = lowest.index / n;
			int j = lowest.index % n;
			int height = lowest.height;

			result = Math.max(result, height);
			// if reached bottom right, return
			if (i == n - 1 && j == n - 1) {
				return result;
			}

			for (int[] dir : directions) {
				int nextX = i + dir[0];
				int nextY = j + dir[1];
				int idx = nextX * n + nextY;

				if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || visited.contains(idx)) {
					continue;
				}
				pq.add(new Pair(idx, grid[nextX][nextY]));
				// add idx to visited set
				visited.add(idx);
			}
		}

		return result;
	}
}