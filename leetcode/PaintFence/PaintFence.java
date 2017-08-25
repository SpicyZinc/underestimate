/*
There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

idea:
https://segmentfault.com/a/1190000003790650

any 3 adjacent fence posts,
either 3rd one different from 1st one or 3rd one different from 2nd one in term of color
why: either or so these are addition relationship
as long as satisfying the aforementioned condition, the no more than two adjacent fence posts having the same color would NOT be broken
*/


public class PaintFence {
    public int numWays(int n, int k) {
    	// 0, k , k * k
    	// no fence, 0 
    	// 1 fence, k
    	// 2 fence k * k
        int dp[] = {0, k , k * k, 0};
        if (n <= 2) {
            return dp[n];
        }
        for (int i = 2; i < n; i++ ) {
            dp[3] = (k - 1) * dp[1] + (k - 1) * dp[2];
            dp[1] = dp[2];
            dp[2] = dp[3];
        }
        return dp[3];
    }
}