/*
You are given a m x n 2D grid initialized with these three possible values.
-1 - A wall or an obstacle.
 0 - A gate.
INF - Infinity means an empty room.

We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:
	3  -1   0   1
	2   2   1  -1
	1  -1   2  -1
	0  -1   3   4

idea:
1. DFS, easy to follow
from '0' position to dfs
2. BFS https://segmentfault.com/a/1190000003906674

note, rooms[i][j] >= distance `=`
*/

import java.util.*;

public class WallsAndGates {
	// 07/09/2018
	public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        int m = rooms.length;
        int n = rooms[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
            	// for each gate, gate to itself of course it is zero distance
                if (rooms[i][j] == 0) {
                    dfsFill(rooms, i, j, 0);
                }
            }
        }
    }
    
    public void dfsFill(int[][] rooms, int i, int j, int distance) {
        int m = rooms.length;
		int n = rooms[0].length;

		int[][] directions = new int[][] {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0}
		};
        
        for (int[] dir : directions) {
            int newX = i + dir[0];
            int newY = j + dir[1];
            
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && rooms[newX][newY] > distance + 1) {
                rooms[newX][newY] = distance + 1;
                dfsFill(rooms, newX, newY, distance + 1);
            }           
        }
    }

	public static void main(String[] args) {
		WallsAndGates eg = new WallsAndGates();
		int INF = Integer.MAX_VALUE;
		int[][] rooms = new int[][] {
			{INF,  -1,  0,  INF},
			{INF, INF, INF,  -1},
			{INF,  -1, INF,  -1},
			{0,  -1, INF, INF}
		};

		eg.wallsAndGates(rooms);

		for (int[] row : rooms) {
			System.out.println(Arrays.toString(row));
		}
	}

	public void wallsAndGates(int[][] rooms) {
		if (rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

		int m = rooms.length;
		int n = rooms[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (rooms[i][j] == 0) {
					dfsFill(rooms, i, j, 0);
				}
			}
		}		
	}

	public void dfsFill(int[][] rooms, int i, int j, int distance) {
		int m = rooms.length;
		int n = rooms[0].length;

		int[][] directions = new int[][] {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0}
		};

		if (i >= 0 && i < m && j >= 0 && j < n && rooms[i][j] >= distance) {
			rooms[i][j] = distance;
			for (int[] dir : directions) {
				int newX = i + dir[0];
				int newY = j + dir[1];

				dfsFill(rooms, newX, newY, distance + 1);
			}
		}
	}
}