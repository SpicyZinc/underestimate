/*Given a 32-bit signed integer, reverse digits of an integer.

Example 1:
Input: 123
Output: 321

Example 2:
Input: -123
Output: -321

Example 3:
Input: 120
Output: 21

Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

idea:
note overflow,
r不能到最后check 那时已经overflow了 ( / 10)先
*/

import java.util.*;

public class ReverseInteger {
	public static void main(String[] args) {
		ReverseInteger eg = new ReverseInteger(); 
		int x = eg.reverse(123);
		int y = eg.reverse(-123);
		System.out.println(x);
		System.out.println(y);
	}

	public int reverse(int x) {
		int r = 0;
		
		while (x != 0) {
			if (Math.abs(r) > Integer.MAX_VALUE / 10) {
				return 0;
			}

			r = r * 10 + x % 10;
			x /= 10;
		}
		
		return r;
	}
}
