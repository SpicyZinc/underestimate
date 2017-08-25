/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, 312211, 13112221

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.

idea:
The idea is simple, compare the current char in the string with the previous one, if they are the same, count +1, 
if not, print the previous char (count + char), set the new char and count, until the string ends.

One thing to note:
any sequence whose last digit is always 1
*/

import java.util.*;

public class CountAndSay {
	public static void main(String[] args) {
		CountAndSay aTest = new CountAndSay();
		Scanner aScanner = new Scanner(System.in);
		System.out.print("\nPlease input an integer to get CountAndSay sequence: ");
		int input = aScanner.nextInt();
		System.out.print("The CountAndSay sequence of " + input + " is: ");
		System.out.print(aTest.countAndSay(input));
		System.out.println();
	}
	
    public String countAndSay(int n) {
        String temp = String.valueOf(1);
        // attention: i < n, because temp initialization is index 0
        for (int i = 1; i < n; i++) {
            temp = cas(temp);
        }

        return temp;
    }
 
    public String cas(String n) {
        char[] chars = n.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            int count = 1;
            while (i+1 < chars.length && chars[i] == chars[i+1]) {
                count++;
				i++;                
            }
            sb.append(String.valueOf(count) + String.valueOf(chars[i]));
        }  
        return sb.toString();
    }

    // self written version passed test
    public String countAndSay(int n) {
        String start = "1";
        for (int i = 1; i < n; i++) {
            start = cas(start);
        }
        
        return start;
    }
    
    public String cas(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int count = 1;
            while ( i + 1 < s.length() && s.charAt(i + 1) == s.charAt(i) ) {
                count++;
                i++;
            }
            sb.append(count + "" + s.charAt(i));
        }
        
        return sb.toString();
    }
}