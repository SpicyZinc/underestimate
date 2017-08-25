/**
 * implement pow(double x, int n) function
 * idea:
 * 1. direct O(n)
 * 2. recursion O(logN), temp = pow(x, n / 2);
 */

class Exponent {
	public static void main(String[] args) {
		Exponent eg = new Exponent();
		System.out.println("pow(3, 3) == " + eg.powerI(3, 3));
		System.out.println("pow(-0.3, 3) == " + eg.powerI(-0.3, 3));
		System.out.println("pow(-3.1, 2) == " + eg.powerI(-3.1, 2));
		System.out.println("power(0.5, 4) == " + eg.powerI(0.5, 4));
		System.out.println("pow(-3.0, 4) == " + eg.powerI(-3.0, 4));
		System.out.println("pow(1, 9) == " + eg.powerI(1, 9));
		System.out.println("pow(0, 1) == " + eg.powerI(0, 1));
		System.out.println();
		System.out.println("pow(3, 3) == " + eg.powerII(3, 3));
		System.out.println("pow(-0.3, 3) == " + eg.powerII(-0.3, 3));
		System.out.println("pow(-3.1, 2) == " + eg.powerII(-3.1, 2));
		System.out.println("power(0.5, 4) == " + eg.powerII(0.5, 4));
		System.out.println("pow(-3.0, 4) == " + eg.powerII(-3.0, 4));
		System.out.println("pow(1, 9) == " + eg.powerII(1, 9));
		System.out.println("pow(0, 1) == " + eg.powerII(0, 1));

	}
	// direct and easy method
	public double powerI(double x, int n) {
		if ( x == 0 ) return 0;
        if ( n == 0 ) return 1;
        if ( n < 0 ) {
        	x = 1.0 / x;
        	n = -n;
        }

		double result = 1.0;
		for (int i = 1; i <= n; i++) {
			result = result * x;
		} 
		return result;
	}

	// binary 
	public double powerII(double x, int n) {
		if ( x == 0 ) {
			return 0;
		}
		if ( n == 0 ) {
			return 1;
		}
		if ( n < 0 ) {
			x = 1.0 / x;
			n = -n;
		}

		double temp = powerII(x, n / 2);
		if ( n % 2 == 0 ) {
			return temp * temp;
		}
		else {
			return temp * temp * x;
		}
	}
}


