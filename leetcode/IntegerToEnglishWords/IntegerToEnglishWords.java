/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Hint:
1. Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
2. Group the number by thousands (3 digits). 
You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
3. There are many edge cases. What are some good test cases? 
Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)

idea:
http://www.rgagnon.com/javadetails/java-0426.html

divide the number into a chunk of 3 digits (thousand)
create a helper to get English words for number less than a thousand
*/

import java.util.*;

public class IntegerToEnglishWords {
    public static final int BILLION = 1000000000;
    public static final int MILLION = 1000000;
    public static final int THOUSAND = 1000;

    public String numberToWords(int num) {
        Map<Integer, String> hm = new HashMap<>();

        hm.put(0, "Zero");
        hm.put(1, "One");
        hm.put(2, "Two");
        hm.put(3, "Three");
        hm.put(4, "Four");
        hm.put(5, "Five");
        hm.put(6, "Six");
        hm.put(7, "Seven");
        hm.put(8, "Eight");
        hm.put(9, "Nine");
        hm.put(10, "Ten");
        hm.put(11, "Eleven");
        hm.put(12, "Twelve");
        hm.put(13, "Thirteen");
        hm.put(14, "Fourteen");
        hm.put(15, "Fifteen");
        hm.put(16, "Sixteen");
        hm.put(17, "Seventeen");
        hm.put(18, "Eighteen");
        hm.put(19, "Nineteen");
        hm.put(20, "Twenty");
        hm.put(30, "Thirty");
        hm.put(40, "Forty");
        hm.put(50, "Fifty");
        hm.put(60, "Sixty");
        hm.put(70, "Seventy");
        hm.put(80, "Eighty");
        hm.put(90, "Ninety");

        // edge case
        if (num == 0) {
            return hm.get(0);
        }

        StringBuilder sb = new StringBuilder();
        String word = "";
        if (num >= BILLION) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            word = lessThan1000Number(num / BILLION, hm) + " Billion";
            sb.append(word);
            num %= BILLION;
        }

        if (num >= MILLION) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            word = lessThan1000Number(num / MILLION, hm) + " Million";
            sb.append(word);
            num %= MILLION;
        }

        if (num >= THOUSAND) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            word = lessThan1000Number(num / THOUSAND, hm) + " Thousand";
            sb.append(word);
            num %= THOUSAND;
        }

        if (num > 0) {
            if (sb.length() > 0) {
                sb.append(" ");
            }

            word = lessThan1000Number(num, hm);
            sb.append(word);
        }

        return sb.toString();
    }
    // helper to convert number < 1000 to English words
    // 1000 is unit
    public String lessThan1000Number(int num, Map<Integer, String> hm) {
        // take 156 as an example
        StringBuilder sb = new StringBuilder();
        String word = "";

        if (num >= 100) {
            word = hm.get(num / 100) + " Hundred";
            sb.append(word);
            num %= 100;
        }

        if (num > 20) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            word = hm.get(num / 10 * 10);
            sb.append(word);
            num %= 10;
        }

        if (num > 0) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            word = hm.get(num);
            sb.append(word);
        }

        return sb.toString();
    }
}
