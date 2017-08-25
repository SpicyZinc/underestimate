/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

idea:
maxSum need to be Integer.MIN_VALUE

*/

public class MaximumSubarray  {
	public int maxSubArray(int[] nums) {
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;

		for (int num : nums) {
			sum += num;
			maxSum = Math.max(maxSum, sum); 
			if (sum < 0) {
				sum = 0;
			}
		}
 
		return maxSum;
	}

	public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum >= 0) {
                sum += nums[i];
            }
            else {
                sum = nums[i];
            }
            max = Math.max(max, sum);
        }

        return max;
    }
    // understand why this wrong
	public int maxSubArray(int[] nums) {
		int max = Integer.MIN_VALUE;
		int max_ending_here = 0;
		
		for (int num : nums) {
			max_ending_here += num;
			if (max_ending_here < 0) {
				max_ending_here = 0;
			}
			if (max < max_ending_here) {
		    	max = max_ending_here;
			}
		}

		return max;
	}
}