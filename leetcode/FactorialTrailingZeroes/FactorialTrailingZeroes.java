/*
Given an integer n, return the number of trailing zeroes in n!
logarithmic time complexity: O(logn)

idea:
the direct way is to get n!,
t = n!
while ( t > 0 ) {
	t %= 10;
	zero_cnt++;
}

the number of trailing zeroes are decided by the number of 2's and 5's
since 2 is always outnumber 5, so the number of 5 dominates
a thing to note:
25: how many 5s in 25, 25/5, there is one more in 5 * 5
*/


public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        int fiveCnt = 0;
        while (n > 0) {
            n /= 5;
            fiveCnt += n;
        }
        
        return fiveCnt;
    }
}