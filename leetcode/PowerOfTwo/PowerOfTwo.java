/*
Given an integer, 
write a function to determine if it is a power of two.

idea:
power of 2 means only one bit of n is '1' in the binary format, 
so use (n & (n-1)) == 0 to judge whether that is the case

Attention: == has higher priority than &
*/

public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
		if ( n <= 0 ) {
			return false;
		}

		return (n & (n - 1)) == 0;
    }

    // general method
	public boolean isPowerOfTwo(int n) {
        return isPowerOfBase(n, 2);
    }
    
    public boolean isPowerOfBase(int n, int base) {
        if (n <= 0) {
            return false;
        }

        while (n % base == 0) {
            n /= base;
        }

        return n == 1;
    }
}