/*
Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]
Explanation:

Note:
The total number of elements of the given matrix will not exceed 10,000.

idea:
just find the regularity
index row + col == sum. sum is some constant looping from 0 to totalRow + totalCol
Second, the boundary of row and col is either sum or four edges. Four edges correspond to row == 0 || row == totalRow - 1 || col == 0 || col == totalCol - 1
*/
public class DiagonalTraverse {
	public int[] findDiagonalOrder(int[][] matrix) {
        int r = matrix.length;
        if (r == 0) {
    		return new int[0];
    	}
        int c = matrix[0].length;
        int[] res = new int[r * c];
        boolean flip = true;
        int idx = 0;
        for (int sum = 0; sum <= r + c; sum++) {
            int row, col;
            if (flip == true) {                               
                row = Math.min(sum, r-1);                    
                col = sum - row;
                while (row >= 00 && col < c) {
                    res[idx++] = matrix[row--][col++];  
                }
            }
            else {
                col = Math.min(sum, c-1);                    
                row = sum - col;
                while (col >= 00 && row < r) {                  
                    res[idx++] = matrix[row++][col--];
                }
            }
            flip = !flip;           
        }
        return res;
    }

	// 2. self written
    public int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 0) {
    		return new int[0];
    	}

        int[] result = new int[m * n];
        int idx = 0;
        boolean flag = true;
        for (int i = 1; i < m + n; i++) {
        	int num = i;
        	while (num > 0) {
    			if (flag) {
    				for (int col = 0; col < n; col++) {
        				int row = (num - 1) - col;
        				if (row >= 0 && row < m) {
        					result[idx++] = matrix[row][col];
        				}
    				}
    			} else {
    				for (int row = 0; row < m; row++) {
        				int col = (num - 1) - row;
        				if (col >= 0 && col < n) {
        					result[idx++] = matrix[row][col];
        				}
    				}
    			}
    			num--;
        	}
        	flag = !flag;
    	}
    	return result;
    }
}