/*
Write a program to solve a Sudoku puzzle by filling the empty cells.
Empty cells are indicated by the character '.'.
You may assume that there will be only one unique solution.

  -----------------------
 |   8   | 4   2 |   6   |
 |   3 4 |       | 9 1   |
 | 9 6   |       |   8 4 |
  -----------------------
 |       | 2 1 6 |       |
 |       |       |       |
 |       | 3 5 7 |       |
  -----------------------
 | 8 4   |       |   7 5 |
 |   2 6 |       | 1 3   |
 |   9   | 7   1 |   4   |
  ----------------------- 

  -----------------------
 | 1 8 7 | 4 9 2 | 5 6 3 |
 | 5 3 4 | 6 7 8 | 9 1 2 |
 | 9 6 2 | 1 3 5 | 7 8 4 |
  -----------------------
 | 4 5 8 | 2 1 6 | 3 9 7 |
 | 2 7 3 | 8 4 9 | 6 5 1 |
 | 6 1 9 | 3 5 7 | 4 2 8 |
  -----------------------
 | 8 4 1 | 9 6 3 | 2 7 5 |
 | 7 2 6 | 5 8 4 | 1 3 9 |
 | 3 9 5 | 7 2 1 | 8 4 6 |
  -----------------------

idea:
1. direct thought
fill by choosing from 1 to 9, and check against 3 cases row, column, small block
all tests run on tmpBoard, if found one, then copy to board, and return

2. without using copy board
http://rleetcode.blogspot.com/2014/01/sudoku-solver-java.html
*/

public class SudokuSolver {
    // method 2, without extra space
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        solve(board);
    }

    public boolean solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValidSudoKu(board, i, j, num)) {
                            // fill the cell
                            board[i][j] = num;
                            // recursion
                            if (solve(board)) {
                                return true;
                            } else {
                                // backtrack with empty cell
                                board[i][j] = '.';
                            }
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    // after insert 'num' at row and col, is it valid
    // no duplication
    private boolean isValidSudoKu(char[][] board, int row, int col, char num) {
        int m = board.length;
        int n = board[0].length;
        // check column
        for (int i = 0; i < m; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // check row
        for (int j = 0; j < n; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }
        // check small block
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int newX = row / 3 * 3 + i;
                int newY = col / 3 * 3 + j;
                if (board[newX][newY] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // need extra space
    public void solveSudoku(char[][] board) {
        char[][] tmpBoard = new char[9][9];
        copy(board, tmpBoard);
        solveSudoku(tmpBoard, 0, board);
    }

    public void solveSudoku(char[][] tmpBoard, int c, char[][] board) {
        if (c == 81) {
            copy(tmpBoard, board);
            return;
        }

        int row = c / 9;
        int col = c % 9;
    
        if (tmpBoard[row][col] != '.') {
            solveSudoku(tmpBoard, c + 1, board);
        } else {
            for (int i = 0; i < 9; i++) {
                if (isValid(tmpBoard, row, col, i + 1)) {
                    tmpBoard[row][col] = (char)('0' + i + 1);
                    solveSudoku(tmpBoard, c + 1, board);
                    tmpBoard[row][col] = '.';
                }
            }
        }
    }
  
    private boolean isValid(char[][] board, int a, int b, int cell) {
        // check each column when one row is fixed
        // check each row when one column is fixed
        char c = (char) ('0' + cell);
        for (int i = 0; i < 9; i++) {
            if (board[a][i] == c || board[i][b] == c) {
                return false;
            }
        }
        // check each small block of nine blocks
        // a, b can locate which small block of the nine blocks   
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                int blockstartX = x / 3 * 3 + m;
                int blockstartY = y / 3 * 3 + n;
                if (board[blockstartX][blockstartY] == c) {
                    return false;
                }
            }
        }
        return true;
    }
  
    private void copy(char[][] arr1, char[][] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                arr2[i][j] = arr1[i][j];
            }
        }
    }
}
