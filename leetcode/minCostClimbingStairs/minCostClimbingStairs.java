/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
Once you pay the cost, you can either climb one or two steps.
You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.

Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].

Note:
cost will have a length in the range [2, 1000].
Every cost[i] will be an integer in the range [0, 999].

idea:
same as climb stairs, difference pay to climb one or two steps
understand as 你pay了这个step上的cost can proceed to next

https://www.cnblogs.com/grandyang/p/8343874.html
*/

class MinCostClimbingStairs {
	public int minCostClimbingStairs(int[] cost) {
		int n = cost.length;

		if (n == 1) {
			return cost[0];
		}

		if (n == 2) {
			return cost[1];
		}
		// to finish i steps, min cost
		int[] dp = new int[n + 1];
        dp[1] = 0;
        dp[2] = Math.min(cost[0], cost[1]);
		for (int i = 3; i <= n; i++) {
			dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
		}

		return dp[n];
	}
}