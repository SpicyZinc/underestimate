/*
Given a positive integer K, you need find the smallest positive integer N such that N is divisible by K, and N only contains the digit 1.
Return the length of N.  If there is no such N, return -1.

Example 1:
Input: 1
Output: 1
Explanation: The smallest answer is N = 1, which has length 1.

Example 2:
Input: 2
Output: -1
Explanation: There is no such positive integer N divisible by 2.

Example 3:
Input: 3
Output: 3
Explanation: The smallest answer is N = 111, which has length 3.
 
Note:
1 <= K <= 10^5

idea:
need to come back

If N exist, N <= K, just do a brute force check.
Also if K % 2 == 0, return -1, because 111....11 can't be even.
Also if K % 5 == 0, return -1, because 111....11 can't end with 0 or 5.

Explanation
For different N, we calculate the remainder of mod K.
It has to use the remainder for these two reason:

Integer overflow
The division operation for big integers, is NOT O(1), it's actually O(logN).
Prove
Why 5 is a corner case? It has a reason and we can find it.
Assume that N = 1 to N = K, if there isn't 111...11 % K == 0
There are at most K - 1 different remainders: 1, 2, .... K - 1.

So this is a pigeon holes problem:
There must be at least 2 same remainders.

Assume that,
f(N) ≡ f(M), N > M
f(N - M) * 10 ^ M ≡ 0
10 ^ M ≡ 0, mod K
so that K has factor 2 or factor 5.

Proof by contradiction，
If (K % 2 == 0 || K % 5 == 0) return -1;
otherwise, there must be a solution N <= K.
*/

class SmallestIntegerDivisibleByK {
	public int smallestRepunitDivByK(int K) {
		if (K % 2 == 0 || K % 5 == 0) {
			return -1;
		}

		int r = 0;
		for (int N = 1; N <= K; N++) {
			r = (r * 10 + 1) % K;

			if (r == 0) {
				return N;
			}
		}

		return -1;
	}
}