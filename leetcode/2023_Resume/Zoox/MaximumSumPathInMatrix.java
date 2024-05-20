/*
Given an n*m matrix, the task is to find the maximum sum of elements of cells starting from the cell (0, 0) to cell (n-1, m-1). 
However, the allowed moves are right, downwards or diagonally right, i.e, from location (i, j) next move can be (i+1, j), or, (i, j+1), or (i+1, j+1). Find the maximum sum of elements satisfying the allowed moves.
*/
import java.util.*;

class MaximumSumPathInMatrix {
	int[][] directions = new int[][] {
        {0, 1},
        {1, 0},
        {1, 1}
    };

    int max = 0;

	public static void main(String[] args) {
		// int[][] matrix = {
		// 	{100, -350, -200},
		// 	{-100, -300, 700}
		// };

		int[][] matrix = {
			{500, 100, 230},
           	{1000, 300, 100},
           	{200, 1000, 200}
        };

    	MaximumSumPathInMatrix eg = new MaximumSumPathInMatrix();
		System.out.println(eg.maxPathSum(matrix));
	}

	public void dfs(int[][] matrix, int i, int j, int currentVal) {
        int m = matrix.length;
        int n = matrix[0].length;

        if (i == m || j == n) {
        	return;
        }

        currentVal += matrix[i][j];

        max = Math.max(max, currentVal);

        for (int[] dir : directions) {
            int nextX = i + dir[0];
            int nextY = j + dir[1];
    
            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
            	dfs(matrix, nextX, nextY, currentVal);
            }
        }
        currentVal -= matrix[i][j];
    }

	public int maxPathSum(int matrix[][]) {
		dfs(matrix, 0, 0, 0);
		return max;
	}
}
