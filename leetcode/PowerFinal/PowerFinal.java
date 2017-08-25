/*
Implement pow(x, n)

idea:

1. binary (odd, even), pay attention to (n < 0)
binary, assign the half value to be double tmp at very first

2. bitwise operation, pay attention to (n < 0)
   n >>= 1 <==> n /= 2
*/

class FinalPower {
	// direct brute force, timeout, fail to pass test
	public double pow(double x, int n) {
		if (x == 1.0) {
			return x;
		}
		if (n == 0) {
			return 1.0;
		}

		double ret = 1.0;
		for (int i = 1; i <= n; i++) {
			ret *= x;
		}

		return ret;
	}
	// good to use
	public double pow(double x, int n) {
		if (n == 0) {
			return 1.0;
		}

		double tmp = pow(x, n / 2);
		if (n % 2 == 0) {
			return tmp * tmp;
		}
		else {
			if (n > 0) {
				return tmp * tmp * x;
			}
			else {
				return tmp * tmp * (1/x);	
			}
		}
	}
	// good to use
	public double pow(double x, int n) {
		if (n == 0) {
			return 1.0;
		}
		
		if (n < 0) {
			n = -n;
			x = 1/x;
		}
		double tmp = pow(x, n / 2);
		if (n % 2 == 0) {
			return tmp * tmp;
		}
		else {
			return tmp * tmp * x;
		}
	}
	// timeout, not pass
	public double pow(double x, int n) {
		int ret = 1.0;

		while (n != 0) {
			if (n & 1 == 1) {
				ret = ret * x;
			}
			n >>= 1;
			x = x * x;
		}

		return ret;
	}
} 