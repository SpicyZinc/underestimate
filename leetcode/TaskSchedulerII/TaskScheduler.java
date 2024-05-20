/*
You are given a 0-indexed array of positive integers tasks, representing tasks that need to be completed in order, where tasks[i] represents the type of the ith task.
You are also given a positive integer space, which represents the minimum number of days that must pass after the completion of a task before another task of the same type can be performed.
Each day, until all tasks have been completed, you must either:
Complete the next task from tasks, or Take a break.
Return the minimum number of days needed to complete all tasks.

Example 1:
Input: tasks = [1,2,1,2,3,1], space = 3
Output: 9
Explanation:
One way to complete all tasks in 9 days is as follows:
Day 1: Complete the 0th task.
Day 2: Complete the 1st task.
Day 3: Take a break.
Day 4: Take a break.
Day 5: Complete the 2nd task.
Day 6: Complete the 3rd task.
Day 7: Take a break.
Day 8: Complete the 4th task.
Day 9: Complete the 5th task.
It can be shown that the tasks cannot be completed in less than 9 days.

Example 2:
Input: tasks = [5,8,8,5], space = 2
Output: 6
Explanation:
One way to complete all tasks in 6 days is as follows:
Day 1: Complete the 0th task.
Day 2: Complete the 1st task.
Day 3: Take a break.
Day 4: Take a break.
Day 5: Complete the 2nd task.
Day 6: Complete the 3rd task.
It can be shown that the tasks cannot be completed in less than 6 days.

Constraints:
1 <= tasks.length <= 10^5
1 <= tasks[i] <= 10^9
1 <= space <= tasks.length

idea:
task 在第几天完成的(0-indexed)

method 1
map : task <-> days to complete

mine 存 value <-> index 的map
1.  We simply iterate in the array and check if a[i] type of task is present in the map or not.
2.  If not present, days increment by one, and we move to next index.
3.  But if map contains a[i], we process as follows :
	a. We find current space = what is the gap of last occurrence of a[i] till now
	b. if this current gap is less than space, calculate breaks and add it to days.
	b. else if current gap is already more than "space", simply day++
4. return days
*/

class TaskSchedulerII {
    public long taskSchedulerII(int[] tasks, int space) {
        long days = 0;
        // task 在第几天完成的(0-indexed)
        Map<Integer, Long> map = new HashMap<>();

        for (int i = 0; i < tasks.length; i++) {
            int task = tasks[i];
            
            if (map.containsKey(task)) {
                long last = map.get(task);
                long timeElapsed = days - last;
                if (timeElapsed <= space) {
                    days += space - timeElapsed + 1;
                }
            }
            map.put(task, days++);
        }

        return days;
    }
    // My own way
    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Integer> hm = new HashMap<>();
        long days = 0;
        for (int i = 0; i < tasks.length; i++) {
            int task = tasks[i];
            if (hm.containsKey(task)) {
                int prevIdx = hm.get(task);
                int diff = i - prevIdx - 1;
                if (diff < space) {
                    long extraBreaks = space - diff;
                    days += extraBreaks;
                    // travel map and increment no. of breaks for all keys
                    for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
                        hm.put(entry.getKey() , entry.getValue() - (int) extraBreaks);
                    }
                }
            }
            days++;
            hm.put(task, i);
        }

        return days;
    }
}