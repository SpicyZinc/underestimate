/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
(you may want to display this pattern in a fixed font for better legibility)
-------->
P   A   H   N
-------->
A P L S I I G
-------->
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:
string convert(string s, int numRows);

Example 1: 
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2: 
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

idea:
第一行
最后一行
步长 base (zigzag size) 就是 多少行 * 2 然后 再加上 折回来的部分剪去两个
numRows + numRows - 1 - 1
0,8
1,9
3,11


对于其他的中间行 还要多一个元素
还不到下一个 所以 j+base - i * 2
j + base - 2 * i (row_index)


0       8        16 ...
1     7 9      15
2   6   10   14 
3 5     11 13 
4       12

line by line
get index and use charAt() to get char, then append to the return result

*/

public class ZigZagConversion {
	public static void main(String[] args) {
		ZigZagConversion eg = new ZigZagConversion();
		String s = eg.convert("PAYPALISHIRING", 3);
		System.out.println("After ZigZag Conversion: " + s);
	}

	public String convert(String s, int nRows) {
		if (s.length() == 0 || s == null) {
			return "";
		}
		if (nRows == 1) {
			return s;
		}

		String result = "";
		int n = s.length();
		int base = 2 * nRows - 2;

		for (int i = 0; i < nRows; i++) {
			for (int j = i; j < n; j += base) {
				result += s.charAt(j);
				// 在中间的row 还要多加一个char
				if (i > 0 && i < nRows - 1 && j + base - 2 * i < n) {
					result += s.charAt(j + base - 2 * i);
				}
			}
		}

		return result;
	}
}
