/*
Given an 2D board, count how many different battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s.

You may assume the following rules:
You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically.
In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.

Example:
X..X
...X
...X
In the above board there are 2 battleships.

Invalid Example:
...X
XXXX
...X
This is not a valid board - as battleships will always have a cell separating between them.

Your algorithm should not modify the value of the board.

idea:
https://www.cnblogs.com/grandyang/p/5979207.html

所谓的战舰起始点, 就是为X的点, 而且该点的上方和左边的点不能为X
loop through the board,
if top or left to the current position has X,
then current position belongs to a spaceship, count still zero, since no adjacent ships, either zero or two

if not, count++

这个就是 Number of Islands 的翻版
*/

public class BattleshipsInABoard {
	public int countBattleships(char[][] board) {
		if (board.length == 0 || board[0].length == 0 || board == null) {
			return 0;
		}

		int m = board.length;
		int n = board[0].length;
		int count = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				char c = board[i][j];

				if (c == 'X') {
					if (i > 0 && c == board[i - 1][j]) {
						continue;
					}
					if (j > 0 && c == board[i][j - 1]) {
						continue;
					}
					count++;
				}
			}
		}

		return count;
	}

	// dfs, the same as Number of Islands
	public int countBattleships(char[][] board) {
		if (board.length == 0 || board[0].length == 0 || board == null) {
			return 0;
		}

		int m = board.length;
		int n = board[0].length;

		boolean[][] visited = new boolean[m][n];

		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'X' && !visited[i][j]) {
					count++;
					dfs(board, i, j, visited);
				}
			}
		}

		return count;
	}

	public void dfs(char[][] board, int i, int j, boolean[][] visited) {
		int m = board.length;
		int n = board[0].length;

		int[][] directions = {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0}
		};

		visited[i][j] = true;

		for (int[] dir : directions) {
			int nextX = i + dir[0];
			int nextY = j + dir[1];

			if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && board[nextX][nextY] == 'X' && !visited[nextX][nextY]) {
				dfs(board, nextX, nextY, visited);
			}
		}
	}
}