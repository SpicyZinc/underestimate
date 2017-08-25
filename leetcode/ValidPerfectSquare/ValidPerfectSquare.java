/*
Given a positive integer num,
write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:
Input: 16
Returns: True

Example 2:
Input: 14
Returns: False


idea:
1. a square number is 1+3+5+7+... Time Complexity O(sqrt(N)) (Credit to lizhibupt, thanks for correcting this)
math problem
1 = 1
4 = 1 + 3
9 = 1 + 3 + 5
16 = 1 + 3 + 5 + 7
25 = 1 + 3 + 5 + 7 + 9
36 = 1 + 3 + 5 + 7 + 9 + 11
....
so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = n * n = n ^ 2

Do NOT USE, time limited
		int i = 1;
        int sum = 0;
        while (sum < num) {
        	sum += i;
        	i += 2;
        }

        return sum == num;

2. binary search. Time Complexity O(logN)
3. Newton Method. See this wiki page. Time Complexity is close to constant, given a positive integer.
*/

public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
	    int i = 1;
	    while (num > 0) {
	        num -= i;
	        i += 2;
	    }

	    return num == 0;
    }
	// method 2
    public boolean isPerfectSquare(int num) {
		if (num < 1) {
			return false;
		}
  		// long type to avoid 2147483647 case
  		long left = 1, right = num;
		while (left <= right) {
			long mid = (left + right) / 2;
			long t = mid * mid;
			if (t > num) {
			  	right = mid - 1;
			} else if (t < num) {
			  	left = mid + 1;
			} else {
			  	return true;
			}
		}

  		return false;
	}
	// method 3
	public boolean isPerfectSquare(int num) {
		long t = num;
	    while (t * t > num) {
	        t = (t + num / t) / 2;
	    }
	    return t * t == num;
	}
}