/*
Given an array of n positive integers and a positive integer s, 
find the minimal length of a subarray of which the sum ≥ s. 
If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

If you have figured out the O(n) solution, 
try coding another solution of which the time complexity is O(nlogn).

idea:
time: O(n)
1. maintain a sliding window

two pointers, left and right to maintain a sliding window
left = right = 0;
while loop to move right pointer towards the end of the array
add element to sum
while sum >= s
sum -= nums[left]
left++, move left towards the end of the array

2. accumulative sum
*/

public class MinimumSizeSubarraySum {
	// lintcode version
	public int minimumSize(int[] nums, int s) {
		int min = Integer.MAX_VALUE;
		int left = 0;
		int sum = 0;
		for (int right = 0; right < nums.length; right++) {
			sum += nums[right];
			
			while (sum >= s) {
				min = Math.min(min, right - left + 1);
				sum -= nums[left++];
			}
		}
		
		return min == Integer.MAX_VALUE ? -1 : min;
	}
	// sliding window
	// 01/26/2019
	public int minSubArrayLen(int s, int[] nums) {
		if (s == 0 || nums.length == 0 || nums == null) {
			return 0;
		}

		int left = 0;
		int right = 0;
		int min = nums.length + 1;
		int sum = 0;

		while (right < nums.length) {
			sum += nums[right];
			while (sum >= s) {
				min = Math.min(min, right - left + 1);
				sum -= nums[left++];
			}
			right++;
		}
		
		return min == nums.length + 1 ? 0 : min;
	}
	// accumulative sum
	public int minSubArrayLen(int s, int[] nums) {
		if (s == 0 || nums.length == 0 || nums == null) {
			return 0;
		}

		int n = nums.length;
		int[] sum = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			sum[i] = nums[i - 1] + sum[i - 1];
		}

		int min = n + 1;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				if (sum[j] - sum[i] >= s) {
					min = Math.min(min, j - i);
				}
			}
		}

		return min == n + 1 ? 0 : min;
	}

	// binary search version
	public int minSubArrayLenBinarySearch(int s, int[] nums) {
		if (null == nums || nums.length == 0) {
			return 0;
		}

		int min = nums.length + 1;
		int[] sum = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			sum[i + 1] = sum[i] + nums[i];
			if (sum[i + 1] >= s) {
				int left = binarySearch(sum, sum[i + 1] - s, 0, i + 1);
				min = Math.min(min, i + 1 - left);
			}
		}

		return min == nums.length + 1 ? 0 : min;
	}
	 
	private int binarySearch(int[] nums, int target, int start, int end) {
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] >= target) {
				end = mid;
			} else {
				start = mid;
			}
		}
		if (nums[end] <= target) {
			return end;
		} else {
			return start;
		}
	}
}