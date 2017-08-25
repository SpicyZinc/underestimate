/*
Given two strings S and T, determine if they are both one edit distance apart.

idea:
http://blog.csdn.net/xudli/article/details/42340867
https://www.cnblogs.com/grandyang/p/5184698.html

has nothing to do with dp "edit distance"
delete, insert, replace are one edit distance.
delete and insert: difference between two string is 1
replace must the same length

count is the number of edit times
*/

public class OneEditDistance {
	public boolean isOneEditDistance(String s, String t) {
		if ( (s == null || s.length == 1) && (t == null || t.length == 1) ) {
			return false;
		}
		if (s == null || s.length == 1) {
			return t.length == 1;
		}
		if (t == null || t.length == 1) {
			return s.length == 1;
		}
		if (Math.abs(s.length - t.length) > 1) {
			return false;
		}

		int m = s.length();
		int n = t.length();
		int editCnt = 0;
		int i = 0;
		int j = 0;

		while (i < m && j < n) {
			if (s.charAt(i) != t.charAt(j)) {
				editCnt++;
				if (editCnt > 1) {
					return false;
				}
				if (m > n) {
					i++;
				}
				else if (m < n) {
					j++;
				}
				else {
					i++;
					j++;
				}
			}
			else {
				i++;
				j++;
			}
		}
		if (i < m || j < n) {
			editCnt++;
		}

		return editCnt == 1;
	}
}