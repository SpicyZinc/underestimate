/*
Follow up for "Unique Paths":
Now consider if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.

idea:
top-down
1. first row and first column
2. the rest
    if obstacleGrid[i][j]=1, dp[i][j] = 0;
    if obstacleGrid[i][j]=0, dp[i][j] = d[i-1][j] + dp[i][j-1];
*/

public class UniquePathWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (m == 0 || n == 0 || obstacleGrid == null) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        // first row, always same walking ways, which is 1
        for (int j = 1; j < n; j++) {
            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : dp[0][j - 1];
        }
        // first column, always same walking ways, which is 1
        for (int i = 1; i < m; i++) {
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
        }
        // the rest
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = (obstacleGrid[i][j] == 1) ? 0 : (dp[i - 1][j] + dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }
}