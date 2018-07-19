/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, 
find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. 
Could you implement it using only constant extra space complexity?

idea:
since only one number is missing and the array of integers is consecutive
use Arithmetic array to calculate the sum,
then loop through the array to get another sum,
the difference between these two sums are the missing number
*/

public class MissingNumber {
    public int missingNumber(int[] nums) {
	    if (nums == null || nums.length == 0) {
	    	return 0;
	    }
	    int sum = 0;
	    for (int i = 0; i < nums.length; i++) {
	        sum = sum + nums[i];
	    }
	    // Sum of n distinct numbers = n * (n + 1) / 2;
	    int sumShouldBe = (nums.length * (nums.length + 1)) / 2;

	    return (sumShouldBe - sum);
	}
}

