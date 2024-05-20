/*
The count-and-say sequence is the sequence of integers with the first five terms as following:
1.     1
2.     11
3.     21
4.     1211
5.     111221

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:
Input: 1
Output: "1"

Example 2:
Input: 4
Output: "1211"

idea:
The idea is simple, compare the current char in the string with the previous one, if they are the same, count +1, 
if not, print the previous char (count + char), set the new char and count, until the string ends.

note,
any sequence whose last digit is always 1

Time Complexity: O(4^{n/3})
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

    // 03/14/2019
    public String countAndSay(int n) {
        String start = "1";
        // note, i < n, because temp initialization is index 0
        for (int i = 1; i < n; i++) {
            // generate a new ith start, overwrite the last one
            start = cas(start);
        }
        
        return start;
    }
    
    public String cas(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int count = 1;
            while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                i++;
                count++;
            }
            sb.append(count + "" + s.charAt(i));
        }

        return sb.toString();
    }

 // public String countAndSay(int n) {
 //        if (n == 1) {
 //            return "1";
 //        }
 //        String s = "1";
 //        int i = 2;
 //        while (i <= n) {
 //            s = helper(s);
 //            i++;
 //        }
 //        return s;
 //    }

 //    public String helper(String s) {
 //        StringBuilder sb = new StringBuilder();
 //        int i = 0;
 //        int n = s.length();

 //        while (i < n) {
 //            int count = 1;
 //            while (i + 1 < n && s.charAt(i) == s.charAt(i + 1)) {
 //                i++;
 //                count++;
 //            }
 //            sb.append(count + "" + s.charAt(i));
 //            i++;
 //        }
        
 //        return sb.toString();
 //    }
}