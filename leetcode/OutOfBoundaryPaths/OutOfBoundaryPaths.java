/*
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball,
you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right).
However, you can at most move N times.

Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.

Example 1:
Input:m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:
https://leetcode.com/static/images/problemset/out_of_boundary_paths_1.png

Example 2:
Input:m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:
https://leetcode.com/static/images/problemset/out_of_boundary_paths_2.png

Note:
Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].


idea:
1. dfs with memo, recursion with memoization
https://www.cnblogs.com/grandyang/p/6927921.html
2. dp, dp[k][i][j] represents总共走k步, 从(i,j)位置走出边界的总路径数
走k步出边界的总路径数等于其周围四个位置的走k-1步出边界的总路径数之和
如果周围某个位置已经出边界了, 那么就直接加上1种走法, otherwise就在dp数组中找出该值
*/

public class OutOfBoundaryPaths {
	public int findPaths(int m, int n, int N, int i, int j) {
        int[][][] memo = new int[m][n][N + 1];
        for (int[][] steps : memo) {
            for (int[] array : steps) {
                Arrays.fill(array, -1);
            }
        }

        return findPaths(m, n, N, i, j, memo);
    }

    public int findPaths(int m, int n, int N, int i, int j, int[][][] memo) {
        int[][] directions = new int[][] {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };
        int mod = 1000000007;

        // out of boundary 1 times
        if (i == m || i < 0 || j == n || j < 0) {
            return 1;
        }

        if (N == 0) {
            return 0;
        }

        if (memo[i][j][N] >= 0) {
            return memo[i][j][N];
        }
        
        int steps = 0;
        for (int[] dir : directions) {
            int newX = i + dir[0];
            int newY = j + dir[1];
            
            steps += findPaths(m, n, N - 1 , newX, newY, memo);
            steps %= mod;
        }
        memo[i][j][N] = steps;

        return memo[i][j][N];
    }

    // method 2, 87 / 94 test cases passed
    public int findPaths(int m, int n, int N, int i, int j) {
		int mod = 1000000007;
		int[][][] dp = new int[m][n][N + 1];

        for (int k = 1; k <= N; k++) {
		    for (int x = 0; x < m; x++) {
			    for (int y = 0; y < n; y++) {
					// 4 directions
					int val1 = x == 0 ? 1 : dp[x - 1][y][k - 1];
					int val2 = y == 0 ? 1 : dp[x][y - 1][k - 1];
					int val3 = x == m - 1 ? 1 : dp[x + 1][y][k - 1];
					int val4 = y == n - 1 ? 1 : dp[x][y + 1][k - 1];

					dp[x][y][k] = (val1 + val2 + val3 + val4) % mod;
				}
			}
		}

		return dp[i][j][N];
	}
}