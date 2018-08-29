/*
Follow up for N-Queens problem, instead outputting board configurations, 
return the total number of distinct solutions.

idea:
must pass an array of length 1,
primitive value is not counted in recursion

two key parts:

1. recursion
solution[row] choose from 0 to n-1
solution[row] can be any one of 0 to n-1
if one is chosn, then recursively call placeNQueens(),
of course there are conditions
(1) not the same row, which is guaranteed already by sloution[] array
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
		int result = totalNQueens(5);
		System.out.print("5-Queens has " + result + " distinct ways");
	}

	public int totalNQueens(int n) {
		int[] count = new int[1];
		if (n <= 0) {
			return count[0];
		}
		// index is row number in the grid
		// element is column number in the grid
		int[] solution = new int[n];
		placeNQueens(count, solution, 0);
		return count[0];		
	}
	
	private void placeNQueens(int[] count, int[] solution, int row) {
		int n = solution.length;
		if (row == n) {
			count[0]++;
			return;
		} else {
			// i is element in solution[]
			// in fact it is column index
			// key part I
			for (int i=0; i<n; i++) {
				if (!attack(solution, row, i)) {
					solution[row] = i;
					placeNQueens(count, solution, row+1);
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

	// self written version passed test
	public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] solutions = new int[n];
        int[] count = new int[1];
        total(count, solutions, 0);
        
        return count[0];
    }

    public void total(int[] count, int[] solutions, int row) {
        int n = solutions.length;
        if (row == n) {
            count[0]++;
        } else {
            for (int i = 0; i < n; i++) {
                solutions[row] = i;
                if ( isValid(solutions, row, i) ) {
                    total(count, solutions, row + 1);
                }
            }
        }
    }
    
    public boolean isValid(int[] solutions, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (solutions[i] == col) {
                return false;
            }
            if (row - i == Math.abs(solutions[i] - col)) {
                return false;
            }
        }
        
        return true;
    }
}
