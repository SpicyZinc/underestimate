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
http://www.cnblogs.com/feiling/p/3304120.html
board的四个边上的O是无法被X包围的, 如果在四个边上发现O, 则这些O应当被保留 从这些O出发继续寻找相邻的O, 这些O也是要保留的
http://blog.csdn.net/linhuanmars/article/details/22904855

http://blog.sina.com.cn/s/blog_b9285de20101j1dt.html
http://fisherlei.blogspot.com/2013/03/leetcode-surrounded-regions-solution.html
http://coding-exercise.blogspot.com/2013/03/leetcode-surrounded-regions.html

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

class Position {
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}

public class SurroundedRegions {	
	public static void main(String[] args) {
		new SurroundedRegions();
	}
	public SurroundedRegions() {
		char[][] board = {
			{'X', 'X', 'X', 'X'},
			{'X', 'O', 'O', 'X'},
			{'X', 'X', 'O', 'X'},
			{'X', 'O', 'X', 'X'},	
		};
		solve(board);
		for (int i=0; i<board.length; i++) {  
			for (int j=0; j<board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public void solve(char[][] board) {
		if (board.length == 0 || board[0].length == 0) {
            return;
        }

		int r = board.length; 
		int c = board[0].length;
		ArrayList<Position> unsurrounded = new ArrayList<Position>();
		// the first and last column
		for (int i=0; i<r; i++) {
			if (board[i][0] == 'O') {
				unsurrounded.add(new Position(i, 0));
			}
			if (board[i][c-1] == 'O') {
				unsurrounded.add(new Position(i, c-1));
			}
		}
		// the first and last row
		for (int j=0; j<c; j++) {
			if (board[0][j] == 'O') {
				unsurrounded.add(new Position(0, j));
			}
			if (board[r-1][j] == 'O') {
				unsurrounded.add(new Position(r-1, j));
			}
		}

		int index = 0;
		while (index < unsurrounded.size()) {
			int x = unsurrounded.get(index).getX();
			int y = unsurrounded.get(index).getY();
			board[x][y] = 'N';

			if (x > 0 && board[x-1][y] == 'O')
				unsurrounded.add(new Position(x-1, y));
			if (x < r-1 && board[x+1][y] == 'O')
				unsurrounded.add(new Position(x+1, y));
			if (y > 0 && board[x][y-1] == 'O')
				unsurrounded.add(new Position(x, y-1));
			if (y > c-1 && board[x][y+1] == 'O')
				unsurrounded.add(new Position(x, y+1));

			index++;
		}
		// scan again, 'N' is replaced with O, 'O' is replaced with 'X'
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				if (board[i][j] == 'N') {
					board[i][j] = 'O';
				}
				else if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
	}

	// BFS search, use queue
	public void solve(char[][] board) {
		Queue<Integer> queue = new LinkedList<Integer>();
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
 	
 	    int row = board.length;
        int col = board[0].length;
        
 		// for each row, first and last column
 		for (int i = 0; i < row; i++) {
 			if (board[i][0] == 'O') {
 				bfs(board, i, 0, queue);
 			}
 			if (board[i][col-1] == 'O') {
 				bfs(board, i, col-1, queue);
 			}
 		}
 		// for each column, first and last row
 		for (int j = 0; j < col; j++) {
 			if (board[0][j] == 'O') {
 				bfs(board, 0, j, queue);
 			}
 			if (board[row-1][j] == 'O') {
 				bfs(board, row-1, j, queue);
 			}
 		}

 		// scan again
 		for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'N') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void bfs(char[][] board, int i, int j, Queue<Integer> queue) {
    	fill(board, i, j, queue);
    	while ( !queue.isEmpty() ) {
    		int currentPosition = queue.poll();
    		int x = currentPosition / board[0].length;
    		int y = currentPosition % board[0].length;
    		fill(board, x-1, y, queue);
    		fill(board, x+1, y, queue);
    		fill(board, x, y-1, queue);
    		fill(board, x, y+1, queue);
    	}
    }
    // set elements connected to edges to be 'N'
    private void fill(char[][] board, int i, int j, Queue<Integer> queue) {
    	int r = board.length;
    	int c = board[0].length;

    	if (i < 0 || i >= r || j < 0 || j >= c || board[i][j] != 'O') {
    		return;
    	}

    	board[i][j] = 'N';
    	queue.offer( c * i + j);
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