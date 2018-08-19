/*
There is a ball in a maze with empty spaces and walls.
The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall.
When the ball stops, it could choose the next direction.
There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance.
The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included).
Output the moving directions by using 'u', 'd', 'l' and 'r'.
Since there could be several different shortest ways, you should output the lexicographically smallest way.
If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space.
You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example 1
Input 1: a maze represented by a 2D array
0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0
Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)
Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

Example 2
Input 1: a maze represented by a 2D array
0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0
Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)
Output: "impossible"
Explanation: The ball cannot reach the hole.

Note:
There is only one ball and one hole in the maze.
Both the ball and hole exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.

idea:
dfs()
hashmap to save position i * n + j to path (ulrd) map
update with shorter distance's path
*/

import java.util.*;

class TheMaze {
	public static void main(String[] args) {
		TheMaze eg = new TheMaze();
		int[][] maze = {
			{0,0,0,0,0},
			{1,1,0,0,1},
			{0,0,0,0,0},
			{0,1,0,0,1},
			{0,1,0,0,0}	
		};

		int[] ball = {4, 3};
		int[] hole = {0, 1};

		String result = eg.findShortestWay(maze, ball, hole);

		System.out.println(result);
	}

	public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
		int m = maze.length;
		int n = maze[0].length;

		int[][] distance = new int[m][n];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[ball[0]][ball[1]] = 0;

        Map<Integer, String> hm = new HashMap<>();
        dfs(maze, ball[0], ball[1], hole, distance, hm);

        String result = hm.get(hole[0] * n + hole[1]);
        return result == null ? "impossible" : result;
	}

	public void dfs(int[][] maze, int i, int j, int[] hole, int[][] distance, Map<Integer, String> hm) {
		char[] ways = {'l','u','r','d'};
		int[][] directions = {{0,-1},{-1,0},{0,1},{1,0}};

		int m = maze.length;
		int n = maze[0].length;

		if (i == hole[0] && j == hole[1]) {
			return;
		}

        for (int k = 0; k < 4; k++) {
            int x = i;
            int y = j;
            int[] dir = directions[k];
            int dist = distance[x][y];
            String path = hm.getOrDefault(x * n + y, "");

			while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0 && (x != hole[0] || y != hole[1])) {
				x += dir[0];
				y += dir[1];
				dist++;
			}

            if (x != hole[0] || y != hole[1]) {
                x -= dir[0];
                y -= dir[1];
                dist--;
            }

            path += ways[k];

            if (distance[x][y] > dist) {
                distance[x][y] = dist;
                hm.put(x * n + y, path);
                dfs(maze, x, y, hole, distance, hm);
            } else if (distance[x][y] == dist && hm.getOrDefault(x * n + y, "").compareTo(path) > 0) {
                // save even shorter (lexicographically) path
                hm.put(x * n + y, path);
                dfs(maze, x, y, hole, distance, hm);
            }
        }
    }
}