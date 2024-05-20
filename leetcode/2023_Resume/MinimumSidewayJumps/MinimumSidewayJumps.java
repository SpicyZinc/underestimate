/*

dp[i][j]: the min jump to arrive at lane j at point i

Since the frog starts at lane 2, the initial states should be,
dp[0][2] = 0,
dp[0][1] = 1,
dp[0][3] = 1.

another: https://leetcode.com/problems/minimum-sideway-jumps/solutions/1152455/java-c-python-dp-greedy-bonus/

need to come back
*/

class MinimumSidewayJumps {
    public int minSideJumps(int[] obstacles) {
        int n = obstacles.length;
        // dp[i][j]: the min jump to arrive at lane j at point i
        int[][] dp = new int[n][4];
        // initial point
        dp[0][1] = dp[0][3] = 1;
        for (int i = 1; i < n; i++) {
            int obstacleLane = obstacles[i];
            for (int j = 1; j <= 3; j++) {
                if (j == obstacleLane) {
                    dp[i][j] = Integer.MAX_VALUE;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            // get the min at point i for all 3 lanes
            int min = Math.min(dp[i][1], Math.min(dp[i][2], dp[i][3]));
            for (int j = 1; j <= 3; j++) {
                if (j == obstacleLane) continue;
                // update dp[i][j]
                dp[i][j] = Math.min(min + 1, dp[i][j]);
            }
        }
 
        return Math.min(dp[n - 1][1], Math.min(dp[n - 1][2], dp[n - 1][3]));
    }
}
