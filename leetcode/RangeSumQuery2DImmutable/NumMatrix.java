/*
Given a 2D matrix matrix, 
find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.

idea:
http://blog.welkinlan.com/2015/11/17/range-sum-query-1d-2d-immutable-leetcode-java/
dynamic programming

-----------------
|       |       |
|   A   |   B   |
|       |       |
-----------------
|       |       |
|   C   |   D   |
|       |       |
-----------------

Zone 1 = A
Zone 2 = A + B
Zone 3 = A + C
Zone 4 = A + B + C + D

in the successive function:
Zone 4 = Zone 2 + Zone 3 - Zone 1 + D = (A + B) + (A + C) - A + D = A + B + C + D

in final result:
D = Zone 4 - Zone 2 - Zone 3 + Zone 1 = (A + B + C + D) - (A + B) - (A + C) + A = D
*/

public class NumMatrix {
	public int[][] sum;

	public NumMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}

		int m = matrix.length;
		int n = matrix[0].length;

		sum = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return sum[row2 + 1][col2 + 1] - sum[row2 + 1][col1] - sum[row1][col2 + 1] + sum[row1][col1];
	}
}

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);