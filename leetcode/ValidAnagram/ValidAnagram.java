/*
Given two strings s and t, 
write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

idea:
256 is key point, since alphabet
use char (int) as key, the number of character appearing as value
loop through the 1st string, to increase the number of characters 
loop through the 2nd string, to decrease the number of characters
if any one is not zero
return false
otherwise return true
*/
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
    	if (s == null && t == null) {
	    	return true;
	    }
        if ( s == null || t == null || s.length() != t.length() ) {
        	return false;
        }
        int[] c = new int[256];
            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i)]++; 
                c[t.charAt(i)]--;
            }

            for (int i = 0; i < 256; i++) {
                if (c[i] != 0) { 
                	return false;
                }
            }
            return true;
    }

    public boolean isAnagram(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) {
        	alphabet[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
        	alphabet[t.charAt(i) - 'a']--;
        }
        for (int i : alphabet) {
        	if (i != 0) {
        		return false;
        	}
        }
        return true;
    }

    public boolean isAnagram(String s, String t) {
	    if (s == null && t == null) {
	        return true;
	    }
	    if (s == null || t == null || s.length() != t.length()) {
	        return false;
	    }
	    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	    for (int i = 0; i < s.length(); i++) {
	        if (map.containsKey(s.charAt(i))) {
	            map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
	        }
	        else {
	            map.put(s.charAt(i), 1);
	        }
	    }
	    for (int i = 0; i < t.length(); i++) {
	        if (map.containsKey(t.charAt(i))) {
	            map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
	        }
	    }
	    for (int i = 0; i < s.length(); i++) {
	        if (map.get(s.charAt(i)) != 0) {
	            return false;
	        }
	    }
	    return true;
	}
}