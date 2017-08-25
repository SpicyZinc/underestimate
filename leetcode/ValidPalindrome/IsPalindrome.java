/**
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
For the purpose of this problem, we define empty string as valid palindrome.

idea:
1. str = str.replaceAll("\\W", "");
2. str = str.toLowerCase();
3. use str.charAt();
*/

public class IsPalindrome {
	public static void main(String[] args) {
		new IsPalindrome();
	}
	// constructor
	public IsPalindrome() {
		System.out.println("a ba is Palindrome: " + isPalindrome("a ba"));
	}
	
    public boolean isPalindrome(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        String str = s;
    	str = str.replaceAll("\\W", "");
        str = str.toLowerCase();
		char[] chars = str.toCharArray();		
		int length = str.length();
		
		int i = 0;		
		while (i < length / 2) {
			char tmp = chars[i];
			chars[i] = chars[length - i - 1];
			chars[length - i - 1] = tmp;
			i++;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int j=0; j<length; j++) {
			sb.append(chars[j]);
		}
		
		return str.equals(sb.toString());
    }
}