/*
Write an efficient algorithm that searches for a value in an m x n matrix.
This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

For example,
Consider the following matrix:
[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
 
idea:
serpentine like 2d array can use i * n + j method

How to apply binary search in 2D array
binary search in rows first to locate which row,
binary search in columns to locate cell based on the found row

build up a tmp int array to hold column 0 for all rows, binary search all rows to locate a row in which target lies in 
binary search on this tmp array first

then binary on the return value row's all elements
*/

public class Search2DMatrix {
	public static void main(String[] args) {
		int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
		
		Search2DMatrix eg = new Search2DMatrix();
		System.out.println("Is target in the Matrix: " + eg.searchMatrix(matrix, 20));
	}
	// treated as a sorted array, note this is a serpentine
	// note: left and right are equal, mid - 1 and mid + 1
	public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int x = mid / n;
            int y = mid % n;

            if (matrix[x][y] == target) {
                return true;
            }

            if (matrix[x][y] > target) {
                right = mid - 1; 
            } else {
                left = mid + 1;
            }
        }

        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;
		if (matrix[0][0] > target) return false;
		if (matrix[m - 1][n - 1] < target) return false;
		
		// all elements at column 0
		int[] firstColumn = new int[m];
		for (int i = 0; i < m; i++) {
            firstColumn[i] = matrix[i][0];
		}
		
		int r = binarySearch(firstColumn, target); // row
		if (r == -1) {
			return false;
		}
		int c = binarySearch(matrix[r], target); // column
		
		return matrix[r][c] == target;
	}
	// if all elements are >= target, return -1
	// return the index of biggest number which is < target
	private int binarySearch(int[] a, int target) {
		int start = 0;
		int end = a.length - 1;

		int result = -1;
		while (start <= end) {
			int mid = start + (end - start) / 2;

			if (a[mid] == target) {
				return mid;
			}

			if (a[mid] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
				
				result = mid;
			}
		}

		return result;
	}
}