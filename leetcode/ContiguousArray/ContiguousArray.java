/*
Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

Example 1:
Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.

Example 2:
Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Constraints:
1 <= nums.length <= 10^5
nums[i] is either 0 or 1.

idea:
到i为止 sum 这个sum 就像open close 括号的个数
如果某两个index 相等 说明中间 正负抵消 1 和 0的个数抵消
不断的求最大值
注意没有sum==0 有可能不是从头开始这种正负抵消 1 和 0的subarray
*/

public class ContiguousArray {
    // public static void main(String[] args) {
    //     Solution eg = new Solution();
    //     int[] nums = {1,0,0,0,0,1,1,1};
    //     int result = eg.findMaxLength(nums);
    //     System.out.println(result);
    // }
    // diff[i] is "count of 1s" minus "count of 0s" so far.

    public int findMaxLength(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        int sum = 0;
        int maxlen = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if (sum == 0) {
                maxlen = i + 1;
            } else if (mp.containsKey(sum)) {
                maxlen = Math.max(maxlen, i - mp.get(sum));
            } else {
                mp.put(sum, i);
            }
        }

        return maxlen;
    }
}