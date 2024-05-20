/*
You are given a list of songs where the ith song has a duration of time[i] seconds.

Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

Example 1:
Input: time = [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60

Example 2:
Input: time = [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.

Constraints:
1 <= time.length <= 6 * 104
1 <= time[i] <= 500

https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/solutions/256738/java-c-python-two-sum-with-k-60/
idea:
similar to 2 sum with equal to K of 60
map 
key is [0 - 59]
value is the frequency

because time[i] < 500
so 60 * 9 540 or 600 should be fine
or use x % 60 = (60 - t % 60) % 60,
*/

class PairsOfSongsWithTotalDurationsDivisibleBy60 {
	// 2023
    public int numPairsDivisibleBy60(int[] time) {
        int map[]  = new int[60];
        int pairsCount = 0;
        for (int t : time) {
        	// 540 also works
            pairsCount += map[(600 - t) % 60];
            map[t % 60] += 1;
            // Both works
            // pairsCount += map[(60 - t % 60) % 60];
            // map[t % 60] += 1;
        }

        return pairsCount;
    }
    // 2019
	public int numPairsDivisibleBy60(int[] time) {
        int[] map = new int[60];
        int pairsCnt = 0;

        for (int t : time) {
        	int remainder = t % 60;
            pairsCnt += map[(60 - remainder) % 60];

            map[remainder] += 1;
        }

        return pairsCnt;
    }
}
