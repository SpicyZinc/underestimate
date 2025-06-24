/*
idea:
moves 
一正一反 L R
backtrack 用

first DFS to populate grid
then BFS to find the shortest path

从中间走?
come back
*/

/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * class GridMaster {
 *     boolean canMove(char direction);
 *     void move(char direction);
 *     boolean isTarget();
 * }
 */

class ShortestPathInAHiddenGrid {
    private int length = 1002; // Max bounds

    private char[][] moves = new char[][] {{'R', 'L'}, {'L', 'R'}, {'D', 'U'}, {'U', 'D'}};
    private int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int[][] grid = new int[length][length];
    private boolean[][] visited = new boolean[length][length];

    private int destX = -1;
    private int destY = -1;

    public int findShortestPath(GridMaster master) {
        int sourceX = 500;
        int sourceY = 500;
        Queue<int[]> queue = new LinkedList<>();

        // Build grid, mainly to find destX, destY
        traverse(sourceX, sourceY, visited, master);

        if (destX == -1 && destY == -1) {
            return -1;
        }
        // reset visited map
        visited = new boolean[length][length];
        // 目前走了distance = 0
        queue.offer(new int[] {sourceX, sourceY, 0});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            if (node[0] == destX && node[1] == destY) {
                return node[2];
            }

            for (int i = 0; i < directions.length; i++) {
                int r = node[0] + directions[i][0];
                int c = node[1] + directions[i][1];

                if (r < 0 || c < 0 || r > grid.length - 1 || c > grid[0].length - 1 || visited[r][c] || grid[r][c] == 0) {
                    continue;
                }

                visited[r][c] = true;
                queue.offer(new int[] {r, c, node[2] + 1});
            }

        }

        return -1;
    }

    private void traverse(int r, int c, boolean[][] visited, GridMaster master) {
        if (r < 0 || c < 0 || r > grid.length - 1 || c > grid[0].length - 1 || visited[r][c]) {
            return;
        }

        if (master.isTarget()) {
            destX = r;
            destY = c;
        }

        visited[r][c] = true;
        grid[r][c] = 1;

        for (int i = 0; i < directions.length; i++) {
            int newR = r + directions[i][0];
            int newC = c + directions[i][1];

            if (master.canMove(moves[i][0])) {
                master.move(moves[i][0]);

                traverse(newR, newC, visited, master);

                master.move(moves[i][1]);
            }
        }
    }
}
