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
DFS and use a boolean array to keep track if one cell was visited before or not.

http://gongxuns.blogspot.com/2013/01/leetcodeword-search.html
failed case ["ABCE","SFES","ADEE"] "ABCESEEEFS"
*/

import java.util.*;

public class WordSearch {
	public static void main(String[] args) {
		new WordSearch();
	}
	public WordSearch() {
		char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
		System.out.println("SEE is in board ? " + exist(board, "SEE"));
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
        int m = board.length;
        int n = board[0].length;
        int[][] directions = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
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