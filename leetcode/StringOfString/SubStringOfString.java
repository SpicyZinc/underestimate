/*
Implement strStr()
Returns a pointer to the first occurrence of needle in haystack, 
or null if needle is not part of haystack. 

idea:
1. null is different from length() == 0
if null, there is no length
cannot use length to judge is null or not
some corner cases must be considered for all problems

*/


public class SubStringOfString {
	public static void main(String[] args) {
		String s = "";
		String ss = null;
		System.out.println(s.length());
		// if null, there is no length
		System.out.println(ss.length());
	}
	// passed small test, but failed the big because of run time
    public String strStr(String haystack, String needle) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (needle == null || needle.length() == 0)
            return haystack;
        
        int sizeHaystack = haystack.length();
        int sizeNeedle = needle.length();
		int p1 = 0;
        while (p1 < sizeHaystack) {			
            int pBegin = p1;
            int p2 = 0;
            while (p1 < sizeHaystack && p2 < sizeNeedle && haystack.charAt(p1) == needle.charAt(p2)) {
                    p1++;
                    p2++;
			}
            
            if (p2 == sizeNeedle)
                return haystack.substring(pBegin);
            p1 = pBegin + 1;
        }        
        return null;
    }
	// passed small test, but failed the big because of run time
	public String search(String haystack, String needle) {
		if(needle == null || needle.length() == 0) {
			return haystack;
		}
		// pointer to walk the haystack one by one
		int p1 = 0;
		while(p1 < haystack.length()) {
			int pStart = p1;
			int p2 = 0;
			while(p1 < haystack.length() && p2 < needle.length() && haystack.charAt(p1) == needle.charAt(p2)) {
				p1++;
				p2++;
				if(p2 == needle.length()) {
					return haystack.substring(pStart);
				}
			}
			p1 = pStart + 1;
		}
		return null;
	}
}