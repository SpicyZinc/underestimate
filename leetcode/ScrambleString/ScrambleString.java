/*
idea:
1. recursion
If string s1 and s2 are scramble strings, 
there must be a point that breaks s1 to two parts s11, s12, and a point that breaks s2 to two parts, s21, s22, 
and isScramble(s11, s21) && isScramble(s12, s22) is true, 
or isScramble(s11, s22) && isScramble(s12, s21) is true.

Some checks are needed otherwise it will time out
(1) same length
(2) same set of characters

2. 3D DP
http://blog.csdn.net/linhuanmars/article/details/24506703
http://blog.csdn.net/fightforyourdream/article/details/17707187
http://www.lifeincode.net/programming/leetcode-scramble-string-java/

optimal substructure
canT[i][j][k]
*/

public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        // Check if having the same length
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        // Check if having the same set of characters
        int L = s1.length();
        int[] letters = new int[26];
        for (int i = 0; i < L; i++) {
            letters[ s1.charAt(i) - 'a' ]++;
            letters[ s2.charAt(i) - 'a' ]--;
        }
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }
        for (int i = 1; i < L; i++) {
            String s1Left = s1.substring(0, i);
            String s1Right = s1.substring(i, L);
            String s2Left = s2.substring(0, i);
            String s2Right = s2.substring(i, L);
            if ( isScramble(s1Left, s2Left) && isScramble(s1Right, s2Right) ) {
                return true;
            }
            String anotherPossibleS2Left = s2.substring(0, L - i);
            String anotherPossibleS2Right = s2.substring(L - i, L);
            // note, scramble string must be the same length first
            // s1Left = i
            // anotherPossibleS2Right = L - (L - i) = i
            if ( isScramble(s1Left, anotherPossibleS2Right) && isScramble(s1Right, anotherPossibleS2Left) ) {
                return true;
            }
        }
        
        return false;
    }

    public boolean isScrambleDP(String s1, String s2) {
    	if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }

        boolean[][][] canT = new boolean[s1.length()][s2.length()][s1.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
        	for (int j = 0; j < s2.length(); j++) {
        		canT[i][j][1] = s1.charAt(i) == s2.charAt(j);
        	}
        }

        for (int k = 2; k <= s1.length(); k++) {
        	for (int i = 0; i < s1.length() - k + 1; i++) {
        		for (int j = 0; j < s2.length() - k + 1; j++) {
        			// (len-1) of cutting ways
        			for (int cut = 1; cut < k; cut++) {
        				canT[i][j][k] = canT[i][j][k] | ( canT[i][j][cut] && canT[i+cut][j+cut][k-cut] || canT[i][j+k-cut][cut] && canT[i+cut][j][k-cut] );
        			}
        		}
        	}
        }

        return canT[0][0][s1.length()];  
    }
}