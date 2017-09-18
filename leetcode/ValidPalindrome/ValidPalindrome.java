/*
Valid Palindrome
Determine if a string is a palindrome, 
considering only alphanumeric characters and ignoring cases.

"A man, a plan, a canal: Panama" is a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.
For the purpose of this problem, we define empty string as valid palindrome. 

idea:
first use regex to replace all non-word character with empty space
then convert all to toLowerCase()
\w 	A word character: [a-zA-Z_0-9]
\W 	A non-word character: [^\w]

even: is ideal, half half compare
odd: leave the middle one alone, no need to compare
note, i < s.length() / 2, then start from head and tail of the string
*/

public class ValidPalindrome {
	public static void main(String[] args) {
		ValidPalindrome eg = new ValidPalindrome();
		System.out.println("a ba is Palindrome: " + eg.isPalindrome("a ba"));
	}
    public boolean isPalindrome(String s) {
        if (s.length() == 0 || s == null) return true;		
		
		s = s.replaceAll("\\W", "");
    	s = s.toLowerCase();

        int n = s.length();
        int i = 0;
		while (i < n / 2) {
			if (s.charAt(i) != s.charAt(n-1-i)) {
			    return false;
            }
			i++;
		}
		return true;
    }
}