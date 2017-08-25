/**
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR"
 * -------->
 * P   A   H   N
 * -------->
 * A P L S I I G
 * -------->
 * Y   I   R
 * 
 * PAHNAPLSIIGYIR
 * 
 * idea:
 *
 * http://www.lifeincode.net/programming/leetcode-zigzag-conversion-java/
 *
 * 0       8        16 ...
 * 1     7 9      15
 * 2   6   10   14 
 * 3 5     11 13 
 * 4       12
 * 
 * line by line
 * get index and use charAt() to get char, then append to the return result
 * 
 * based on row number, zigzag size is 2 * nRows - 2
 * zigzag step length is always 2 * nRows - 2
 * except for first row and last row, 
 * rows in between first and last rows 
 * numbers' index between 4-number or 5-number or nRows-number column == (index + base - 2 * row_index)
 * index += step length
 * */

public class ZigZagConversion {
	public static void main(String[] args) {
		ZigZagConversion aTest = new ZigZagConversion();
	}
	public ZigZagConversion() {
		String s = convert("PAYPALISHIRING", 3);		
		System.out.println("After ZigZag Conversion: " + s);
	}
    public String convert(String s, int nRows) {
    	if (s.length() == 0 || s == null) {
    		return "";
        }
        if (nRows == 1) {
        	return s;
        }
        String res = "";
        int base = 2 * nRows - 2;
        for (int i = 0; i < nRows; i++) {  
            for (int j = i; j < s.length(); j += base) {  
                res += s.charAt(j);  
                if (i != 0 && i != nRows - 1 && j + base - 2 * i < s.length()) {
                    res += s.charAt(j + base - 2 * i);
                }
            }                  
        }  
        
        return res;
    }
}
