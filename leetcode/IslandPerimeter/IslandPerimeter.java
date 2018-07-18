/*
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
Grid cells are connected horizontally/vertically (not diagonally).
The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1.
The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:

idea: 
if next to 0 perimeter + 1
if out of boundary perimeter + 1
*/
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    result += getCellPerimeter(grid, i, j);
                }
            }
        }
        
        return result;
    }
    // key is no duplicates
    // getCellPerimeter to help get an land's perimeter, not dfs
    public int getCellPerimeter(int[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;

        int perimeter = 0;
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            
            if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] == 0) {
                perimeter += 1;
            }
        }
        
        return perimeter;
    }
}