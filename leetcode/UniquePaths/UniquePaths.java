/*
A robot is located at the top-left corner of a m x n grid
The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

idea:
1. 1D DP

	|----------------------|
	|                      |
	|                      |
	|       |---|----------|
i+1	|		| j |j-1|	   |
	|-------|---|----------|  ^
i	|		| j |		   |  |
	|-------|---|          |  |
	|                      |
	|----------------------|
	
after step[j] = step[j-1] + step[j]
line i changes to line i+1
in fact, use line i's j and line (i+1)'s j-1 to get line (i+1)'s j

2. 2D DP
Bottom-up

use a 2D grid same length as m, n
to remember the number of ways to reach 
right bottom corner from current position(i, j)(as starting point)
(i, j) at most can be (m-1, n-1) 

another thing to note:
remember[i][j] can either go right or down, so totally it has two options,
add these two options together, each option has the number of ways to reach right bottom corner.

2D DP
1   1   1
1   2   3
1   3   6
*/

public class UniquePaths {
    // save space by only creating 1D array
    public int uniquePaths(int m, int n) {
        int[] step = new int[n];
        step[0] = 1;
        for (int i = 0; i < m; i++) { 
            for (int j = 1; j < n; j++) {
                step[j] += step[j - 1];
            }
        }

        return step[n-1];
    }

    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n]; 
        // init left
        for (int i = 0; i < m; i++) {
            res[i][0] = 1;
        }
        // init top
        for (int j = 0; j < n; j++) {
            res[0][j] = 1;
        }
        // add values
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }
         
        return res[m - 1][n - 1];
    }

    public int uniquePaths(int m, int n) {
        int[][] remember = new int[m][n];
        // last row
        for (int j=0; j<n; j++) {
            remember[m-1][j] = 1;
        }
        // last column
        for (int i=0; i<m; i++) {
            remember[i][n-1] = 1;
        }
        // DP
        for (int i=m-2; i>=0; i--) {
            for (int j=n-2; j>=0; j--) {
                remember[i][j] = remember[i+1][j] + remember[i][j+1];
            }
        }
        
        return remember[0][0];
    }  


	// direct recursion only passed Judge Small
    public int uniquePaths(int m, int n) {
        return uniquePaths(m, n, 0, 0);
    }
	
	private int uniquePaths(int m, int n, int i, int j) {
		if (i == m-1 && j == n-1) return 1;
		if (m <= i || n <= j) return 0;
		return uniquePaths(m, n, i+1, j) + uniquePaths(m, n, i, j+1);
    }

	// combinatorial only passed Judge Small partially
	// https://leetcode.com/problems/unique-paths/#/solutions
	public int uniquePaths(int m, int n) {
        return combination(m+n-2, n-1); // or (m+n-2, (m+n-2)-(n-1))
    }
	private int combination(int x, int y) {
		return factorial(x) / (factorial(y) * factorial(x-y));
	}
	private int factorial(int n) {
        if (n == 0) return 1;
		if (n == 1) return n;
		return n * factorial(n-1);
	}

	// implementation of C(m, n)
	public int gcd(int a, int b) {
		while (b > 0) {
	        int c = a % b;
	        a = b;
	        b = c;
	    }

	    return a;
	}

	public int C(int m, int n) {
	    if (m - n < n) {
	        n = m - n;
	    }
	    int result = 1;
	    for (int i = 1; i <= n; i++) {
	        int mult = m;
	        int divi = i;
	        int g = gcd(mult,divi);
	        mult /= g;
	        divi /= g;
	        result /= divi;
	        result *= mult;
	        m--;
	    }
	    return result;
	}
}