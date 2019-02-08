/*
Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.

Example 1:
Input: "Hello"
Output: "hello"

Example 2:
Input: "here"
Output: "here"

Example 3:
Input: "LOVELY"
Output: "lovely"

idea:

*/

class ToLowerCase {
	public String toLowerCase(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isLetter(c) && !Character.isLowerCase(c)) {
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public String toLowerCase(String str) {
		return str.toLowerCase();
	}
}