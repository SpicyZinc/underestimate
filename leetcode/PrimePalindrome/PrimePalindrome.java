/*
Find the smallest prime palindrome greater than or equal to N.
Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1.

For example, 2,3,5,7,11 and 13 are primes.
Recall that a number is a palindrome if it reads the same from left to right as it does from right to left. 

For example, 12321 is a palindrome.

Example 1:
Input: 6
Output: 7

Example 2:
Input: 8
Output: 11

Example 3:
Input: 13
Output: 101
 
Note:
1 <= N <= 10^8
The answer is guaranteed to exist and be less than 2 * 10^8.

idea:
brute fore try from N, N++ check if prime and palindrome
yes, turn early
no, 10^7 <  N < 10^8, N = 10^8 (not understand)
*/

class PrimePalindrome {
	public int primePalindrome(int N) {
        if (N == 1) {
            return 2;
        }

        while (true) {
            if (isPrime(N) && isPalindrome(N)) {
                return N;
            }
            N++;
            if (10000000 < N && N < 100000000) {
                N = 100000000;
            }
        }
    }
    
    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean isPalindrome(int x) {
        int copy = x;
        int reverse = 0;
        
        while (copy != 0) {
            reverse = reverse * 10 + copy % 10;
            copy /= 10;
        }

        return reverse == x;
    }
}