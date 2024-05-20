/*
Sqrt(x)
Implement int sqrt(int x)

idea:
1. binary cut, use long
note: x < 1 case
start = 0
end = x or end = 1 when x < 1
mid = (start + end) / 2

while (end >= start)
    return (int) end;

2. Newton's method to approach the guess
guess = x / 2;
guess = (guess + x / guess) / 2;
condition is always (> tolerance)
Integer version sqrt is exceptional (guess * guess > x)
note: (a half of a number) square is >= this number
*/

public class Sqrt {
	public static void main(String[] args) {
		new Sqrt();
	}
	// constructor
	public Sqrt() {
		System.out.println("3 sqrt == " + sqrt(3));
	}

    // best
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        
        long left = 0;
        long right = x;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (mid * mid > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return (int) right;
    }

    // integer version best
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        // 二分答案
        int guess = x / 2;
        while (guess * guess > x || guess > 46340) {
            guess = (guess + x / guess) / 2; 
        }
        
        return guess;
    }
    // double version
    public int mySqrt(int x) {
        if (x <= 1) {
            return x; 
        }
        
        double precision = 0.00001;
        double guess = x / 2.0;
        
        if (guess * guess == x) {
            return (int) guess;
        }
        // use Newton's method to approach precision
        while (Math.abs(guess * guess - x) > precision) {
            guess = (guess + x / guess) / 2;
        }
        
        return (int) guess;
    }
}
