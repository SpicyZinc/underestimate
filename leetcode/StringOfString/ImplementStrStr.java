/*
Returns a pointer to the first occurrence of needle in haystack, or null 
if needle is not part of haystack.

idea:
1. substring() and equals()
2. brute force, two for loops, the good part is i + j, use one pointer, instead of start, j
https://www.programcreek.com/2012/12/leetcode-implement-strstr-java/
*/

public class ImplementStrStr {
    // Thu Feb  1 15:58:26 2024
    public int strStr(String haystack, String needle) {
        int hsLen = haystack.length();
        int nLen = needle.length();
        if (hsLen < nLen) {
            return -1;
        }

        for (int i = 0; i <= hsLen - nLen; i++) {
            int j = 0;
            while (j < nLen && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == nLen) {
                return i;
            }
        }

        return -1;
    }

	// Tue May 21 23:23:08 2019
	public int strStr(String haystack, String needle) {
        if (haystack.length() == 0 && needle.length() == 0) {
            return 0;
        }

        for (int i = 0; i < haystack.length(); i++) {
            String s = haystack.substring(i);
            if (s.contains(needle)) {
                return i + s.indexOf(needle);
            }
        }
        
        return -1;
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
        	return 0; 
        }
        if (haystack.length() == 0) {
        	return -1;
        }
        if (needle.length() > haystack.length()) {
        	return -1;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int k = i + needle.length();
            if (haystack.substring(i, k).equals(needle)) {
                return i; 
            }
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
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                	break;
                }
            }
            if (j == n) {
            	return i;
            }
        }
        
        return -1;
    }
    // use this one
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return 0;
        }

        if (needle.length() == 0) {
            return 0;
        }

        int lenS = haystack.length();
        int lenT = needle.length();

        for (int i = 0; i < lenS; i++) {
            if (i + lenT > lenS) {
                return -1;
            }

            int start = i;
            for (int j = 0; j < lenT; j++) {
                if (haystack.charAt(start) == needle.charAt(j)) {
                    if (j == lenT - 1) {
                        return i;
                    }
                    start++;
                } else {
                    break;
                }
            }
        }

        return -1;
    }

    // lintcode version, need to check some source == null
    public int strStr(String source, String target) {
        if (source == null || target == null) {
            return 0;
        }
        if (target.length() == 0) {
            return 0;
        }

        int lenS = source.length();
        int lenT = target.length();
        
        for (int i = 0; i < lenS; i++) {
            if (i + lenT > lenS) {
                return -1;
            }
            int start = i;
            for (int j = 0; j < lenT; j++) {
                if (source.charAt(start) == target.charAt(j)) {
                    if (j == lenT - 1) {
                        return i;
                    }
                    start++;
                } else {
                    break;
                }
            }
        }
        
        return -1;
    }
}