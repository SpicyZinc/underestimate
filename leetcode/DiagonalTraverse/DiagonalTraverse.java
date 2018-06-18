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
https://www.cnblogs.com/grandyang/p/6414461.html

the regularity index row + col == sum.
sum is some constant looping from 0 to totalRow + totalCol

Second, the boundary of row and col is either sum or four edges.
Four edges correspond to row == 0 || row == totalRow - 1 || col == 0 || col == totalCol - 1
*/
public class DiagonalTraverse {
	public int[] findDiagonalOrder(int[][] matrix) {
        int r = matrix.length;
        if (r == 0) {
            return new int[0];
        }
        int c = matrix[0].length;

        int[] res = new int[r * c];
        int idx = 0;
        for (int sum = 0; sum <= r + c; sum++) {
            int row, col;
            if (sum % 2 == 0) {                               
                row = Math.min(sum, r-1);                    
                col = sum - row;
                while (row >= 0 && col < c) {
                    res[idx++] = matrix[row--][col++];  
                }
            }
            else {
                col = Math.min(sum, c-1);                    
                row = sum - col;
                while (col >= 0 && row < r) {                  
                    res[idx++] = matrix[row++][col--];
                }
            }
        }

        return res;
    }

    // [0,0] -> [0,1],[1,0] -> [2,0],[1,1],[0,2] -> [1,2],[2,1] -> [2,2]
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[] diagnoal = new int[m * n];
        int idx = 0;
        for (int i = 0; i < m + n - 1; i++) {
            int low = Math.max(0, i - n + 1);
            int high = Math.min(m - 1, i);
            if (i % 2 == 0) {
                for (int j = high; j >= low; j--) {
                    diagnoal[idx++] = matrix[j][i - j];
                }
            } else {
                for (int j = low; j <= high; j++) {
                    diagnoal[idx++] = matrix[j][i - j];   
                }
            }
        }
        
        return diagnoal;
    }

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[] diagnoal = new int[m * n];
        int idx = 0;
        for (int k = 0; k < m + n - 1; k++) {
            int step = 1 - 2 * (k % 2 == 0 ? 1 : 0);
            int ii = (m - 1) * (k % 2 == 0 ? 1 : 0);
            int jj = (n - 1) * (k % 2 == 0 ? 1 : 0);
            for (int i = ii; i >= 0 && i < m; i += step) {
                for (int j = jj; j >= 0 && j < n; j += step) {
                    if (i + j == k) {
                        diagnoal[idx++] = matrix[i][j];
                    }
                }
            }
        }

        return diagnoal;
    }
}
