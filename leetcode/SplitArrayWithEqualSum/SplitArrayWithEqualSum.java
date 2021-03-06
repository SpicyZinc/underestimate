/*
Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:
1. 0 < i, i + 1 < j, j + 1 < k < n - 1
2. Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
where we define that subarray (L, R) represents a slice of the original array
starting from the element indexed L to the element indexed R.

Example:
Input: [1,2,1,2,1,2,1]
Output: True
Explanation:
i = 1, j = 3, k = 5. 
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1

Note:
1 <= n <= 2000.
Elements in the given array will be in range [-1,000,000, 1,000,000].

idea:
https://www.cnblogs.com/grandyang/p/6854492.html
换一个角度
先搜索j的位置
那么i和k的位置就可以固定在一个小的范围内了
而且可以在j的循环里面同时进行
这样就少嵌套了一个循环
所以时间复杂度会降一维度


note, 三个位置上的数字不属于任何一段
*/

public class SplitArrayWithEqualSum {
	public boolean splitArray(int[] nums) {
		int n = nums.length;

		if (n < 7) {
			return false;
		}

		// accumulative sum
		int[] sum = new int[n];
		sum[0] = nums[0];

		for (int i = 1; i < n; i++) {
			sum[i] = sum[i - 1] + nums[i];
		}

		// i, j, k 以 j 为准
		// 左右各留三个
		for (int j = 3; j < n - 3; j++) {
			Set<Integer> set = new HashSet<>();

			// 以j为界 分别loop i and k
			for (int i = 1; i < j - 1; i++) {
				int s1 = sum[i - 1];
				int s2 = sum[j - 1] - sum[i];

				if (s1 == s2) {
					set.add(s1);
				}
			}
			// 以j为界 分别loop i and k
			for (int k = j + 2; k < n - 1; k++) {
				int s3 = sum[k - 1] - sum[j];
				int s4 = sum[n - 1] - sum[k];

				if (s3 == s4 && set.contains(s3)) {
					return true;
				}
			}
		}
		
		return false;
	}
}