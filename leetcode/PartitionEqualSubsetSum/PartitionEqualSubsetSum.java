/*
Given a non-empty array containing only positive integers,
find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Both the array size and each of the array element will not exceed 100.

Example 1:
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

idea:
find the subset with a sum equals a given target.
half of the total sum

dfs, if can be partitioned, sum must be an even number
subtract one by one from the sum, if final zero, return true
otherwise, cannot be partitioned
*/

public class PartitionEqualSubsetSum {
	// Fri Apr 26 01:48:53 2024
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        // find sum of all array elements
        for (int num : nums) {
            totalSum += num;
        }
        // if totalSum is odd, it cannot be partitioned into equal sum subset
        if (totalSum % 2 != 0) return false;
        int subSetSum = totalSum / 2;
        int n = nums.length;
        boolean dp[][] = new boolean[n + 1][subSetSum + 1];
        dp[0][0] = true;
        // bottom up DP
        for (int i = 1; i <= n; i++) {
            int curr = nums[i - 1];
            for (int j = 0; j <= subSetSum; j++) {
                if (j < curr) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || (dp[i - 1][j - curr]);
                }
            }
        }

        return dp[n][subSetSum];
    }

	public boolean canPartition(int[] nums) {
		if (nums.length == 0 || nums == null) {
			return false;
		}

		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		if (sum % 2 == 1) {
			return false;
		}
		
		return dfs(nums, 0, sum / 2);
	}

	public boolean dfs(int[] nums, int pos, int sum) {
		if (sum == 0) {
			return true;
		} else if (sum < 0) {
			return false;
		}

		for (int i = pos; i < nums.length; i++) {
			if (dfs(nums, i + 1, sum - nums[i])) {
				return true;
			}
		}

		return false;
	}
}
