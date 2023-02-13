/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once. 

For example,
Given board = [
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

idea:
从word的第一个char 开始 如果有match 就dfs() 下去
return true or return false

注意与第二方法对区别 这个是在dfs()里判断相等

DFS and use a boolean array to keep track if one cell was visited before or not.
in this case, set board[i][j] = 0 as a flag
*/

import java.util.*;

public class WordSearch {
    public static void main(String[] args) {
        WordSearch eg = new WordSearch();
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        System.out.println("SEE is in board ? " + eg.exist(board, "SEE"));
    }
    // Sat Jun 15 21:19:46 2019
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board == null) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, visited, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfs(char[][] board, int i, int j, boolean[][] visited, String word, int pos) {
        int[][] directions = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
        };

        int m = board.length;
        int n = board[0].length;

        visited[i][j] = true;

        if (word.charAt(pos) == board[i][j]) {
            // where to do base check, matters
            if (pos == word.length() - 1) {
                return true;
            }

            for (int[] dir : directions) {
                int nextX = i + dir[0];
                int nextY = j + dir[1];

                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY]) {
                    if (dfs(board, nextX, nextY, visited, word, pos + 1)) {
                        return true;
                    }
                }
            }
        }

        visited[i][j] = false;

        return false;
    }

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || word.length() == 0) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                if (c == word.charAt(0)) {
                    // set to be visited, use 0 as a flag
                    board[i][j] = 0;
                    if (dfs(board, word, i, j, 0)) {
                        return true;   
                    }
                    board[i][j] = c;
                }
            }
        }

        return false;
    }
    
    public boolean dfs(char[][] board, String word, int i, int j, int start) {
        if (start == word.length() - 1) {
            return true;
        }

        int[][] directions = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};

        int m = board.length;
        int n = board[0].length;

        for (int[] dir : directions) {
            int newX = i + dir[0];
            int newY = j + dir[1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && board[newX][newY] == word.charAt(start + 1)) {
                board[newX][newY] = 0;

                if (dfs(board, word, newX, newY, start + 1)) {
                    return true;
                }

                board[newX][newY] = word.charAt(start + 1);
            }
        }

        return false;
    }
}