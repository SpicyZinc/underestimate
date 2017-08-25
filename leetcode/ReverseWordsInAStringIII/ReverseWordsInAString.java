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
    	String res = "";
    	for (String word : words) {
    		res += reverse(word) + " ";
    	}
    	return res.substring(0, res.length() - 1);
    }

    private String reverse(String s) {
    	char[] ch = s.toCharArray();
    	for (int i = 0; i < ch.length / 2; i++) {
    		char temp = ch[i];
    		ch[i] = ch[ch.length - 1 - i];
    		ch[ch.length - 1 - i] = temp;
    	}
    	return new String(ch);
    }
}