/*
Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".

idea:
1. convert to char array, swap, return new String(chars)
2. use StringBuilder, reverse() function
3. recursively
*/
public class ReverseString {
	public static void main(String[] args) {
		ReverseString eg = new ReverseString();
		System.out.println(eg.reverseString("abc"));
	}

	public void reverseString(char[] s) {
		char[] chars = s;
		int size = s.length;

		for (int i = 0; i < size / 2; i++) {
			char temp = chars[i];
			chars[i] = chars[size - 1 - i];
			chars[size - 1 - i] = temp;
		}
	}

	public void reverseString(char[] s) {
		if (s == null || s.length == 0) {
			return;
		}

		int begin = 0;
		int end = s.length - 1;

		while (begin <= end) {
			char c = s[begin];
			s[begin] = s[end];
			s[end] = c;

			begin++;
			end--;
		}
	}

	// use StringBuilder reverse()
	public String reverseString(String s) {
		StringBuilder str = new StringBuilder(s);

		return str.reverse().toString();
	}

	// recursively reverse, Memory Limit Exceeded
	public String reverseString(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}

		return reverseString(s.substring(1)) + s.substring(0, 1);
	}
}