/*
Divide two integers without using multiplication, division and mod operator. 

idea:
http://www.lifeincode.net/programming/leetcode-divide-two-integers-java/
bit manipulation
think of 178 / 2


*/
import java.util.*;

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
		if (dividend == 0 || divisor == 1) {
			return dividend;
		}
		if (divisor == -1) {
			return 0 - dividend;
		}

		long divd = Math.abs((long)dividend);
		long divs = Math.abs((long)divisor);

		ArrayList<Long> divisors = new ArrayList<Long>();
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
		/*
		if (dividend > 0 && divisor <= 0) {
			return -result;
		}
		else if (dividend <= 0 && divisor > 0) {
			return -result;
		}
		else {
			return result;
		}
		*/
    }

    // simpler version
    public int divide(int dividend, int divisor) {
        long p = Math.abs((long)dividend);
        long q = Math.abs((long)divisor);
        
        int ret = 0;
        while (p >= q) {
            int counter = 0;
            while (p >= (q << counter)) {
                counter++;
            }
            ret += 1 << (counter - 1);
            p -= q << (counter - 1);
        }
        
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            return ret;
        }
        else {
            return -ret;
        }
    }
}

// another method, not very important
class DivideTwoInt { 	
	public int divide(int dividend, int divisor) {
        long a = Math.abs((long) dividend); 
		long b = Math.abs((long) divisor);
        long[] res = dividePos(a,b);
        long temp = dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0 ? -res[0] : res[0];
        return (int)temp;
    }
   
    private long[] dividePos(long a, long b) {
        long[] res = new long[2];
        if (a < b) {
            res[0] = 0;
            res[1] = a;
        }
		else if (a == b) {
            res[0]=1;
            res[1]=0;
        }
		else {
            long[] temp = dividePos(a>>1, b);
            res[0] = temp[0] << 1;
            res[1] = temp[1] << 1;
            if ((a & 1) == 1) 
				res[1] += 1;
            if (res[1] >= b) {
                res[0] += 1;
                res[1] -= b;
            }
        }
        return res;
    }
	
	public static void main(String[] args) {
		DivideTwoInt s = new DivideTwoInt();
		// 2147483647 -2147483648
		int dividend = 2147483647;
		int divisor = -2147483648;
		int result = s.divide(dividend, divisor);
		System.out.println(result);
	}
}