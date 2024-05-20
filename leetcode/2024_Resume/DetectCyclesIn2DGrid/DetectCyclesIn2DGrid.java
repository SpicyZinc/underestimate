/*
Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
Return true if any cycle of the same value exists in grid, otherwise, return false.


Example 1:
Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:

Example 2:
Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:

Example 3:
Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid consists only of lowercase English letters.

idea:
dfs
而且不能走重复路
visited true return true
*/
class DetectCyclesIn2DGrid {

    int[][] directions = new int[][] {
        {-1,0},
        {0,1},
        {1,0},
        {0,-1}
    };

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (isCycle(grid, i, j, visited, -1, -1)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    
    public boolean isCycle(char[][] grid, int i, int j, boolean[][] visited, int prevI, int prevJ) {
        visited[i][j] = true;
        
        for (int[] dir: directions) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            
            if (nextI < 0 || nextI >= grid.length || nextJ < 0 || nextJ >= grid[0].length || grid[nextI][nextJ] != grid[i][j]) {
                continue;
            }
            // 不走重复路
            if (nextI == prevI && nextJ == prevJ) {
                continue;
            }
            
            if (visited[nextI][nextJ]) {
                return true;
            }
            
            if (isCycle(grid, nextI, nextJ, visited, i, j)) {
                return true;
            }
        }

        return false;
    }
}