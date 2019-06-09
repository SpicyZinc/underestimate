/*
We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.
Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.

Example:
Input: 28
Output: True
Explanation: 28 = 1 + 2 + 4 + 7 + 14
Note: The input number n will not exceed 100,000,000. (1e8)

idea: just for fun
*/
class PerfectNumber {
	// TLE
	public boolean checkPerfectNumber(int num) {
		int sum = 0;
		for (int i = 1; i < num; i++) {
			if (num % i == 0) {
				sum += i;
			}
		}

		return num == sum;
	}

	// till Math.sqrt(num)
	// note corner case 0 and 1
	public boolean checkPerfectNumber(int num) {
        if (num == 0 || num == 1) {
        	return false;
        }

		int sum = 0;
		for (int i = 1; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
                if ((num / i) < num) {
                    sum += i + (num / i);    
                } else {
                    sum += i;
                }
			}
		}

		return num == sum;	
	}
}