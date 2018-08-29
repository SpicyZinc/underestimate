/*
Implement pow(x, n)

idea:
one thing to note:
avoid recursion too deep when (n < 0)
not recursively call function, just {
	n = -n;
	x = 1.0 / x;
}
	recursion too deep, leading to stackoverflow
	if (n < 0)  
		return 1.0 / pow(x, -n); 
		
1. direct thought is recursion
2. note, binary (odd, even), pay attention to (n < 0)
3. bitwise operation, pay attention to (n < 0)
   n >>= 1 <==> n /= 2
*/

public class Pow {
	public static void main(String[] args) {
		Pow eg = new Pow();

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

		System.out.println("pow(1, 3) == " + myPow(1, 3));
		System.out.println("power(2, 4) == " + myPow(2, 4));
		System.out.println("power(0, 1) == " + myPow(0, 1));
	}

	// good to use
	public double myPow(double x, int n) {
		if (n == 0) {
			return 1.0;
		}
		if (x > 1 && n <= Integer.MIN_VALUE) {
			return 0.0;
		}
		/*
		too deep recursion leads to stackoverflow
		if (n < 0) {
			return 1.0 / pow(x, -n); 
		}
		*/
		if (n < 0) {
			n = -n;
			x = 1.0 / x;
		}
		double tmp = myPow(x, n / 2);  
		if (n % 2 == 0) {
			return tmp * tmp;
		} else {
			return x * tmp * tmp;
		}
	}

	public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        if (x > 1 && n <= Integer.MIN_VALUE) {
            return 0.0;
        }
        
        if (n < 0) {
            n = -n;
            x = 1.0 / x;
        }
        if (n % 2 == 0) {
            return myPow(x * x, n / 2);
        } else {
            return x * myPow(x * x, n / 2);
        }
    }
	
	// direct recursion
	public double myPow(double x, int n) { 
		if (n == 0) {
			return 1.0;
		}
		if (n < 0) {
			n = -n;
			x = 1.0 / x;
		}
		
		return x * myPow(x, n - 1);
	}

	// direct and easy method
	public double myPow(double x, int n) {
		if (x == 0) return 0;
		if (n == 0) return 1;
		if (n < 0) {
			x = 1.0 / x;
			n = -n;
		}

		double result = 1.0;
		for (int i = 1; i <= n; i++) {
			result *= x;
		}

		return result;
	}

	// binary cut version 1
	public double pow(double x, int n) {
		if (n < 0)  
			return 1.0 / power(x, -n);  
		else  
			return power(x, n);      
	}
	private double power(double x, int n) {  
		if (n == 0) {
			return 1;  
		}

		double v = power(x, n / 2);  
		if (n % 2 == 0) {
			return v * v;  
		} else  {
			return v * v * x;
		}
	}
	
	// binary cut version 2
	public double pow(double x, int n) {
		if (n == 0)  
			return 1;
		if (n < 0) {
            n =- n;
            x = 1.0 / x;
        }		
		double tmp = pow(x, n / 2);  
		if (n % 2 == 0) {
			return tmp * tmp;  
		} else {
			return tmp * tmp * x;     
		}
	}

	// bitwise operation
	// thought is the same as binary, use right shift to count how many times to multiply
	// if n is odd, must multiply one more
	public double pow(double x, int n) {  
    	if (n == 0) {
			return 1.0;
    	}

        if (n < 0) {
            n =- n;
            x = 1.0 / x;
        }
        
        double res = 1.0;
        while(n > 0) {
            // if((n & 1) == 1) 
            if (n % 2 == 1) {
                res *= x;
            }
            x *= x;
            n >>= 1;
        }
        return res;
    }
}