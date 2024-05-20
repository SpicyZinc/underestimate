/*
There is a ball in a maze with empty spaces and walls.
The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array.
1 means the wall and 0 means the empty space.
You may assume that the borders of the maze are all walls.
The start and destination coordinates are represented by row and column indexes.

Example 1
Input 1: a maze represented by a 2D array
0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0
Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
https://leetcode.com/static/images/problemset/maze_1_example_1.png

Example 2
Input 1: a maze represented by a 2D array
0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0
Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.
https://leetcode.com/static/images/problemset/maze_1_example_2.png

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

idea:
dfs() with visited[][]
记得 while 到底
*/

class TheMaze {
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		if (maze.length == 0 || maze[0].length == 0) {
			return true;
		}

		int m = maze.length;
		int n = maze[0].length;

        boolean[][] visited = new boolean[m][n];

        return dfs(maze, visited, start[0], start[1], destination[0], destination[1]);
    }
    // Tue Apr 30 18:13:47 2024
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        return dfs(maze, start[0], start[1], destination, visited);
    }

    public boolean dfs(int[][] maze, int x, int y, int[] destination, boolean[][] visited) {
        int m = maze.length;
        int n = maze[0].length;

        if (x == destination[0] && y == destination[1]) {
            return true;
        }

        if (visited[x][y]) {
            return false;
        }

        int[][] directions = {{0,-1},{-1,0},{0,1},{1,0}};

        visited[x][y] = true;

        for (int[] dir : directions) {
            int nextX = x;
            int nextY = y;

            while (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && maze[nextX][nextY] == 0) {
                nextX += dir[0];
                nextY += dir[1];
            }
            // while stops, backup one step to make it valid
            nextX -= dir[0];
            nextY -= dir[1];

            if (dfs(maze, nextX, nextY, destination, visited)) {
                return true;
            }
        }

        return false;
    }

    public boolean dfs(int[][] maze, boolean[][] visited, int i, int j, int di, int dj) {
        int m = maze.length;
		int n = maze[0].length;
        
        if (visited[i][j]) {
            return false;
        }
        if (i == di && j == dj) {
            return true;
        }

        visited[i][j] = true;

		int[][] directions = {{0,-1},{-1,0},{0,1},{1,0}};

		for (int[] dir : directions) {
			int x = i;
			int y = j;
			// left -> left -> ...
			// down -> down -> ...
			// as long as no wall, continue going
			while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
				x += dir[0];
				y += dir[1];
			}
			// backwards 1 step
			x -= dir[0];
			y -= dir[1];

			if (dfs(maze, visited, x, y, di, dj)) {
				return true;
			}
		}

        return false;
    }
}