/*
There are G people in a gang, and a list of various crimes they could commit.
The i-th crime generates a profit[i] and requires group[i] gang members to participate.
If a gang member participates in one crime, that member can't participate in another crime.

Let's call a profitable scheme any subset of these crimes that generates at least P profit,
and the total number of gang members participating in that subset of crimes is at most G.

How many schemes can be chosen?  Since the answer may be very large, return it modulo 10^9 + 7.

Example 1:
Input: G = 5, P = 3, group = [2,2], profit = [2,3]
Output: 2
Explanation: 
To make a profit of at least 3, the gang could either commit crimes 0 and 1, or just crime 1.
In total, there are 2 schemes.

Example 2:
Input: G = 10, P = 5, group = [2,3,5], profit = [6,7,8]
Output: 7
Explanation: 
To make a profit of at least 5, the gang could commit any crimes, as long as they commit one.
There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).

Note:
1 <= G <= 100
0 <= P <= 100
1 <= group[i] <= 100
0 <= profit[i] <= 100
1 <= group.length = profit.length <= 100

idea:
dp
dp[c][i][j] # of schemes of making profit i with j gang members by committing first c crimes
dp[0][0][0] = 1;
*/

class ProfitableSchemes {
	public int profitableSchemes(int G, int P, int[] group, int[] profit) {
		int mod = 1000000007;
		int C = group.length;
		int[][][] dp = new int[C + 1][P + 1][G + 1];

		// commit no crimes and make no profit requires no gang member to attend which is 1 scheme
		dp[0][0][0] = 1;

		for (int c = 1; c <= C; c++) {
			int p = profit[c - 1];
			// commit crime c needs g persons
			int g = group[c - 1];

			for (int i = 0; i <= P; i++) {
				for (int j = 0; j <= G; j++) {
					int prevProfit = Math.max(0, i - p);
					dp[c][i][j] = (dp[c - 1][i][j] + (j < g ? 0 : dp[c - 1][prevProfit][j - g])) % mod;
				}
			}
		}

		int count = 0;
		for (int i = 0; i < dp[C][P].length; i++) {
			count += dp[C][P][i];
            count %= mod;
		}

		return count;
	}
}