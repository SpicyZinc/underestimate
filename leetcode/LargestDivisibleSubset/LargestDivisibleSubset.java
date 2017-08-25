/*
Given a set of distinct positive integers,
find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:
nums: [1,2,3]
Result: [1,2] (of course, [1,3] will also be ok)

Example 2:
nums: [1,2,4,8]
Result: [1,2,4,8]

idea:
dp, output the biggest subset, so need the pre[]
dp[] == the biggest subset size
pre[] == element index before current element which makes the biggest subset size 
so have pre[] to construct the result

Note: if b > a && b % a == 0
if c > b && c % b == 0 
it must c % a == 0
*/

public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        if (nums.length == 0) {
        	return ans;
        }

        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int[] pre = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(pre, -1);

        int max_dp = 1;
        int max_index = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    pre[i] = j;
                }
            }
            if (max_dp < dp[i]) {
                max_dp = dp[i];
                max_index = i;
            }
        }
        for (int i = max_index; i >= 0; i = pre[i]) {
            ans.add(nums[i]);
        }

        return ans;
    }
}