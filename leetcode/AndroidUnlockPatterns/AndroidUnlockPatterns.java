/*
Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9,
count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

Rules for a valid pattern:
Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern.
No jumps through non selected key is allowed.
The order of keys used matters.

Explanation:
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |

Invalid move: 4 - 1 - 3 - 6 
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

Example:
Given m = 1, n = 1, return 9.

idea:
https://www.cnblogs.com/grandyang/p/5541012.html
如果以前visited过 就可以skip
如果adjacent 也可以skip
*/

class AndroidUnlockPatterns {
	public static void main(String[] args) {
		AndroidUnlockPatterns eg = new AndroidUnlockPatterns();
		int cnt = eg.numberOfPatterns(1, 1);
		System.out.println(cnt);
		cnt = eg.numberOfPatterns(5, 6);
		System.out.println(cnt);
	}

    public int numberOfPatterns(int m, int n) {
        // Skip array represents number to skip between two pairs
        int skip[][] = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        boolean visited[] = new boolean[10];

        int ret = 0;
        // dfs search each length from m to n
        for (int i = m; i <= n; i++) {
            ret += dfs(visited, skip, 1, i - 1) * 4;    // 1, 3, 7, 9 are symmetric
            ret += dfs(visited, skip, 2, i - 1) * 4;    // 2, 4, 6, 8 are symmetric
            ret += dfs(visited, skip, 5, i - 1);        // 5
        }

        return ret;
    }

    public int dfs(boolean visited[], int[][] skip, int currKey, int remainingKeyCnt) {
        if (remainingKeyCnt < 0) return 0;
        if (remainingKeyCnt == 0) return 1;
        
        int cnt = 0;
        visited[currKey] = true;
        for (int i = 1; i <= 9; i++) {
        	int skippable = skip[i][currKey];
        	// two number are adjacent if skippable = 0
            // If visited[i] is not visited and (two numbers are adjacent or skip number is already visited)
            if (!visited[i] && (skippable == 0 || (visited[skippable]))) {
                cnt += dfs(visited, skip, i, remainingKeyCnt - 1);
            }
        }
        visited[currKey] = false;

        return cnt;
    }
}