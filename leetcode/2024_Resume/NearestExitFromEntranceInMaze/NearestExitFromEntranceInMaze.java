/*
You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze.
Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.
Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.

Example 1:

Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
Output: 1
Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
Initially, you are at the entrance cell [1,2].
- You can reach [1,0] by moving 2 steps left.
- You can reach [0,2] by moving 1 step up.
It is impossible to reach [2,3] from the entrance.
Thus, the nearest exit is [0,2], which is 1 step away.

Example 2:

Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
Output: 2
Explanation: There is 1 exit in this maze at [1,2].
[1,0] does not count as an exit since it is the entrance cell.
Initially, you are at the entrance cell [1,0].
- You can reach [1,2] by moving 2 steps right.
Thus, the nearest exit is [1,2], which is 2 steps away.

Example 3:

Input: maze = [[".","+"]], entrance = [0,0]
Output: -1
Explanation: There are no exits in this maze.

Constraints:
maze.length == m
maze[i].length == n
1 <= m, n <= 100
maze[i][j] is either '.' or '+'.
entrance.length == 2
0 <= entrancerow < m
0 <= entrancecol < n
entrance will always be an empty cell.

idea:
typical BFS
*/

class NearestExitFromEntranceInMaze {
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;

        // For breadth first search, offer the first node ('entrance').
        // Note that we immediately mark the node as visited when putting into the queue as to
        // prevent other nodes from visiting it. Otherwise, we will be trapped in an infinite loop.
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        // Mark as visited as wall
        maze[entrance[0]][entrance[1]] = '+';

        int[][] directions = new int[][] {
            {0,1},{0,-1},{1,0},{-1,0}
        };

        int steps = 0;

        while (!queue.isEmpty()) {
            // We take a step before checking the directions for the nodes that we are at (in the queue).
            steps++;
            // Make sure to use a variable to keep track of the queue.size(),
            // because the queue size continuously changes as we check for the other nodes,
            // which can lead to infinite loops or undue termination of the for-loop.
            int size = queue.size();

            // Check every node at the current step.
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                // For each node, check every direction.
                for (int[] direction : directions) {
                    int nextX = current[0] + direction[0];
                    int nextY = current[1] + direction[1];

                    // Check if this direction out of bound or this direction is the wall.
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || maze[nextX][nextY] == '+') continue;

                    // If this direction is empty, not visited and is at the boundary, we have arrived at the exit.
                    if (nextX == 0 || nextX == m - 1 || nextY == 0 || nextY == n - 1) {
                        return steps;
                    }

                    // Otherwise, we change this direction as visited and put into the queue to check at the next step.
                    maze[nextX][nextY] = '+';
                    queue.offer(new int[] {nextX, nextY});
                }
            }

        }
        // If all the possible nodes and directions checked but no exits found, return -1.
        return -1;
    }
}