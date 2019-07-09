/*
Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
Return the length of the longest (contiguous) subarray that contains only 1s.

Example 1:
Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation: 
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Example 2:
Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation: 
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Note:
1 <= A.length <= 20000
0 <= K <= A.length
A[i] is 0 or 1

idea:
sliding window
this is equivalent to
Find the longest subarray with at most K zeros.

dp
http://www.noteanddata.com/leetcode-1004-Max-Consecutive-Ones-III-java-solution-note.html
*/

class MaxConsecutiveOnes {
	public int longestOnes(int[] A, int K) {
		int left = 0;
		int right = 0;

		for (; right < A.length; right++) {
			if (A[right] == 0) {
				// convert 0 to 1
				K--;
			}
			// K used up, 用光了
			if (K < 0 && A[left++] == 0) {
				K++;
			}
		}

		return right - left;
	}

	public int longestOnes(int[] A, int K) {
		int size = A.length;
		int[] dp = new int[K + 1];

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < size; i++) {
			for (int j = K; j >= 0; j--) {
				if (A[i] == 1) {
					dp[j]++;
				} else {
					if (j == 0) {
						dp[j] = 0;
					} else {
						dp[j] = dp[j - 1] + 1;
					}
				}

				max = Math.max(max, dp[j]);
			}
		}

		return max;
	}
}