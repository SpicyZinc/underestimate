/*
Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles.
If it is not possible to find such walk return -1.


Example 1:
Input: 
grid = 
[[0,0,0],
 [1,1,0],
 [0,0,0],
 [0,1,1],
 [0,0,0]], 
k = 1
Output: 6
Explanation: 
The shortest path without eliminating any obstacle is 10. 
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).

Example 2:
Input: 
grid = 
[[0,1,1],
 [1,1,1],
 [1,0,0]], 
k = 1
Output: -1
Explanation: 
We need to eliminate at least two obstacles to find such a walk.
 

Constraints:
grid.length == m
grid[0].length == n
1 <= m, n <= 40
1 <= k <= m*n
grid[i][j] == 0 or 1
grid[0][0] == grid[m-1][n-1] == 0


idea:
BFS
visited[x][y][k + 1]
*/

class ShortestPathInAGridWithObstaclesElimination {
    int[][] directions = new int[][] {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0, 0});

        boolean[][][] visited = new boolean[m][n][k + 1];
        visited[0][0][0] = true;

        int shortestPath = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] info = queue.poll();
                int x = info[0];
                int y = info[1];
                int currK = info[2];

                if (x == m - 1 && y == n - 1) {
                    return shortestPath;
                }

                for (int[] dir : directions) {
                    int nextX =  x + dir[0];
                    int nextY =  y + dir[1];
                    int nextK = currK;

                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
                        if (grid[nextX][nextY] == 1) {
                            nextK++;
                        }
                        if (nextK <= k && !visited[nextX][nextY][nextK]) {
                            visited[nextX][nextY][nextK] = true;
                            queue.offer(new int[] {nextX, nextY, nextK});
                        }
                    }
                }
            }
            shortestPath++;
        }

        return -1;
    }
}