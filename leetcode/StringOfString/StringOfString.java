/*
Implement strStr()
Returns a pointer to the first occurrence of needle in haystack, 
or null if needle is not part of haystack. 

*/
public class StringOfString {

	public static void main(String[] args) {
		String source = "hereroheroero";
		String pattern = "hero";

		if (search(source, pattern) >= 0) {
			System.out.print("Found needle in haystack position: " + search(source, pattern) + "\n");
		}
		else 
			System.out.print("No target string in the source string");

	}
	// brute force algorithm
	public static int search (String haystack, String needle) {
		if (needle.length() == 0 || needle == null) 
            return haystack;
		for (int i = 0; i < haystack.length(); i++) {
			for (int j = 0; j < needle.length() && i+j < haystack.length(); j++) {
				// once found one letter not matching, break the for loop on the needle (target)
				if (needle.charAt(j) != haystack.charAt(i+j)) {
					break;
				}
				// once needle j index equals the length-1, finish the loop on the needle (target) 
				// j == length-1 means needle has been walked thoroughly
				else if (j == needle.length()-1) {
					// return i;
					return haystack.substring(i);
				}
			}
		}
		return null;
		return -1;
	}
}
