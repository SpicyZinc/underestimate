/*
There are 2N people a company is planning to interview.
The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a city such that exactly N people arrive in each city. 

Example 1:
Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 
Note:
1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000

idea:
dp[i][j] represents the cost i people assigned to city A and j people assigned to city B
in first (i + j) people
*/

class TwoCityScheduling {
	public int twoCitySchedCost(int[][] costs) {
		int n = costs.length;
		int N = n / 2;

		int[][] dp = new int[N + 1][N + 1];

		// first N go to city A
		for (int i = 1; i <= N; i++) {
			dp[i][0] = dp[i - 1][0] + costs[i - 1][0];
		}
		// first N go to city B
		for (int i = 1; i <= N; i++) {
			dp[0][i] = dp[0][i - 1] + costs[i - 1][1];
		}

		// all N + N people
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int curr = i + j - 1; // array index, not people
				int costToA = costs[curr][0];
				int costToB = costs[curr][1];
				// [i - 1][j] => curr should go to A
				// [i][j - 1] => curr should go to B
				dp[i][j] = Math.min(dp[i - 1][j] + costToA, dp[i][j - 1] + costToB);
			}
		}

		return dp[N][N];
	}
}