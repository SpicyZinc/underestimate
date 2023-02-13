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
    // Fri Jul 12 01:25:14 2019
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        long p = Math.abs((long) dividend);
        long q = Math.abs((long) divisor);
        
        int quotient = 0;
        
        while (p >= q) {
            int counter = 0;
                        
            while (p >= (q << counter)) {
                counter++;
            }
            // it is just a little larger than dividend
            // then minus 1
            int littleLessThanCounter = counter - 1;
            quotient += 1 << littleLessThanCounter;
            p -= q << littleLessThanCounter;
        }
        // 同号
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            return quotient;
        } else { // 异号
            return -quotient;
        }
    }

    // Thu Jul 11 22:32:38 2019
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        long p = Math.abs((long) dividend);
        long q = Math.abs((long) divisor);
        
        int result = 0;

        while (p >= q) {
            int counter = 0;
            // 变成原来的2倍
            // q + q + q + ..., + q
            // counter is consecutive
            // 2^0 1 
            // 2^1 2
            // 2^2 4
            // 2^3 8
            // 2^4 16
            while (p >= (q << counter)) {
                counter++;
            }

            // 2 ^ (counter - 1) < p
            result += 1 << (counter - 1);
            p -= q << (counter - 1);
        }

        // 同号
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            return result;
        } else { // 异号
            return -result;
        }
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

        List<Long> divisors = new ArrayList<>();
        while (divd >= divs) {
            divisors.add(divs);
            divs <<= 1;
        }

        int result = 0;
        int curr = divisors.size() - 1;
        while (divd > 0 && curr >= 0) {
            while (divd >= divisors.get(curr)) {
                divd -= divisors.get(curr);
                result += 1 << curr;
            }
            curr--;
        }

        return (dividend > 0) ^ (divisor > 0) ? (-result) : result; 
    }
}

