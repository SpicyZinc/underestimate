/*
Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.
An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.

Example 1:
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

Example 2:
Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.

Example 3:
Input: nums = [23,2,6,4,7], k = 13
Output: false

Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= sum(nums[i]) <= 231 - 1
1 <= k <= 231 - 1

idea:
1. sum[], accumulate sum to current number, note the array length should be length + 1
2. hashmap
*/

public class ContinuousSubarraySum {
	// Tue May 25 00:39:16 2021
	// TLE
	public boolean checkSubarraySum(int[] nums, int k) {
		int n = nums.length;
		int[] sum = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			sum[i] = sum[i - 1] + nums[i - 1];
		}
		
		for (int i = 0; i <= n; i++) {
			for (int j = i + 2; j <= n; j++) {
				if ((sum[j] - sum[i]) == 0 && k == 0) {
					return true;
				}
				if (k != 0 && (sum[j] - sum[i]) % k == 0) {
					return true;
				}
			}
		}

		return false;
	}

	// map
	public boolean checkSubarraySum(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);

		int sum = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			if (k != 0) {
				sum = sum % k;
			}

			if (map.containsKey(sum)) {
				if (i - map.get(sum) > 1) {
					return true;
				}
			} else {
				map.put(sum, i);
			}
		}

		return false;
	}
}
