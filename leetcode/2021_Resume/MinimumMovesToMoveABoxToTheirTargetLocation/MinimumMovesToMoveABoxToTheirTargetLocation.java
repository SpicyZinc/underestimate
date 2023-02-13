/*
A storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.
The game is represented by an m x n grid of characters grid where each element is a wall, floor, or box.
Your task is to move the box 'B' to the target position 'T' under the following rules:

The character 'S' represents the player. The player can move up, down, left, right in grid if it is a floor (empty cell).
The character '.' represents the floor which means a free cell to walk.
The character '#' represents the wall which means an obstacle (impossible to walk there).
There is only one box 'B' and one target cell 'T' in the grid.
The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
The player cannot walk through the box.

Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.

Example 1:
Input: grid = [["#","#","#","#","#","#"],
               ["#","T","#","#","#","#"],
               ["#",".",".","B",".","#"],
               ["#",".","#","#",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: 3
Explanation: We return only the number of times the box is pushed.

Example 2:
Input: grid = [["#","#","#","#","#","#"],
               ["#","T","#","#","#","#"],
               ["#",".",".","B",".","#"],
               ["#","#","#","#",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: -1

Example 3:
Input: grid = [["#","#","#","#","#","#"],
               ["#","T",".",".","#","#"],
               ["#",".","#","B",".","#"],
               ["#",".",".",".",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: 5
Explanation:  push the box down, left, left, up and up.

Example 4:
Input: grid = [["#","#","#","#","#","#","#"],
               ["#","S","#",".","B","T","#"],
               ["#","#","#","#","#","#","#"]]
Output: -1
 

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 20
grid contains only characters '.', '#', 'S', 'T', or 'B'.
There is only one character 'S', 'B', and 'T' in the grid.

idea:
https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/discuss/709355/Java-use-2-level-BFS-beat-99
*/

class MinimumMovesToMoveABoxToTheirTargetLocation {
    char[][] grid;
    int m, n;
    int[][] dir = new int[][] {
        {0, 1},
        {1, 0},
        {0, -1},
        {-1, 0}
    };

    public int minPushBox(char[][] grid) {
        this.grid = grid;
        m = grid.length; 
        n = grid[0].length;
        int step = 0;
        boolean[][][] visited = new boolean[m][n][4]; // considering 4 directions
        
        Queue<int[]> boxQueue = new LinkedList<>();
        Queue<int[]> playerQueue = new LinkedList<>();

        int[] boxLoc = new int[2];
        int[] targetLoc = new int[2];
        int[] playerLoc = new int[2];

        // Get box, target, player location
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'B') boxLoc = new int[] {i, j};
                if (grid[i][j] == 'T') targetLoc = new int[] {i, j};
                if (grid[i][j] == 'S') playerLoc = new int[] {i, j};
            }
        }

        boxQueue.offer(new int[] {boxLoc[0], boxLoc[1]});
        playerQueue.offer(new int[] {playerLoc[0], playerLoc[1]});
        
        while (!boxQueue.isEmpty()) { 
            int size = boxQueue.size();
            for (int i = 0; i < size; i++) { //as we care about all directions, it should be like this.--> it's related to calculating 'step'
                int[] currentBoxLocation = boxQueue.poll();
                int[] currPlayerLoc = playerQueue.poll();

                // If box arrives at the target, it returns 'step'
                if (currentBoxLocation[0] == targetLoc[0] && currentBoxLocation[1] == targetLoc[1]) {
                    return step;
                }
                // Checking all directions
                for (int j = 0; j < dir.length; j++) {
                    int[] d = dir[j];

                    if (visited[currentBoxLocation[0]][currentBoxLocation[1]][j]) continue;

                    int possiblePlayerPosX = currentBoxLocation[0] + d[0];
                    int possiblePlayerPosY = currentBoxLocation[1] + d[1];  // where player stands, need a space to push
                    if (possiblePlayerPosX < 0 || possiblePlayerPosX >= m || possiblePlayerPosY < 0 || possiblePlayerPosY >= n || grid[possiblePlayerPosX][possiblePlayerPosY] == '#') continue; // if no space, continue

                    // The box location after pushed
                    int nextBoxLocationX = currentBoxLocation[0] - d[0];
                    int nextBoxLocationY = currentBoxLocation[1] - d[1];
                    if (nextBoxLocationX < 0 || nextBoxLocationX >= m || nextBoxLocationY < 0 || nextBoxLocationY >= n || grid[nextBoxLocationX][nextBoxLocationY] == '#') continue; // if no space for box, continue

                    // Check if the player can reach (possiblePlayerPosX, possiblePlayerPosY). If not, continue
                    if (!reachable(possiblePlayerPosX, possiblePlayerPosY, currentBoxLocation, currPlayerLoc)) continue;
                    // After pushed, the player is at 'currentBoxLocation'
                    visited[currentBoxLocation[0]][currentBoxLocation[1]][j] = true;
                    boxQueue.offer(new int[] {nextBoxLocationX, nextBoxLocationY}); // update queues accordingly.
                    playerQueue.offer(new int[] {currentBoxLocation[0], currentBoxLocation[1]}); 
                }
            }

            step++;
        }

        return -1;
    }
    
    // From playerLoc, it can reach the location (i, j) to push the box boxLoc
    private boolean reachable(int i, int j, int[] boxLoc, int[] playerLoc) {
        boolean[][] visited = new boolean[m][n];
        // Player cannot go through the spot where the box is located at.
        visited[boxLoc[0]][boxLoc[1]] = true;

        Queue<int[]> q = new LinkedList<>();
        q.offer(playerLoc);

        while (!q.isEmpty()) {
            int[] currentPlayerLocation = q.poll();
            if (currentPlayerLocation[0] == i && currentPlayerLocation[1] == j) {
                return true;
            }

            for (int[] d : dir) {
                // player's new location after moving
                int r = currentPlayerLocation[0] + d[0];
                int c = currentPlayerLocation[1] + d[1];
                // check if player can move to (r, c)
                if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c] || grid[r][c] == '#') {
                    continue;
                }
                visited[r][c] = true; // if possible, check it visited.
                q.offer(new int[] {r, c});
            }
        }

        return false;
    }
}