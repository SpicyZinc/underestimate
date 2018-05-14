/*
Find the contiguous subarray within an array (containing at least one number) 
which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

idea:
note: this is to get consecutive subarray

1. handle all the negative input
2. negative times negative makes the maximum product
*/

public class MaximumProductSubarray {
    public static void main(String[] args) {
        MaximumProductSubarray eg = new MaximumProductSubarray();
        // int[] A = {-4, -3}; // 12
        int[] A = {-2, 0, -1}; // 0
        int max = eg.maxProduct(A);
        System.out.println(max);
    }
    // easy to understand
    public int maxProduct(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        
        int max = Integer.MIN_VALUE;
        int max_ending_here = 1;
        int min_ending_here = 1;
        
        for (int num : nums) {
            int prevMax = Math.max(max_ending_here, 1);
            if (num > 0) {
                max_ending_here = prevMax * num;
                min_ending_here = min_ending_here * num;
            } else {
                max_ending_here = min_ending_here * num;
                min_ending_here = prevMax * num;
            }
            // update max
            max = Math.max(max, max_ending_here);
        }
        
        return max;
    }
    // recent easy solution
    public int maxProduct(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        int max_ending_here = nums[0];
        int min_ending_here = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max_ending_here;
            max_ending_here = Math.max(nums[i], Math.max(max_ending_here * nums[i], min_ending_here * nums[i]));
            min_ending_here = Math.min(nums[i], Math.min(temp * nums[i], min_ending_here * nums[i]));
            max = Math.max(max, max_ending_here);
        }

        return max;
    }
}