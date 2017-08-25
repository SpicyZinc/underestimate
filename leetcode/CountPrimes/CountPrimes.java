/*
Description:
Count the number of prime numbers less than a non-negative number, n.

idea:
1. direct, helper function isPrime(), only count to Math.sqrt(n); proof by contradiction why only to Math.sqrt(n);
2. sieve of Eratosthenes, use primes to sieve, if a number is divisible by a prime, mark as NOT prime
*/

public class CountPrimes {
    // direct method, time out
    public int countPrimes(int n) {
        int count = 0;
        for ( int i = 2; i < n; i++ ) {
            if ( isPrime(i) ) {
                count++;
            }
        }
        
        return count;
    }
    
    private boolean isPrime(int n) {
        for ( int i = 2; i <= Math.sqrt(n); i++ ) {
            if ( n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
    // time out
    public int countPrimes(int n) {
        ArrayList<Integer> primes = new ArrayList<Integer>();

        if ( n == 2 ) {
            return 0;
        }
        if ( n == 3 ) {
            return 1;
        }
        if ( n == 4 ) {
            return 2;
        }

        primes.add(2);
        primes.add(3);
        for (int i = 4; i < n; i++){
            boolean isPrime = true;
            for (int p : primes) {
                if (i%p == 0) {
                    isPrime = false;
                    break;
                }
            }
     
            if ( isPrime ) {
                primes.add(i);
            }
        }
     
        return primes.size();
    }

    // sieve of Eratosthenes
    public int countPrimes(int n) {
        int count = 0;
        boolean[] isPrime = new boolean[n];
        for ( int i = 2; i < n; i++ ) {
            isPrime[i] = true;
        }    
        
        for ( int i = 2; i < n; i++ ) {
            if ( isPrime[i] ) {
                for ( int j = i+i; j < n; j+=i ) { // this is already meaning j is divisible by multiple of i
                    isPrime[j] = false;
                }
            }
        }

        for ( int i = 2; i < n; i++ ) {
            if ( isPrime[i] == true ) {
                count++;
            }
        }

        return count; 
    }
}