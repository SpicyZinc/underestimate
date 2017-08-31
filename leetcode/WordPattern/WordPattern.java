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
Isomorphic Strings

note: vice versa
string - char
but no string, it has value of char, return false

1. str to char map
2. char to str map
*/

import java.util.*;

public class WordPattern {
	public static void main(String[] args) {
		HashMap hm = new HashMap();
		System.out.println(hm.put('A', "111"));    // null
		System.out.println(hm.put('A', "222"));    // 111
		System.out.println(hm.get('A'));           // 222
	}
    // str to char map
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split("\\s");
        if (pattern.length() != words.length) return false;

        Map<String, Character> hm = new HashMap<String, Character>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char c = pattern.charAt(i);
            if (hm.containsKey(word)) {
                if (hm.get(word) != c) return false;
            } else {
                if (hm.containsValue(c)) return false;
                hm.put(word, c);
            }
        }
        
        return true;
    }
    // char to str map
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split("\\s");
        if (pattern.length() != words.length) return false;

        Map<Character, String> hm = new HashMap<Character, String>();
        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (hm.containsKey(c)) {
                if (!hm.get(c).equals(word)) return false;
            } else {
                if (hm.containsValue(word)) return false;
                hm.put(c, word);
            }
        }

        return true;
    }
}
