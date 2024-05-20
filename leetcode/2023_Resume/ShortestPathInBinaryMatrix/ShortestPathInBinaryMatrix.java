/*
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

Example 1:
https://assets.leetcode.com/uploads/2021/02/18/example1_1.png
Input: grid = [[0,1],[1,0]]
Output: 2

Example 2:
https://assets.leetcode.com/uploads/2021/02/18/example2_1.png
Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Example 3:
Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1

Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1

idea:
BFS to find shortest from all possible paths
*/

class ShortestPathInBinaryMatrix {    
    int[][] directions = {
        {1, -1},
        {1, 0},
        {1, 1},
        {-1, -1},
        {-1, 0},
        {-1, 1},
        {0, -1},
        {0, 0},
        {0, 1}
    };
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][0] == 1) return -1;
        if (grid[m - 1][n - 1] == 1) return -1;
        if (m == 1 && n == 1) {
            return 1;
        }

        int shortestPath = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                if (current[0] == m - 1 && current[1] == n - 1) {
                    return shortestPath;
                }         
                for (int[] dir : directions) {
                    int nextX = current[0] + dir[0];
                    int nextY = current[1] + dir[1];
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                        queue.offer(new int[] {nextX, nextY});
                        visited[nextX][nextY] = true;
                    }
                }
            }
            // 这才走完一步
            shortestPath++;
        }

        return -1;
    }
}
