/*
A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by some number of 1's (also possibly none).
You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
Return the minimum number of flips to make s monotone increasing.

Example 1:
Input: s = "00110"
Output: 1
Explanation: We flip the last digit to get 00111.

Example 2:
Input: s = "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.

Example 3:
Input: s = "00011000"
Output: 2
Explanation: We flip to get 00000000.

Constraints:
1 <= s.length <= 10^5
s[i] is either '0' or '1'.


idea:
1. direct
left window with all 0 supposedly
right window with all 1 supposedly
increase size of left window from 0
decrease size of right window from s.length()

从right to left 过来一个0 无所谓
从right to left 过来一个1 flip 加一
*/

class FlipStringToMonotoneIncreasing {
    public int minFlipsMonoIncr(String s) {
        // which is the maxFlipCount
        int totalZeroCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                totalZeroCount++;
            }
        }

        int minFlipCount = totalZeroCount;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                totalZeroCount--;
                minFlipCount = Math.min(minFlipCount, totalZeroCount);
            } else {
                totalZeroCount++;
            }
        }

        return minFlipCount;
    }
}
