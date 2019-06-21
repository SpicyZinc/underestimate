/*
A message containing letters from A-Z is being encoded to numbers using the following mapping way
'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
Also, since the answer may be very large, you should return the output mod 10^9 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".

Example 2:
Input: "1*"
Output: 9 + 9 = 18

Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.

idea:
DP, need to go back to digest more
f(i) = ( f(i-1) * ways(i) ) + ( f(i-2) * ways(i-1, i) )
note: need to use long
*/
public class DecodeWays {
	public int numDecodings(String s) {
		int M = 1000000007;

		long first = ways(s.charAt(0));
		if (s.length() <= 1) {
			return (int) first;
		}

		long second = first * ways(s.charAt(1)) + ways(s.charAt(0), s.charAt(1));
		for (int i = 2; i < s.length(); i++) {
			long temp = second;
			second = ( first * ways(s.charAt(i - 1), s.charAt(i)) + second * ways(s.charAt(i)) ) % M;
			first = temp;
		}

		return (int) second;
	}
	
	private int ways(char c) {
		if (c == '*') {
			return 9;
		}

		if (c == '0') {
			return 0;
		}

		return 1;
	}

	private int ways(char c1, char c2) {
		// four cases
		// 15
		// **
		// *6
		// 2*
		int val = 0;

		if (c1 != '*' && c2 != '*') {
			val = Integer.parseInt("" + c1 + c2);
			if (val >= 10 && val <= 26) {
				return 1;
			}
		} else if (c1 == '*' && c2 == '*') {
			return 9 + 6;
		} else if (c1 == '*') {
			val = Integer.parseInt(c2 + "");
			if (val >= 0 && val <= 6) {
				return 2;
			} else {
				return 1;
			}
		} else {
			val = Integer.parseInt(c1 + "");
			if (val == 1) {
				return 9;
			} else if (val == 2) {
				return 6;
			}
		}

		return 0;
	}
}