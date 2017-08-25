/*
Valid Palindrome
Determine if a string is a palindrome, 
considering only alphanumeric characters and ignoring cases.

"A man, a plan, a canal: Panama" is a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.
For the purpose of this problem, we define empty string as valid palindrome. 

idea:
regex to replaceAll first
toLowerCase()
\w 	A word character: [a-zA-Z_0-9]
\W 	A non-word character: [^\w]

even: is ideal, half half compare
odd: leave the middle one alone, no need to compare, 
i < s.length() / 2
key point

*/

public class ValidPalindrome {
	public static void main(String[] args) {
		new ValidPalindrome();
	}
	// constructor
	public ValidPalindrome() {
		System.out.println("a ba is Palindrome: " + isPalindrome("a ba"));
	}
    public boolean isPalindrome(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (s.length() == 0 || s == null)
    		return true;		
		
		s = s.replaceAll("\\W", "");
    	s = s.toLowerCase();
        int i = 0;
        int n = s.length();
		// remember
		while (i < n/2) {
			if (s.charAt(i) == s.charAt(n-1-i))
				i++;
			else 
				return false;
		}
		return true;
    }
}