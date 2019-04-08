/*
Given an array nums of integers, you can perform operations on the array.
In each operation, you pick any nums[i] and delete it to earn nums[i] points.
After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

You start with 0 points. Return the maximum number of points you can earn by applying such operations.

Example 1:
Input: nums = [3, 4, 2]
Output: 6
Explanation: 
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.

Example 2:
Input: nums = [2, 2, 3, 3, 3, 4]
Output: 9
Explanation: 
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.

Note:
The length of nums is at most 20000.
Each element nums[i] is an integer in the range [1, 10000].

idea:
the same as house rob
小偷不能偷相邻的房子,
这道题相邻的数字不能累加积分 而是delete
一个道理
*/

class DeleteAndEarn {
	// 03/10/2019
	public int deleteAndEarn(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
        
        if (nums.length == 1) {
            return nums[0];
        }

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int num : nums) {
			min = Math.min(min, num);
			max = Math.max(max, num);
		}

		int n = max - min + 1;
		int[] sums = new int[n];
		for (int num : nums) {
			int idx = num - min;
			sums[idx] += num;
		}
        // reuse sums array as dp[]
		sums[1] = Math.max(sums[0], sums[1]);
		for (int i = 2; i < n; i++) {
			sums[i] = Math.max(sums[i - 1], sums[i - 2] + sums[i]);
		}

		return sums[n - 1];
	}

	public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int n = max - min + 1;
        // sums实际上相当于建立了数字和其总积分的映射
        int[] sums = new int[n];
        for (int i = 0; i < n; i++) {
        	int num = i + min;
            sums[i] = (num) * (map.getOrDefault(num, 0));
        }
        // till i, the max score obtained, it is accumulative value
        int[] dp = new int[n];
        dp[0] = sums[0];

        for (int i = 1; i < n; i++) {
            if (i == 1) {
            	dp[i] = Math.max(sums[0], sums[1]);
            } else {
            	dp[i] = Math.max(sums[i] + dp[i - 2], dp[i - 1]);
            }
        }

        return dp[n - 1];
    }
}