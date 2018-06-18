/*
A magical string S consists of only '1' and '2' and obeys the following rules:
The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.
The first few elements of string S is the following: S = "1221121221221121122……"

If we group the consecutive '1's and '2's in S, it will be:
1 22 11 2 1 22 1 22 11 2 11 22 ......
and the occurrences of '1's or '2's in each group are:
1 2	2 1 1 2 1 2 2 1 2 2 ......

You can see that the occurrence sequence above is the S itself.
Given an integer N as input, return the number of '1's in the first N number in the magical string S.
Note: N will not exceed 100,000.

Example 1:
Input: 6
Output: 3
Explanation: The first 6 elements of magical string S is "12211" and it contains three 1's, so return 3.

idea:
native thought
construct the string first
then count how many 1's

0 1 2 3 4 5 6 7 8 9 10 11  12  13 14 15  16 17 18
1 2 2 1 1 2 1 2 2 1 2  2 | 1   1  2  1   1  2  2 ......
1  2   2  1 1  2  1  2       2    1    2     2 ......
*/

public class MagicalString {
    public int magicalString(int n) {
        StringBuilder sb = new StringBuilder("1221121221221121122");
        
        int basePointer = 12;
        int magicStrPointer = sb.length();
        
        while (magicStrPointer < n) {
            char startChar = sb.charAt(basePointer);
            if (startChar == '1') {
                if (sb.charAt(magicStrPointer - 1) == '1') {
                    sb.append('2');
                } else {
                    sb.append('1');
                }
                magicStrPointer++;
            } else {
                if (sb.charAt(magicStrPointer - 1) == '1') {
                    sb.append("22");
                } else {
                    sb.append("11");
                }
                magicStrPointer += 2;
            }
            basePointer++;
        }
        
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (sb.charAt(i) == '1') {
                cnt++;
            }
        }
        
        return cnt;
    }
}