/**
You are given a string time in the form of hh:mm, where some of the digits in the string are hidden (represented by ?).
The valid times are those inclusively between 00:00 and 23:59.
Return the latest valid time you can get from time by replacing the hidden digits.

Example 1:
Input: time = "2?:?0"
Output: "23:50"
Explanation: The latest hour beginning with the digit '2' is 23 and the latest minute ending with the digit '0' is 50.

Example 2:
Input: time = "0?:3?"
Output: "09:39"

Example 3:
Input: time = "1?:22"
Output: "19:22"
 
Constraints:
time is in the format hh:mm.
It is guaranteed that you can produce a valid time from the given string.

idea:
brute force,
not good interview question

level: easy
*/

import java.util.*;

class LatestTimeByReplacingHiddenDigits {
    public static void main(String[] args) {
        LatestTimeByReplacingHiddenDigits eg = new LatestTimeByReplacingHiddenDigits();
        String s = eg.maximumTime("2?:?0");

        System.out.println(s);
    }

    public String maximumTime(String time) {
        char h1 = time.charAt(0);
        char h2 = time.charAt(1);
        char m1 = time.charAt(3);
        char m2 = time.charAt(4);

        if (h1 == '?') {
            // cannot be 24
            h1 = h2 != '?' && h2 >= '4' ? '1' : '2';
        }

        if (h2 == '?') {
            // cannot be 24
            h2 = h1 != '2' ? '9' : '3';
        }

        if (m1 == '?') {
            // cannot be 59
            m1 = '5';
        }
        if (m2 == '?') {
            // cannot be 59
            m2 = '9';
        }

        return "" + h1 + h2 + ':' + m1 + m2;
    }
}