/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, 
return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.

idea:
make use of bitwise AND consecutive numbers
*/
public class BitwiseANDNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        while ( n > m ) {
        	n &= (n - 1);
        }
        return n & m;
    }
}