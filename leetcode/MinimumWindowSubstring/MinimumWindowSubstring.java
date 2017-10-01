/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".
If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

idea:
http://blog.csdn.net/linhuanmars/article/details/20343903

遇到没在Map的字符不断移动窗口右端 直到cover all T's chars
移动窗口左端的条件是当找到满足条件的串之后, 一直移动窗口左端直到有Map的字符不再在窗口里

一开始key包含字典中所有字符, value就是该字符的数量, 然后遇到字典中字符时就将对应字符的数量减一

suppose char are 256 ascii code
two count array, ascii code as index
count[] for String S
t[] for String T

the worst assumption is minWindow = S.length() + 1
take each char in S as starting point, find shortest substring containing the all t's characters

similar to SubstringWithConcatenationOfAllWords
*/
public class MinimumWindowSubstring {
	public static void main(String[] args) {
		String S = "ADOBECODEBANC";
		String T = "ABC";
		System.out.println("S == " + S);
		System.out.println("T == " + T);
		MinimumWindowSubstring aTest = new MinimumWindowSubstring();
		String minimumWindow = aTest.minWindow(S, T);
		System.out.println("minimumWindow == " + minimumWindow);		
	}

    // self written version passed test
    public String minWindow(String S, String T) {  
        if (S == null || S.length() == 0 || T == null || T.length() == 0) {
            return "";
        }
        
        Map<Character, Integer> hm = new HashMap<Character, Integer>();
        int tLen = T.length();
        for (int i = 0; i < tLen; i++) {
            char c = T.charAt(i);
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }
        
        int sLen = S.length();
        int minLen = sLen + 1;
        int windowLeft = 0;
        int count = 0; // counter for appeared T's character in S
        String window = "";
        
        for (int j = 0; j < sLen; j++) {
            char c = S.charAt(j);
            if (hm.containsKey(c)) {
                hm.put( c, hm.get(c) - 1 );
                if (hm.get(c) >= 0) {
                    count++;
                }
                
                while (count == tLen) {
                    if (hm.containsKey(S.charAt(windowLeft))) {
                        // restore the map
                        hm.put(S.charAt(windowLeft), hm.get(S.charAt(windowLeft)) + 1);
                        if (hm.get(S.charAt(windowLeft)) >= 1) {
                            if (minLen > j - windowLeft + 1) {
                                minLen = j - windowLeft + 1;
                                window = S.substring(windowLeft, j + 1);
                            }
                            count--;
                        }
                    }
                    windowLeft++;
                }
            }
        }
        
        return window;
    }

	// self written, passed 267 / 268 test cases, TLE
    public String minWindow(String s, String t) {
        Map<Character, Integer> hm = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }
        
        int size = s.length();
        int minLen = size + 1;
        String window = "";
        int left = 0;
        while (size - left >= t.length()) {
            Map<Character, Integer> copyMap = new HashMap<Character, Integer>(hm);
            for (int i = left; i < size; i++) {
                char c = s.charAt(i);
                if (copyMap.containsKey(c)) {
                    int count = copyMap.get(c);
                    if (count == 1) {
                        copyMap.remove(c);
                    } else if (count > 1) {
                        copyMap.put(c, count - 1);
                    }
                }
                if (copyMap.size() == 0) {
                    int windowLen = i - left + 1;
                    if (minLen > windowLen) {
                        minLen = windowLen;
                        window = s.substring(left, i + 1);
                        break;
                    }
                }
            }
            left++;
        }
        
        return window;
    }
}