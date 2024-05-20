/*
Write an efficient algorithm that searches for a value in an m x n matrix. 

This matrix has the following properties:
Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

For example,
Consider the following matrix:
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.
Given target = 20, return false.

idea:

http://articles.leetcode.com/2010/10/searching-2d-sorted-matrix-part-ii.html
1. step wise
from the top right corner 
to the bottom left corner

if the target is greater than the value of current position, then the target can not be in entire row of current position because the row is sorted
if the target is less than the value of current position, then the target can not in the entire column of current position because the column is sorted

one direction
    <-
----|-|- |
----|-|- V

2. quadratic
*/

public class Search2Dmatrix {
    // lintcode version to find the count
    public int searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 ||  matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0;
        int col = n - 1;
        int cnt = 0;

        // towards the middle or the center
        while (row < m && col >= 0) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                cnt++;

                col--;
                row++;
            }
        }

        return cnt;
    }

    // 07/15/2018
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int i = 0;
        int j = n - 1;
        
        while (i < m && j >= 0) {
            if (matrix[i][j] < target) {
                i++;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                return true;
            }
        }

        return false;
    }
    // this returns how many targets are found out
    public int searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0 || matrix == null) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
        int row = m - 1;
        int col = 0;

        while (row >= 0 && row < m && col >= 0 && col < n) {
            int curr = matrix[row][col];

            if (curr > target) {
                row--;
            } else if (curr < target) {
                col++;
            } else {
                col++;
                row--;

                count++;
            }
        }

        return count;
    }

    // quadratic method
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        return dfs(matrix, 0, n - 1, 0, m - 1, target);
    }

    boolean dfs(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd, int target ) {
        if (rowStart > rowEnd || colStart > colEnd) {
            return false;
        }

        int rm = (rowStart + rowEnd) / 2;
        int cm = (colStart + colEnd) / 2; 
        if (matrix[rm][cm] == target) { 
            return true;
        } else if (matrix[rm][cm] > target) {
            return dfs(matrix, rowStart, rm-1,colStart, cm-1,target)|| dfs(matrix, rm, rowEnd, colStart,cm-1,target) || dfs(matrix, rowStart, rm-1,cm, colEnd,target);
        } else { 
            return dfs(matrix, rm+1, rowEnd, cm+1,colEnd,target)|| dfs(matrix, rm+1, rowEnd, colStart,cm,target) || dfs(matrix, rowStart, rm,cm+1, colEnd,target);
        }
    }   
}