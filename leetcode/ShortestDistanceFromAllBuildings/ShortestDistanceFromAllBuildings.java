/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

idea:
Start from each building, do bfs to calculate the shortest distance to each empty land which this building can reach.
how nextX and nextY, when next, level += 1, up, down, left and right distance increases by one
repeat this process for all buildings
get the sum of shortest distance from every '0' to all reachable buildings
This value is stored in distance[][]

need another reach[][] to track how many buildings each '0' can be reached

loop again,
Our building should be placed at those empty places  and reach[i][j] == totalBuildingNum

note: Be careful the case where there are only buildings and no empty lands, like [[1]].
so shortest distance at last is still Integer.MAX_VALUE should be turned to -1

*/

import java.util.*;

public class ShortestDistanceFromAllBuildings {
	public static void main(String[] args) {
		ShortestDistanceFromAllBuildings eg = new ShortestDistanceFromAllBuildings();
		int[][] grid = {
			{1, 0, 2, 0, 1},
			{0, 0, 0, 0, 0},
			{0, 0, 1, 0, 0},
		};
		int shortest = eg.shortestDistance(grid);

		System.out.println(shortest);
	}

	public int shortestDistance(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int[][] directions = {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0},
		};

		int m = grid.length;
		int n = grid[0].length;
		// empty land at i, j distance to some building
		int[][] distance = new int[m][n];
		// the number of buildings empty land at i, j can reaches
		int[][] reach = new int[m][n];
		int buildingNum = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					buildingNum++;
					Queue<int[]> queue = new LinkedList<int[]>();
					queue.offer(new int[] {i, j});

					boolean[][] visited = new boolean[m][n];
					int level = 1;

					while (!queue.isEmpty()) {
						int size = queue.size();
						for (int k = 0; k < size; k++) {
							int[] position = queue.poll();

							for (int[] dir : directions) {
								int nextX = position[0] + dir[0];
								int nextY = position[1] + dir[1];

								if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == 0 && !visited[nextX][nextY]) {
									distance[nextX][nextY] += level;
									reach[nextX][nextY]++;
									visited[nextX][nextY] = true;
									queue.offer(new int[] {nextX, nextY});
								}
							}
						}
						level++;
					}
				}
			}
		}

		int shortestDistance = Integer.MAX_VALUE;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
					shortestDistance = Math.min(shortestDistance, distance[i][j]);
				}
			}
		}

		return shortestDistance == Integer.MAX_VALUE ? -1 : shortestDistance;
	}
}