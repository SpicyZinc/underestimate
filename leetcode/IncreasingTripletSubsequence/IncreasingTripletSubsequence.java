/*
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.

idea:
continually increase min and secondMin
see if there is a third number greater than secondMin;
if there is, min < secondMin < 3rd min num
then we get the answer
*/

public class IncreasingTripletSubsequence {
	public boolean increasingTriplet(int[] nums) {
		int min = Integer.MAX_VALUE;
		int secondMin = Integer.MAX_VALUE;

		for (int num : nums) {
			if (num <= min) {
				min = num;
			} else if (num <= secondMin && secondMin > min) {
				secondMin = num;
			} else {
				return true;
			}
		}

		return false;
	}
}

