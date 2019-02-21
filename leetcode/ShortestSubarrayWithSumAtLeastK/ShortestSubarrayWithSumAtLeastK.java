/*
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
If there is no non-empty subarray with sum at least K, return -1.
 
Example 1:
Input: A = [1], K = 1
Output: 1

Example 2:
Input: A = [1,2], K = 4
Output: -1

Example 3:
Input: A = [2,-1,2], K = 3
Output: 3

Note:
1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9

idea:
1. direct method O(n^2)
前 x 数（包括第 x 个数）的和为 frontSum[x]
则第 i 个数到第 j 个数的和为 frontSum[j]-frontSum[i-1]
遍历 i, j 的情况
求出和大于等于 K
且长度最小的数组即可

2.
https://blog.csdn.net/yanglingwell/article/details/80875790
j > i
当 [i, j] sum < 0 会成为负担 不要考虑了
*/

class ShortestSubarrayWithSumAtLeastK {
	// direct method, TLE, 84 / 93 test cases passed
	public int shortestSubarray(int[] A, int K) {
		int n = A.length;
		int[] sum = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			sum[i] = sum[i - 1] + A[i - 1];
		}

		int shortestLen = -1;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (sum[j + 1] - sum[i] >= K && (shortestLen == -1 || shortestLen > j + 1 - i)) {
					shortestLen = j + 1 - i;
					break;
				}
			}
		}

		return shortestLen;
	}

	// use double ended queue
	public int shortestSubarray(int[] A, int K) {
		int n = A.length;
		int shortestLen = n + 1;

		int[] sum = new int[n + 1];
		for (int i = 0; i < n; i++) {
			sum[i + 1] = sum[i] + A[i];
		}

		Deque<Integer> d = new ArrayDeque<>();
		for (int i = 0; i <= n; i++) {
			// getFirst() 本身的值 就是大于 getLast()
			while (d.size() > 0 && sum[i] - sum[d.getFirst()] >=  K) {
				shortestLen = Math.min(shortestLen, i - d.pollFirst());
			}
			while (d.size() > 0 && sum[i] - sum[d.getLast()] <= 0) {
				d.pollLast();
			}

			d.addLast(i);
		}

		return shortestLen <= n ? shortestLen : -1;
	}
}