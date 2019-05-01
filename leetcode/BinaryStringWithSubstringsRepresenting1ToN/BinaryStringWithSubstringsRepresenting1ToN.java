/*
Given a binary string S (a string consisting only of '0' and '1's) and a positive integer N,
return true if and only if for every integer X from 1 to N,
the binary representation of X is a substring of S.

Example 1:
Input: S = "0110", N = 3
Output: true

Example 2:
Input: S = "0110", N = 4
Output: false

Note:
1 <= S.length <= 1000
1 <= N <= 10^9

idea:
first helper function to convert to binary format
then loop, S.contains()
*/

class BinaryStringWithSubstringsRepresenting1ToN {
	public boolean queryString(String S, int N) {
		for (int i = 1; i <= N; i++) {
			String binaryFormat = toBinary(i);
			// both work
			// if (S.indexOf(binaryFormat) == -1) {
			if (!S.contains(binaryFormat)) {
				return false;
			}
		}

		return true;
	}
	// how to convert 10-base number to 2-base number
	public String toBinary(int n) {
		StringBuilder sb = new StringBuilder();

		while (n > 0) {
			sb.append(n % 2);
			n /= 2;
		}

		return sb.reverse().toString();
	}
}

