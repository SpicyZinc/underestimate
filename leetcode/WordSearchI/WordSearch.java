/*
* idea: lintcode version of Word Search
* 
*/

public class WordSearch {
	/**
	 * @param board: A list of lists of character
	 * @param word: A string
	 * @return: A boolean
	 */
	public boolean exist(char[][] board, String word) {
		if (board == null || board.length == 0) {
			return false;
		}

		int m = board.length;
		int n = board[0].length;

		boolean visited[][] = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (search(word, 0, board, i, j, visited)) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean search(String word, int index, char[][] board, int i, int j, boolean[][] visited) {
		int m = board.length;
		int n = board[0].length;
		int[][] directions = new int[][] {
			{1,0},
			{-1,0},
			{0,1},
			{0,-1}
		};

		if (i < 0 || j < 0 || i == m || j == n || visited[i][j]) {
			return false;
		}

		visited[i][j] = true;

		if (board[i][j] == word.charAt(index)) {
			if (index == word.length() - 1) {
				return true;
			}

			for (int[] dir : directions) {
				int newX = i + dir[0];
				int newY = j + dir[1];

				if (search(word, index + 1, board, newX, newY, visited)) {
					return true;
				}
			}
		}
		visited[i][j] = false;

		return false;
	}
}