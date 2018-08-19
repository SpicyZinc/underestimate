/*
Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

A partially filled sudoku which is valid.
The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:
Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true

Example 2:
Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being 
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.

Note:
A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.

idea:
use hashset to store ever appearing elements in row, column, small block
which are all 9 elements

if contains(), return false;
if not contains, add this to set

another version see link as below:
http://blog.csdn.net/u010500263/article/details/18905027
*/
public class ValidSudoku {
    // 07/28/2018
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