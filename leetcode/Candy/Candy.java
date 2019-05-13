/*
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:
Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example 1: 
Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

Example 2: 
Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.

idea:
https://www.cnblogs.com/grandyang/p/4575026.html

declare candies array represent how many candies each child should have
then loop from left to right to make the candies number of each child meet the requirement, 
then loop from right to left make the candies array meet the requirement
finally, calculate the total candies number

time complexity: O(2*n) = O(n)
space complexity: O(n)

note:
loop through array, each rating could be bigger or less than its right or left element
注意不是 += 1

similar to Trapping Rain Water
*/
public class Solution {
	public int candy(int[] ratings) {
		int n = ratings.length;

		int[] candies = new int[n];
		Arrays.fill(candies, 1);

		// from left to right, loop once
		for (int i = 0; i < n - 1; i++) {
			int curr = ratings[i];
			int next = ratings[i + 1];
			// right neighbor
			// if next rating > curr rating, next child get current child's candy num + 1
			if (next > curr) {
				candies[i + 1] = candies[i] + 1;
			}
		}

		for (int i = n - 1; i >= 1; i--) {
			int prev = ratings[i - 1];
			int curr = ratings[i];
			// left neighbor
			// note 2nd loop candies[i - 1] <= candies[i], 否则没有必要 + 1
			if (prev > curr && candies[i - 1] <= candies[i]) {
				candies[i - 1] = candies[i] + 1;
			}
		}

		int total = 0;
		for (int num : candies) {
			total += num;
		}

		return total;
	}
}