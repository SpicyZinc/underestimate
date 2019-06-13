/*
Given an array of integers nums and a positive integer k,
find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

Note:
1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.

idea:
最暴力最原始的 dfs 应该会
k == 1 partition to 1 subset, 就是不 partition

就是 挑着 加成 target (sum / k) 的一些数 marked as visited
剩下的就是 partition to (k - 1) parts
*/

class PartitionToKEqualSumSubsets {
	// Mon Jun 10 22:34:14 2019
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}

		if (sum % k != 0) {
			return false;
		}

		boolean[] visited = new boolean[nums.length];
		return dfs(nums, k, sum / k, 0, 0, visited);
	}

	public boolean dfs(int[] nums, int k, int target, int pos, int accSum, boolean[] visited) {
		if (k == 1) {
			return true;
		}

		if (accSum == target) {
			return dfs(nums, k - 1, target, 0, 0, visited);
		}

		for (int i = pos; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}

			visited[i] = true;
			if (dfs(nums, k, target, i + 1, nums[i] + accSum, visited)) {
				return true;
			}
			visited[i] = false;
		}

		return false;
	}

	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}

		if (sum % k != 0) {
			return false;
		}
		
		boolean[] visited = new boolean[nums.length];

		return dfs(nums, k, sum / k, 0, 0, visited);
	}

	public boolean dfs(int[] nums, int k, int target, int pos, int sum, boolean[] visited) {
		if (k == 1) {
			return true;
		}

		if (sum == target) {
			return dfs(nums, k - 1, target, 0, 0, visited);
		}

		for (int i = pos; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}

			visited[i] = true;
			if (dfs(nums, k, target, i + 1, sum + nums[i], visited)) {
				return true;
			}
			visited[i] = false;
		}

		return false;
	}
}