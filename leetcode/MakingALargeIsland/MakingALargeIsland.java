/*
In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

Example 1:
Input: [[1, 0], [0, 1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

Example 2:
Input: [[1, 1], [1, 0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.

Example 3:
Input: [[1, 1], [1, 1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.

Notes:
1 <= grid.length = grid[0].length <= 50.
0 <= grid[i][j] <= 1.

idea:
typical union find
similar to ConnectingGraph II

对每个 0 (找 set as 1 之后) 相连的 四个 方向的 带头大哥 的 size
如果不是一个组 用 set 就 加上这个 带头大哥 所领导的 size

用这种 i * n + j 作为 idx
*/

class MakingALargeIsland {
    // Mon Apr 22 02:46:04 2024
    public int largestIsland(int[][] grid) {
        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
        };

        int m = grid.length;
        int n = grid[0].length;
        int[] f = new int[m * n];
        int[] size = new int[m * n];

        // initialize f, size
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int id = i * n + j;
                f[id] = id;
                if (grid[i][j] == 1) {
                    size[id] = 1;
                }
            }
        }

        // connect current grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int id = i * n + j;

                    for (int[] dir : directions) {
                        int nextX = i + dir[0];
                        int nextY = j + dir[1];

                        if (within(nextX, nextY, m, n) && grid[nextX][nextY] == 1) {
                            int nextId = nextX * n + nextY;
                            union(f, size, id, nextId);
                        }
                    }
                }
            }
        }

        // find current max
        int max = 0;
        for (int i = 0; i < m * n; i++) {
            max = Math.max(max, size[i]);
        }

        // try to flip each '0' cell, update 'max'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int id = i * n + j;
                    int currentSize = 1;
                    Set<Integer> superFatherSet = new HashSet<>();

                    for (int[] dir : directions) {
                        int nextX = i + dir[0];
                        int nextY = j + dir[1];

                        if (within(nextX, nextY, m, n) && grid[nextX][nextY] == 1) {
                            int nextId = nextX * n + nextY;
                            int superFather = find(f, nextId);

                            if (superFatherSet.isEmpty() || !superFatherSet.contains(superFather)) {
                                currentSize += size[superFather];
                                superFatherSet.add(superFather);
                            }
                        }
                    }

                    max = Math.max(max, currentSize);
                }
            }
        }

        return max == 0 ? m * n : max;
    }
    
    public boolean within(int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    // connect
    public void union(int[] f, int[] size, int a, int b) {
        int fa = find(f, a);
        int fb = find(f, b);

        if (fa != fb) {
            f[fa] = fb;
            size[fb] += size[fa];
        }
    }

    // find
    public int find(int[] f, int i) {
        while (f[i] != i) {
            i = f[i];
        }

        return i;
    }


    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;


        int[] f = new int[m * n];
        int[] size = new int[m * n];

        for (int i = 0; i < m * n; i++) {
            f[i] = i;
        }

        Arrays.fill(size, 1);

        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int id = i * n + j;

                    for (int[] dir : directions) {
                        int nextX = i + dir[0];
                        int nextY = j + dir[1];

                        if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == 1) {
                            int nextId = nextX * n + nextY;
                            connect(f, size, id, nextId);
                        }
                    }                   
                }
            }
        }

        // find current max island size before flipping any 0 to 1
        int max = 0;
        for (int i = 0; i < m * n; i++) {
            max = Math.max(max, size[i]);
        }

        // find max island size after flipping any 0 to 1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int id = i * n + j;
                    int combinedSize = 1;
                    // 看四个方向上的father 隶属于同一个么 还是各自为政
                    // use set
                    Set<Integer> superFathersGroup = new HashSet<>();

                    for (int[] dir : directions) {
                        int nextX = i + dir[0];
                        int nextY = j + dir[1];

                        if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == 1) {
                            int nextId = nextX * n + nextY;
                            int superFather = find(f, nextId);

                            if (superFathersGroup.isEmpty() || !superFathersGroup.contains(superFather)) {
                                combinedSize += size[superFather];
                                superFathersGroup.add(superFather);
                            }
                        }
                    }

                    max = Math.max(max, combinedSize);
                }
            }
        }

        return max == 0 ? m * n : max;
    }

    // union
    public void connect(int[] f, int[] size, int a, int b) {
        int fa = find(f, a);
        int fb = find(f, b);
        
        if (fa != fb) {
            f[fa] = fb;
            // 注意方向
            size[fb] += size[fa];
        }
    }

    // find
    public int find(int[] f, int i) {
        while (i != f[i]) {
            i = f[i];
        }
        
        return i;
    }
}
