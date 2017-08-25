/*
Valid Sudoku
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
Is a partially filled sudoku valid?

idea:
use hashset to store ever appearing elements in row, column, small block
which are all 9 elements

if contains(), return false;
if not contains, add this to set

another version see link as below:
http://blog.csdn.net/u010500263/article/details/18905027
*/
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
		for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
				if (!isValid(board, i, j)) {
					return false;
                }
			}
        }

        return true;          
    }
	
	private boolean isValid(char[][] board, int a, int b) {
		// check each column when one row is fixed
        Set<Character> contained = new HashSet<Character>();
        for (int j=0; j<9; j++) {
            if (contained.contains(board[a][j])) {
				return false;
            }
            if (board[a][j] > '0' && board[a][j] <= '9') {
                contained.add(board[a][j]);
            }
        }
		// check each row when one column is fixed
		contained = new HashSet<Character>();
        for (int i=0; i<9; i++) {
            if (contained.contains(board[i][b])) {
				return false;
            }
            if (board[i][b] > '0' && board[i][b] <= '9') {
                contained.add(board[i][b]);
            }
        }
		// check each small block of nine blocks
		// i, j can locate which small block of the nine blocks		
        contained = new HashSet<Character>();
        for (int m=0; m<3; m++) {
            for (int n=0; n<3; n++) {
                int x = a / 3 * 3 + m;
				int y = b / 3 * 3 + n;
                if (contained.contains(board[x][y])) {
					return false;
                }
				if (board[x][y] > '0' && board[x][y] <= '9') {
					contained.add(board[x][y]);
                }
            }  
        }
    
        return true;
    }

    // best version
    public boolean isValidSudoku(char[][] board) {
        Set<Character> ifContains = new HashSet<Character>();
        
        // check rows
        for (int i = 0; i < board.length; i++) {
            ifContains = new HashSet<Character>();
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.' && !ifContains.add(board[i][j])) {
                    return false;
                } 
            }
        }
        
        // check columns
        for (int j = 0; j < board[0].length; j++) {
            ifContains = new HashSet<Character>();
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] != '.' && !ifContains.add(board[i][j])) {
                    return false;
                } 
            }
        }
        
        // check 3 * 3 sub-box
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ifContains = new HashSet<Character>();
                for (int m = i * 3; m < i * 3 + 3; m++) {
                    for (int n = j * 3; n < j * 3 + 3; n++) {
                        if (board[m][n] != '.' && !ifContains.add(board[m][n])) {
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }
}