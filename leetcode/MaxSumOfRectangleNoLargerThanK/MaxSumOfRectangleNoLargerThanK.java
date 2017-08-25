/*
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

idea:
related to max sum subarray

https://www.cnblogs.com/grandyang/p/5617660.html
1. brute force
枚举起始行, 枚举结束行, 枚举起始列, 枚举终止列O(m^2 * n^2)

2. using sum query
https://discuss.leetcode.com/topic/48923/2-accepted-java-solution/2

sum(i, j) represents sum from (0, 0) to (i,j) inclusive
four layers loop
first two loops to get sum[i][j]
meanwhile, second two loops to get the all possible sub-matrix sum

3. use treeset
http://www.programcreek.com/2014/08/leetcode-max-sum-of-rectangle-no-larger-than-k-java/

4. dp
https://discuss.leetcode.com/topic/49625/java-233mm-solution-with-dynamic-programming
*/

public class MaxSumOfRectangleNoLargerThanK {
	// don't know why not passed
    public int maxSumSubmatrix(int[][] matrix, int k) {
		if (matrix.length == 0 || matrix == null) {
			return 0;
		}

		int m = matrix.length;
		int n = matrix[0].length;
		int res = Integer.MIN_VALUE;
		int[][] sum = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int value = matrix[i][j];
				if (i >= 1) {
					value += sum[i - 1][j];
				}
				if (j >= 1) {
					value += sum[i][j - 1];	
				}
				if (i >= 1 && j >= 1) {
					value -= sum[i - 1][j - 1];	
				}
				sum[i][j] = value;
				for (int r = 0; r <= i; r++) {
					for (int c = 0; c <= j; c++) {
						int area = sum[i][j];
						if (r >= 1) {
							area -= sum[r-1][c];
						}
						if (c >= 1) {
							area -= sum[r][c-1];
						}
						if (r >= 1 && c >= 1) {
							area += sum[r-1][c-1];	
						}
						if (area <= k) {
							res = Math.max(area, res);
						}
					}
				}
			}
		}
    
    	return res;
    }
    // passed OJ
    public int maxSumSubmatrix(int[][] matrix, int k) {
		if (matrix.length == 0 || matrix == null) {
			return 0;
		}

		int m = matrix.length;
		int n = matrix[0].length;
		int res = Integer.MIN_VALUE;
		int[][] sum = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int value = matrix[i][j];
				if (i >= 1) {
					value += sum[i - 1][j];
				}
				if (j >= 1) {
					value += sum[i][j - 1];	
				}
				if (i >= 1 && j >= 1) {
					value -= sum[i - 1][j - 1];	
				}
				sum[i][j] = value;
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int r = i; r < m; r++) {
					for (int c = j; c < n; c++) {
						int area = sum[r][c];
						if (i >= 1) {
							area -= sum[i-1][c];
						}
						if (j >= 1) {
							area -= sum[r][j-1];
						}
						if (i >= 1 && j >= 1) {
							area += sum[i-1][j-1];	
						}
						if (area <= k) {
							res = Math.max(area, res);
						}
					}
				}
			}
		}
    
    	return res;
    }
}
