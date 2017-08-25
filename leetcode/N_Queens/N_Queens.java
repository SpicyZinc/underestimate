/*
The n-queens puzzle is the problem of placing n queens on an n * n chessboard 
such that no two queens attack each other.
 // Solution 1
 [".Q..",  
  "...Q",
  "Q...",
  "..Q."]
 // Solution 2
 ["..Q.",  
  "Q...",
  "...Q",
  ".Q.."]

idea:
http://blog.csdn.net/u011095253/article/details/9158473
two key parts:

1. recursion
solution[row] choose from 0 to n-1
solution[row] can be any one of 0 to n-1
if one is chosen, then recursively call placeNQueens(),
of course there are conditions
(1) not the same row, which is already guaranteed by sloution[] array
(2) not the same column, which is checked by attack()
(3) not on the diagnal, which is checked by attack()

2. check diagnal
(i, j) (i+k, j+k)
i+k-i == j+k-j

one thing to note:
solution[i] = j
for each row i, j is the position of queen
	   j
  . . . . . .
  .
i .
  .
  .
*/

import java.util.*;

public class N_Queens {	
	public static void main(String[] args) {
		new N_Queens();
	}
	public N_Queens() {
		ArrayList<String[]> result = solveNQueens(5);
		for (int i=0; i<result.size(); i++) {
			String[] tmpS = result.get(i);
			for (int j=0; j<tmpS.length; j++) {
				System.out.print(tmpS[j] + "\n");
			}
			System.out.print("\n");
		}
	}
	public ArrayList<String[]> solveNQueens(int n) {
		ArrayList<String[]> ret = new ArrayList<String[]>();
		if (n <= 0) {
			return ret;
		}
		// index is row number in the grid
		// element is column number in the grid
		int[] solution = new int[n];
		placeNQueens(ret, solution, 0);

		return ret;		
	}
	
	private void placeNQueens(ArrayList<String[]> ret, int[] solution, int row) {
		int n = solution.length;
		if (row == n) {
			ret.add(convert(solution));
			return;
		}
		else {
			// i is element in solution[]
			// in fact it is column index
			// key part I
			for (int i=0; i<n; i++) {
				if (!attack(solution, row, i)) {
					solution[row] = i;
					placeNQueens(ret, solution, row+1);
				}				
			}
		}		
	}
	
	private boolean attack(int[] solution, int row, int col) {		
		for (int i=0; i<row; i++) {
			// check column
			if (solution[i] == col) {
				return true;
			}
			// check diagnal
			// key part II
			if (row - i == Math.abs(col - solution[i])) {
				return true;
			}
		}

		return false;
	}
	
	private String[] convert(int[] solution) {
		int n = solution.length;
		String[] tmp = new String[n];
		for (int i=0; i<n; i++) {
			StringBuilder sb = new StringBuilder();
			int q = solution[i];
			for (int before=0; before<=q-1; before++) {
				sb.append('*');
			}
			sb.append('Q');
			for (int after=q+1; after<n; after++) {
				sb.append('*');
			}
			tmp[i] = sb.toString();
		}	

		return tmp;
	}
}

// self written version
public class N_Queens {	
	public static void main(String[] args) {
		new N_Queens();
	}
	public N_Queens() {
		ArrayList<String[]> result = solveNQueens(5);
		for (int i=0; i<result.size(); i++) {
			String[] tmpS = result.get(i);
			for (int j=0; j<tmpS.length; j++) {
				System.out.print(tmpS[j] + "\n");
			}
			System.out.print("\n");
		}
	}
	public ArrayList<String[]> solveNQueens(int n) {
		ArrayList<String[]> ret = new ArrayList<String[]>();
		if (n <= 0) {
			return ret;
		}
		// index is row number in the grid
		// element is column number in the grid
		int[] solution = new int[n];
		dfs(ret, solution, 0);

		return ret;		
	}

	public void dfs(ArrayList<String[]> ret, int[] solution, int row) {
		int n = solution.length;
		if (row == n) {
			ret.add(printBoard(solution));

			return ;
		}
		else {
			for (int col = 0; col < n; col++) {
				solution[row] = col;
				if ( isValid(solution, row, col) ) {
					dfs(ret, solution, row + 1);
				}
			}
		}
	}

	public boolean isValid(int[] solution, int row, int col) {
		for (int i = 0; i < row; i++) {
			// check column
			if (solution[i] == col) {
				return false;
			}
			if ( row - i == Math.abs(col - solution[i]) ) {
				return false;
			}
		}
		return true;
	}

	public String[] printBoard(int[] solution) {
		int n = solution.length;
		String[] temp = new String[n];
		for (int i = 0; i < n; i++) {
			StringBuilder sb = new StringBuilder();
			int q = solution[i];
			for (int j = 0; j <= q-1; j++) {
				sb.append('.');
			}
			sb.append('Q');
			for (int j = q+1; j <= n-1; j++) {
				sb.append('.');
			}

			temp[i] = sb.toString();
		}

		return temp;
	}
}
