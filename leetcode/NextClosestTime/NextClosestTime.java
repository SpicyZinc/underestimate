/*
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.
There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:
Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later. It is not 19:33, because this occurs 23 hours and 59 minutes later.

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
	public static void main(String[] args) {
		NextClosestTime eg = new NextClosestTime();
		String time1 = "19:34";
		String time2 = "23:59";
		String next1 = eg.nextClosestTime(time1);
		String next2 = eg.nextClosestTime(time2);

		System.out.println(next1);
		System.out.println(next2);
	}

	public String nextClosestTime(String time) {
        
        String[] matches = time.split(":");
        String hours = matches[0];
        String minutes = matches[1];
        
        int timeInMins = 60 * Integer.parseInt(hours) + Integer.parseInt(minutes);
        // HH:MM each digit represents different minutes
        int[] map = {10 * 60, 60, 10, 1};
        char[] next = new char[4];
        
        int NEXT = 60 * 24;
        // for each minute in next 60 * 24 minutes
        for (int i = 1; i <= NEXT; i++) {
            int nextMins = (timeInMins + i) % NEXT;
            // convert minutes to HH:MM
            int d = 0;
            // will always overwrite next for each next minute
            for (; d < 4; d++) {
                next[d] = (char) ('0' + nextMins / map[d]);
                nextMins %= map[d];
                
                if (time.indexOf(next[d]) == -1) {
                    break;
                }
            }
            // break early if digit == 4, meaning found out a closest time
            if (d == 4) {
                break;
            }
        }
        
        return new String(next, 0, 2) + ":" + new String(next, 2, 2);
    }
}
