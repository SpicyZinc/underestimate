/*
Given a column title as appear in an Excel sheet, 
return its corresponding column number.

For example:
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28

idea:
both working
treat this as 26-inary
*/

public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        char c = 'A';
        for (int i = 1; i <= 26; i++) {
            hm.put(c, i);
            c += 1; 
        }
        int result = 0;
        for ( int i = s.length() - 1; i >= 0 ; i-- ) {
            char current = s.charAt(i);
            // result += hm.get(current) * (int)Math.pow(26, s.length() - 1 - i);
            result += (current-'A'+1) * (int)Math.pow(26, s.length() - 1 - i);
        }

        return result;
    }

    public int titleToNumber(String s) {
        int res = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(len - i - 1);
            res += ((ch - 'A' + 1) * (int)Math.pow(26, i));
        }
        
        return res;
    }
}