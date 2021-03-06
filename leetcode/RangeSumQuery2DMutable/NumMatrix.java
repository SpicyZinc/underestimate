/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle
defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10

Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.


idea:
https://www.cnblogs.com/grandyang/p/5300458.html
keep column sum colSum
O(m) + O(n)

keep row sum rowSum example is here
https://evanyang.gitbooks.io/leetcode/content/LeetCode/range_sum_query_2d_-_mutable.html
*/
public class NumMatrix {
	public static void main(String[] args) {
		int[][] matrix = {
			{3, 0, 1, 4, 2},
			{5, 6, 3, 2, 1},
			{1, 2, 0, 1, 5},
			{4, 1, 0, 1, 7},
			{1, 0, 3, 0, 5},
		};

		NumMatrix nm = new NumMatrix(matrix);
		int before = nm.sumRegion(2, 1, 4, 3);
		nm.update(3, 2, 2);
		int after = nm.sumRegion(2, 1, 4, 3);

		System.out.println("Before === " + before + " => After === " + after);
	}

	// Sun Jul  7 19:47:18 2019
	int[][] matrix;
	int[][] columnSum;

	public NumMatrix(int[][] matrix) {
		if (matrix.length == 0 || matrix == null) {
			return;
		}

		this.matrix = matrix;

		int m = matrix.length;
		int n = matrix[0].length;

		this.columnSum = new int[m][n];

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				columnSum[i][j] = i == 0 ? matrix[i][j] : (matrix[i][j] + columnSum[i - 1][j]);
			}
		}
	}

	public void update(int row, int col, int val) {
		int m = matrix.length;

		// from 'row' to 'm - 1' will be updated
		for (int i = row; i < m; i++) {
			columnSum[i][col] = columnSum[i][col] - matrix[row][col] + val;
		}

		matrix[row][col] = val;
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		int sum = 0;

		for (int j = col1; j <= col2; j++) {
			sum += columnSum[row2][j] - (row1 == 0 ? 0 : columnSum[row1 - 1][j]);
		}

		return sum;
	}


	// col sum method
	int[][] matrix;
	int[][] colSum;

	public NumMatrix(int[][] matrix) {
		if (matrix.length == 0 || matrix == null) {
			return;
		}
		
		this.matrix = matrix;
		
		int m = matrix.length;
		int n = matrix[0].length;
		// colSum[i][j] = matrix[0][j] + matrix[1][j] + ... + matrix[i][j]
		this.colSum = new int[m][n];

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				this.colSum[i][j] = matrix[i][j] + (i == 0 ? 0 : this.colSum[i - 1][j]);
			}
		}
	}

	// time complexity for the worst case scenario: O(m)
	public void update(int row, int col, int val) {
		int m = matrix.length;
		
		for (int i = row; i < m; i++) {
			colSum[i][col] = colSum[i][col] - matrix[row][col] + val;
		}
		// note where to update matrix, made a mistake to update it too early in 1D
		matrix[row][col] = val;
	}

	// time complexity for the worst case scenario: O(n)
	public int sumRegion(int row1, int col1, int row2, int col2) {
		int sum = 0;
		// for each column, how many, [col1, col2], use colSum to get sum
		for (int j = col1; j <= col2; j++) {
			sum += (row1 == 0 ? colSum[row2][j] : colSum[row2][j] - colSum[row1 - 1][j]);
		}

		return sum;
	}
}