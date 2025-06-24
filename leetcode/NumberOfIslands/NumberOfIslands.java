/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example 1:
11110
11010
11000
00000
Answer: 1

Example 2:
11000
11000
00100
00011
Answer: 3

idea:
http://blog.csdn.net/ljiabin/article/details/44975717
http://www.jiuzhang.com/solutions/numbers-of-islands/

find the number of areas of connected 1's
by connected it means 1 is adjacent to every other 1 horizontally or vertically

use dfs to change 1 to something else e.g. 2
so the count of 1 will be the number of islands
这一个下去后 与这个connected的1 all changed to 2
跟 similar string groups 异曲同工之妙 关键是建立这种图的表示方法 那个用hash
这个用 matrix 建立双重 u - v and v - u
*/

public class NumberOfIslands {
    // Sun Jun  9 17:45:08 2019
    public int[][] directions = {
        {0, -1},
        {0, 1},
        {-1, 0},
        {1, 0},
    };

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int cnt = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    dfs(grid, i, j);
                }
            }
        }

        return cnt;
    }
    
    // main purpose is to set 1 to 2 to indicated visited
    public void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }

        if (grid[i][j] == '1') {
            // set to 2 to indicate visited
            grid[i][j] = '2';

            for (int[] dir : directions) {
                int newX = i + dir[0];
                int newY = j + dir[1];
                dfs(grid, newX, newY);    
            }
        }
    }


    // Thu May 23 19:46:10 2024
    int count = 0;
    int[][] directions = {
        {0, -1},
        {0, 1},
        {-1, 0},
        {1, 0},
    };

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        count = m * n;
        int[] nums = new int[count];
        boolean[] visited = new boolean[count];

        for (int k = 0; k < count; k++) {
            // Initializing union find with its own index
            // Each element is its own disjoint set
            nums[k] = k;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    visited[i * n + j] = true;
                    for (int[] dir : directions) {
                        int newX = i + dir[0];
                        int newY = j + dir[1];

                        if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == '1' && !visited[newX * n + newY]) {
                            union(nums, i * n + j, newX * n + newY);
                        }
                    }
                } else {
                    // not an island
                    count--;
                }
            }
        }

        return count;
    }

    public void union(int[] nums, int i, int j) {
        int x = find(nums, i);
        int y = find(nums, j);
        if (x != y) {
            nums[x] = y;
            count--; // Reducing count since grid[i][j] is not a new separate island but just a connected part of existing island (grid[i-1][j] or grid[i][j-1])
        }
    }
    // note, i == nums[i] 谁在前
    public int find(int[] nums, int i) {
        while (i != nums[i]) {
            i = nums[i];
        }
        return i;
    }


    int count = 0;
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        count = m * n;

        int[] nums = new int[count];
        for (int k = 0; k < count; k++) {
            // Initializing union find with its own index
            // Each element is its own disjoint set
            nums[k] = k;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        union(nums, i * n + j, (i - 1) * n + j);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        union(nums, i * n + j, i * n + j - 1);
                    }
                } else {
                    // not an island
                    count--;
                }
            }
        }

        return count;
    }

    public void union(int[] nums, int i, int j) {
        int x = find(nums, i);
        int y = find(nums, j);
        if (x != y) {
            nums[x] = y;
            count--; // Reducing count since grid[i][j] is not a new separate island but just a connected part of existing island (grid[i-1][j] or grid[i][j-1])
        }
    }

    public int find(int[] nums, int i) {
        while (i != nums[i]) {
            i = nums[i];
        }
        return i;
    }

    // both working
    public int find(int[] nums, int k) {
        int root = k;
        while (nums[root] != root) {
            root = nums[root];
        }
        // path compression 
        while (nums[k] != k) {
            int temp = nums[k];
            nums[k] = root;
            k = temp;
        }

        return root;
    }
}
