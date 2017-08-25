/*
According to the Wikipedia's article: 
"The Game of Life, also known simply as Life, is a cellular automaton 
devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) 
using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: 
You cannot update some cells first and then use their updated values to update other cells.

In this question, we represent the board using a 2D array. 
In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. 
How would you address these problems?

idea:
first of all, have to define
State transitions
0 : dead to dead
1 : live to live
2 : live to dead
3 : dead to live

1. define eight directions
|-------|------|----- |
|(1,-1) |(0,0) |(1,1) |
|-------|------|----- |
|(0,-1) |(0,0) |(0,1) |
|-------|------|----- |
|(-1,-1)|(0,0) |(-1,1)|
|-------|------|----- |
2. for each cell, check all 8 directions to calculate how many lives
if cell live == 1 or == 2, live++
update each cell to be 3 or 2
3. loop again, module 2
*/

public class GameOfLife {
    public void gameOfLife(int[][] board) {
        int[][] directions = {
            {1, 1},
            {1, -1},
            {1, 0},
            {0, 1},
            {0, -1},
            {-1, 1},
            {-1, -1},
            {-1, 0},
        };
        
        int m = board.length;
        int n = board[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = 0;
                for (int[] dir : directions) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        // board[x][y] == 2 means from live to dead, so count it as live
                        if (board[x][y] == 1 || board[x][y] == 2) {
                            lives++;
                        }
                    }
                }
                // dead before && live == 3, live now
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 3;
                }
                // live before && (live < 2 || live > 3), die now
                if (board[i][j] == 1 && (lives < 2 || lives > 3)) {
                    board[i][j] = 2;
                }
            }
        }
        
        // re-calculate to get new state, module 2
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] %= 2;
            }
        }
    }
}