/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5

Explanation: 
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
There are 5 ways to assign symbols to make the sum of nums be target 3.

Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.

idea:
DFS Solution is the only way I think of at first thought

*/
public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        int[] cnt = new int[1];
        dfs(nums, 0, 0, cnt, S);
        return cnt[0];
    }
    
    public void dfs(int[] nums, int pos, int sum, int[] cnt, int S) {
        if (pos == nums.length) {
            if (sum == S) cnt[0]++;
            return;    
        }
        
        dfs(nums, pos + 1, sum + nums[pos], cnt, S);
        dfs(nums, pos + 1, sum - nums[pos], cnt, S);
    }
}