/*
Given a matrix of m x n elements (m rows, n columns), 
return all elements of the matrix in spiral order.

For example,
Given the following matrix:
[
	[ 1, 2, 3 ],
	[ 4, 5, 6 ],
	[ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].

idea:
Imagine peeling an onion

	.->.->.->. 
   |.  .  .  .__
   |.  .  .  . |
 __|.  .  .  . |
	.<-.<-.  . |
		   |    
	

Clockwise peel-off 
First, peel off the outer-layer skin. 
After the first layer is peeled off, there is another layer to peel. (where recursion applies)
So peel off the remaining onion again until there is no more to peel, which is where you stop.

Attention
A[m][0]
A[0][n]
is 1D array expressed in 2D array

Thus, top and right can be enough to print all elements in a 2-D array
only row and column length > 1 need to check bottom and left, otherwise not necessary to go to next step
*/

import java.util.*;

public class SpiralMatrix {
	public static void main(String[] args) {
		int[][] grid = {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9},
		};
		
		SpiralMatrix eg = new SpiralMatrix();
		List<Integer> spiral = eg.spiralOrder(grid);
		System.out.println(spiral);
	}
	// Sun May 19 00:11:16 2019
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<>();

		if (matrix.length == 0 || matrix[0].length == 0) {
			return list;
		}

		int m = matrix.length;
		int n = matrix[0].length;

		int top = 0;
		int right = n - 1;
		int bottom = m - 1;
		int left = 0;

		while (list.size() < m * n) {
			// top
			for (int i = left; i <= right; i++) {
				list.add(matrix[top][i]);
			}
			top++;
			// right
			for (int i = top; i <= bottom; i++) {
				list.add(matrix[i][right]);
			}
			right--;
			// bottom
			if (bottom >= top) {
				for (int i = right; i >= left; i--) {
					list.add(matrix[bottom][i]);
				}
				bottom--;
			}
			// left
			if (left <= right) {
				for (int i = bottom; i >= top; i--) {
					list.add(matrix[i][left]);
				}
				left++;
			}
		}

		return list;
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<>();
		
		if (matrix.length == 0 || matrix[0].length == 0) {
			return list;
		}
		
		int m = matrix.length;
		int n = matrix[0].length; 
		
		int top = 0;
		int left = 0;
		int bottom = m - 1;
		int right = n - 1;

		while (top <= bottom && left <= right) {
			// top
			for (int i = left; i <= right; i++) {
				list.add(matrix[top][i]);
			}
			top++;
			// right
			for (int i = top; i <= bottom; i++) {
				list.add(matrix[i][right]);
			}
			right--;
			// bottom
			if (top <= bottom) {
				for (int i = right; i >= left; i--) {
					list.add(matrix[bottom][i]);
				}
				bottom--;
			}
			// left
			if (left <= right) {
				for (int i = bottom; i >= top; i--) {
					list.add(matrix[i][left]);
				}
				left++;
			}
		}

		return list;
	}

	// recursion
	public List<Integer> spiralPrint(int[][] matrix) {
		if (matrix.length == 0) {
			return new ArrayList<Integer>();
		}

		return spiralPrint(matrix, 0, 0, matrix.length, matrix[0].length);
	}
	// m number of rows
	// n number of columns
	public List<Integer> spiralPrint(int[][] matrix, int x, int y, int m, int n)  {
		List<Integer> seq = new ArrayList<>();
		if (m <= 0 || n <= 0) {
			return seq;
		} 
		// be careful with the 4 corners, do not duplicate them
		
		// top side
		// row starts and stops at x
		for (int j = y; j < y + n; j++) {
			seq.add(matrix[x][j]);
		}
		// right side
		// i starts at x+1 and stops at x+m-1-1
		for (int i = x + 1; i < x + m - 1; i++) {
			seq.add(matrix[i][y + n - 1]);
		} 
		// bottom side
		// check if only one row
		// more than one row can go next
		// row starts at x+m-1
		if (m > 1) {
			for (int j = y + n - 1; j >= y; j--) {
				seq.add(matrix[x + m - 1][j]);
			}
		} 
		// left side
		// check if only one column
		// more than one column can go next
		if (n > 1) {
			for (int i = x + m - 2; i > x; i--) {
				seq.add(matrix[i][y]);
			}
		}
		// inside layer
		// recursively call spiralPrint()
		// top left starts form (x+1, y+1)
		// row and column length turn into m-2 and n-2
		// because the outmost layer reduce both row and column length by 2, 
		// for row's length top and bottom -1 each
		// for column's length right and left -1 each
		seq.addAll(spiralPrint(matrix, x+1, y+1, m-2, n-2));
 
		return seq;
	}
}

