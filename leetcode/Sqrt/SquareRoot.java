/*
idea:
best two versions so far
*/

public class SquareRoot {
	public static void main(String[] args) {
		new SquareRoot();
	}
	// constructor
	public SquareRoot() {
		System.out.println("3 sqrt == " + sqrt(3));
	}

	// best version
	public int sqrt(int x) {
        long lo = 0;
        long hi = x;

        while (hi - lo >= 0) {     
            long mid = (lo + hi) / 2;
            if (mid * mid > x)
                hi = mid-1;
            else
                lo = mid+1;    
        }
        return (int)hi;
    }

    // another method, pass test with 0.00001 or less 
    public double sqrt(double x) {
		if (x < 0) 
			return -1;
        if (x == 0) 
			return 0;
        
        double y = ((double)x) / 2.0;
		// res * res <= x < (res+1) * (res+1)
        while (Math.abs(y * y - x) > 0.00001) {
            y = (y + x/y) / 2.0;
        }		
        return (int)y;
	}

	// makes more sense, but not get passed
    public int sqrt(int x) {
		if (x <= 1) 
            return x;
        
        int guess = (0 + x) / 2;		
		while (Math.abs(guess*guess - x) > Integer.MIN_VALUE) {
			if (guess * guess == x) 
                return guess;
			guess = (guess + x / guess) / 2; 
		}
		return guess;
    }
}

	