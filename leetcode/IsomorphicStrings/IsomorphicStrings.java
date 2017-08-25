/*
Given two strings s and t, determine if they are isomorphic. Two strings are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.
Given "foo", "bar", return false.
Given "paper", "title", return true.

idea:


*/

import java.util.*;

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if ( s == null || t == null ) {
            return false;
        }

        if ( s.length() != t.length() ) {
            return false;
        }

        if ( s.length() == 0 && t.length() == 0 ) {
            return true;
        }

        HashMap<Character, Character> hm = new HashMap<Character, Character>();
        for (int i = 0; i < s.length(); i++) {
            Character c1 = s.charAt(i);
            Character c2 = t.charAt(i);

            Character c = getKey(hm, c2);
            if ( c != null && c != c1 ) {
                return false;
            }
            else if ( hm.containsKey(c1) ) {
                if ( hm.get(c1) != c2 ) {
                    return false;
                }
            }
            else {
                hm.put(c1, c2);
            }
        }

        return true;
    }


    private Character getKey(HashMap<Character, Character> map, Character target) {
        for ( Map.Entry<Character, Character> entry : map.entrySet() ) {
            if ( entry.getValue().equals(target) ) {
                return entry.getKey();
            }
        }

        return null;
    }
}