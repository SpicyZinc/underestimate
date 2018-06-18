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
2. ***** binary (odd, even), pay attention to (n < 0) *****
3. bitwise operation, pay attention to (n < 0)
   n >>= 1 <==> n /= 2
*/

public class Pow {
	public static void main(String[] args) {
		new Pow();				
	}
	public Pow() {
		System.out.println("pow(1, 3) == " + pow(1, 3));		
		System.out.println("power(2, 4) == " + pow(2, 4));
		System.out.println("power(0, 1) == " + pow(0, 1));
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
/*	
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
		}
		else  {
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
		if (n % 2 == 0)
			return tmp * tmp;  
		else  
			return tmp * tmp * x;     
	}
	
	// bitwise operation
	// thought is the same as binary, use right shift to count how many times to multiply
	// if n is odd, must multiply one more
	public double pow(double x, int n) {  
    	if (n == 0) 
			return 1.0;
        if (n < 0) {
            n =- n;
            x = 1.0 / x;
        }
        
        double res = 1.0;
        while(n > 0) {
            if (n % 2 == 1) 
            // if((n & 1) == 1) 
                res *= x;
            x *= x;
            n >>= 1;
        }
        return res;
    }
*/
}