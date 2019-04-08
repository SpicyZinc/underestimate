/*
Rotate Image
Given an n x n 2D matrix representing an image,
Rotate the image by 90 degrees (clockwise).
1  2  3
4  5  6
7  8  9
After rotation
7  4  1
8  5  2
9  6  3

Follow up:
Could you do this in-place?

idea:
1. 	need to create another matrix of same size as old matrix
	populate it in a way as below:
	1st row - last column 
	copyMatrix[j][n-i-1] = matrix[i][j]; 

2.  to realize clockwise 90 degrees rotate
	first, flip along with Anti-diagonal
	main diagonal - the diagonal of a square matrix running from the upper left entry to the lower right entry
	second, flip along with x axis
*/

public class RotateImage {
	public static void main(String[] args) {
		new RotateImage();
	}
	// constructor
	public RotateImage() {
		int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
		// int[][] matrix = {{1,2,3}, {4,5,6}};
		int n = matrix.length;
		// int n = matrix[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + "  ");
			}
			System.out.print("\n");
		}
		System.out.println("After rotation");
		rotate(matrix);				
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + "  ");
			}
			System.out.print("\n");
		}
	}

	// method 1, use extra space copyMatrix[][]
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		int[][] copyMatrix = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				copyMatrix[j][n - i - 1] = matrix[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = copyMatrix[i][j] ;
			}
		}
    }
	// method 2
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		// flip along with anti-diagonal
		// do not flip twice, twice would not change the matrix
		for (int i=0; i<n; i++) {
			for (int j=0; j<n-1-i; j++) {
				swap(matrix, i, j, n-1-j, n-1-i);
			}
		}
		// flip along with x axis
		for (int i=0; i<n/2; i++) {
			for (int j=0; j<n; j++) {
				swap(matrix, i, j, n-1-i, j);
			}
		}
	}
	// helper method swap()
	private void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
		int temp = matrix[i1][j1];
		matrix[i1][j1] = matrix[i2][j2];
		matrix[i2][j2] = temp;
	}
	// self written passed test
	public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                swap(matrix, new int[] {i,j}, new int[] {n-1-j, n-1-i});
            }
        }
        
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                swap(matrix, new int[] {i, j}, new int[] {n - 1 - i, j});
            }
        }
    }
    public void swap(int[][] matrix, int[] a, int[] b) {
        int temp = matrix[a[0]][a[1]];
        matrix[a[0]][a[1]] = matrix[b[0]][b[1]];
        matrix[b[0]][b[1]] = temp;
    }
	// method 3
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i=0; i<n/2; i++) {
			for (int j=i; j<n-1-i; j++) {
				swap(matrix, i, j, j, n-1-i);
				swap(matrix, n-1-j, i, n-1-i, n-1-j);
				swap(matrix, i, j, n-1-i, n-1-j);
			}
		}
	}
	// figure out how to flip anti-diagonal in a regular matrix
	public void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n-1-i; j++) {
        		swap(matrix, i, j, m-1-j, n-1-i);
        	}
        }
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		System.out.printf("%d  ", matrix[i][j]);
        	}
        	System.out.println();
        }
    }
    // swap for regular flip
    private void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
		int temp = matrix[i1][j1];
		matrix[i1][j1] = matrix[i2][j2];
		matrix[i2][j2] = temp;
	}
}