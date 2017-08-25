/* 
A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequences: 
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9

The following sequence is not arithmetic.
1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.
A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.
The function should return the number of arithmetic subsequence slices in the array A.
The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.

Example:
Input: [2, 4, 6, 8, 10]
Output: 7
Explanation:
All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]

idea:

i 1-- j 0
i 2-- j 0
i 2-- j 1
i 3-- j 0
i 3-- j 1
i 3-- j 2
i 4-- j 0
i 4-- j 1
i 4-- j 2
i 4-- j 3


dp + hashmap
https://discuss.leetcode.com/topic/69540/dynamic-programming-java-solution-with-detailed-explanation
https://discuss.leetcode.com/topic/67413/detailed-explanation-for-java-o-n-2-solution/2
*/

public class ArithmeticSlicesSubsequence {
    public int numberOfArithmeticSlices(int[] A) {
        int res = 0;
        if (A.length <= 2 || A == null) {
        	return res;
        }
        int n = A.length;
        Map<Integer, Integer>[] dp = new Map[n];
        for (int i = 0; i < n; i++) {
        	dp[i] = new HashMap<>(i);
        	for (int j = 0; j < i; j++) {
        		long diff = (long)A[i] - (long)A[j];
        		if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) {
        			continue;
        		}
        		int d = (int)diff;
        		int c1 = dp[i].getOrDefault(d, 0);
        		int c2 = dp[j].getOrDefault(d, 0);
        		dp[i].put(d, c1 + c2 + 1);
        		res += c2;
        	}
        }

        return res;
    }
}
