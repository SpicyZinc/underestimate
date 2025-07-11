/*
There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day.
A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.

Given n online courses represented by pairs (t, d), your task is to find the maximal number of courses that can be taken.

Example:
Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
Output: 3

Explanation: 
There're totally 4 courses, but you can take 3 courses at most:
First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day. 
Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day. 
The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.

Note:
The integer 1 <= d, t, n <= 10,000.
You can't take two courses simultaneously.

idea:
greedy algorithm
*/
public class CourseSchedule {
    public int scheduleCourse(int[][] courses) {
        // sort the course based on deadlines, courses ending earlier are at first
        Arrays.sort(courses, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        // PriorityQueue courses having longer duration are at front
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        int finishTime = 0;
        for (int[] course : courses) {
            int duration = course[0];
            int deadline = course[1];
            finishTime += duration; // update finish time
            pq.add(duration);
            // if finish time exceeds deadline, drop so far the longest duration
            if (finishTime > deadline) {
                finishTime -= pq.poll();
            }
        }

        return pq.size();
    }
}