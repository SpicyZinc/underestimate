/*
Returns a pointer to the first occurrence of needle in haystack, or null 
if needle is not part of haystack.

idea:
1. substring() and equals()
2. brute force, two for loops
*/

public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0; 
        if (haystack.length() == 0) return -1;
        if (needle.length() > haystack.length()) return -1;

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int k = i + needle.length();
            if ( haystack.substring(i, k).equals(needle) ) return i; 
        }
        return -1;
    }

    // better brute force version
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        int h = haystack.length();
        int n = needle.length();
        for (int i = 0; i <= h - n; i++) {
            int j = 0;
            for (j = 0; j < n; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) break;
            }
            if (j == n) return i;
        }
        
        return -1;
    }
}