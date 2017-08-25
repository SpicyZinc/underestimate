/*
Given a pattern and a string str, find if str follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

idea:
hashmap is used
*/

import java.util.*;

public class WordPattern {
	public static void main(String[] args) {
		HashMap hm = new HashMap();
		System.out.println(hm.put('A', "111"));    // null
		System.out.println(hm.put('A', "222"));    // 111
		System.out.println(hm.get('A'));           // 222
	}
	// string to character map
	public boolean wordPattern(String pattern, String str) {
       	String[] words = str.split("\\s");
	    if (words.length != pattern.length()) {
	        return false;
	    }
       	Map<String, Character> hm = new HashMap<String, Character>();
	    for (int i = 0; i < words.length; i++) {
	    	String word = words[i];
	    	if (hm.containsKey(word)) {
	    		if (hm.get(word) != pattern.charAt(i)) {
	    			return false;
	    		}
	    	}
	    	else {
		    	if (hm.size() > 0 && hm.containsValue(pattern.charAt(i))) return false;
	    		hm.put(word, pattern.charAt(i));
	    	}
	    }

	    return true;
    }
    // character to string map
    public boolean wordPattern(String pattern, String str) {
    	if ((pattern.length() == 0 || pattern == null) && (str.length() == 0 || str == null)) {
    		return true;
    	}
    	if (pattern.length() != str.split("\\s").length) {
    		return false;
    	}
       	HashMap<Character, String> hm = new HashMap<Character, String>();
       	String[] words = str.split("\\s");
       	for (int i = 0; i < pattern.length(); i++) {
       		char c = pattern.charAt(i);
       		String word = words[i];
       		if (!hm.containsKey(c)) {
       			if (hm.containsValue(word)) {
       				return false;
       			}
       			else {
       				hm.put(c, word);
       			}
       		}
       		else {
       			if (!hm.get(c).equals(word)) {
       				return false;
       			}
       		}
       	}

       	return true;
    }
}
