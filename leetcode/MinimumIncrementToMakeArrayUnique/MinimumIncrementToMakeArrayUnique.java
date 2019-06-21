/*
Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
Return the least number of moves to make every value in A unique.

Example 1:
Input: [1,2,2]
Output: 1
Explanation:  After 1 move, the array could be [1, 2, 3].

Example 2:
Input: [3,2,1,2,1,7]
Output: 6
Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.

Note:
0 <= A.length <= 40000
0 <= A[i] < 40000

idea:
先 sort
都要比前一个 至少 +1
*/

class MinimumIncrementToMakeArrayUnique {
	public int minIncrementForUnique(int[] A) {
		// 1 1 2 2 3 7
		Arrays.sort(A);

		int cnt = 0;
		int numberNeedToBe = 0;

		for (int num : A) {
			cnt += Math.max(numberNeedToBe - num, 0);
			numberNeedToBe = Math.max(num, numberNeedToBe) + 1;
		}

		return cnt;
	}
}