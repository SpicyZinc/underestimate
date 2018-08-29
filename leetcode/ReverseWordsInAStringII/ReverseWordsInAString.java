/*
Given an input string, reverse the string word by word. 
Example:
Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]

Note: 
A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.

Follow up: Could you do it in-place without allocating extra space?

idea:
input is char array to represent words only separated by a space between words

1. reverse each word in place, then reverse the whole string
"the sky is blue" => "eht yks si eulb" => "blue is sky the"

2. reverse the whole string, then reverse each word
"the sky is blue" => "eulb si yks eht" => "blue is sky the"
*/

public class ReverseWordsInAString {
	public static void main(String[] args) {
		ReverseWordsInAString eg = new ReverseWordsInAString();
		String s = "the sky is blue";
		char[] chars = s.toCharArray();
		eg.reverseWordsI(chars);
		for (char c : chars) {
			System.out.print(c);
		}
		System.out.println();
	}
	// method 1
	public void reverseWords(char[] s) {
		int start = 0;
		// reverse each word in place
		for (int i = 0; i < s.length; i++) {
			if (s[i] == ' ') {
				reverse(s, start, i-1);
				start = i + 1;
			}
		}
		reverse(s, start, s.length - 1); // reverse last word
		// reverse the whole string
		reverse(s, 0, s.length - 1);
	}
	// method 2
	public void reverseWords(char[] s) {
		reverse(s, 0, s.length - 1);
		int start = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i] == ' ') {
				reverse(s, start, i - 1);
				start = i + 1;
			}
		}
		reverse(s, start, s.length - 1);
	}

	public void reverse(char[] s, int i, int j) {
		while (i < j) {
			char temp = s[i];
			s[i] = s[j];
			s[j] = temp;
			i++;
			j--;
		}
	}
}
