/*
idea:
http://blog.unieagle.net/2012/11/07/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Avalid-sudoku/

can use a boolean array of length 10
array[0] = # of '.';
array['1'] = 1
...
array['9'] = 1
if (array['1 to 9'] == 1)
	duplicate, return false
	
use hashset instead

why use hashset partially passed?
*/

public class ValidSudokuII {
    public boolean isValidSudoku(char[][] board) {
		int rows = board.length;        
        int cols = board[0].length;
		if(rows != 9) 
			return false;
        if(cols != 9) 
			return false;
			
		// check each column when one row is fixed		
        Set<Character> contained = new HashSet<Character>();
		for(int ir=0; ir<rows; ir++) {
			for(int j=0; j<9; j++) {
				if(contained.contains(board[ir][j])) 
					return false;
				if(board[ir][j] > '0' && board[ir][j] <= '9')
					contained.add(board[ir][j]);
			}
		}
        
		// check each row when one column is fixed
		contained = new HashSet<Character>();
		for(int jc=0; jc<cols; jc++) {
			for(int i=0; i<9; i++) {
				if(contained.contains(board[i][jc])) 
					return false;
				if(board[i][jc] > '0' && board[i][jc] <= '9')
					contained.add(board[i][jc]);
			}
		}
        
		// check each small block of nine blocks
		// i, j can locate which small block of the nine blocks		
        contained = new HashSet<Character>();
		for(int ir=0; ir<rows; ir++) {
			for(int jc=0; jc<cols; jc++) {
				for(int m=0; m<3; m++) {
					for(int n=0; n<3; n++) {
						int x = ir / 3 * 3 + m;
						int y = jc / 3 * 3 + n;
						if(contained.contains(board[x][y])) 
							return false;
						if(board[x][y] > '0' && board[x][y] <= '9')
							contained.add(board[x][y]);
					}  
				}
			}
		}    
        return true;		
    }
	// this version works
	public boolean isValidSudoku(char[][] board) {
		int rows = board.length;
        if(9 != rows) return false;
        int cols = board[0].length;
        if(9 != cols) return false;
        // 1. check duplications in each row
        int[] dup = new int[10]; // hash for 1 to 9
        for(int ir = 0; ir < rows; ir++) {
            for(int i = 0; i <= 9; i++)
                dup[i] = 0;
            for(int i = 0; i < 9; i++){
                char c = board[ir][i];
                if(c == '.')
                    dup[0]++;
                else {
                    int hash = c - '0';
                    if(dup[hash] == 1) {
                        return false;
                    } else {
                        dup[hash]++;
                    }
                }
            }
        }
        // 2. check duplications in each cols
        for(int ic = 0; ic < cols; ic++) {
            for(int i = 0; i <= 9; i++)
                dup[i] = 0;
            for(int i = 0; i < 9; i++){
                char c = board[i][ic];
                if(c == '.')
                    dup[0]++;
                else {
                    int hash = c - '0';
                    if(dup[hash] == 1) {
                        return false;
                    } else {
                        dup[hash]++;
                    }
                }
            }
        }
        // 3. check duplications in each 3 * 3 grid
        for(int ir = 0; ir < rows; ir += 3) {
            for(int ic = 0; ic < cols; ic += 3) {
                for(int i = 0; i <= 9; i++)
                    dup[i] = 0;
                for(int iir = 0; iir < 3; iir++) {
                    for(int iic = 0; iic < 3; iic++) {
                        char c = board[ir + iir][ic + iic];
                        if(c == '.')
                            dup[0]++;
                        else {
                            int hash = c - '0';
                            if(dup[hash] == 1) {
                                return false;
                            } else {
                                dup[hash]++;
                            }
                        }
                    }
                }
            }
        }
	
        return true;
	}
}