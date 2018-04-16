/*
Implement strStr()
Returns a pointer to the first occurrence of needle in haystack, 
or null if needle is not part of haystack. 

idea:
1. haystack string must be walked one letter by one
it might happen first several letters of haystack and first several letters of needle are the same,
but not all the same,
Remember when this happens, starts from next letter, i=i+1 will skip the needle-length span
"mississippi", "issip"	
without i-j+1 return null	
with i-j+1 return "issippi", which is correct

2. null is different from length() == 0
some corner cases must be considered for all problems

this one works the best
*/

public class StringString {
	// this is the only one passing big test 
	// use this one
	public String strStr(String haystack, String needle) {
		if(haystack.length() < needle.length()) {
			return null;
		}
		// null is different from length() == 0
		if(needle == null || needle.length() == 0) {
			return haystack;
		}
		int i = 0;
		int j = 0;
		while (i < haystack.length() && j < needle.length()) {
			if (haystack.charAt(i) == needle.charAt(j)) {
				i++;
				j++;
				if (j == needle.length()) {
					return haystack.substring(i - j);
				}
			} else {
				// i-j+1 is necessary
				// i-j is substring, +1 is to move to next letter
				i = i - j + 1;
				j = 0;
			}
		}

		return null;        
	}
}

