/*
Given m arrays, and each array is sorted in ascending order.
Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance.
We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4

Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.

Note:
Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10000].
The integers in the m arrays will be in the range of [-10000, 10000].

idea:
pick min and max from first element of the 2D array
*/
public class MaximumDistanceInArrays {
	public static void main(String[] args) {
		MaximumDistanceInArrays eg = new MaximumDistanceInArrays();
		int[][] arrays = {
			{1,2,3}, {4,5}, {1,2,3},
		};
		int maxDis = eg.maxDistance(arrays);
		System.out.println(maxDis);
	}

	public int maxDistance(int[][] arrays) {
		int maxDis = Integer.MIN_VALUE;
		int min = arrays[0][0];
		int max = arrays[0][arrays[0].length - 1];
		for (int i = 1; i < arrays.length; i++) {
			int n = arrays[i].length;
			maxDis = Math.max( maxDis, Math.abs(max - arrays[i][0]) );
			maxDis = Math.max( maxDis, Math.abs(arrays[i][n - 1] - min) );
			min = Math.min(min, arrays[i][0]);
			max = Math.max(max, arrays[i][n - 1]);
		}

		return maxDis;
	}
}