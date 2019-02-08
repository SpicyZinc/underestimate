/*
Divide two integers without using multiplication, division and mod operator. 

idea:
http://www.lifeincode.net/programming/leetcode-divide-two-integers-java/
bit manipulation
think of 178 / 2

*/
import java.util.*;

public class DivideTwoIntegers {
	public static void main(String[] args) {
		DivideTwoInt s = new DivideTwoInt();
		// 2147483647 -2147483648
		int dividend = 2147483647;
		int divisor = -2147483648;
		int result = s.divide(dividend, divisor);
		System.out.println(result);
	}

    public int divide(int dividend, int divisor) {
		if (dividend == 0 || divisor == 1) {
			return dividend;
		}
		if (divisor == -1) {
			return 0 - dividend;
		}

		long divd = Math.abs((long) dividend);
		long divs = Math.abs((long) divisor);

		List<Long> divisors = new ArrayList<Long>();
		while (divd >= divs) {
			divisors.add(divs);
			divs <<= 1;
		}

		int result = 0;
		int cur = divisors.size() - 1;
		while (divd > 0 && cur >= 0) {
			while (divd >= divisors.get(cur)) {
				divd -= divisors.get(cur);
				result += 1 << cur;
			}
			cur--;
		}

		return (dividend > 0) ^ (divisor > 0) ? (-result) : result; 
    }

    // simpler version
    public int divide(int dividend, int divisor) {
        long p = Math.abs((long) dividend);
        long q = Math.abs((long) divisor);
        
        int result = 0;
        while (p >= q) {
            int counter = 0;
            while (p >= (q << counter)) {
                counter++;
            }

            result += 1 << (counter - 1);
            p -= q << (counter - 1);
        }

        if (dividend && divisor > 0) {
        	return result;
        } else {
        	return -result;
        }
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            return ret;
        } else {
            return -ret;
        }
    }

    // 02/02/2019
    // lintcode version
	public int divide(int dividend, int divisor) {
        if (divisor == 0) {
             return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        
        if (dividend == 0) {
            return 0;
        }
        
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        long p = Math.abs((long) dividend);
        long q = Math.abs((long) divisor);
        
        int result = 0;

        while (p >= q) {
            int counter = 0;
            while (p >= (q << counter)) {
                counter++;
            }

            result += 1 << (counter - 1);
            p -= q << (counter - 1);
        }

        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            return result;
        } else {
            return -result;
        }
    }
}