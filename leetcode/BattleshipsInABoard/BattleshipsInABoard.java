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
http://bookshadow.com/weblog/2016/10/13/leetcode-battleships-in-a-board/

loop through the board,
if top or left to the current position has X,
then current position belongs to a spaceship, count still zero, since no adjacent ships, either zero or two

if not, count++
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
				    if (i > 0 && c == board[i-1][j]) {
						continue;
					}
					if (j > 0 && c == board[i][j-1]) {
						continue;
					}
					count++;
				}
				// my wrong solution
				// if (i > 0 && c == 'X') {
				// 	if (c == board[i-1][j]) {
				// 		continue;
				// 	}
				// 	count++;
				// }
				// if (j > 0 && c == 'X') {
				// 	if (c == board[i][j-1]) {
				// 		continue;
				// 	}
				// 	count++;
				// }
			}
		}

		return count;
    }
}
