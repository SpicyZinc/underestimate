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
Our building should be placed at those empty places and reach[i][j] == totalBuildingNum

note, the case where there are only buildings and no empty lands, like [[1]].
so shortest distance at last is still Integer.MAX_VALUE should be returned to -1
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

    // Sun Jun 30 22:54:29 2019
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid[0].length == 0) {
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

        int buildingCount = 0;

        int[][] distanceToAllBuildings = new int[m][n];
        int[][] reachable = new int[m][n];

        // 构造 每一个空地 到 building 的 距离场
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // for each building
                if (grid[i][j] == 1) {
                    buildingCount++;

                    int distance = 1;
                    // 注意每次都新一个visited
                    // 这样保证 之前visit的 但是这次没有visit 被reset 不会影响结果
                    boolean[][] visited = new boolean[m][n];

                    Queue<int[]> queue = new LinkedList<>();

                    queue.offer(new int[] {i, j});

                    while (!queue.isEmpty()) {
                        int size = queue.size();

                        for (int k = 0; k < size; k++) {

                            int[] position = queue.poll();
                            int x = position[0];
                            int y = position[1];

                            for (int[] dir : directions) {
                                int nextX = x + dir[0];
                                int nextY = y + dir[1];

                                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                                    // The shortest distance from [nextX][nextY] to this building is 'distance'.
                                    // 因为是从building出发的所以肯定是最近的最远的排列
                                    distanceToAllBuildings[nextX][nextY] += distance;
                                    reachable[nextX][nextY] += 1;

                                    visited[nextX][nextY] = true;

                                    queue.offer(new int[] {nextX, nextY});
                                }
                            }
                        }

                        distance++;
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reachable[i][j] == buildingCount) {
                    min = Math.min(min, distanceToAllBuildings[i][j]);
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    // 12/01/2018
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid[0].length == 0) {
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

        int[][] distance = new int[m][n];
        int[][] reachable = new int[m][n];

        int buildingCnt = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // from each building
                if (grid[i][j] == 1) {
                    buildingCnt++;

                    Queue<int[]> queue = new LinkedList<int[]>();
                    queue.offer(new int[] {i, j});

                    boolean[][] isVisited = new boolean[m][n];
                    int dist = 1;
                    
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int k = 0; k < size; k++) {

                            int[] position = queue.poll();
                            int x = position[0];
                            int y = position[1];

                            for (int[] dir : directions) {
                                int newX = x + dir[0];
                                int newY = y + dir[1];

                                if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 0 && !isVisited[newX][newY]) {
                                    // The shortest distance from [newX][newY] to this building is 'dist'.
                                    System.out.println(newX + ", " + newY + " == " + dist);
                                    distance[newX][newY] += dist;
                                    reachable[newX][newY]++;

                                    isVisited[newX][newY] = true;
                                    queue.offer(new int[] {newX, newY});
                                }
                            }
                        }

                        System.out.println("=======");

                        dist++;
                    }
                }
            }
        }

        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reachable[i][j] == buildingCnt) {
                    shortest = Math.min(shortest, distance[i][j]);
                }
            }
        }

        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }

    // note, hard copy array is key point
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
        int[][] copy = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        
        // similar to dp, 累加值 (i, j) 到某个building的距离
        int[][] sum = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j] = copy[i][j];
            }
        }
        
        int shortestDistance = Integer.MAX_VALUE;
        int val = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // from each building
                if (grid[i][j] == 1) {
                    shortestDistance = Integer.MAX_VALUE;

                    int[][] dist = new int[m][n];
                    dist = copy;

                    Queue<int[]> queue = new LinkedList<int[]>();
                    queue.offer(new int[] {i, j});
                    // from a cell == 1 (building), traverse all 0 places, and mark them as (val - 1)
                    // in other words, all 0 places to this building are obtained, so update the min
                    while (!queue.isEmpty()) {
                        int[] position = queue.poll();
                        int x = position[0];
                        int y = position[1];

                        for (int[] dir : directions) {
                            int newX = x + dir[0];
                            int newY = y + dir[1];
                            if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == val) {
                                // 下次找 (val - 1) 的 其实就是 empty 0 places
                                grid[newX][newY]--;
                                dist[newX][newY] = dist[x][y] + 1;
                                sum[newX][newY] += dist[newX][newY] - 1;

                                queue.offer(new int[] {newX, newY});

                                shortestDistance = Math.min(shortestDistance, sum[newX][newY]);
                            }
                        }
                    }
                    val--;
                }
            }
        }

        return shortestDistance == Integer.MAX_VALUE ? -1 : shortestDistance;
    }
}