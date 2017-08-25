/**
 * Reverse digits of an integer
 * 
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321 
 * 
 * Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, 
 * then the reverse of 1000000003 overflows. How should you handle such cases?
 * 
 * 
 * idea:
 * % and / are used to get each digit from bit to thousand, high bit
 * then save them into list
 * now list from the beginning has already reversed integer 
 * walk through the list, use multiply times of ten according to how many zeros
 * 
 * */

import java.util.*;

public class ReverseIntegerII {
	public static void main(String[] args) {
		ReverseIntegerII aTest = new ReverseIntegerII(); 
	}
	public ReverseIntegerII() {
		int x = reverse(123);
		// int y = reverse(-123);
		System.out.println(x);
		// System.out.println(y);
	}
	// not accepted by leetcod oj
	public int reverse(int x) {
        String tmp = "" + x;
		// String tmp = Integer.toString(x);
		// System.out.println(tmp + "??");
        int i = 0;
        int size = tmp.length();
        char[] ss = tmp.toCharArray();
        while(i < size / 2) {
        	char c = ss[i];
        	ss[i] = ss[size - 1 - i];
        	ss[size - 1 - i] = c;
			i++;
        } 
        tmp = new String(ss, 0, size);
		// System.out.println(tmp + "?/?");
        return Integer.parseInt(tmp);
    }
}