/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Your goal is to reach the last index in the minimum number of jumps.

Given array A = [2,3,1,1,4]
The minimum number of jumps to reach the last index is 2. 
(Jump 1 step from index 0 to 1, then 3 steps to the last index.) 

idea:
1. https://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
O(n)
2. DP
*/

public class JumpGame {
	// Sat Jun 15 15:12:42 2019
	// i now think this is easy to understand
	// steps = maxReach - i
	public int jump(int[] nums) {
		if (nums.length <= 1) {
			return 0;
		}

		// 已经跳了一次
		int jumps = 1;
		// 一个 jump 包含的步数 steps
		// i 增加 会消耗掉 steps
		int steps = nums[0];
		int maxReach = 0 + nums[0]; // index + nums[index]
		
		for (int i = 1; i < nums.length; i++) {
			if (i == nums.length - 1) {
				return jumps;
			}
			
			// update maxReach
			maxReach = Math.max(maxReach, i + nums[i]);
			
			steps--;
			
			if (steps == 0) {
				// steps == 0, need to another jump
				jumps++;
				steps = maxReach - i;
			}
		}
		
		return jumps;
	}

	// dp[i] The minimum number of jumps to reach i
	// why TLE
	public int jump(int[] nums) {
		if (nums.length <= 1) {
			return 0;
		}

		int size = nums.length;
		int[] dp = new int[size];

		for (int i = 1; i < size; i++) {
			// min number of jumps to reach i
			int min = Integer.MAX_VALUE;

			for (int j = 0; j < i; j++) {
				// if min number of jumps to j < i already > steps to i, no need to compare
				if (dp[j] >= min) {
					continue;
				}
				if (j + nums[j] >= i) {
					min = Math.min(min, dp[j] + 1);
				}
			}

			dp[i] = min;
		}

		return dp[dp.length - 1];
	}

	// best version, greedy algorithm
	public int jump(int[] nums) {
		int n = nums.length;
		int prevReachedPos = 0; // 上一个可以reach到的 position

		int minSteps = 0;
		int maxReach = 0;

		for (int i = 0; i < n - 1; i++) {
			maxReach = Math.max(maxReach, i + nums[i]);
			// Once the current point reaches curEnd, then trigger another jump
			if (i == prevReachedPos) {
				minSteps++;
				prevReachedPos = maxReach;

				if (prevReachedPos == n - 1) {
					break;
				}
			}
		}

		return minSteps;
	}

	/*
	each time select the max reachable position to jump to
	then for loop from last max+1 through this new max
	this for loop is one jump, anywhere in this for loop we can choose to be startPosition to jump start
	of course this way can guarantee the minimum jump count to reach last index
	because each step is maximum jump length
	*/
	public int jump(int[] A) {
		int jumpCnt = 0;
		int startPos = 0;
		int max = 0;
		// int max = 0 + A[0]; // wrong
		int newMax = 0;
		while (max < A.length - 1) {
			jumpCnt++;
			for (int i = startPos; i <= max; i++) {
				newMax = Math.max(newMax, i + A[i]);
			}
			startPos = max + 1;
			// cannot reach last index
			// cannot jump, always stay where it is, A[i] == 0
			if (newMax <= max) {
				return -1;
			}
			max = newMax;
		}

		return jumpCnt;
	}
}
