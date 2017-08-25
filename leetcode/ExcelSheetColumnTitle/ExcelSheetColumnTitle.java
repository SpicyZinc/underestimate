/*
Given a positive integer, 
return its corresponding column title as appear in an Excel sheet.

For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB

idea:
a simple question,
it is just 26-nary
*/

public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while ( n > 0 ) {
        	n--;
        	char x = (char)(n % 26 + 'A');
        	sb.append( x );
        	n /= 26;
        }

        return sb.reverse().toString();
    }
}