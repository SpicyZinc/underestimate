/*
Given an input string, reverse the string word by word.
Example: 
Input: "the sky is blue",
Output: "blue is sky the".

Note:
A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.

idea:
trim() first
*/

public class ReverseWordsInAString  {
	public static void main(String[] args) {
		ReverseWordsInAString eg = new ReverseWordsInAString();
		String s = " 1";
		String result = eg.reverseWords(s);

		System.out.println("|" + result + "|");
	}
	// Fri Jul 12 22:28:53 2019
	public String reverseWords(String s) {
		s = s.trim();
		List<String> list = new ArrayList<>();
		
		int size = s.length();
		int start = 0;
		int i = 0;
		
		while (i < size) {
			if (s.charAt(i) == ' ') {
				String word = s.substring(start, i);
				list.add(0, word);
				
				while (s.charAt(i) == ' ') {
					i++;
				}

				start = i;
			} else {
				i++;
			}
		}
		
		String lastWord = s.substring(start, size);
		list.add(0, lastWord);

		String reversed = "";
		for (int j = 0; j < list.size(); j++) {
			reversed += list.get(j) + (j == list.size() - 1 ? "" : " ");
		}

		return reversed;
	}

	public String reverseWords(String s) {
		s = s.trim();
		int len = s.length();
		int idx = 0;
		int start = 0;

		List<String> words = new ArrayList<>();
		String result = "";
		
		while (idx < len) {
			if (s.charAt(idx) == ' ') {
				String word = s.substring(start, idx);
				words.add(word);
				// 穷尽个数未知的 spaces
				while (s.charAt(idx) == ' ') {
					idx++;
				}
				start = idx;
			} else {
				idx++;
			}
		}
		// Don't forget the last word
		String lastWord = s.substring(start, len);
		words.add(lastWord);  

		for (int i = words.size() - 1; i >= 0; i--) {
			result += words.get(i) + (i > 0 ? " " : "");
		}
		
		return result;
	}

	// passed, but not guarantee that spaces count
	public String reverseWords(String s) {
		if (s.length() == 0 || s == null) {
			return "";
		}

		s = s.trim();
		String[] ss = s.split("\\s+");

		String result = "";        
		for (int i = ss.length - 1; i >= 0; i--) {
			result += ss[i] + (i != 0 ? " " : "");
		}

		return result;
	}
}