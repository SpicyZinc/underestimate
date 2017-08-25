/*
Given an array of integers, find the subarray with smallest sum.
Return the sum of the subarray.

Example
For [1, -1, -2, 1], return -3.

idea:
1. each element * -1, then the problem becomes maximum sum subarray
2. direct method like maximum sum subarray
*/

public class MinimumSubarray {
	// method 1
    public int minSubArray(List<Integer> nums) {
    	if (nums.size() == 0 || nums == null) {
    		return 0;
    	}
        List<Integer> negate = new ArrayList<Integer>();
        for (int i = 0; i < nums.size(); i++) {
        	negate.add( nums.get(i) * -1 );
        }

        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = 0; i < nums.size(); i++) {
        	int current = negate.get(i);
        	// note when to add current, sequence matters	
        	if (curSum < 0) {
        		curSum = 0;
        	}
            curSum += current;
        	maxSum = Math.max(maxSum, curSum);
        }

        return maxSum * -1;
    }
    // method 2
    public int minSubArray(List<Integer> nums) {
    	if (nums.size() == 0 || nums == null) {
    		return 0;
    	}

    	int minSum = Integer.MAX_VALUE;
        int curSumMin = 0;
        for (int i = 0; i < nums.size(); i++) {
        	int current = nums.get(i);
            curSumMin += current;
            curSumMin = Math.min(current, curSumMin);
            minSum = Math.min(minSum, curSumMin);
        }

        return minSum;
    }
}