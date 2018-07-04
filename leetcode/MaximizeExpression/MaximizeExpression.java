/*
You are given an arithmetic expression containing n real numbers and n − 1 operators, each
either + or ×. Your goal is to perform the operations in an order that maximizes the value of
the expression. That is, insert n − 1 pairs of parentheses into the expression so that its value
is maximized.

idea:
http://www.voidcn.com/article/p-meqmxzif-b.html
dp is to solve problem by solving subproblem
not very sure of (i - j <= 2)
first j maximum value either + (i - j) of values addition 
						or * (i - j) of values addition
*/

class MaximizeExpression {
	public static void main(String[] args) {
		MaximizeExpression eg = new MaximizeExpression();
		// String s = "01231";
		// String s = "18179";
		// String s = "891";
		String s = "5111111";
		int max = eg.getMax(s);
		int[] nums = {5, 1, 1, 1, 1, 1, 1};
		int max2 = eg.solve(nums);
		System.out.println("max == " + max);
		System.out.println("max2 == " + max2);
	}

	public int solve(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        int[][] dp = new int[n + 1][n + 1];
        // 初始化累加数组, 还有不用乘号的情况
        for (int idx = 1; idx <= n; idx++) {
            sum[idx] = sum[idx - 1] + nums[idx - 1];
            dp[idx][1] = sum[idx];
        }
        int max = dp[n][0];
        // 对于总长为numOfDigitsInTotal(i) 的数字序列
        for (int i = 2; i <= n; i++) {
            // 前numOfDigitsWithMult(j) 个数字可以包含乘号来计算的话
            for (int j = 2; j <= i; j++) {
                // 从splitPointBetweenAddAndMult(k) 开始只用加号的话, 求最大值
                for (int k = j; k <= i; k++){
                    dp[i][j] = Math.max(dp[i][j], dp[k - 1][j - 1] * (sum[i] - sum[k - 1]));
                }
                max = Math.max(max, dp[n][j]);
            }
        }

        return max;
    }

	public int getMax2(String s) {
		if (s.length() == 0 || s == null) {
			return 0;
		}
		s.replaceAll("0", "");

		int len = s.length();
		if (len <= 1) {
			return Integer.parseInt(s);
		}

		int[] mem = new int[len];
	    mem[0] = s.charAt(0) - '0';
	    for (int i = 1; i < len; i++) {
	        mem[i] = 0;
	        int sum = 0;
	        // 不用走到i 变成0 j距离i最多2, 就已经可以创造最大值了
	        for (int j = i; j >= 0 && i - j <= 2; j--) {
	            sum += s.charAt(j) - '0';
	            int max = sum;
	            if (j >= 1 && mem[j - 1] >= 1) {
	            	max = mem[j - 1] * sum;
	            }
	            // * or +, >= 1 * for sure, 
	            mem[i] = Math.max(mem[i], max);
	        }
	    }

	    return mem[len - 1];
	}
	// self is wrong
	public int getMax(String s) {
		if (s.length() == 0 || s == null) {
			return 0;
		}
		int len = s.length();
		if (len == 1) {
			return s.charAt(0) - '0';
		}

		int[] dp = new int[len];
		int first = s.charAt(0) - '0';
		int second = s.charAt(1) - '0';
		dp[0] = first;
		dp[1] = Math.max(first + second, first * second);

		for (int i = 2; i < len; i++) {
			int prev = s.charAt(i - 1) - '0';
			int curr = s.charAt(i) - '0';

			int a = dp[i - 1] * curr;
			int b = dp[i - 1] + curr;
			// go back by 2 and use +
			int c = dp[i - 2] + prev + curr;
			int d = dp[i - 2] + prev * curr;
			// go back by 2 and use *
			int e = dp[i - 2] * (prev + curr);
			int f = dp[i - 2] * prev * curr;

			dp[i] = maxOfSix(a, b, c, d, e, f);
		}

		return dp[len - 1];
	}

	public int maxOfSix(int a, int b, int c, int d, int e, int f) {
		return Math.max(a, Math.max(b, Math.max(c, Math.max(d, Math.max(e, f)))));
	}
}