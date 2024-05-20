/*
Chase question

Sam is part of the organizing team arranging the university's career fair and
has the list of companies and their respective arrival time and durations.
Due to limited budget, there is only one stage.
Given each company's arrival time and the durations, determine the max number of
promotional events that can be hosted in the career fair

https://leetcode.com/discuss/interview-question/374846/Twitter-or-OA-2019-or-University-Career-Fair

idea:
greedy

*/

import java.util.*;

public class MaxEvents {
    public int getMaxEvents (int[] arr, int[] dur) {
        int numbersToDrop = 0;
        int size = arr.length;
        int [][] intervals = new int[size][2];

        for (int i = 0; i < size; i++) {
            intervals[i] = new int[] {arr[i], arr[i] + dur[i]};
        }

        Arrays.sort(intervals, (a, b) -> (a[1] - b[1]));

        for (int[] interval : intervals) {
        	System.out.println("interval " + Arrays.toString(interval));
        }

        // the finish time of first event;
        int curTime = intervals[0][1];

        for (int i = 1; i < size; i++) {
            int[] toSchedual = intervals[i];
            int start = toSchedual[0];
            int end = toSchedual[1];

            if (start < curTime) {
                numbersToDrop++;
            } else {
                curTime = end;
            }
        }

        return size - numbersToDrop;
    }

    int solve(int[] start, int[] duration) {
        int result = 1;
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < start.length; i++)
            intervals.add(new int[] { start[i], start[i] + duration[i] - 1 });
        Collections.sort(intervals, (a, b) -> a[1] - b[1]);
        int curEnd = intervals.get(0)[1];
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i)[0] > curEnd) {
                result++;
                curEnd = intervals.get(i)[1];
            }
        }
        return result;
    }

    public static void main(String[] args) {
    	MaxEvents eg = new MaxEvents();

        // int[] arrival1 = {1,3,3,5,7};
        // int[] duration1 = {2,2,1,2,1};
        // System.out.println(eg.getMaxEvents(arrival1, duration1));
        // int[] arrival2 = {1,3,3,5,7, 6, 9};
        // int[] duration2 = {2,2,1,2,1, 10,2};
        // System.out.println(eg.getMaxEvents(arrival2, duration2));
        // int[] arrival = {1,3,35,7};
        // int[] duration = {2,2,1,2,1};
        // System.out.println(eg.getMaxEvents(arrival, duration));

        int[] arrival3 = {1,2,1,1,4};
        int[] duration3 = {3, 1, 8, 2, 7};
        // [1, 4]
        // [1, 6]
        // [1, 9]
        // [1, 3]
        // [4, 11]
        System.out.println(eg.getMaxEvents(arrival3, duration3));
    }
}