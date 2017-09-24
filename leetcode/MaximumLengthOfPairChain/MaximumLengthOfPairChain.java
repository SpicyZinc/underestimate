/*
You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]

Note:
The number of given pairs will be in the range [1, 1000].

idea:
similar to CourseScheduleIII/CourseSchedule.java
note: depends how to sort the pairs array
not sort based on the 1st element, then on 2nd element
sort only on 2nd element
because [-9, 8] if no following pair will be bigger than 8
*/
import java.util.*;

class MaximumLengthOfPairChain {
	public static void main(String[] args) {
		MaximumLengthOfPairChain eg = new MaximumLengthOfPairChain();
		int[][] pairs = {
			{-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}
		};

		int maxLen = eg.findLongestChain(pairs);
		System.out.println(maxLen);
	}
	public int findLongestChain(int[][] pairs) {
		Arrays.sort(pairs, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
                return a[1] - b[1];
				// if (a[0] == b[0]) {
				// 	return a[1] - b[1];
				// } else {
				// 	return a[0] - b[0];
				// }
			}
		});

		int maxLen = 1;
		int[] prev = pairs[0];
		for (int i = 1; i < pairs.length; i++) {
			int[] curr = pairs[i];
			if (curr[0] > prev[1]) {
				maxLen++;
				prev = curr;
			}
		}
		return maxLen;
	}
}