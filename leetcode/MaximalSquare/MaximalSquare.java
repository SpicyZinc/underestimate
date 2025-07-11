/*
Given a 2D binary matrix filled with 0's and 1's,
find the largest square containing only 1's and return its area.

For example, given the following matrix:
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Return 4.

idea:
https://segmentfault.com/a/1190000003709497
某个点为正方形右下角时最大的正方形时, 那它的上方, 左方和左上方三个点也一定是某个正方形的右下角

dp[i][j] is the maximum side length of the square based on (i, j) as bottom right corner of the square
存的是边长

dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1;
in term of bottom right point
current = min(top to current, left to current, diagonal to current) + 1
*/

public class MaximalSquare {
    // 02/03/2019
    // lintcode version
    public int maxSquare(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int maxLen = 0;
        
        int[][] dp = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            dp[i][0] = matrix[i][0];
            maxLen = Math.max(maxLen, dp[i][0]);
        }
        
        for (int i = 0; i < m; i++) {
            dp[0][i] = matrix[0][i];
            maxLen = Math.max(maxLen, dp[0][i]);
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    int minSideLen = getMinOfThree(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]);
                    dp[i][j] = minSideLen + matrix[i][j];
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = matrix[i][j];
                }
            }
        }
        
        return maxLen * maxLen;
    }
    
    public int getMinOfThree(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        // update 1st column
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] - '0';
            max = Math.max(max, dp[i][0]);
        }
        // update 1st row
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j] - '0';
            max =  Math.max(max, dp[0][j]);
        }
        // update the rest of the matrix
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = matrix[i][j] == '1' ? minOfThree(dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]) + 1 : 0;
                max = Math.max(max, dp[i][j]);      
            }
        }

        return max * max;
    }

    private int minOfThree(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}