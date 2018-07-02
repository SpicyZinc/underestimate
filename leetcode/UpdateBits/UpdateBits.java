/*
Given two 32-bit numbers, N and M, and two bit positions, i and j. 
Write a method to set all bits between i and j in N equal to M 
(e g , M becomes a substring of N located at i and starting at j)

Notice
In the function, the numbers N and M will given in decimal, you should also return a decimal number.

Clarification
You can assume that the bits j through i have enough space to fit all of M.
That is, if M=10011， you can assume that there are at least 5 bits between j and i.
You would not, for example, have j=3 and i=2, because M could not fully fit between bit 3 and bit 2.

Example
Given N = (10000000000)2, M = (10101)2, i = 2, j = 6
return N = (10001010100)2


Thinking process:
Create a mask: xxxx000000xxxx.
Trick part: when it encounters negative number or dealing with index at edge index = 31, it starts having issue.
Interesting fix: use long for masks.

Thoughts:
Need a mask of 1111110000001111... where the '0's are representing range [i, j].
Use the mask to n & mask, so this block will be 0 in n.
shift m << i, then n | m will do.
Problem is:
how to create that mask?
Trick:
We can create 00000001111110000, and reverse it.
32-bit 111111...111 is actually -1 in decimal. Or, we can get it by ~0.
1. We want a block of size [j - i], so right-logic-shift -1 >>> (j - i + 1). (be careful with negative leading 1)
2. Left-shift << i so the block is at correct position.
3. Reverse to complete the mask
End:
1. n & mask
2. left-shift m by i
3. n | m
*/

public class UpdateBits {
	/**
	 * @param n: An integer
	 * @param m: An integer
	 * @param i: A bit position
	 * @param j: A bit position
	 * @return: An integer
	 */

	public int updateBits(int n, int m, int i, int j) {
		// prepare mask
		int mask = -1; // 111111...1111
		mask = mask >>> (32 - (j - i + 1));
		mask = mask << i;
		mask = ~mask;

		n = n & mask;
		return n | (m << i);
	}
}