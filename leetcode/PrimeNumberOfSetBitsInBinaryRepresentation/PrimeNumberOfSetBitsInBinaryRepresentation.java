/*
Given two integers L and R, find the count of numbers in the range [L, R] (inclusive) having a prime number of set bits in their binary representation.

(Recall that the number of set bits an integer has is the number of 1s present when written in binary.
For example, 21 written in binary is 10101 which has 3 set bits. Also, 1 is not a prime.)

Example 1:
Input: L = 6, R = 10
Output: 4
Explanation:
6 -> 110 (2 set bits, 2 is prime)
7 -> 111 (3 set bits, 3 is prime)
9 -> 1001 (2 set bits , 2 is prime)
10-> 1010 (2 set bits , 2 is prime)

Example 2:
Input: L = 10, R = 15
Output: 5
Explanation:
10 -> 1010 (2 set bits, 2 is prime)
11 -> 1011 (3 set bits, 3 is prime)
12 -> 1100 (2 set bits, 2 is prime)
13 -> 1101 (3 set bits, 3 is prime)
14 -> 1110 (3 set bits, 3 is prime)
15 -> 1111 (4 set bits, 4 is not prime)

Note:
L, R will be integers L <= R in the range [1, 10^6].
R - L will be at most 10000.

idea:
direct
isPrime() note, only up to 19 has to be checked
get the number of set bit
*/

class PrimeNumberOfSetBitsInBinaryRepresentation {
	public int countPrimeSetBits(int L, int R) {
		int count = 0;
		for (int i = L; i <= R; i++) {
            if (L > 1) {
                int cnt = countSetBits(i);
                if (isPrime(cnt)) {
                    count++;
                }                
            }
		}

		return count;
	}

	public boolean isPrime(int x) {
        // since 10 ^ 6 < 2 ^ 20
        // the number of set bits is at most 20
        // so at most 20 primes, the biggest prime < 20 is 19
        Integer[] primes = new Integer[] {2, 3, 5, 7, 11, 13, 17, 19};
        Set<Integer> hs = new HashSet<Integer>(Arrays.asList(primes));
        return hs.contains(x);
	}

	public int countSetBits(int n) {
		int count = 0;
		while (n > 0) {
			count += n & 1;
			n >>= 1;
		}

		return count;
	}

	public int countOfSetbit(int n) {
		int cnt = 0;
		for (int i = 0; i < 32; i++) {
			if ((n & 1) != 0) {
				cnt++;
			}
			n >>= 1;
		}

		return cnt;
	}
}