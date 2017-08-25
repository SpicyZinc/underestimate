/*
Given a m x n grid filled with non-negative numbers, 
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

idea:

a typical DP
set up first row and first column value
then for coming to a cell, it could either from left or from top
min[i][j] = Math.min( min[i][j-1], min[i-1][j] ) + grid[i][j]

*/
import java.util.*;

public class MinimumPathSum {
	public static void main(String[] args) {
		int m = 5;
		int n = 6;
		int[][] grid = new int[5][6];
		Random random = new Random();
		for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = random.nextInt(30);
				System.out.print(grid[i][j] + "  ");
            }
			System.out.print("\n");
        }
		
		MinimumPathSum aTest = new MinimumPathSum();
		System.out.println("For this grid the minimum path sum is " + aTest.minPathSum(grid));
	}
    public int minPathSum(int[][] grid) {         
        if (grid == null || grid.length == 0) {
            return 0;             
        }
        int m = grid.length; // rows
		int n = grid[0].length; // columns
        int[][] min = new int[m][n];
 
        min[0][0] = grid[0][0]; // start from top left
        for (int j = 1; j < n; j++) {
            min[0][j] = min[0][j-1] + grid[0][j];  
        }
 
        for (int i = 1; i < m; i++) {
            min[i][0] = min[i-1][0] + grid[i][0];
        }
 
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                min[i][j] = Math.min( min[i-1][j], min[i][j-1] ) + grid[i][j];				
            }
        }
 
        return min[m-1][n-1];
    }
}