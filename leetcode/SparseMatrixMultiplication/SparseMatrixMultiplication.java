/*
Given two sparse matrices A and B, return the result of AB.
You may assume that A's column number is equal to B's row number.

Example:
Input:
A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]
B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

Output:

	 |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
				  | 0 0 1 |

idea:
understand Matrix Multiplication, then for 0 element no need to multiply
*/

class SparseMatrixMultiplication {
	// A[i][k] * B[k][j] = M[i][j]
	// just skip the 0 cell
	public int[][] multiply(int[][] A, int[][] B) {
		int m = A.length;
		int n = B[0].length;

		int[][] multiplication = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int k = 0; k < A[i].length; k++) {
				if (A[i][k] != 0) {
					for (int j = 0; j < n; j++) {
						if (B[k][j] != 0) {
							multiplication[i][j] += A[i][k] * B[k][j];
						}
					}
				}
			}
		}

		return multiplication;
	}
}