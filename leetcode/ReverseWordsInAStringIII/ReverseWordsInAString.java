/*
Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.

idea:
not much to mention, just do it
*/

public class ReverseWordsInAString {
	public String reverseWords(String s) {
		if (s.length() == 0 || s == null) {
			return s;
		}

		String[] words = s.split("\\s+");
		String result = "";
		for (String word : words) {
			result += reverse(word) + " ";
		}

		return result.substring(0, result.length() - 1);
	}

	private String reverse(String s) {
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length / 2; i++) {
			char temp = chars[i];
			chars[i] = chars[chars.length - 1 - i];
			chars[chars.length - 1 - i] = temp;
		}

		return new String(chars);
	}
}