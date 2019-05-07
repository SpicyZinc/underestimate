/*
Given a string, determine if a permutation of the string could form a palindrome.
For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:
Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?

idea:
https://segmentfault.com/a/1190000003790181

count the occurrence of each character, only at most one character appearing odd times
or use hash table
*/
public class PalindromePermutation {
	public boolean canPermutePalindrome(String s) {
		int[] cnts = new int[256];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			cnts[c] += 1;
		}

		boolean hasOneOddChar = false;
		for (int i = 0; i < cnts.length; i++) {
			if (cnts[i] % 2 == 1) {
				if (!hasOneOddChar) {
					hasOneOddChar = true;
				} else {
					return false;
				}
			}
		}

		return true;
	}
}