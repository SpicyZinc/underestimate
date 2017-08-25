/*
RangeSumQueryImmutable
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.

idea:
build up a sums array to save the sums
sums[i] nums[0] - nums[i-1] except i
*/

public class NumArray {
    private int[] sums;
    public NumArray(int[] nums) {
        sums = new int[nums.length+1];
        sums[0] = 0;
        for ( int i = 1; i < sums.length; i++ ) {
            sums[i] = sums[i-1] + nums[i-1];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j+1] - sums[i];
    }
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);