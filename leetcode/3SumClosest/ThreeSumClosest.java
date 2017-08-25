/*
Given an array S of n integers, find three integers in S 
such that the sum is closest to a given number, target. 
Return the sum of the three integers. 
You may assume that each input would have exactly one solution.

idea:
1. brute force to find

2. sort the array first
for (int i = 0; i < num.length; i++) {
	for (int j = i + 1, k = num.length - 1; j < k; ) {
	}
}

*/
public class ThreeSumClosest {
    // OJ passed
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                int sum = nums[i] + nums[j] + nums[k];
                closestSum = Math.abs(sum - target) < Math.abs(closestSum - target) ? sum : closestSum;
                if (sum < target) {
                    j++;
                }
                else if (sum > target) {
                    k--;
                }
                else {
                    return target;
                }
            }    
        }

        return closestSum;
    }
    // Time Limit Exceeded
    public int threeSumClosest(int[] num, int target) {
		int min = Integer.MAX_VALUE;
		int res = 0;
        for (int i=0; i<num.length-2; i++) {
			for (int j=i+1; j<num.length-1; j++) {
				for (int k=j+1; k<num.length; k++) {
					int tmp = num[i] + num[j] + num[k];
					if (min > Math.abs(tmp - target)) {
						min = Math.abs(tmp - target);
						res = tmp;
					}
				}
			}
		}

		return res;
    }
}