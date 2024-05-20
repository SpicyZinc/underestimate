/*
Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).


Example 1:

Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.

Example 2:

Input: matrix = [[-19,57],[-40,-5]]
Output: -59
Explanation: The falling path with a minimum sum is shown.

idea:
dfs + memo
https://leetcode.com/problems/minimum-falling-path-sum/solutions/2908970/full-explanation-of-dynamic-programming-process-starting-from-recursion-on-a-graph/

*/

class MinimumFallingPathSum {
    int[][] directions = new int[][] {
        {1, 1},
        {1, 0},
        {1, -1}
    };

    int minFallingPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //initialize memorized values array.
        Integer[][] memo = new Integer[m][n];
        // key point
        // The bottom row of the matrix doesn't need to change, as there are no alternative paths available to further minimize the sum.
        // This also saves a bounds check in our memorized function.
        for (int col = 0; col < n; col++) {
            memo[m - 1][col] = matrix[m - 1][col];
        }

        int minSumPath = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            minSumPath = Math.min(minSumPath, dfs(memo, matrix, 0, col));
        }

        return minSumPath;
    }

    int dfs(Integer[][] memo, int[][] matrix, int row, int col) {
        int m = matrix.length;
        int n = matrix[0].length;
        // Return previously calculated value if it exists.
        if (memo[row][col] != null) {
            return memo[row][col];
        }

        int currentMin = Integer.MAX_VALUE;
        for (int[] dir : directions) {
            int nextX = row + dir[0];
            int nextY = col + dir[1];
    
            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
                currentMin = Math.min(currentMin, dfs(memo, matrix, nextX, nextY));
            }
        }
        memo[row][col] = matrix[row][col] + currentMin;

        return memo[row][col];
    }
}
