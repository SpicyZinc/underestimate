/*

Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true.
Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?

idea:
https://segmentfault.com/a/1190000003481153
power of two
power of three
power of four

Method 1 based on 1 digit appear on odd position, and only one 1 in the binary format
	1. only deal with 1 digit e.g.'111001' 1, 2, 3, 6
	2. if 1 digit is on even, direct return false
	3. if 1 digit is on odd position for the first time, return true 

Method 2
	减一位与法 1000 && (1000-1) == 1000 && 0111 == 0000
	in addition, power of 4 minus 1 always divided by 3
*/
public class PowerOfFour {
	// method 1
    public boolean isPowerOfFour(int num) {
        boolean res = false;
    	if (num <= 0) {
    		return res;
    	}
    	for (int i = 1; i <= 32; i++) {
	        if ((num & 1) == 1) {
	            if (i % 2 == 0) {
	            	return false;
	            }
	            if (res) {
	            	return false;
	            } else {
	                res = true;
	            }
	        }
	        num = num >>> 1;
	    }
    	return res;
    }
    // method 2
	public boolean isPowerOfFour(int num) {
		return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
	} 
}



