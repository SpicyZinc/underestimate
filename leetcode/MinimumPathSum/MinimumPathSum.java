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
		
		MinimumPathSum eg = new MinimumPathSum();
		System.out.println("For this grid the minimum path sum is " + eg.minPathSum(grid));
	}
    // typical DP
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        // first value
        dp[0][0] = grid[0][0];
        // first row
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        // first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        // the rest
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        
        return dp[m - 1][n - 1];
    }
}