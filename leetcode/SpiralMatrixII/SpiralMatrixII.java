/*
Given an integer n, 
generate a square matrix filled with elements from 1 to n^2 in spiral order.

For example,
Given n = 3,
You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

idea:
http://my.oschina.net/jdflyfly/blog/284502
based on the order as 
top right bottom left 

while (num <= n * n) {
	update top right bottom left

	.->.->.->. 
   |.  .  .  .__
   |.  .  .  . |
 __|.  .  .  . |
    .<-.<-.  . |
   		   |	
}

one thing to note:
left < right
top  < bottom

O(n)
*/
public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        if (n <= 0) {
        	return matrix;
        }

        int top = 0;
        int right = n - 1;
        int bottom = n - 1;
        int left = 0;

        int num = 1;
        int i;

        while (num <= n * n) {
        	// top
        	for (i = left; i <= right; i++) {
        		matrix[top][i] = num++;
        	}
        	// right
        	for (i = top+1; i <= bottom; i++) {
        		matrix[i][right] = num++;
        	}
        	// bottom
        	if (top < bottom) {
        		for (i = right - 1; i >= left; i--) {
        			matrix[bottom][i] = num++; 
        		}
        	}
        	// left
        	if (left < right) {
        		for (i = bottom - 1; i >= top + 1; i--) {
        			matrix[i][left] = num++; 
        		}
        	}

        	top++;
        	right--;
        	bottom--;
        	left++;
        }

        return matrix;
    }
    // self written version passed test
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1;
        
        int top = 0;
        int right = n - 1;
        int bottom = n - 1;
        int left = 0;
        
        int i = 0;
        while (num <= n * n) {
            for (i = left; i <= right; i++) {
                res[top][i] = num++;
            }
            for (i = top + 1; i <= bottom; i++) {
                res[i][right] = num++;
            }
            if (top < bottom) {
                for (i = right - 1; i >= left; i--) {
                    res[bottom][i] = num++;
                }
            }
            if (left < right) {
                for (i = bottom - 1; i >= top + 1; i--) {
                    res[i][left] = num++;
                }
            }
            
            top++;
            right--;
            bottom--;
            left++;
        }
        
        return res;
    }
    // self written after 2 years
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int val = 1;
        
        int top = 0;
        int right = n - 1;
        int bottom = n - 1;
        int left = 0;
        
        
        while (val <= n * n) {
            // top
            for (int i = left; i <= right; i++) {
                matrix[top][i] = val++;
            }
            // right
            for (int i = top + 1; i <= bottom; i++) {
                matrix[i][right] = val++;
            }
            // bottom
            for (int i = right - 1; i >= left; i--) {
                matrix[bottom][i] = val++;
            }
            // left
            for (int i = bottom - 1; i >= top + 1; i--) {
                matrix[i][left] = val++;
            }
            top++;
            right--;
            bottom--;
            left++;
        }
        
        return matrix;
    }
}