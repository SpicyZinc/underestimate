/*
X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.
Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation.
0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other;
6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
Input: 10
Output: 4
Explanation: 
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.

Note:
N will be in range [1, 10000].

idea:
brute force, check number 1 by 1
isGood() use recursion and flag
*/

class RotatedDigits {
	public int rotatedDigits(int N) {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (isGood(i, false)) {
                cnt++;
            }
        }
        
        return cnt;
    }
    
    public boolean isGood(int n, boolean flag) {
        if (n == 0) return flag;

		String halfGood = "018";
		String badDigits = "347";

        int digit = n % 10;
        if (badDigits.contains("" + digit)) return false;
        if (halfGood.contains("" + digit)) return isGood(n / 10, flag);

        return isGood(n / 10, true);
    }
}