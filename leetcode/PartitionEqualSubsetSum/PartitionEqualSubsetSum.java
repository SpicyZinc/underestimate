/*
Given a non-empty array containing only positive integers,
find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Both the array size and each of the array element will not exceed 100.

Example 1:
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

idea:
dfs, if can be partitioned, sum must be an even number
subtract one by one from the sum, if final zero, return true
otherwise, cannot be partitioned

*/

public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        if (nums.length == 0 || nums == null) {
        	return false;
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
        	sum += nums[i];
        }

        if (sum % 2 == 1) {
        	return false;
        }
        
        return dfs(nums, 0, sum / 2);
    }

    public boolean dfs(int[] nums, int start, int sum) {
    	if (sum == 0) {
    		return true;
    	} else if (sum < 0) {
    		return false;
    	}

    	for (int i = start; i < nums.length; i++) {
    		if (dfs(nums, i + 1, sum - nums[i])) {
    		    return true;
    		}
    	}

    	return false;
    }
}
