/*
Let's play the minesweeper game!

You are given a 2D char matrix representing the game board.
'M' represents an unrevealed mine,
'E' represents an unrevealed empty square,
'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines,
digit ('1' to '8') represents how many mines are adjacent to this revealed square,
and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'),
return the board after revealing this position according to the following rules:
If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed,
then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed,
then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.

Example 1:
Input:
[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output:
[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]


Example 2:
Input:
[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output:
[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Note:
The range of the input matrix's height and width is [1,50].
The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
The input board won't be a stage when game is over (some mines have been revealed).
For simplicity, not mentioned rules should be ignored in this problem. For example,
you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.

idea:
direct, follow the rule using DFS
note 'E', keep DFS, not 'E', return
完全按题意来 但是 我不明白哪里来的 bomb, 没有炸弹啊
*/

public class Minesweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        // first time hit the mine, set it to be 'X', game is over
        // 只要求 看 一次click后的结果
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        dfs(board, x, y);
        return board;
    }
    
    public void dfs(char[][] board, int x, int y) {
        int[][] directions = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            // {0, 0},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1},
        };
        int m = board.length;
        int n = board[0].length;
        // why not equal to 'E'? still it is rule
        // The click position will only be an unrevealed square ('M' or 'E')
        if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] != 'E') {
            return;
        }
        int num = getNumOfBombs(board, x, y);
        if (num == 0) {
            // based on definition, has no adjacent (above, below, left, right, and all 4 diagonals) mines
    		board[x][y] = 'B';
    		// go to other 8 directions
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                dfs(board, newX, newY);
            }
        } else {
            board[x][y] = (char) (num + '0');
        }
    }
    
    public int getNumOfBombs(char[][] board, int x, int y) {
        int[][] directions = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 0}, // if x, y is 'M', return already
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1},
        };
        int m = board.length;
        int n = board[0].length;
        int num = 0;
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                continue;
            }
            // 周围 发现的 未发现的 mines
            if (board[newX][newY] == 'M' || board[newX][newY] == 'X') {
                num++;
            }
        }

        return num;
    }
}
