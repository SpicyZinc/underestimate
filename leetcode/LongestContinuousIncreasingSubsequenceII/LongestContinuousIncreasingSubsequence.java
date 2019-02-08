/*
Give an integer array，find the longest increasing continuous subsequence in this array.

An increasing continuous subsequence:
Can be from right to left or from left to right.
Indices of the integers in the subsequence should be continuous.

Example 1: 
Input: [5, 4, 2, 1, 3]
Output: 4
Explanation:
For [5, 4, 2, 1, 3], the LICS  is [5, 4, 2, 1], return 4.

Example 2: 
Input: [5, 1, 2, 3, 4]
Output: 4
Explanation:
For [5, 1, 2, 3, 4], the LICS  is [1, 2, 3, 4], return 4.

Challenge
O(n) time and O(1) extra space.

idea:
lintcode version
note could be either way from both ends, and increasing

1.
using 2 vectors f[] for ascending and g[] for descending
initialization f(n, 1), g(n, 1)
f[i] = f[i - 1] + 1 (if f[i -1] <f[i]) else 1
g[i] = g[i - 1] + 1 (if g[i-1] > g[i]) else 1

*/

class LongestContinuousIncreasingSubsequence {
	public int longestIncreasingContinuousSubsequence(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        
		int n = nums.length;

		int[] ascending = new int[n];
		int[] descending = new int[n];

		Arrays.fill(ascending, 1);
		Arrays.fill(descending, 1);
		
		int maxVal = 1;
		for (int i = 1; i < n; i++) {
			if (nums[i - 1] < nums[i]) {
				ascending[i] = ascending[i - 1] + 1;
			}

			if (nums[i - 1] > nums[i]) {
				descending[i] = descending[i - 1] + 1;
			}

			maxVal = Math.max(maxVal, Math.max(ascending[i], descending[i]));
		}

		return maxVal;
	}

	public int longestIncreasingContinuousSubsequence(int[] nums) {
		if (nums.length == 0 || nums == null) {
			return 0;
		}

		int n = nums.length;

		int ascendingCnt = 1;
		int descendingCnt = 1;
		int maxVal = 1;

		for (int i = 1; i < n; i++) {
			if (nums[i - 1] < nums[i]) {
				ascendingCnt += 1;
			} else {
				ascendingCnt = 1;
			}

			if (nums[i - 1] > nums[i]) {
				descendingCnt += 1;
			} else {
				descendingCnt = 1;
			}

			maxVal = Math.max(maxVal, Math.max(ascendingCnt, descendingCnt));
		}

		return maxVal;
	}


	public int longestIncreasingContinuousSubsequence(int[] nums) {
		int n = nums.length;

		int maxLen1 = 0;
		int cnt = 0;

		for (int i = 0; i < nums.length; i++) {
			if (i == 0 || nums[i - 1] < nums[i]) {
				maxLen1 = Math.max(maxLen1, ++cnt);
			} else {
				cnt = 1;
			}
		}

		int maxLen2 = 0;
		cnt = 0;

		for (int i = 0; i < nums.length; i++) {
			if (i == 0 || nums[i - 1] > nums[i]) {
				maxLen2 = Math.max(maxLen2, ++cnt);
			} else {
				cnt = 1;
			}
		}


		return Math.max(maxLen1, maxLen2);
    }
}