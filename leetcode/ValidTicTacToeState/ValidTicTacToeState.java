/*
A Tic-Tac-Toe board is given as a string array board.
Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.
The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

Here are the rules of Tic-Tac-Toe:
Players take turns placing characters into empty squares (" ").
The first player always places "X" characters, while the second player always places "O" characters.
"X" and "O" characters are always placed into empty squares, never filled ones.
The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.

Example 1:
Input: board = ["O  ", "   ", "   "]
Output: false
Explanation: The first player always plays "X".

Example 2:
Input: board = ["XOX", " X ", "   "]
Output: false
Explanation: Players take turns making moves.

Example 3:
Input: board = ["XXX", "   ", "OOO"]
Output: false

Example 4:
Input: board = ["XOX", "O O", "XOX"]
Output: true

Note:
board is a length-3 array of strings, where each string board[i] has length 3.
Each board[i][j] is a character in the set {" ", "X", "O"}.

idea:
two invalid cases
1. countX == countO and 3 consecutive Xs
2. countX - countO == 1 and 3 consecutive 0s, game should be over, no such case countX > countO
*/

class ValidTicTacToeState {
    public boolean validTicTacToe(String[] board) {
		int m = board.length;
		int n = board[0].length();
		char[][] charBoard = new char[m][n];
		for (int i = 0; i < m; i++) {
			String row = board[i];
			for (int j = 0; j < n; j++) {
				charBoard[i][j] = row.charAt(j);
			}
		}

		int countX = 0;
		int countO = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				char c = charBoard[i][j];
				if (c == 'X') {
					countX++;
				} else if (c == 'O') {
					countO++;
				}
			}
		}
        // # of O cannot be bigger than # of X
        // # of X cannot be 2 more than # of O
        if (countO > countX || countX - countO > 1) {
            return false;
        }

		if (countX == countO) {
			if (!helperToDetectValid(charBoard, 'X')) {
				return false;
			}
		} else if (countX - countO == 1) {
			if (!helperToDetectValid(charBoard, 'O')) {
				return false;
			}
		}
        
        return true;
	}
	// check if row, column, diagonal and anti has a line of 3 consecutive character
	private boolean helperToDetectValid(char[][] board, char c) {
		for (int i = 0; i < 3; i++) {
			// row
			if (c == board[i][0] && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return false;
			}
			// column
			if (c == board[0][i] && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				return false;
			}
		}
		// diagonal
		if (c == board[0][0] && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return false;
		}
		// antiDiagonal
		if (c == board[0][2] && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return false;
		}

		return true;
	}
}