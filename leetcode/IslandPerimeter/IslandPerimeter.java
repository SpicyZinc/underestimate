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
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int[][] directions = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int value = grid[i][j];
                if (value == 1) {
                    result += getCellPerimeter(grid, i, j, directions);
                }
            }    
        }
        return result;
    }
    
    private int getCellPerimeter(int[][] grid, int x, int y, int[][] directions) {
        int perimeter = 0;
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX < 0 || newY < 0 || newX >= grid.length || newY >= grid[0].length) {
                perimeter += 1;
            } else if (grid[newX][newY] == 0) {
                perimeter += 1;
            }
        }
        return perimeter;
    }
}