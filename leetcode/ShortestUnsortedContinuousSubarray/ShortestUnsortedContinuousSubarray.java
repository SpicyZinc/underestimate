/*
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order,
then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.

idea:
1. sort array first, then compare and find mismatch
2. see below
*/

public class ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        ShortestUnsortedContinuousSubarray eg = new ShortestUnsortedContinuousSubarray();
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        int len = eg.findUnsortedSubarray(nums);

        System.out.println(len);
    }
    // method 1
    public int findUnsortedSubarray(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        int n = nums.length;
        int start = n - 1;
        int end = 0;
        for (int i = 0; i < n; i++) {
            System.out.println(copy[i]);
            if (nums[i] != copy[i]) {start = i; break;}
        }        
        for (int i = 0; i < n; i++) {
            if (nums[n - 1 - i] != copy[n - 1 - i]) {end = n - 1 - i; break;}
        }

        if (start == n - 1 && end == 0) {
            return 0;
        }
        return end - start + 1;
    }
    // method 2
	public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        // initialize this way, if no change to start and end, then the array already sorted
        int start = -1;
        int end = -2;
        int max = nums[0];
        int min = nums[n - 1];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[n - 1 - i]);
            // find last decreasing point
            if (max > nums[i]) end = i; 
            // find last increasing point
            if (min < nums[n - 1 - i]) start = n - 1 - i;
        }
        
        return end - start + 1;
    }
}