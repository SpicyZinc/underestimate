/*
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.
There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:
Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.

Example 2:
Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.

idea:
convert HH:MM to minutes
from next 24 * 60 minutes, try each minute, found one, must break early
[60 * 10, 60, 10, 1]
*/

class NextClosestTime {
	public String nextClosestTime(String time) {
        int[] mins = { 60 * 10, 60, 10, 1 };
        String[] matches = time.split(":");
        // convert time to minutes representation
        int minutes = Integer.parseInt(matches[0]) * 60 + Integer.parseInt(matches[1]);
        char[] next = new char[4];
        // in the next 24 * 60 minutes
        for (int i = 1; i <= 1440; i++) {
            int m = (minutes + i) % 1440;
            int digit = 0;
            for (digit = 0; digit < 4; digit++) {
                next[digit] = (char) ('0' + m / mins[digit]);
                m %= mins[digit];
                if (time.indexOf(next[digit]) == -1) break;
            }
            // break early if digit == 4, meaning found out a closest time
            if (digit == 4) break;
        }

        return new String(next, 0, 2) + ':' + new String(next, 2, 2);
    }
}
