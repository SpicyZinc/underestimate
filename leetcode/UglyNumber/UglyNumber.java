/*
Write a program to check whether a given number is an ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

Note that 1 is typically treated as an ugly number.

idea:
check if this number is divided by either 2, 3, or 5
if so, use while loop to divide clearly

return num == 1
*/

public class UglyNumber {
	// method 1
    public boolean isUgly(int num) {
        if (num <= 0) {
        	return false;
        }
        while ((num % 2) == 0) {
        	num /= 2;
        }
        while ((num % 3) == 0) {
        	num /= 3;
        }
        while ((num % 5) == 0) {
        	num /= 5;
        }
        return num == 1;
    }
    // method 2
    public boolean isUgly(int num) {
	    for (int i = 2; i <= 5 && num > 0; i++) {
	    	while (num % i == 0) {
	        	num /= i;
	    	}
		}
		return num == 1;
    }
    // self written method
    public boolean isUgly(int num) {
        while (num > 1 && num % 2 == 0) {
            num /= 2;    
        }
        while (num > 1 && num % 3 == 0) {
            num /= 3;    
        }
        while (num > 1 && num % 5 == 0) {
            num /= 5;    
        }
        return num == 1;
    }

}