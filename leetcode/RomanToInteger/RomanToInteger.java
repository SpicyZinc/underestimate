/*
multiple of 1
The symbols "I", "X", "C", and "M" can be repeated three times in succession, but never no more. 
(They may appear more than three times if they appear non-sequentially, such as XXXIX.)

"D", "L", and "V" can never be repeated.

"I" can be subtracted from "V" and "X" only. 
"X" can be subtracted from "L" and "C" only. 
"C" can be subtracted from "D" and "M" only.

multiple of 5 
"V", "L", and "D" can never be subtracted.

rule: always 1 subtracted from 5, 10
in this analogy, 10 subtracted 50

some rules:
相同的数字连写, 所表示的数等于这些数字相加得到的数, 例如: III = 3
小的数字在大的数字右边, 所表示的数等于这些数字相加得到的数, 例如: VIII = 8
小的数字, 限于（I, X和C）在大的数字左边, 所表示的数等于大数减去小数所得的数, 例如: IV = 4
正常使用时, 连续的数字重复不得超过三次
在一个数的上面画横线, 表示这个数扩大1000倍（本题只考虑3999以内的数, 所以用不到这条规则）
*/

import java.util.*;

public class RomanToInteger {
    public static void main(String[] args) {
        RomanToInteger aTest = new RomanToInteger();
        String roman1 = "MDCCCCLXXXXVIIII"; // 1999
        String roman2 = "MCMXCIX"; // 1999
        String roman3 = "MIM"; // 1999
        String roman4 = "MCMXC"; // 1990
        String thisYear = "MMXIII"; // 2013
        
        System.out.println("roman 1 == " + aTest.romanToInt(roman1));
        System.out.println("roman 2 == " + aTest.romanToInt(roman2));
        System.out.println("roman 3 == " + aTest.romanToInt(roman3));
        System.out.println("roman 4 == " + aTest.romanToInt(roman4));
        System.out.println("This year == " + aTest.romanToInt(thisYear));
    }
    // 07/18/2018
    // note, compare the char at the behind following with current preceding, 
    // compare integer value not the ROMAN char value
    // note a equal sign
    public int romanToInt(String s) {
        Map<Character, Integer> hm = new HashMap<>();
        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);
        
        int val = 0;
        
        for (int i = 0; i < s.length(); i++) {
            boolean isLastPos = i == s.length() - 1;
            char c = s.charAt(i);
            // curr and next 指的是c 对应的 value 从 hm里 对应的
            int curr = hm.get(c);
            int next = isLastPos ? 0 : hm.get(s.charAt(i + 1));

            if (isLastPos || curr >= next) {
                val += curr;
            } else {
                val -= curr;
            }
        }

        return val;
    }

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
 
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            x += sign(s, i, map) * map.get(c);
        }
 
        return x;
    }
    // sign function to decide plus "+" sign and negative "-" sign  
    public int sign(String s, int i, Map<Character, Integer> map) {
        if (i == s.length()-1) {
            return 1;
        }
        // MCMXLIV = 1000 + (1000 - 100) + (50 - 10) + (5 - 1) = 1944
        if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
            return -1;
        } else { // map.get(s.charAt(i)) >= map.get(s.charAt(i+1))
            return 1;
        }
    }
}