/*
On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved.
If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.

Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.

Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]

Input: board = [[3,2,4],[1,5,0]]
Output: 14

Note:
board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].

idea:
DFS + hashmap memory
*/

class SlidingPuzzle {
	int min = Integer.MAX_VALUE;
	Map<Integer, Integer> hm = new HashMap<Integer, Integer>();

	public int slidingPuzzle(int[][] board) {
		int m = board.length;
		int n = board[0].length;
		hm.put(123450, 0);

		int zeroX = 0;
		int zeroY = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) {
					zeroX = i;
					zeroY = j;
					break;
				}
			}
		}
		dfs(board, zeroX, zeroY, 0);
		
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	public void dfs(int[][] board, int x, int y, int moveCnt) {
		// some early return cases
		// 1. if current move count > min, no need to continue
		// 2. if fount one successful, update min and return
		// 3. if hashmap contains code <-> moveCnt pair before, avoid duplicate, return
		if (moveCnt > min) {
			return;
		}
		int code = encode(board);
		if (code == 123450) {
			min = moveCnt;
			return;
		}
		// code here cannot be 123450
		if (hm.containsKey(code)) {
			if (moveCnt > hm.get(code)) {
				return;
			}
		}

		hm.put(code, moveCnt);

		int m = board.length;
		int n = board[0].length;
		int[][] directions = {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0},
		};
		for (int[] dir : directions) {
			int newX = x + dir[0];
			int newY = y + dir[1];
			if (newX < m && newX >= 0 && newY < n && newY >= 0) {
				swap(board, x, y, newX, newY);
				dfs(board, newX, newY, moveCnt + 1);
				swap(board, x, y, newX, newY);
			}
		}
	}

	public void swap(int[][] board, int m, int n, int x, int y) {
		int temp = board[m][n];
		board[m][n] = board[x][y];
		board[x][y] = temp;
	}

	public int encode(int[][] board) {
		int m = board.length;
		int n = board[0].length;
		int code = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				code *= 10;
				code += board[i][j];
			}
		}

		return code;
	}
}