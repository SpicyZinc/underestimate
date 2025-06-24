/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.
For example, Given n = 2, return ["11","69","88","96"].

idea:
https://segmentfault.com/a/1190000003787462

insert from the middle
typical recursion in for loop
convert string to stringbuilder, pass it to append(), then toString() to recursively call build
先产生简单的 Strobogrammatic number 例如 1位的 Strobogrammatic number 然后在那个基础上不断两边加1 6 8 9 和 1 9 8 6
*/

import java.util.*;

public class StrobogrammaticNumber {
    public static void main(String[] args) {
        StrobogrammaticNumber eg = new StrobogrammaticNumber();
        List<String> result = eg.findStrobogrammatic(4);
        System.out.println(result);
    }
    // 12/26/2018
    // 应该考虑到 empty string
    // 1“”1, 6“”9 ...
    // m < n 能两边加 0
    public List<String> findStrobogrammatic(int n) {
        return build(n, n);
    }

    public List<String> build(int m, int n) {
        List<String> result = new ArrayList<>();

        if (m == 0) {
            result.add("");

            return result;
        }

        if (m == 1) {
            result.add("0");
            result.add("1");
            result.add("8");

            return result;
        }

        // why minus 2, because insert one before and one after
        List<String> list = build(m - 2, n);

        for (String num : list) {
            if (m < n) {
                result.add("0" + num + "0");
            }

            result.add("1" + num + "1");
            result.add("6" + num + "9");
            result.add("8" + num + "8");
            result.add("9" + num + "6");
        }

        return result;
    }


    public List<String> findStrobogrammatic(int n) {
        List<String> result = new ArrayList<>();
        build(n, "", result);

        return result;
    }

    public void build(int n, String sNumber, List<String> result) {
        char[] table = {'0', '1', '8', '6', '9'};

        if (sNumber.length() == n) {
            result.add(sNumber);
            return;
        }

        boolean isLast = n - sNumber.length() == 1;

        for (int i = 0; i < table.length; i++) {
            char c = table[i];
            // two cases no need to append c
            // first character cannot be '0', how to detect? sNumber.length() == 0 && c == '0', n == 1 no need to satisfy these
            // last character cannot be 6 or 9, because no matching part
            if ( (sNumber.length() == 0 && c == '0' && n != 1) || isLast && (c == '6' || c == '9') ) {
                continue;
            }
            StringBuilder sb = new StringBuilder(sNumber);
            append(isLast, c, sb);
            build(n, sb.toString(), result);
        }
    }
    
    public void append(boolean isLast, char c, StringBuilder sb) {
        if (c == '6') {
            sb.insert(sb.length() / 2, "69");
        } else if (c == '9') {
            sb.insert(sb.length() / 2, "96");
        } else {
            sb.insert(sb.length() / 2, isLast ? c : "" + c + c);
        }
    }
}