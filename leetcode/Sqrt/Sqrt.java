/*
Sqrt(x)
Implement int sqrt(int x)

idea:

1. binary cut
// key point pay attention to x < 1 case
start = 0
end = x or end = 1 when x < 1
mid = (start + end) / 2
while(> tolerance)
if mid * mid == x
return mid
if mid * mid > x
end = mid;
else start = mid;
mid = (start + end) / 2

2. Newton's method
y = x / 2;
y = (y + x/y) / 2;
condition is always (> tolerance)
Integer version sqrt is exceptional (y * y > x)
*** common sense: (a half of a number) squre is > this number ***

*/

public class Sqrt {
	public static void main(String[] args) {
		new Sqrt();
	}
	// constructor
	public Sqrt() {
		System.out.println("3 sqrt == " + sqrt(3));
	}

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
	
    public int sqrt(int x) {
        if (x < 0) 
			return -1;
        if (x == 0) 
			return 0;
        
        double y = ((double)x) / 2.0;
		// res * res <= x < (res+1) * (res+1)
		// use float number to get close to limit
        while (Math.abs(y * y - x) > 0.0000001) {
            y = (y + x/y) / 2.0;
        }		
        return (int) y;
    }
	// Integer version
    public int sqrt(int x) {
        if (x <= 1) 
            return x;
        
        int mid = x / 2;        
        while (mid * mid > x || mid > 46340) {
            mid = (mid + x / mid) / 2;
        }
		
        return mid;
    }	
}