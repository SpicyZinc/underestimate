/*
Given n items with size nums[i] which an integer array and all positive numbers. An integer target denotes the size of a backpack.
Find the number of possible fill the backpack.
note: Each item may only be used once

Example
Given candidate items [1,2,3,3,7] and target 7,

A solution set is: 
[7]
[1, 3, 3]
return 2

idea:
dp[i] when target is i, # of ways to fill the backpack

*/

public class Backpack {
    /**
     * @param nums: an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
	public int backPackV(int[] nums, int target) {
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 1; j <= target; j++) {
				if (nums[i] == j) {
					// one more way to reach j since nums[i] == j
					dp[j] += 1;
				} else if (nums[i] < j) {
					// ways to reach (j - nums[i]) will be # to reach j, whatever # of ways + nums[i]
					dp[j] += dp[j - nums[i]];
				}
			}
		}

		return dp[target];
	}
}