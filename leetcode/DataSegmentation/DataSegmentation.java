/*
Given a string str, we need to extract the symbols and words of the string in order.
The length of str does not exceed 10000.
The given str contains only lowercase letters, symbols, and spaces.

Example
Given str = "(hi (i am)bye)", return ["(","hi","(","i","am",")","bye",")"].
Explanation:
Separate symbols and words.

Given str = "#ok yes", return ["#","ok","yes"]
Explanation:
Separate symbols and words.

Given str = "##s",return ["#","#","s"]
Explanation:
Separate symbols and words.

idea:
lintcode
just one while loop, I think it has practical usage

*/

import java.util.*;

public class DataSegmentation {
    public static void main(String[] args) {
        DataSegmentation eg = new DataSegmentation();
        String str = "(hi (i am)bye)";
        String[] result = eg.dataSegmentation(str);

        for (String s : result) {
            System.out.print(s + " ");
        }
    }

    /**
     * @param str: The input string
     * @return: The answer
     */
    public String[] dataSegmentation(String str) {
        List<String> result = new ArrayList<String>();

        int len = str.length();
        int i = 0;
        while (i < len) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                int right = i;
                while (right < len && Character.isLetter(str.charAt(right))) {
                    right++;
                }
                result.add( str.substring(i, right) );    
                i = right;
            } else {
                i++;
                if (c == ' ') {
                    continue;
                } else {
                    result.add(String.valueOf(c));    
                }
            }
        }
        
        return result.toArray(new String[result.size()]);
    }
}