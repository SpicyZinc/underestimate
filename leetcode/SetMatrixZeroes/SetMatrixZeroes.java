/*
Set Matrix Zeroes
Given a m x n matrix, if an element is 0, set its entire row and column to 0. 
Do it in place

some English knowledge, words with 'o', plural format:
"videos", "tattoos", "kangaroos", "studios", and "zoos"

idea:

The general idea is to use first row and col as storage, 6 for loops

1. search for zeros in first row and col to determine it's own status
2. search for zeros in other position to sign the first row and col
3. set zeroes in other positions according to first col and row
4. set zeroes for first row and col
*/
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
						
		boolean firstRow = false;
		boolean firstColumn = false;
		
		// check first row and first column if there is zero, and mark boolean value
		for (int i=0; i<m; i++) {
			if (matrix[i][0] == 0) {
				firstColumn = true;
				break;
			}
		}
        for (int j=0; j<n; j++) {
			if (matrix[0][j] == 0) {
				firstRow = true;
				break;
			}
		}
		
		// check if a cell is '0', use first row and column to record for further use
		for (int i=1; i<m; i++) {
			for (int j=1; j<n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		// according to 1st row and column result, begin to set matrix to zeros
		for (int i=1; i<m; i++) {
			for (int j=1; j<n; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;					
				}
			}
		}
		// lastly, to deal with 1st row and column according to boolean firstRow firstColumn
		for (int i=0; i<m; i++) {
			if (firstColumn) {
				matrix[i][0] = 0;
			}
		}
		for (int j=0; j<n; j++) {
			if (firstRow) {
				matrix[0][j] = 0;
			}
		}
    }
}

/*
self version passed test directly

idea:
1. find all cells which are zeros
2. loop through these cells
3. set repetitively whole row or whole column to be zero
*/
public class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ArrayList<Integer> tmp = new ArrayList<Integer>();
                if (matrix[i][j] == 0) {
                    tmp.add(i);
                    tmp.add(j);
                }
                ret.add(tmp);
            }
        }
        
        for (int k = 0; k < ret.size(); k++) {
            ArrayList<Integer> holder = ret.get(k);
            setZero(holder, matrix);
        }
    }
    
    private void setZero(ArrayList<Integer> holder, int[][] matrix) {
        if (holder.size() > 0) {
            int x = holder.get(0);
            int y = holder.get(1); 

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