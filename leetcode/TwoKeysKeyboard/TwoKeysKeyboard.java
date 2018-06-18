/*
Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted.
Output the minimum number of steps to get n 'A'.

Example 1:
Input: 3
Output: 3
Explanation:
Initially, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.

Note:
The n will be in the range [1, 1000].

idea:
find the rule, n == 2, minSteps = 2; n == 3, minSteps = 3;
1. recursion
2. iteration

to get n 'A', at worst need n steps
if want < n steps
need to find all factors of n, e.g. x is a factor
n % x == 0
copy, paste, ... === x times

subset == (n / x) to call recursion on

*/
public class TwoKeysKeyboard {
	// method 1
    public int minSteps(int n) {
        if (n == 1) {
        	return 0;
        }
        if (n <= 3) {
        	return n;
        }
        for (int i = 2; i < n; i++) {
        	if (n % i == 0) {
        		return i + minSteps(n / i);
        	}
        }

        return n;
    }
    // method 2
    // one time copy, one time paste e.g. 2
    public int minSteps(int n) {
        int min = 0;
        for (int i = 2; i <= n; i++) {
        	while (n % i == 0) {
        		min += i;
        		n /= i;
        	}
        }

        return min;
    }
}