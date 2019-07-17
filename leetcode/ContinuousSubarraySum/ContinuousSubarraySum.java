/*
Given a list of non-negative numbers and a target integer k,
write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k,
that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7], k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

Example 2:
Input: [23, 2, 6, 4, 7], k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.

Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

idea:
accumulate sum to current number, note the array length should be length + 1
*/

public class ContinuousSubarraySum {
	public boolean checkSubarraySum(int[] nums, int k) {
		int[] sum = new int[nums.length + 1];

		for (int i = 1; i <= nums.length; i++) {
			sum[i] = sum[i - 1] + nums[i - 1];
		}
		
		for (int i = 0; i <= nums.length; i++) {
			for (int j = i + 2; j <= nums.length; j++) {
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