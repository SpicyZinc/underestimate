/*
Given two strings s and t, determine if they are isomorphic. Two strings are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.
Given "foo", "bar", return false.
Given "paper", "title", return true.

idea:
same as word pattern
note: s to t map or t to s map
*/

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> hm = new HashMap<Character, Character>();
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (hm.containsKey(sChar)) {
                if (hm.get(sChar) != tChar) return false;
            } else {
                if (hm.containsValue(tChar)) return false;
                hm.put(sChar, tChar);
            }
        }
        return true;
    }
}
