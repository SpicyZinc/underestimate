/*
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

idea:
1. my solution is wasting time,
create another sum array to hold sum to i so far, note length is 1 greater than nums
since this is continuous subarrays

2. if want to save time, waste space
hashmap sum -> frequency, how many sum, how many possibilities
*/
public class SubarraySumEqualsK {
    // 262 ms
    public int subarraySum(int[] nums, int k) {
    	if (nums.length == 0 || nums == null) {
    		return 0;
    	}
    	int n = nums.length;
    	int[] sum = new int[n + 1];
    	for (int i = 1; i < sum.length; i++) {
    		sum[i] = sum[i - 1] + nums[i - 1];
    	}
    	int cnt = 0;
    	for (int i = 0; i <= n; i++) {
    		for (int j = i + 1; j <= n; j++) {
	    		if ( sum[j] - sum[i] == k ) {
	    			cnt++;
	    		}
    		}
    	}

    	return cnt;
    }
    // 198 ms
    public int subarraySum(int[] nums, int k) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int idx = i;
            int sum = 0;
            while (idx < nums.length) {
                sum += nums[idx++];
                if (sum == k) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
    // 37 ms
    public int subarraySum(int[] nums, int k) {
        int cnt = 0;
        int sum = 0;
        // sum : appearence frequency hash
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        // note, don't forget initialization
        hm.put(0, 1);
        for (int num : nums) {
            sum += num;
            // sum - (sum - k) == k
            if ( hm.containsKey(sum - k) ) {
                cnt += hm.get(sum - k);
            }
            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }
}