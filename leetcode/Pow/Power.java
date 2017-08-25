/*
Implement pow(x, n)

idea:
binary, assign the half value to be double tmp at very first
*/

class Power {
	public static double pow(double x, int n) {
		if (x==0.0) return 0.0;
		if (x==1.0) return 1.0;
		if (n==1) return x;
		if (x < 0) {
			if (n % 2 == 0) {
				return pow((-x), n);
			}
			else {
				return -pow((-x), n);
			} 
		}
		return x * pow(x, n-1);	
	}
	// this is the final version of power, not pass because of timeout	
	private static double power(double x, int n) {
		double result = 1.0;
    	while (n != 0) {
			if ((n & 1) == 1) {
				result *= x;
			}
			n >>= 1;
			x *= x;			
		}

		return result;
	}
	// direct and easy method, but timeout
	private static double power(double x, int n) {
		if (x == 0) return 0;
        if (n == 0) return 1;

		double result = 1.0;
		for (int i = 1; i <= n; i++) {
			result = result * x;
		} 
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("pow(3, 3) == " + pow(3, 3));
		System.out.println("pow(-0.3, 3) == " + pow(-0.3, 3));
		System.out.println("pow(-3.1, 2) == " + pow(-3.1, 2));
		System.out.println("pow(-3.0, 4) == " + pow(-3.0, 4));
		System.out.println("pow(1, 9) == " + pow(1, 9));
		System.out.println("pow(0, 1) == " + pow(0, 1));
		
		System.out.println("ipow(0.3, 3) == " + ipow(0.3, 3));
		System.out.println("power(0.5, 4) == " + power(0.5, 4));
		System.out.println("power(-3.1, 2) == " + power(-3.1, 2));
		System.out.println("ipow(-3.1, 2) == " + ipow(-3.1, 2));
		System.out.println("=====");
		System.out.println("power(2, 4) == " + power(2, 4));
		System.out.println("Bitwise AND & example 7&1 == " + (7&1));
		System.out.println("Bitwise AND & example 6&1 == " + (6&1));
	}
}