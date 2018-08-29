/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:
X X X X
X X X X
X X X X
X O X X

idea:
https://www.programcreek.com/2014/04/leetcode-surrounded-regions-java/
http://www.cnblogs.com/feiling/p/3304120.html
board的四个边上的O是无法被X包围的, 如果在四个边上发现O, 则这些O应当被保留 从这些O出发继续寻找相邻的O, 这些O也是要保留的
http://blog.csdn.net/linhuanmars/article/details/22904855


Solving from edges to the center is the key to reduce the workload
all elements cannot be connected from the O at the edges are real elements needing to be removed, or real surrounded elements

surrounded regions cannot be connected regions which can reach one of the four edges at least
because if so, they cannot be called surrounded.

1, start from up, down, left, right, find all connected 'O', mark as 'N', meaning not surrounded regions
2, finally scan all char[][], all existent 'O' are set to be 'X', 'N' is to be 'O'

时间复杂度是O(m*n)
空间复杂度是O(m+n)
*/

import java.util.*;

public class SurroundedRegions {

	public static void main(String[] args) {
		SurroundedRegions eg = new SurroundedRegions();
		char[][] board = {
			{'X', 'X', 'X', 'X'},
			{'X', 'O', 'O', 'X'},
			{'X', 'X', 'O', 'X'},
			{'X', 'O', 'X', 'X'},
		};

		solve(board);
		for (char[] row : board) {
			System.out.println(Arrays.toString(row));
		}
	}
	// 08/26/2018
	public void solve(char[][] board) {
		if (board.length == 0 || board[0].length == 0) {
			return;
		}
		
		int m = board.length;
		int n = board[0].length;

		// first and last column
		for (int i = 0; i < m; i++) {
			if (board[i][0] == 'O') {
				dfsSolve(board, i, 0);
			}
			if (board[i][n - 1] == 'O') {
				dfsSolve(board, i, n - 1);
			}
		}
		// first and last row
		for (int j = 0; j < n; j++) {
			if (board[0][j] == 'O') {
				dfsSolve(board, 0, j);
			}
			if (board[m - 1][j] == 'O') {
				dfsSolve(board, m - 1, j);
			}
		}

		// those not filled with '#' are now safe to be filled with X
		// because they are the real ones surrounded by X
		// meanwhile change # back to O
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
				if (board[i][j] == '#') {
					board[i][j] = 'O';
				}
			}
		}
	}

	public void dfsSolve(char[][] board, int i, int j) {
		int[][] directions = {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0}
		};

		int m = board.length;
		int n = board[0].length;

		if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
			return;
		}

		board[i][j] = '#';

		for (int[] dir : directions) {
			int nextX = i + dir[0];
			int nextY = j + dir[1];

			dfsSolve(board, nextX, nextY);
		}
	}

	// BFS
	public void solve(char[][] board) {
		if (board.length == 0 || board[0].length == 0) {
			return;
		}

		int[][] directions = {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0}
		};

		int m = board.length;
		int n = board[0].length;

		List<int[]> unsurrounded = new ArrayList<int[]>();
		// the first and last column
		for (int i = 0; i < m; i++) {
			if (board[i][0] == 'O') {
				unsurrounded.add(new int[] {i, 0});
			}
			if (board[i][n - 1] == 'O') {
				unsurrounded.add(new int[] {i, n - 1});
			}
		}

		// the first and last row
		for (int j = 0; j < n; j++) {
			if (board[0][j] == 'O') {
				unsurrounded.add(new int[] {0, j});
			}
			if (board[m - 1][j] == 'O') {
				unsurrounded.add(new int[] {m - 1, j});
			}
		}

		int index = 0;
		while (index < unsurrounded.size()) {
			int x = unsurrounded.get(index)[0];
			int y = unsurrounded.get(index)[1];

			board[x][y] = 'N';
			index++;

			for (int[] dir : directions) {
				int nextX = x + dir[0];
				int nextY = y + dir[1];

				if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && board[nextX][nextY] == 'O') {
					unsurrounded.add(new int[] {nextX, nextY});
				}
			}
		}
		// scan again, 'N' is replaced with O, 'O' is replaced with 'X'
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'N') {
					board[i][j] = 'O';
				} else if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
	}

    // self written version passed test
    public void solve(char[][] board) {
        Queue<Integer> queue = new LinkedList<Integer>();
        
        if (board.length == 0 || board[0].length == 0) {
            return;
        }

        int row = board.length;
        int col = board[0].length;
        // for each row, 1st and last column
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                bfs(board, i, 0, queue);
            }
            if (board[i][col - 1] == 'O') {
                bfs(board, i, col - 1, queue);
            }
        }
        // for each column, 1st and last row
        for (int j = 0; j < col; j++) {
            if (board[0][j] == 'O') {
                bfs(board, 0, j, queue);
            }
            
            if (board[row - 1][j] == 'O') {
                bfs(board, row - 1, j, queue);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'P') {
                    board[i][j] = 'O';
                }
            }
        }

    }

    private void bfs(char[][] board, int i, int j, Queue<Integer> queue) {
        int col = board[0].length;

        fill(board, i, j, queue);
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / col;
            int y = cur % col;

            fill(board, x - 1, y, queue);
            fill(board, x + 1, y, queue);
            fill(board, x, y - 1, queue);
            fill(board, x, y + 1, queue);
        }
    }
    
    private void fill(char[][] board, int i, int j, Queue<Integer> queue) {
        int row = board.length;
        int col = board[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] != 'O') {
            return;
        }

        queue.offer(i * col + j);
        board[i][j] = 'P';
    }
}