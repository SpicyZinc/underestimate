/**
idea:
Treat 2-D matrix as a 1-D array, since it is sorted.
then mid is divided by col to get current_row, is mod to get current_col
*/
public class ReWrite {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
		int row = matrix.length;
		int col = matrix[0].length;
        if(matrix == null || row == 0 || col == 0)
			return false;
		
		int start = 0;
		int end = col * row - 1;
		// remember "<="
		while(start <= end) {
			int mid = (start + end) / 2;
			int midX = mid / col;
			int midY = mid % col;
			if(matrix[midX][midY] == target)
				return true;
			else if(matrix[midX][midY] > target) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		return false;
    }
}