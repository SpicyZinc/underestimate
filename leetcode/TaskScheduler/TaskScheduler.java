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

first understand the problem, n = 2 intervals, A -> B -> idle -> A -> B -> idle -> A -> B, first two A there are 2 intervals
use the letter appearing the most to schedule
there will be the appearing times of this letter sections
each section is n + 1 length, this way to guarantee that
every two same tasks has n intervals (different task or idle are all treated as interval) in between

note to last section
*/

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        if (tasks.length == 0) return 0;
        if (n == 0) return tasks.length;
        int size = tasks.length;
        int[] letters = new int[26];
        for (int i = 0; i < size; i++) {
            letters[tasks[i] - 'A']++;
        }
        Arrays.sort(letters);
        int last = letters.length - 1;
        int i = last;
        while (i >= 0 && letters[last] == letters[i]) i--;
        return Math.max(size, (letters[last] - 1) * (n + 1) + (last - i));
    }
}