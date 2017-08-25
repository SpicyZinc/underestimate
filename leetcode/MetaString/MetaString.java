import java.util.*;

public class MetaString {
	public static void main(String[] args) {
		MetaString eg = new MetaString();
		// boolean a = eg.isMetaString("converse", "conserve");
		// boolean b = eg.isMetaString("abc", "cba");
		// boolean c = eg.isMetaString("erts", "stre");
		boolean a = eg.areMetaStrings("converse", "conserve");
		boolean b = eg.areMetaStrings("abc", "cba");
		boolean c = eg.areMetaStrings("erts", "stre");
		boolean d = eg.areMetaStrings("aee", "eea");
		System.out.println(a + " " + b + " " + c + " " + d);
	}

	public boolean areMetaStrings(String str1, String str2) {
	    int len1 = str1.length();
	    int len2 = str2.length();

	    if (len1 != len2) return false;
	 	if (str1.equals(str2)) return false;
		// store indexes of mismatched characters
	    int prev = -1, curr = -1;
	    int count = 0;
	    for (int i = 0; i < len1; i++) {
	        if (str1.charAt(i) != str2.charAt(i)) {
	            count++;
	            if (count > 2) return false;
	            prev = curr;
	            curr = i;
	        }
	    }
	    // Check if previous unmatched character of string1 is equal to current unmatched of string2
	    // also check for current unmatched character
	    return count == 2 && str1.charAt(prev) == str2.charAt(curr) && str1.charAt(curr) == str2.charAt(prev);
	}

	public boolean isMetaString(String s, String t) {
		if (s == null || s.length() == 0 || t == null || t.length() == 0) {
			return false;
		}
		if (s.length() != t.length()) {
			return false;
		}
		if (s.equals(t)) {
			return false;
		}
		int len = s.length();
		char[] ss = s.toCharArray();
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				swap(ss, i, j);
				String temp = new String(ss);
				if (temp.equals(t)) {
					return true;
				}
				swap(ss, i, j);
			}
		}

		return false;
	}

	public void swap(char[] s, int a, int b) {
		char temp = s[a];
		s[a] = s[b];
		s[b] = temp;
	}
}