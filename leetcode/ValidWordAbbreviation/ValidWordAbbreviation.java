/*
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word".
Any other string is not a valid abbreviation of "word".

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

note,
word
w02d
false

千万别搞错 i j 指向谁
The worst case time complexity is O(n) to the length of the abbreviation string

double pointers
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
    // Tue May 14 00:47:00 2024
    public boolean validWordAbbreviation(String word, String abbr) {
        int n = word.length();
        int m = abbr.length();
        if (m > n) {
            return false;
        }

        int i = 0; // abbr
        int j = 0; // word

        while (i < m && j < n) {
            char c = abbr.charAt(i);
            if (Character.isDigit(c)) {
                if (abbr.charAt(i) == '0') {
                    return false;
                }
                int cnt = 0;
                while (i < m && Character.isDigit(abbr.charAt(i))) {
                    cnt = cnt * 10 + abbr.charAt(i) - '0';
                    i++;
                }
                j += cnt;
            } else {
                if (abbr.charAt(i) == word.charAt(j)) {
                    i++;
                    j++;
                } else {
                    return false;
                }
            }
        }

        return i == m && j == n;
    }

    // 07/16/2018
    public boolean validWordAbbreviation(String word, String abbr) {
        if (abbr.length() > word.length()) {
            return false;
        }
        
        int i = 0;
        int j = 0;

        while (i < word.length() && j < abbr.length()) {
            if (Character.isDigit(abbr.charAt(j))) {
                if (abbr.charAt(j) == '0') {
                    return false;
                }
                int cnt = 0;
                while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    cnt = cnt * 10 + abbr.charAt(j) - '0';
                    j++;
                }
                i += cnt;
            } else if (Character.isLetter(abbr.charAt(j))) {
                if (word.charAt(i) == abbr.charAt(j)) {
                    i++;
                    j++;
                } else {
                    return false;
                }
            }
        }

        return i == word.length() && j == abbr.length();
    }
}
