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
        dfs(0, new int[n], result);
        
        return result;
    }
    
    public void dfs(int pos, int[] path, List<List<String>> result) {
        int n = path.length;
        if (pos == n) {
            result.add(constructSolution(path));
            return;
        } else {
            for (int i = 0; i < n; i++) {
                path[pos] = i;
                if (isValid(path, pos)) {
                    dfs(pos + 1, path, result);
                }
            }
        }
    }
    
    private List<String> constructSolution(int[] path) {
        int n = path.length;

        List<String> solution = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            // for each row, find where the Q is
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(path[i] == j ? 'Q' : '.');
            }
            solution.add(sb.toString());
        }
        
        return solution;
    }
    
    private boolean isValid(int[] path, int currentRow) {
        // path[] represents all rows, itself guarantees that same row cannot have two queens
        for (int i = 0; i < currentRow; i++) {
            // same column
            if (path[currentRow] == path[i]) {
                return false;
            }
            // check if on major or minor diagonal by absolute diff value of y1 - y2 == x1 - x2
            if (Math.abs(path[currentRow] - path[i]) == currentRow - i) {
                return false;
            }
        }

        return true;
    }
}