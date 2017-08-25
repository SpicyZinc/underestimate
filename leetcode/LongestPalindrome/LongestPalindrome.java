/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:
Input: "abccccdd"
Output: 7
Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

idea:
The palindrome is either odd or even number of letters.
For odd number of letters, only the letter which appears the largest times would give the the longest palindrome, no more actions;
however, for those letters which appear odd times but less than the largest odd times, we need to cut one time from the total odd times
(oddCnt - maxOdd) == appearing times of the letters which are less than the biggest odd times
(lettersAppearOddTimes-1) == the number of letters appearing odd times except the letter which appears the biggest odd times

*/

public class LongestPalindrome {
	public int longestPalindrome(String s) {
        if (s.length() == 0 || s == null) {
        	return 0;
        }
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	map[c - '0']++;
        }
        int maxLength = 0;
        int maxOdd = 0;
        int lettersAppearOddTimes = 0;
        int oddCnt = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 0) {
                if (map[i] % 2 == 0) {
            		maxLength += map[i];
            	}
            	else {
            		oddCnt += map[i];
            		maxOdd = Math.max(maxOdd, map[i]);
					lettersAppearOddTimes++;
            	}   
            }
        }
        
        return maxLength + (oddCnt == 0 ? 0 : maxOdd + ( oddCnt - maxOdd ) - ( lettersAppearOddTimes - 1 ));
    }
}