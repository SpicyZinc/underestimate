/*
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are 
in the given prime list primes of size k. 
For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is 
the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.

idea:
https://www.cnblogs.com/Liok3187/p/5016076.html
http://www.phperz.com/article/15/1225/177142.html

nthSuperUglyNumber(6, [2, 5, 7])
1.                              [1]                                 min(1 * 2, 1 * 5, 1 * 7)
2.                           [1, 2]                                 min(1 * 5, 1 * 7, 2 * 2)
3.                        [1, 2, 4]                                 min(1 * 5, 1 * 7, 4 * 2)
4.                     [1, 2, 4, 5]                                 min(2 * 5, 1 * 7, 4 * 2)
5.                  [1, 2, 4, 5, 7]                                 min(2 * 5, 2 * 7, 4 * 2)
*/

public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int size = primes.length;
        // for primes[j] it is time to multiply which ith ugly number
        // corresponds to the prime at this position shoule multiply which position's ugly number in the result array
        int[] positionsInResult = new int[size];
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;

        for (int i = 1; i < n; i++) {
            int currMin = Integer.MAX_VALUE;
            for (int j = 0; j < size; j++) {
                // index at which the count should multiply the jth prime in primes array
                int posInUglyNumbersTimesPrime = positionsInResult[j];
                currMin = Math.min(currMin, uglyNumbers[posInUglyNumbersTimesPrime] * primes[j]);
            }
            uglyNumbers[i] = currMin;
            // update positionsInResult[]
            for (int j = 0; j < size; j++) {
                if (uglyNumbers[positionsInResult[j]] * primes[j] == currMin) {
                    positionsInResult[j]++;
                }
            }
        }
        
        return uglyNumbers[n - 1];
    }
}