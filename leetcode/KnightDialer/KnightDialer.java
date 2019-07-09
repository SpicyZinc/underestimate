/*
A chess knight can move as indicated in the chess diagram below:
https://assets.leetcode.com/uploads/2018/10/12/knight.png
https://assets.leetcode.com/uploads/2018/10/30/keypad.png

This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.
Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.
How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.

Example 1:
Input: 1
Output: 10

Example 2:
Input: 2
Output: 20

Example 3:
Input: 3
Output: 46

Note:
1 <= N <= 5000

idea:
https://hackernoon.com/google-interview-questions-deconstructed-the-knights-dialer-f780d516f029

dfs + memo

or
dp
https://buptwc.com/2018/11/05/leetcode-935-Knight-Dialer/
*/

class KnightDialer {
	static final int mod = 1000000007;

	public int knightDialer(int N) {
		// what numbers could reach 0, 1, 2, ..., 9
		int[][] graph = new int[][] {
			{4,6},
			{6,8},
			{7,9},
			{4,8},
			{3,9,0},
			{},
			{1,7,0},
			{2,6},
			{1,3},
			{2,4}
		};

		int size = graph.length;

		int[][] memo = new int[N + 1][size];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(memo[i], -1);
		}

		int count = 0;

		for (int i = 0; i < size; i++) {
			count = (count + dfsMemo(N, i, graph, memo)) % mod;
		}

		return count;
	}

	public int dfsMemo(int remaining, int pos, int[][] graph, int[][] memo) {
		if (remaining == 1) {
			// 1 种 走法
			return 1;
		}

		if (memo[remaining][pos] != -1) {
			return memo[remaining][pos];
		}

		int count = 0;
		for (int neighbor : graph[pos]) {
			count = (count + dfsMemo(remaining - 1, neighbor, graph, memo)) % mod;
		}
		// 好多重复的 已经计算过
		return memo[remaining][pos] = count;
	}
}