/*
Given an integer, 
write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion

idea:
1. recursion
one time mod 3,
if mod/3 == 0, continue call to isPowerOfThree
else return false
if n == 1, clear, return true

2. iteration
while continue to divide by 3, finally n = 1 will jump out of while (n % 3 == 0)
return n == 1
3. one line (without any loop / recursion)
4. log
5. string representation 
*/

public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
    
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        else if (n % 3 == 0) {
            return isPowerOfThree(n / 3);
        }
        else { 
            return false;
        }
    }

	public boolean isPowerOfThree(int n) {
		// 1162261467 is 3^19,  3^20 is bigger than int  
	    return ( n > 0 && 1162261467 % n == 0);
	    // also works
	    // return ( n > 0 && (int)Math.pow(3, 19) % n == 0);
	}

    public boolean isPowerOfThree(int n) {
		return n <= 0 ? false : (Math.log10(n) / Math.log10(3)) % 1 == 0;
	}

	public boolean isPowerOfThree(int n) {
		return Integer.toString(n, 3).matches("10*");
	}
}
