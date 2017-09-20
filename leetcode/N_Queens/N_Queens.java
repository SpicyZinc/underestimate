/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement,
where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]


idea:
note two parts:

1. recursion
one solution[] index is row, value is which column is queen 'Q'
solution[i] = j
	   j
  . . . . . .
  .
i .
  .
  .

solution[row] can be any one of 0 to n-1
when check valid, also from 0 through n - 1
if one is chosen, then recursively call dfs()
3 conditions are not valid
(1) the same row, which is already guaranteed by solution[]
(2) the same column, which is checked by isValid()
(3) the same major and minor diagonal, which is checked by isValid()

2. check diagonal
(i, j) (i+k, j+k)
i+k-i == j+k-j
*/

import java.util.*;

public class N_Queens {	
	public static void main(String[] args) {
		N_Queens eg = new N_Queens();
		List<List<String>> result = eg.solveNQueens(5);
		for (int i = 0; i < result.size(); i++) {
			for (String row : result.get(i)) {
				System.out.print(row + "\n");
			}
			System.out.println();
		}
	}

	public List<List<String>> solveNQueens(int n) {
    	List<List<String>> result = new ArrayList<>();
        enumerate(result, new int[n], 0);

        return result;
    }

    public void enumerate(List<List<String>> result, int[] q, int index) {
        int n = q.length;
        if (index == n) {
            result.add(constructQueens(q));
            return;
        } else {
            for (int i = 0; i < n; i++) {
                q[index] = i;
                if (isValid(q, index)) {
                    enumerate(result, q, index + 1);
                }
            }
        }
    }

    public List<String> constructQueens(int[] q) {
        List<String> solution = new ArrayList<String>();
        int n = q.length;
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(q[i] == j ? 'Q' : '.');
            }
            solution.add(sb.toString());
        }  
        
        return solution;
    }

    // Return true if queen placement q[n] does not conflict with other queens q[0] through q[n-1]
    // will NOT attack each other
    public boolean isValid(int[] q, int currentRow) {
        for (int i = 0; i < currentRow; i++) {
        	// same column
            if (q[i] == q[currentRow]) return false;
            // same major diagonal or minor diagonal
            if ( Math.abs(q[currentRow] - q[i]) == currentRow - i ) return false;
        }

        return true;
    }
}