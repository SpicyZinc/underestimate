/*
Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:
A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.

Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | | // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | | // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | | // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| | // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| | // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| | // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| | // Player 1 makes a move at (2, 1).
|X|X|X|

Follow up:
Could you do better than O(n^2) per move() operation?

Hint:
Could you trade extra space such that move() operation can be done in O(1)?
You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.

idea:
direct
是根据 移动到的row col 来判断
不用在回去了 因为之前判断了
*/

public class TicTacToe {
	public static void main(String[] args) {
		TicTacToe toe = new TicTacToe(3);

		int playerWin = toe.move(0, 0, 1);
		System.out.println(playerWin);
		playerWin = toe.move(0, 2, 2);
		System.out.println(playerWin);
		playerWin = toe.move(2, 2, 1);
		System.out.println(playerWin);
		playerWin = toe.move(1, 1, 2);
		System.out.println(playerWin);
		playerWin = toe.move(2, 0, 1);
		System.out.println(playerWin);
		playerWin = toe.move(1, 0, 2);
		System.out.println(playerWin);
		playerWin = toe.move(2, 1, 1);
		System.out.println(playerWin);
	}

	int[][] matrix;
	public TicTacToe(int n) {
		matrix = new int[n][n];
	}

	/**
	 * Player {player} makes a move at ({row}, {col}).
	 * @param  row    The row of the board.
	 * @param  col    The column of the board.
	 * @param  player The player, can be either 1 or 2.
	 * @return        The current winning condition, can be either:
						0: No one wins.
						1: Player 1 wins.
						2: Player 2 wins.
	 */

	public int move(int row, int col, int player) {
		matrix[row][col] = player;

		int n = matrix.length;
		int i = 0;
		int j = 0;
		// check row
		for (j = 1; j < n; j++) {
			// not consecutive
			if (matrix[row][j - 1] != matrix[row][j]) {
				break;
			}
		}
		if (j == n) {
			return player;
		}

		// check column
		for (i = 1; i < n; i++) {
			// not consecutive
			if (matrix[i - 1][col] != matrix[i][col]) {
				break;
			}
		}
		if (i == n) {
			return player;
		}

		// check diagonal
		if (row == col) {
			for (i = 1; i < n; i++) {
				if (matrix[i - 1][j - 1] != matrix[i][j]) {
					break;
				}
			}
		}
		if (i == n) {
			return player;
		}

		// check anti-diagonal
		if (row + col == n - 1) {
			for (i = 1; i < n; i++) {
				if (matrix[i - 1][n - i] != matrix[i][n - 1 - i]) {
					break;
				}
			}
		}
		if (i == n) {
			return player;
		}

		return 0;
	}

	// better method
	int n;
	int[] rows;
	int[] cols;
	int diagonalSum;
	int antiDiagonalSum;
	public TicTacToe(int n) {
		this.n = n;
		this.rows = new int[n];
		this.cols = new int[n];
		this.diagonalSum = 0;
		this.antiDiagonalSum = 0;
	}

	/**
	 * Player {player} makes a move at ({row}, {col}).
	 * @param  row    The row of the board.
	 * @param  col    The column of the board.
	 * @param  player The player, can be either 1 or 2.
	 * @return        The current winning condition, can be either:
						0: No one wins.
						1: Player 1 wins.
						2: Player 2 wins.
	 */
	public int move(int row, int col, int player) {
		int increment = player == 1 ? 1 : -1;
		rows[row] += increment;
		cols[col] += increment;
		if (row == col) {
			diagonalSum += increment;
		}
		if (row + col == n - 1) {
			antiDiagonalSum += increment;
		}

		if (Math.abs(rows[row]) == n ||
			Math.abs(cols[col]) == n ||
			Math.abs(diagonalSum) == n ||
			Math.abs(antiDiagonalSum) == n
		) {
			return player;
		}

		return 0;
	}
}