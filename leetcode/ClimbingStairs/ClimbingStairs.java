/*
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Note: Given n will be a positive integer.

idea: transformation of Fibonacci 
1. 1D DP
2. recursion
*/
public class ClimbingStairs {
	public static void main(String[] args){
		long start = System.currentTimeMillis();
		System.out.println("Ways of 35 steps to walk via regular recursion " + ways(35) + " Time consumed: " + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		System.out.println("Ways of 35 steps to walk via Iteration (DP) " + iterativeWays(35) + " Time consumed: " + (System.currentTimeMillis() - start));

		for (int i = 1; i <= 10; i++) {
			System.out.printf("ClimbStairsOf(%d)Steps == %d ways\n", i, climb(i));
		}
	}
	// best iterative method
	public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        int a = 1;
        int b = 2;
        int c = a + b;
        int i = 3;
        
        while (i < n) {
            a = b;
            b = c;
            c = a + b;
            
            i++;
        }
        
        return c;
    }
    // typical DP
	public int climbStairs(int n) {
		if (n == 1) return 1;

		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		return dp[n];
	}
	// bottom-up iterative dynamic programming
	public int climbStairs(int n) {
		if (n == 1) return 1;
		if (n == 2) return 2;

        int[] prev = {1, 2};
        // meaning the "current" number of steps to be finished
		int current = 2;
		while (current < n) {
			int preTotal = prev[0] + prev[1];
			prev[0] = prev[1];
			prev[1] = preTotal;
			
			current++;
		}
		
		return prev[1];
	}
	// top-down recursion
	public int climbStairs(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		if (n == 2) return 2;
		
		return climbStairs(n-1) + climbStairs(n-2);
	}
}