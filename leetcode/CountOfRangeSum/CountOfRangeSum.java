/*
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.

idea:
http://blog.csdn.net/zdavb/article/details/51842977
1. naive 2 for loop O(n^2)
2. binary search
these two are all based on accumulative sum
3. https://discuss.leetcode.com/topic/33738/share-my-solution
*/

public class CountOfRangeSum {
	// TLE
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
        	sums[i] = sums[i - 1] + nums[i];
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
        	for (int j = i; j < nums.length; j++) {
        		long temp = sums[j] - sums[i] + nums[i];
        		if (temp >= lower && temp <= upper) {
        			count++;
        		}
        	}
        }
        return count;
    }
}


public class CountOfRangeSum {
	// passed oj
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        long[] sums = new long[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i-1] + nums[i];
        }

        return countNum(nums, sums, 0, n - 1, lower, upper);
    }

    private int countNum(int[] nums, long[] sums, int left, int right, int lower, int upper) {
        if (left == right) {
            if (nums[left] >= lower && nums[right] <= upper) {
                return 1;
            }
            return 0;
        }
        int mid = (left + right) / 2;
        int total = 0;
        for (int i = left; i <= mid; i++) {
            for (int j = mid + 1; j <= right; j++) {
                long tmpNum = sums[j] - sums[i] + nums[i];
                if (tmpNum >= lower && tmpNum <= upper) {
                   	total++;
                }
            }
        }

        return total + countNum(nums, sums, left, mid, lower, upper) + countNum(nums, sums, mid + 1, right, lower, upper);
    }
}