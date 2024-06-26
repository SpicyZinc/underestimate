/*
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down.
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:
nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:
nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

idea:
recursion on every point in the matrix, then DP to memorization
dp[i][j] refers to the length of the longest increasing path staring at (i, j)

recursion return as long as dp[x][y] != 0
*/

public class LongestIncreasingPathInAMatrix {
    int[][] directions = new int[][] {
        {0, 1},
        {1, 0},
        {0, -1},
        {-1, 0}
    };
    // Sun Jun 16 02:06:19 2019
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] memo = new int[m][n];
        int longestIncreasingPathLen = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                longestIncreasingPathLen = Math.max(longestIncreasingPathLen, dfs(matrix, i, j, memo));
            }
        }

        return longestIncreasingPathLen;
    }

    // 以 i, j为起点的path的最大长度
    public int dfs(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int m = matrix.length;
        int n = matrix[0].length;

        for (int[] dir : directions) {
            int nextX = i + dir[0];
            int nextY = j + dir[1];
    
            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && matrix[nextX][nextY] > matrix[i][j]) {
                memo[i][j] = Math.max(memo[i][j], dfs(matrix, nextX, nextY, memo));
            }
        }
        // 增加了1个长度 既它本身
        memo[i][j] += 1;

        return memo[i][j];
    }

    // 02/10/2019
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix == null) {
            return 0;
        }
      
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        int max = 1;
      
        for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
            // 以每个点作为起点 求最长的 increasing path
            max = Math.max(max, dfs(matrix, i, j, memo));        
          }
        }
      
        return max;
    }
    
    public int dfs(int[][] matrix, int x, int y, int[][] memo) {
        if (memo[x][y] != 0) {
          return memo[x][y];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        for (int[] dir : directions) {
          int nextX = x + dir[0];
          int nextY = y + dir[1];
          
          if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && matrix[nextX][nextY] > matrix[x][y]) {
            memo[x][y] = Math.max(memo[x][y], dfs(matrix, nextX, nextY, memo));
          }
        }
        // 增加了1个长度 既它本身
        memo[x][y] += 1;
        
        return memo[x][y];
    }
}
