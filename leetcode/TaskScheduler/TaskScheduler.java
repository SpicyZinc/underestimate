/*
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
However, there is a non-negative cooling interval n that means between two same tasks,
there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ['A','A','A','B','B','B'], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

Note:
The number of tasks is in the range [1, 10000].

idea:
https://www.cnblogs.com/grandyang/p/7098764.html

understand the problem, n = 2 intervals, A -> B -> idle -> A -> B -> idle -> A -> B,
first two A there are 2 intervals
use the letter appearing the most to schedule
there will be the appearing times of this letter sections
each section is n + 1 length, this way to guarantee that
every two same tasks has n intervals (different task or idle are all treated as interval) in between

each row
A and B appear the maxFreq (3) times, maxCount = 2, n = 2
partCount = maxFreq - 1 (2 parts, part 1 and part 2)
partLength = n - (maxCount - 1)

A B _      → part 1
A B _      → part 2
A B        → final part (no idle needed after last)

emptySlots = partCount * partLength

return is The return value is the total number of CPU time slots
*/

public class TaskScheduler {
    // 2025
    public int leastInterval(char[] tasks, int n) {
        int size = tasks.length;
        
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        
        Arrays.sort(freq);
        int maxFreq = freq[25];
        int maxCount = 1;
        
        for (int i = 24; i >= 0; i--) {
            if (freq[i] == maxFreq) {
                maxCount++;
            } else {
                break;
            }
        }

        // (maxFreq - 1) → number of gaps between the most frequent tasks
        // (n + 1) → each gap has space for:
        // 1 most frequent task
        // n cooldown units
        // So (maxFreq - 1) * (n + 1) builds the full "frame"

        return Math.max(size, (n + 1) * (maxFreq - 1) + maxCount);
    }

    public int leastInterval(char[] tasks, int n) {
        int size = tasks.length;
        if (size == 0) {
            return 0;
        }
        if (n == 0) {
            return size;
        }

        int[] letters = new int[26];

        for (int i = 0; i < size; i++) {
            letters[tasks[i] - 'A']++;
        }

        Arrays.sort(letters);
        int mostTask = letters.length - 1;
        int i = mostTask;

        while (i >= 0 && letters[mostTask] == letters[i]) {
            i--;
        }

        int maxCount = mostTask - i;

        return Math.max(size, (letters[mostTask] - 1) * (n + 1) + maxCount);
    }
}
