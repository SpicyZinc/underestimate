/*
Get a cubic root of a number

idea:
binary
*/

import java.util.*;

class CubeRoot {
	public static void main(String[] args) {
		CubeRoot eg = new CubeRoot();
		double n1 = 27;
		double n2 = 36;
		double n3 = 3;

		double root1 = eg.cubicRoot(n1);
		double root2 = eg.cubicRoot(n2);
		double root3 = eg.cubicRoot(n3);

		System.out.println("Cube root of " + n1 + " is " + root1);
		System.out.println("Cube root of " + n2 + " is " + root2);
		System.out.println("Cube root of " + n3 + " is " + root3);
	}
    // Returns the absolute value of n - mid * mid * mid
	private double diff(double n,double mid) {
		return Math.abs(n - mid * mid * mid);
	}

	// Returns cube root of a no. n 
	private double cubicRoot(double n) {
        // Set start and end for binary search 
        double start = 0;
        double end = n;
   
        // Set precision 
        double precision = 0.0000001; 
   
        while (true) { 
			double mid = (start + end) / 2;
			double error = diff(n, mid);
   
            // If error is less than precision then mid is 
            // our answer so return mid 
            if (error <= precision) {
                return mid;
            }

            if (mid * mid * mid > n) {
                end = mid; 
            } else {
                start = mid;
            }
        }
    }
} 