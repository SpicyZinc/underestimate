/*
Given an unsorted array of integers, find the length of longest increasing subsequence.
For example,
given [10, 9, 2, 5, 3, 7, 101, 18], the longest increasing subsequence is [2, 3, 7, 101],
therefore the length is 4.
Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n^2) complexity.
Follow up: Could you improve it to O(nlogn) time complexity?

idea:
https://www.cnblogs.com/grandyang/p/4938187.html

dp[i]表示以nums[i]为结尾的最长递增子串的长度,
对于每一个nums[i],
从第一个数再搜索到i, if 某个数小于nums[i], update dp[i],
dp[i] = max(dp[i], dp[j] + 1),
即比较当前dp[i]的值和那个小于num[i]的数的dp值加1的大小,
不断的更新dp数组, 到最后dp数组中最大的值就是LIS的长度
*/

public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		// int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
		// int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		int[] nums = {2, 4, 3, 5, 1, 7, 6, 9, 8}; // There are 8 LIS but the solution shows only two
		int result = lis.lengthOfLIS(nums);
		System.out.println(result);
	}
	// 02/03/2019
	public int longestIncreasingSubsequence(int[] nums) {
		if (nums.length == 0 || nums == null) {
			return 0;
		}

		int n = nums.length;
		int[] dp = new int[n];
		int maxLen = 1;

		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j <= i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}

			maxLen = Math.max(maxLen, dp[i]);
		}
		
		return maxLen;
	}
	// dp[i] represents array[0, i] inclusive
	// the length of longest increasing substring until i inclusively
	public int lengthOfLIS(int[] nums) {
		int n = nums.length;
		if (n == 0) {
			return 0;
		}
		int max = 1;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}

			max = Math.max(max, dp[i]);
		}
		
		return max;
	}
	// length of longest increasing subsequence path
	public int lengthOfLIS(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		
		int max = 1;
		int[] size = new int[nums.length];
		String[] path = new String[nums.length];
		
		size[0] = 1;
		path[0] = nums[0] + " ";
		for (int i = 1; i < nums.length; i++) {
			size[i] = 1;
			path[i] = nums[i] + " ";
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					size[i] = Math.max(size[i], size[j] + 1);
					path[i] = path[j] + nums[i] + " ";
				}
			}
			if (max < size[i]) {
				max = size[i];
			}
		}
		
		for (int i = 1; i < nums.length; i++) {
			if (size[i] == max) {
				System.out.println("Longest Increasing Subsequence: " + path[i]);
			}
		}

		return max;
	}
	// dfs + memoization === dp
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int[][] memo = new int[nums.length + 1][nums.length];
		for (int[] a : memo) {
			Arrays.fill(a, -1);
		}
		
		return lengthOfLIS(nums, -1, 0, memo);
	}

	public int lengthOfLIS(int nums[], int prevPos, int pos, int[][] memo) {
		int size = nums.length;

		if (pos == size) {
			return 0;
		}

		if (memo[prevPos + 1][pos] > -1) {
			return memo[prevPos + 1][pos];
		}

		// two cases
		// take current number
		// not take current number
		
		int taken = 0;
		if (prevPos < 0 || nums[pos] > nums[prevPos]) {
			taken = lengthOfLIS(nums, pos, pos + 1, memo) + 1;
		}
		int nottaken = lengthOfLIS(nums, prevPos, pos + 1, memo);

		return memo[prevPos + 1][pos] = Math.max(taken, nottaken);
	}

	// 20 / 24 test cases passed
	// failed [10,9,2,5,3,4]
	public int lengthOfLIS(int[] nums) {
		int n = nums.length;
		if (n == 0) {
			return 0;
		}
		int max = 1;
		for (int i = 0; i < n; i++) {
			int curr = nums[i];
			int cnt = 1;
			for (int j = i + 1; j < n; j++) {
				if (nums[j] > curr) {
					curr = nums[j];
					cnt++;
				}
				max = Math.max(max, cnt);
			}
		}

		return max;
	}
	// binary search
	public int lengthOfLIS(int[] nums) {
		if (nums.length == 0) return 0;
		int len = nums.length;
		int[] seqEnd = new int[len + 1];
		seqEnd[1] = 0;
		int lisLen = 1;
		for (int i = 1; i < len; i++) {
			int pos = findPos(nums, seqEnd, lisLen, i);
			seqEnd[pos] = i;
			if (pos > lisLen) {
				lisLen = pos;
			}
		}

		return lisLen;
	}

	public int findPos(int[] nums, int[] seqEnd, int lisLen, int index) {
		int start = 1;
		int end = lisLen;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (nums[index] == nums[seqEnd[mid]]) {
				return mid;
			} else if (nums[index] > nums[seqEnd[mid]]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return start;
	}
}