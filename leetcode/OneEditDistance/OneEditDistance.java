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
        int m = s.length();
        int n = t.length();
        int i = 0;
        int j = 0;
    
        int editCnt = 0;
        
        if (Math.abs(m - n) > 1) {
            return false;
        }
        
        while (i < m && j < n) {
            char a = s.charAt(i);
            char b = t.charAt(j);

            if (a != b) {
                editCnt++;

                if (editCnt > 1) {
                    return false;
                }

                if (m > n) {
                    i++;
                } else if (n > m) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            } else {
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