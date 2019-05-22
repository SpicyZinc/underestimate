/*
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

idea:
binary search since it is sorted
Note: timely break is important
*/

public class TwoSum {
	public int[] twoSum(int[] numbers, int target) {
		int[] answers = new int[2];
		int left = 0;
		int right = numbers.length - 1;

		while (left < right) {
			int sum = numbers[left] + numbers[right];

			if (sum > target) {
				right--;
			} else if (sum < target) {
				left++;
			} else {
				answers[0] = left + 1;
				answers[1] = right + 1;

				break;
			}
		}

		return answers;
	}
}