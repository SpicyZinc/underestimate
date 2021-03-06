/*
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
Example 1:
Input: 11
Output: 3
Explanation: Integer 11 has binary representation 00000000000000000000000000001011

Example 2:
Input: 128
Output: 1
Explanation: Integer 128 has binary representation 00000000000000000000000010000000

idea:
http://articles.leetcode.com/2010/09/number-of-1-bits.html

1. Brute force solution:
Iterate 32 times, each time determining if the ith bit is a ‘1’ or not. 
This solution is machine dependent (Be sure that an unsigned integer is 32-bit). 
In addition, this solution is not very efficient too, as you need to iterate 32 times.

2. Bit hack
making use x & (x-1) to get a single 1 bit erased
*/

public class NumberOfOneBits  {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 1; i <= 32; i++) {
        	if ( isSetBit(n, i) == true ) {
        		count++;
        	}
        }

        return count;
    }

    private boolean isSetBit(int n, int i) {
    	return ( n & (1 << i) ) != 0;
    }

    // 2. bit hack
    public int hammingWeight(int n) {
        int count = 0;
        while ( n != 0 ) {
            n &= (n - 1);
            count++;
        }

        return count;
    }
}