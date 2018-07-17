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
        int notSkippables[][] = new int[10][10];
        notSkippables[1][3] = notSkippables[3][1] = 2;
        notSkippables[1][7] = notSkippables[7][1] = 4;
        notSkippables[3][9] = notSkippables[9][3] = 6;
        notSkippables[7][9] = notSkippables[9][7] = 8;
        notSkippables[1][9] = notSkippables[9][1] = notSkippables[2][8] = notSkippables[8][2] = notSkippables[3][7] = notSkippables[7][3] = notSkippables[4][6] = notSkippables[6][4] = 5;
        
        boolean visited[] = new boolean[10];

        int ret = 0;
        // dfs search each length from m to n
        for (int i = m; i <= n; i++) {
            ret += dfs(visited, notSkippables, 1, i - 1) * 4;    // 1, 3, 7, 9 are symmetric
            ret += dfs(visited, notSkippables, 2, i - 1) * 4;    // 2, 4, 6, 8 are symmetric
            ret += dfs(visited, notSkippables, 5, i - 1);        // 5
        }

        return ret;
    }

    public int dfs(boolean visited[], int[][] notSkippables, int currKey, int remainingKeyCnt) {
        if (remainingKeyCnt < 0) return 0;
        if (remainingKeyCnt == 0) return 1;
        
        int cnt = 0;
        visited[currKey] = true;
        for (int i = 1; i <= 9; i++) {
        	int notSkippable = notSkippables[i][currKey];
            // If visited[i] is not visited and two 'or' cases
            // 1. two number are adjacent if notSkippable = 0 (没有 notSkippable )
            // 2. notSkippable number is already visited
            if (!visited[i] && (notSkippable == 0 || (visited[notSkippable]))) {
                cnt += dfs(visited, notSkippables, i, remainingKeyCnt - 1);
            }
        }
        visited[currKey] = false;

        return cnt;
    }
}