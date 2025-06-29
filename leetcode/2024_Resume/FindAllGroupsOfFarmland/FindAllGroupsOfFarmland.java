/*
You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a hectare of farmland.
To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland. These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not four-directionally adjacent to another farmland in a different group.
land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of farmland. A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].
Return a 2D array containing the 4-length arrays described above for each group of farmland in land. If there are no groups of farmland, return an empty array. You may return the answer in any order.

Example 1:
https://assets.leetcode.com/uploads/2021/07/27/screenshot-2021-07-27-at-12-23-15-copy-of-diagram-drawio-diagrams-net.png
Input: land = [[1,0,0],[0,1,1],[0,1,1]]
Output: [[0,0,0,0],[1,1,2,2]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].

Example 2:
https://assets.leetcode.com/uploads/2021/07/27/screenshot-2021-07-27-at-12-30-26-copy-of-diagram-drawio-diagrams-net.png
Input: land = [[1,1],[1,1]]
Output: [[0,0,1,1]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].

Example 3:
https://assets.leetcode.com/uploads/2021/07/27/screenshot-2021-07-27-at-12-32-24-copy-of-diagram-drawio-diagrams-net.png
Input: land = [[0]]
Output: []
Explanation:
There are no groups of farmland.

Constraints:
m == land.length
n == land[i].length
1 <= m, n <= 300
land consists of only 0's and 1's.
Groups of farmland are rectangular in shape.

idea:
BFS
由一个点 land(1)走到底
怎么走到底 queue while
*/

class FindAllGroupsOfFarmland {
    public int[][] findFarmland(int[][] land) {
        int[][] directions = new int[][] {
            {0,-1}, {-1,0}, {0,1}, {1,0}
        };

        int m = land.length;
        int n = land[0].length;
        List<int[]> result = new ArrayList<>();

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cell = land[i][j];

                if (cell == 1 && !visited[i][j]) {
                    // define a farmland, first 2 are starting points, last 2 will be updated later in while loop
                    int[] farmland = {i, j, i, j};
                    queue.offer(new int[] {i, j});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();

                        for (int[] dir : directions) {
                            int nextX = current[0] + dir[0];
                            int nextY = current[1] + dir[1];

                            if (nextX >= 0 && nextX < m &&
                                nextY >= 0 && nextY < n &&
                                land[nextX][nextY] == 1 &&
                                !visited[nextX][nextY]
                            ) {
                                queue.offer(new int[] {nextX, nextY});
                                visited[nextX][nextY] = true;
                                // update farmland
                                farmland[2] = Math.max(farmland[2], nextX);
                                farmland[3] = Math.max(farmland[3], nextY);
                            }
                        }
                    }

                    result.add(farmland);
                }
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}
