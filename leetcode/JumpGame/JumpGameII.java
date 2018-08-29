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

public class JumpGameII {
	// best version
	public int jump(int[] nums) {
		int n = nums.length;
        int last = 0; // 上一个可以reach到的 position
        int minSteps = 0;
        int maxReach = 0;

        for (int i = 0; i < n - 1; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);
            // Once the current point reaches curEnd, then trigger another jump
            if (i == last) {
                minSteps++;
                last = maxReach;
                if (last == n - 1) {
                	break;
                }
            }
        }

        return minSteps;
    }

	public int jump(int[] A) {
		if (A.length <= 1) {
			return 0;
		}

		int maxReach = A[0];
		int steps = A[0];
		int jumps = 1;
		// 某个 maxReach 步数用光了 才跳一步
		for (int i = 1; i < A.length; i++) {
			if (i == A.length - 1) {
				return jumps;
			}
			if (i + A[i] > maxReach) {
				maxReach = i + A[i];
			}
			steps--;
			if (steps == 0) {
				jumps++;
				steps = maxReach - i;
			}
		}

		return jumps;
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
	
	// dynamic programming
	public int jump_dp(int[] A) {
		if (A.length <= 1) {
			return 0;
		}

		int[] MinJumps = new int[A.length];
		MinJumps[0] = 0;
		for (int i = 1; i < A.length; i++) {
			int Min = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if (MinJumps[j] >= Min) {
					continue;
				}
				if (A[j] >= i - j) {
					Min = Math.min(Min, MinJumps[j] + 1);
				}
			}
			MinJumps[i] = Min;
		}

		return MinJumps[MinJumps.length - 1];
	}
}
