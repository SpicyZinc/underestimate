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
		int m = 3;
		int n = 4;
		int[][] grid = new int[m][n];
		for (int i = 0; i < m; i++) {
			System.out.println();
			for (int j = 0; j < n; j++){
				grid[i][j] = 1 + (m*n)*i + j;
				System.out.printf("%-3d  ", grid[i][j]);
			}
		}		
		System.out.println("\n");
		
		SpiralMatrix aTest = new SpiralMatrix();
		ArrayList<Integer> spiral = aTest.spiral_print(grid);
		
		System.out.print("[  ");
		for (int i=0; i<spiral.size(); i++) {
			System.out.print(spiral.get(i) + "  ");
		}	
		System.out.print("]");
	}

	// self written version passed test, borrowed from SpiralMatrixII
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (matrix.length == 0 || matrix == null) {
			return ret;
		}

		int r = matrix.length;
        int c = matrix[0].length;

        int top = 0;
        int right = c - 1;
        int bottom = r - 1;
        int left = 0;

        int i;

        while (ret.size() < r * c) {
        	// top
        	for (i = left; i <= right; i++) {
        		ret.add(matrix[top][i]);
        	}
        	// right
        	for (i = top+1; i <= bottom; i++) {
        		ret.add(matrix[i][right]);
        	}
        	// bottom
        	if (top < bottom) {
        		for (i = right - 1; i >= left; i--) {
        			ret.add(matrix[bottom][i]);
        		}
        	}
        	// left
        	if (left < right) {
        		for (i = bottom - 1; i >= top + 1; i--) {
        			ret.add(matrix[i][left]);
        		}
        	}

        	top++;
        	right--;
        	bottom--;
        	left++;
        }

        return ret;
    }
    // self written version passed test, a little change from the above method
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int row = 0;
        int col = 0;
        
        int i = 0;
        
        while (row < m && col < n) {
            // first row
            for (i = col; i < n; i++) {
                res.add(matrix[row][i]);
            }
            row++;
            // last column
            for (i = row; i < m; i++) {
                res.add(matrix[i][n-1]);
            }
            n--;
            // bottom row
            if (row < m) {
                for (i = n-1; i >= col; i--) {
                    res.add(matrix[m-1][i]);
                }
                m--;
            }
            // first column
            if (col < n) {
                for (i = m-1; i >= row; i--) {
                    res.add(matrix[i][col]);
                }
                col++;
            }
        }
        
        return res;
    }
	// recursion
    public ArrayList<Integer> spiralPrint(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<Integer>();
        }

        return spiralPrint(matrix, 0, 0, matrix.length, matrix[0].length);
    }
	// m number of rows
	// n number of columns
    public ArrayList<Integer> spiralPrint(int[][] matrix, int x, int y, int m, int n)  {
        ArrayList<Integer> seq = new ArrayList<Integer>();
        if (m <= 0 || n <= 0) {
            return seq;
        } 
        // be careful with the 4 corners, do not duplicate them
		
        // top side
		// row starts and stops at x
        for (int j = y; j < y+n; j++) {
            seq.add(matrix[x][j]);
        }
        // right side
		// i starts at x+1 and stops at x+m-1-1
        for (int i = x+1; i < x+m-1; i++) {
            seq.add(matrix[i][y+n-1]);
        } 
        // bottom side
		// check if only one row
		// more than one row can go next
		// row starts at x+m-1
        if (m > 1) {
            for (int j = y+n-1; j >= y; j--) {
                seq.add(matrix[x+m-1][j]);
            }
        } 
        // left side
		// check if only one column
		// more than one column can go next
        if (n > 1) {
            for (int i = x+m-2; i > x; i--) {
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

