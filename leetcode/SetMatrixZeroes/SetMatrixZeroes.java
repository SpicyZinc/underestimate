/*
Set Matrix Zeroes
Given a m x n matrix, if an element is 0, set its entire row and column to 0. 
Do it in place.

idea:
The general idea is to use first row and col as storage, 6 for loops
不费 空间

1. search for zeros in first row and col to determine it's own status
2. search for zeros in other position to sign the first row and col
3. set zeroes in other positions according to first col and row
4. set zeroes for first row and col

利用1st row and column 先存下来最初状态
然后就是用它们标记
做完后 再看 最初状态 是不是 第一行和列也要set zero

费空间

1. find all cells which are zeros
2. loop through these cells
3. set repetitively whole row or whole column to be zero

*/
public class SetMatrixZeroes {
	// Fri May 24 16:27:08 2024
    public void setZeroes(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
						
		boolean firstRow = false;
		boolean firstColumn = false;
		
		// check first row and first column if there is zero, and mark boolean value
		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0) {
				firstColumn = true;
				break;
			}
		}
        for (int j = 0; j < n; j++) {
			if (matrix[0][j] == 0) {
				firstRow = true;
				break;
			}
		}
		
		// check if a cell is '0', use first row and first column to record for further use
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		// according to 1st row and column result, begin to set matrix to zeros
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;					
				}
			}
		}
		// lastly, to deal with 1st row and column according to boolean firstRow firstColumn
		for (int i = 0; i < m; i++) {
			if (firstColumn) {
				matrix[i][0] = 0;
			}
		}
		for (int j = 0; j < n; j++) {
			if (firstRow) {
				matrix[0][j] = 0;
			}
		}
    }


	public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<List<Integer>> positions = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> tmp = new ArrayList<>();
                if (matrix[i][j] == 0) {
                    tmp.add(i);
                    tmp.add(j);
                }
                positions.add(tmp);
            }
        }
        
        for (int k = 0; k < positions.size(); k++) {
            List<Integer> position = positions.get(k);
            setZero(position, matrix);
        }
    }
    
    private void setZero(List<Integer> position, int[][] matrix) {
        if (position.size() > 0) {
            int x = position.get(0);
            int y = position.get(1); 

            int m = matrix.length;
            int n = matrix[0].length;
            
            for (int i = 0; i < m; i++) {
                matrix[i][y] = 0;
            }
            
            for (int j = 0; j < n; j++) {
                matrix[x][j] = 0;
            }
        }
    }
}
