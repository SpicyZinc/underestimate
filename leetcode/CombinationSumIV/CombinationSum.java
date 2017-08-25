/*

Given an integer array with all positive numbers and no duplicates,
find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?

idea:
https://discuss.leetcode.com/topic/52302/1ms-java-dp-solution-with-detailed-explanation

dp[i] is target == i, the number of solutions

In the example, we can find the # of combinations of 4 with the # of combinations of 3(4 - 1), 2(4- 2) and 1(4 - 3).
As a result, comb[4] = comb[4-1] + comb[4-2] + comb[4-3] = comb[3] + comb[2] + comb[1].
Then think about the base case. Since if the target is 0, there is only one way to get zero, which is using 0, we can set comb[0] = 1.
Note: combs[0] = 1;

a better thinking way:

when i ==nums[k], it means
there is one target in array, no need to calculate, just add 1

follow-up:
restrict the times of every number being used

*/
public class CombinationSum {
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target < 0) {
            return 0;
        }
    	int[] combs = new int[target + 1];
    	combs[0] = 1;
    	for (int i = 1; i <= target; i++) {
    		for (int j = 0; j < nums.length; j++) {
    			if (i - nums[j] >= 0) {
    				combs[i] += combs[i - nums[j]];
    			}
    		}
    	}

    	return combs[target];
    }
    // 2nd thinking way, better to understand
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int num : nums) {
                if (i > num) {
                    dp[i] += dp[i - num];
                }
                else if (i == num) {
                    dp[i] += 1;
                }
            }
        }

        return dp[dp.length - 1];
    }
}