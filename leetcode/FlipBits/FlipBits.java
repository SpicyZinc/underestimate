/*
Determine the number of bits required to flip if you want to convert integer n to integer m.
Example
Given n = 31 (11111), m = 14 (01110), return 2.

idea:
note: both n and m are 32-bit integers.
this is variation of get the number of set bits of a number
why, get n ^ m
n = 1010
m = 1101
n ^ m = 0111
then count the number of set bits
while ( n != 0) { // n == 1
	n = n & (n - 1);
}
*/

class FlipBits {
	public int bitSwapRequired(int n, int m) {
		int cnt = 0;
		int xor = n ^ m;
		while (xor != 0) {
			xor &= (xor - 1);
			cnt++;
		}

		return cnt;
	}
}