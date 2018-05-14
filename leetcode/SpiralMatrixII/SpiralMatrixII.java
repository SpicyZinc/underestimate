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
        
        int topRow = 0;
        int rightCol = n - 1;
        int bottomRow = n - 1;
        int leftCol = 0;
        
        int val = 1;
        while (val <= n * n) {
            // top
            for (int i = leftCol; i <= rightCol; i++) {
                matrix[topRow][i] = val++;
            }
            // right
            for (int i = topRow + 1; i <= bottomRow; i++) {
                matrix[i][rightCol] = val++;
            }
            // bottom
            for (int i = rightCol - 1; i >= leftCol; i--) {
                matrix[bottomRow][i] = val++;
            }
            // left
            for (int i = bottomRow - 1; i >= topRow + 1; i--) {
                matrix[i][leftCol] = val++;
            }
            
            topRow++;
            rightCol--;
            bottomRow--;
            leftCol++;
        }
        
        return matrix;
    }
}