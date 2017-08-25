/*
Implement strStr().

Returns a pointer to the first occurrence of needle in haystack, or null 
if needle is not part of haystack.

idea:

make sure understand the question
if found out the needle in the haystack, 
return the sub string containg the needle and all the rest of the haystack

Best self passed version

*/

public class ImplementStrStr {
    public String strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() == 0) {
            return haystack;
        }
        int h = haystack.length();
        int n = needle.length();
        if (n > h) {
            return null;
        }
        
        for (int i = 0; i < h; i++) {
            int k = i + n;
            String s = "";
            if (k <= h) {
                s = haystack.substring(i, k);  
            }
            if (s.equals(needle)) {
                return haystack.substring(i);
            }
        }
        return null;
    }

    // better brute force version
    public String strStr(String haystack, String needle) {
        if (haystack==null || needle == null || needle.length()==0)
            return haystack;
        if (needle.length()>haystack.length())
            return null;
        for (int i=0; i<=haystack.length()-needle.length(); i++) {
            boolean successFlag = true;
            for (int j=0; j<needle.length(); j++) {
                if (haystack.charAt(i+j) != needle.charAt(j)) {
                    successFlag = false;
                    break;
                }
            }
            if (successFlag)
                return haystack.substring(i);
        }
        return null;
    }
}