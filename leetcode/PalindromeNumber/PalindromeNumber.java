/*
Determine whether an integer is a palindrome. 
Do this without extra space.

idea:

reveres this integer, this is done by very mature method
compare reverse with original integer
if equal, return true;
else return false;

The only place has to be paid attention to is
if the number is negative, return false (negative number is not palindrome)
do not forget to check this case first

*/

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		
		int origin = x;
		int reverse = 0;
		
		while (origin != 0) {
			reverse = reverse * 10 + origin % 10;
			origin /= 10;
		}
        
		if (reverse == x) {
			return true;
		}
		else {
			return false;
		}
    }
}