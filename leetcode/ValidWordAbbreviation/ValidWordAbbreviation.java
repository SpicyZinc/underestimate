/*
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":
Return true.

Example 2:
Given s = "apple", abbr = "a2e":
Return false.

idea:
https://www.cnblogs.com/grandyang/p/5930369.html

note: number could be more than one digit, so have to use cnt = cnt * 10 + Integer.parseInt(c);
and also use while() to extract the value
*/

import java.util.*;

public class ValidWordAbbreviation {
	public static void main(String[] args) {
		ValidWordAbbreviation eg = new ValidWordAbbreviation();
		String s = "internationalization";
		String abbr = "i12iz4n";

		System.out.println(s + ", " + abbr + ", correct abbr? == " + eg.validWordAbbreviation(s, abbr));

		s = "apple";
		abbr = "a2e";
		System.out.println(s + ", " + abbr + ", correct abbr? == " + eg.validWordAbbreviation(s, abbr));

	}

	public boolean validWordAbbreviation(String word, String abbr) {
		int i = 0;
		int j = 0;
		while (i < word.length() && j < abbr.length()) {
			if (Character.isDigit(abbr.charAt(j))) {
				if (j == 0 && abbr.charAt(j) == '0') {
					return false;
				}
				int cnt = 0;
				while ( j < abbr.length() && Character.isDigit(abbr.charAt(j)) ) {
					cnt = cnt * 10 + Character.getNumericValue( abbr.charAt(j) );
					j++;
				}
				i += cnt;
			}
			else if (Character.isLetter(abbr.charAt(j))) {
				if ( word.charAt(i) != abbr.charAt(j) ) {
					return false;
				}
				else {
					i++;
					j++;
				}
			}
		}
		return (i == word.length()) && (j == abbr.length());
	}
	// self written
	public boolean validWordAbbreviation(String word, String abbr) {
		int i = 0, j = 0;
		int wl = word.length();
		int al = abbr.length();
		while (i < wl && j < al) {
			char c1 = word.charAt(i);
			char c2 = abbr.charAt(j);
			if (c2 >= '0' && c2 <= '9') {
				if (c2 == '0') {
					return false;
				}
				int val = 0;
				while (j < al && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
					val = val * 10 + abbr.charAt(j) - '0';
					j++;
				}
				i += val;
			} else {
				if (c1 != c2) {
					return false;
				}
				else {
					i++;
					j++;
				}
			}
		}

		return i == wl && j == al;
    }
}
