/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Return 6.

idea:
http://www.cnblogs.com/lichen782/p/leetcode_maximal_rectangle.html

Method 1: direct
take any matrix[i][j] = 1 (i, j) as starting point to calculate the maxArea using helper
   
Method 2:
Use the "max area under a histogram" algorithm as a building block.
call largestRectangleArea() on each row, and int[] heights will be accumulated 1's

Method 3: preprocess the matrix by recording each row until (i, j) the number of consecutive 1s

Method 4:
1. build a 2D sum table
   DP common idea: and record cumulative sum to current position(i, j)
2. to position i,j, area(i, j) == area(i-1, j) + area(i, j-1) two neighbors, 
   because add one more diagonal common part which is area(i-1, j-1), minus it
   and plus current (i, j)
   
   see picture: diagonal is added twice, so it should be minus one time
   
   |-------|-------|
   |i-1,j-1|i-1,j  |
   |-------|-------|
   |i,j-1  | i,j   |
   |-------|-------|
   
3. process the sum table in n^4 time complexity
   i in [0, m]
   j in [0, n]
   x in [i, m]
   y in [j, n]
   find every cell in 2-D sum table
   any possible rectangular area == sum[i][j] = sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1] + (matrix[i][j] == '0' ? 0 : 1)
4. compare possible area with length * width to check if they are equal

*/
import java.util.*;

public class MaximalRectangle {
	public static void main(String[] args) {
		char[] zeroOrOne = {'0', '1'};		
		int m = 16;
		int n = 12;
		char[][] matrix = new char[m][n];
		Random aRandom = new Random();
		MaximalRectangle aTest = new MaximalRectangle();
		for (int i=0; i<m; i++) {
			System.out.println();
			for (int j=0; j<n; j++) {
				matrix[i][j] = zeroOrOne[aRandom.nextInt(2)];
				System.out.print(matrix[i][j] + "  ");
			}
		}
		System.out.print("\n");
		System.out.print("\n");
		System.out.println("\nMAX area is " + aTest.maximalRectangle(matrix));
		System.out.println("\nMAX area is " + aTest.maximalRectangle_improved(matrix));
	}
	// method 1, passed
	public int maximalRectangle(char[][] matrix) {
		int m = matrix.length;
        if (m == 0 ) {
            return 0;   
        }
        int n = matrix[0].length;
        if (n == 0) {
            return 0;
        }
		int maxArea = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == '1') {
                    maxArea = Math.max(maxArea, maxRectangle(matrix, i, j));
				}
			}
		}

		return maxArea;
	}
    // helper to get max rectangle area from some (1, 1) point
	private int maxRectangle(char[][] matrix, int row, int col) {
        int m = matrix.length;
        int n = matrix[0].length;

		int maxArea = 0;
		int minWidth = Integer.MAX_VALUE;
		for (int i = row; i < m && matrix[i][col] == '1'; i++) {
			int width = 0;
			while (width + col < n && matrix[i][width + col] == '1') {
				width++;
			}
			minWidth = Math.min(minWidth, width);
			maxArea = Math.max(maxArea, minWidth * (i - row + 1));
		}

		return maxArea;
	}

	// method 2
	public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix == null) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        int[] heights = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                }
                else {
                    heights[j] = 0;
                }
            }
            max = Math.max(max, largestRectangleArea(heights));
        }
        return max;
    }
    
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        int start = 0;
        while (start < heights.length) {
            int end = heights.length - 1;
            for (int i = start + 1; i <= end; i++) {
                if (heights[i - 1] > heights[i]) {
                    end = i - 1;
                    break;
                }
            }
            int minHeight = heights[end];
            for (int i = end; i >= 0; i--) {
                minHeight = Math.min(minHeight, heights[i]);
                max = Math.max(max, minHeight * (end - i + 1));
            }
            start = end + 1;
        }
        return max;
    }
    // method 3
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
		int n = matrix[0].length;
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
		    // how come I miss this
		    dp[i][0] = matrix[i][0] - '0';
			for (int j = 1; j < n; j++) {
				dp[i][j] = (matrix[i][j] == '1') ? (dp[i][j - 1] + 1) : 0;
			}
		}
		int maxRectangle = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int area = getMax(dp, i, j);
				maxRectangle = Math.max(area, maxRectangle);
			}
		}

		return maxRectangle;
    }
    // expand to up and down in term of rows
    private int getMax(int[][] dp, int row, int col) {
    	int height = 0;
    	int minWidth = dp[row][col]; // the number of consecutive 1s
    	// go up which means row number decreases
    	for (int i = row - 1; i >= 0; i--) {
    	   // should not 0, should break
    	   if (dp[i][col] >= minWidth) {
    	       height += 1;
    	   }
    	   else {
    	       break;
    	   }
    	}
    	// go down which means row number increases
    	for (int i = row; i < dp.length; i++) {
    	    // should not 0, should break
    		if (dp[i][col] >= minWidth) {
                height += 1;
            }
            else {
                break;
            }
    	}

    	return minWidth * height;
    }

    // method 4
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        // m rows, n columns
        int m = matrix.length;
		int n = matrix[0].length;
		// DP, 2D table
        int[][] sum = new int[m][n];
		// initialize [0][0]
        sum[0][0] = matrix[0][0] == '0' ? 0 : 1;
		// initialize first row
        for (int j = 1; j < n; j++) {
            sum[0][j] = sum[0][j-1] + (matrix[0][j] == '0' ? 0 : 1);
        }
		// initialize first column and the rest of matrix together
        for (int i = 1; i < m; i++) {
            sum[i][0] = sum[i-1][0] + (matrix[i][0] == '0' ? 0 : 1);
            for (int j = 1; j < n; j++) {
                sum[i][j] = sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1] + (matrix[i][j] == '0' ? 0 : 1);
            }
        }
        // now loop over all possible rectangles
		// note: opposite to area[][]
		// subtract two times of common part[i-1][j-1], must plus one time
        int max = 0; 
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = i; x < m; x++) {
                    for (int y = j; y < n; y++) {
                        int s = sum[x][y]
                            - (i>=1 ? sum[i-1][y] : 0)
                            - (j>=1 ? sum[x][j - 1] : 0)
                            + (i>=1 && j >=1 ? sum[i - 1][j - 1] : 0);
                        if ((s == (y-j+1) * (x-i+1)) && max < s) {
							max = s;
                        }
                    }
                }
            }
        } 

		return max;
    }
}