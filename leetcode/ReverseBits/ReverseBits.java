/*
Reverse bits of a given 32 bits unsigned integer.
e.g.: given input 43261596 (represented in binary as 00000010100101000001111010011100), 
return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

idea:
http://www.cnblogs.com/grandyang/p/4321355.html

simple and direct based on the requirement
from right to left (how? keep right shifting n)
if current bit is 1, first left shift, then + 1
if current bit is 0, first left shift, then + 0
*/

public class ReverseBits {
	// you need treat n as an unsigned value
	public int reverseBits(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			result = (result << 1) + (n & 1);
			n = n >> 1;
		}

		return result;
	}
}