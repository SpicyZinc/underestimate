/*
Calculate the a^n % b where a, b and n are all 32 bit integers.

Example
For 231 % 3 = 2
For 1001000 % 1000 = 0

idea:
binary
formula, (a * b) % p = (a % p * b % p) % p
a^n % b = (a^(n/2) % b) * (a^(n/2) % b) % b

divide and conquer
then note long
*/
public class FastPower {
    /**
     * @param a: A 32bit integer
     * @param b: A 32bit integer
     * @param n: A 32bit integer
     * @return: An integer
     */
	public int fastPower(int a, int b, int n) {
		if (n == 0) {
			return 1 % b;
		}
		if (n == 1) {
			return a % b;
		}

		long half = fastPower(a, b, n / 2);
		long result = (half * half) % b;
		if (n % 2 == 1) {
			result = (result * a) % b;
		}

		return (int) result;
	}
}