/*
You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
You can attend an event i at any day d where startTimei <= d <= endTimei. You can only attend one event at any time d.
Return the maximum number of events you can attend.

Example 1:
https://assets.leetcode.com/uploads/2020/02/05/e1.png
Input: events = [[1,2],[2,3],[3,4]]
Output: 3
Explanation: You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.

Example 2:
Input: events= [[1,2],[2,3],[3,4],[1,2]]
Output: 4

Constraints:
1 <= events.length <= 105
events[i].length == 2
1 <= startDayi <= endDayi <= 105

idea:
Sort events increased by start time.
Priority queue pq keeps the current open events.

Iterate from the day 1 to day 100000,
Each day, we add new events starting on day d to the queue pq.
Also we remove the events that are already closed.

Then we greedily attend the event that ends soonest.

*/

class MaximumNumberOfEventsThatCanBeAttended {
    public int maxEvents(int[][] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        Arrays.sort(A, (a, b) -> Integer.compare(a[0], b[0]));
        int i = 0;
        int count = 0;
        int n = A.length;

        for (int d = 1; d <= 100000; d++) {
            while (!pq.isEmpty() && pq.peek() < d) {
                pq.poll();
            }

            while (i < n && A[i][0] == d) {
                pq.offer(A[i++][1]);
            }

            if (!pq.isEmpty()) {
                // greedily attend the event that ends soonest
                pq.poll();
                count++;
            }
        }

        return count;
    }
    // TLE
    public int maxEvents(int[][] A) {
        // greedy thinking: sort the events based on finish time, then grab the first finish event to do in 1 day
        // if there are multiple days can be used, use the earliest.
        
        Arrays.sort(events, (a, b) -> a[1] != b[1]? a[1] - b[1]: a[0] - b[0]);
        
        Set<Integer> hs = new HashSet<>();
        
        for (int[] e: events) {
            if (e[1] == e[0]) {
                hs.add(e[0]);
            } else {
                // if there are many days can be used grab the earliest day
                for (int i = e[0]; i <= e[1]; i++) {
                    if (hs.add(i)) {
                        break;
                    }
                }
            }
        }

        return hs.size();
    }
}
