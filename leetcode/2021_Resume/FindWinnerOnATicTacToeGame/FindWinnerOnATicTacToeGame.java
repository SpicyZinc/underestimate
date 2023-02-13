/*

Tic-tac-toe is played by two players A and B on a 3 x 3 grid.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares (" ").
The first player A always places "X" characters, while the second player B always places "O" characters.
"X" and "O" characters are always placed into empty squares, never on filled ones.
The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
Given an array moves where each element is another array of size 2 corresponding to the row and column of the grid where they mark their respective character in the order in which A and B play.

Return the winner of the game if it exists (A or B), in case the game ends in a draw return "Draw", if there are still movements to play return "Pending".

You can assume that moves is valid (It follows the rules of Tic-Tac-Toe), the grid is initially empty and A will play first.


Example 1:
Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
Output: "A"
Explanation: "A" wins, he always plays first.
"X  "    "X  "    "X  "    "X  "    "X  "
"   " -> "   " -> " X " -> " X " -> " X "
"   "    "O  "    "O  "    "OO "    "OOX"

Example 2:
Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
Output: "B"
Explanation: "B" wins.
"X  "    "X  "    "XX "    "XXO"    "XXO"    "XXO"
"   " -> " O " -> " O " -> " O " -> "XO " -> "XO " 
"   "    "   "    "   "    "   "    "   "    "O  "

Example 3:
Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
Output: "Draw"
Explanation: The game ends in a draw since there are no moves to make.
"XXO"
"OOX"
"XOX"

Example 4:
Input: moves = [[0,0],[1,1]]
Output: "Pending"
Explanation: The game has not finished yet.
"X  "
" O "
"   "

Constraints:
1 <= moves.length <= 9
moves[i].length == 2
0 <= moves[i][j] <= 2
There are no repeated elements on moves.
moves follow the rules of tic tac toe.

idea:
key to this problem
is3ConsecutiveChars()
*/

class FindWinnerOnATicTacToeGame {
    public String tictactoe(int[][] moves) {
        char[][] board = {
            {'0', '0', '0'},
            {'0', '0', '0'},
            {'0', '0', '0'}
        };
        // Populate the board
        for (int i = 0; i < moves.length; i++) {
            int[] move = moves[i];
            int x = move[0];
            int y = move[1];

            if (i % 2 == 0) {
                board[x][y] = 'X';
            } else {
                board[x][y] = 'O';
            }
        }

        boolean aWins = is3ConsecutiveChars(board, 'X');
        boolean bWins = is3ConsecutiveChars(board, 'O');
        if (aWins) {
            return "A";
        }
        if (bWins) {
            return "B";
        }

        if (!aWins && !bWins && moves.length == 9) {
            return "Draw";
        }

        return "Pending";
    }

    public boolean is3ConsecutiveChars(char[][] board, char c) {
        for (int i = 0; i < 3; i++) {
            // row
            if (c == board[i][0] && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
            // column
            if (c == board[0][i] && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        // diagonal
        if (c == board[0][0] && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        // antiDiagonal
        if (c == board[0][2] && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }
}
    