/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. 
Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Hint:
How about using a data structure such as deque (double-ended queue)?

idea:
http://www.shuatiblog.com/blog/2014/07/27/Sliding-Window-Maximum/
http://n00tc0d3r.blogspot.com/2013/04/sliding-window-maximum.html
https://hellosmallworld123.wordpress.com/2014/05/28/sliding-window-maximum/

queue position 0 keeps the max's index so far
if not within the window, should remove it

Each element in the list is being inserted and then removed at most once. 
Therefore, the total number of insert and delete operations is 2n.
Therefore it is an O(n) solution.
*/
public class SlidingWindowMaximum {
    // best solution
    public int[] slidingWindowMax(int[] array, int w) {
        if ( array.length == 0 || array == null ) {
            return array;
        }
        int[] ans = new int[array.length - w + 1];
        List<Integer> q = new LinkedList<Integer>();
        // Queue stores indices of array, and values are in decreasing order.
        // In this way, the top element in queue is the max in window
        for (int i = 0; i < array.length; i++) {
            // 1. remove element from head until first number within window
            if (!q.isEmpty() && q.get(0) + w <= i) {
                // it's OK to change 'while' to 'if' in the line above because we actually remove 1 element at most
                q.remove(0);
            }
            // 2. before inserting i into queue, remove from the tail of the queue indices with smaller value array[i]
            while (!q.isEmpty() && array[q.get(q.size() - 1)] <= array[i]) {
                q.remove(q.size() - 1);
            }
            q.add(i);
            // 3. set the max value in the window (always the top number in queue)
            if (i + 1 >= w) {
                ans[i + 1 - w] = array[q.get(0)];
            }
        }
        return ans;
    }

    // direct solution
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) {
            return new int[] {};
        }
        if (n <= k) {
            return new int[] {getMax(nums, 0, n - 1)};
        }
        int i = 0;
        int[] max = new int[n - k + 1];
        while (i + k <= n) {
            max[i] = getMax(nums, i, i + k - 1);
            i++;
        }
        return max;
    }
    
    public int getMax(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(max, nums[i]);
        }

        return max;
    }
}

public static void maxSlidingWindow(int a[], int n, int w, int b[]) { 
    if (n <= 0 || n < w) {
        return;  
    }
    LinkedList<Integer> l = new LinkedList<Integer>();  
    for (int i = 0; i < w; i++) {  
        while (l.size() > 0 && a[l.peekLast()] <= a[i]) {
            l.pollLast();  
        }
        l.add(i);  
    }
    b[0] = a[l.peekFirst()];  
    for (int i = w; i < n; i++) {  
        while (l.size() > 0 && l.peekFirst() <= i-w) {
            l.removeFirst();  
        }
        while (l.size()>0 && a[l.peekLast()] <= a[i]) {
            l.pollLast();
        }
        l.add(i);  
        b[i-w+1] = a[l.peekFirst()];  
    }
}  