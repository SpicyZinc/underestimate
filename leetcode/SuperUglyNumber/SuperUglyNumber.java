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
*/

public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int size = primes.length;
        int[] positionForEachPrime = new int[size]; // corresponds to the prime at this position reaches what position in the result array
        int[] res = new int[n];
        res[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < size; j++) {
                int idxInResult = positionForEachPrime[j]; // index at which the count should multiply the jth prime in primes array 
                min = Math.min(min, primes[j] * res[idxInResult]);
            }
            // not forget to add to result array
            res[i] = min;
            for (int j = 0; j < size; j++) {
                if (primes[j] * res[positionForEachPrime[j]] == min) {
                    positionForEachPrime[j]++;
                }
            }
        }
        return res[n - 1];
    }
}