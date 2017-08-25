/*
Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:
Input: 3
Output: 3

Example 2:
Input: 11
Output: 0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.

idea:

the regularity

--- 1 - 9  : 9
--- 10 - 99 : 90 * 2
--- 100 - 999 : 900 * 3
--- 1000 - 9999 : 9000 * 4

k the kth layer, starting from 1
on kth layer, each number consists of k digits
note: n-1
(n-1) / k == the position in the layer array index

*/

public class NthDigit {
    public int findNthDigit(int n) {
     	long m = n;
        int k = 1;
        long countDigits = 9;

        while (m > countDigits) {
        	m -= countDigits;
        	k++;
        	countDigits = 9 * (long)Math.pow(10, k - 1) * k;
        }
        long number = (long)Math.pow(10, k - 1) + (m - 1) / k;

        return String.valueOf(number).charAt((int)(m - 1) % k) - '0';
    }
}