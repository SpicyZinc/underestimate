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
        	// set to 2 to indicated visited
            grid[i][j] = '2';

            for (int[] dir : directions) {
                int newX = i + dir[0];
                int newY = j + dir[1];
                dfs(grid, newX, newY);    
            }
        }
    }
}