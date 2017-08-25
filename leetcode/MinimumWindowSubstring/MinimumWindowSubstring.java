/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".
If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

idea:
http://blog.csdn.net/linhuanmars/article/details/20343903
遇到没在字典里面的字符可以继续移动窗口右端, 而移动窗口左端的条件是当找到满足条件的串之后, 一直移动窗口左端直到有字典里的字符不再在窗口里.
Dictionary is implemented as HashMap, 一开始key包含字典中所有字符, value就是该字符的数量, 然后遇到字典中字符时就将对应字符的数量减一

suppose char are 256 ascii code
two count array, ascii code as index
count[] for String S
t[] for String T

Start from each char found in T as it is in S
the worst assumption is minWindow = S.length() + 1, 
as long as finding a smaller minWindow, replace it

each time the outside big for loop, update front = back, front advances to find the next window
then updates back, then front = back, front advances again to find the next window...

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
        
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        int tLen = T.length();
        for (int i = 0; i < tLen; i++) {
            char c = T.charAt(i);
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }
        
        int sLen = S.length();
        int windowLeft = 0;
        int count = 0; // counter for appeared T's character in S
        int minLen = sLen + 1;
        String res = "";
        
        for (int j = 0; j < sLen; j++) {
            char c = S.charAt(j);
            if (hm.containsKey(c)) {
                hm.put( c, hm.get(c) - 1 );
                if (hm.get(c) >= 0) {
                    count++;
                }
                
                while (count == tLen) {
                    if (hm.containsKey(S.charAt(windowLeft))) {
                        hm.put(S.charAt(windowLeft), hm.get(S.charAt(windowLeft)) + 1);
                        if (hm.get(S.charAt(windowLeft)) >= 1) {
                            if (minLen > j - windowLeft + 1) {
                                minLen = j - windowLeft + 1;
                                res = S.substring(windowLeft, j + 1);
                            }
                            count--;
                        }
                    }
                    windowLeft++;
                }
            }
        }
        
        return res;
    }

    public String minWindow(String S, String T) {  
        if (S == null || T == null || S.length()==0 || T.length()==0) {
            return "";
        }

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();  
        for (int i=0; i<T.length(); i++) {  
            if (map.containsKey(T.charAt(i))) {  
                map.put(T.charAt(i), map.get(T.charAt(i))+1);  
            }  
            else {
                map.put(T.charAt(i), 1);
            }
        } 

        String res = "";  
        int count = 0;  
        int pre = 0;  
        int minLen = S.length() + 1;  

        for (int i = 0; i < S.length(); i++) {
            char tmp = S.charAt(i);
            if ( map.containsKey(tmp) ) {  
                map.put(tmp, map.get(tmp) - 1);  
                if (map.get(tmp) >= 0) {
                    count++;
                }
                while (count == T.length()) {  
                    if (map.containsKey(S.charAt(pre))) {  
                        map.put(S.charAt(pre), map.get(S.charAt(pre)) + 1);  
                        if (map.get(S.charAt(pre)) > 0) {  
                            if (minLen > i - pre + 1) {
                                minLen = i - pre + 1;  
                                res = S.substring(pre, i + 1);  
                            }  
                            count--;  
                        }  
                    }  
                    pre++;  
                }  
            }  
        }  

        return res;  
    }
	// self written, passed 267 / 268 test cases
    // borrowed from Substring with concatenation of all words
    public String minWindow(String S, String T) {
        if (T.length() > S.length() || T.length() == 0) {
            return "";
        }
        int len = T.length();
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for (int i = 0; i < len; i++) {
            char c = T.charAt(i);
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int size = S.length();
        int min = size + 1;
        String result = "";
        while (size - left >= len) {
            HashMap<Character, Integer> copy = new HashMap<Character, Integer>(hm);
            for (int i = left; i < size; i++) {
                char c = S.charAt(i);
                if (copy.containsKey(c)) {
                    if (copy.get(c) == 1) {
                        copy.remove(c);
                    }
                    else if (copy.get(c) > 1) {
                        copy.put(c, copy.get(c) - 1);
                    }
                    if (copy.size() == 0) {
                        int window = i - left + 1;
                        if (window < min) {
                            min = window;
                            result = S.substring(left, i + 1);
                        }
                    }
                }
            }
            left++;
        }
        return result;
    }
}