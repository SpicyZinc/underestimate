/*
idea:
brute force
https://leetcode.com/problems/design-most-recently-used-queue/solutions/2127909/java-sqrt-decomposition-with-easy-to-understand-code/

*/

class MRUQueue {

    int[] queue;

    public MRUQueue(int n) {
        queue = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            queue[i] = i;
        }
    }
    
    public int fetch(int k) {
        // get the value at index k
        int value = queue[k];

        // moving all the value forward to free the last spot
        for (int i = k + 1; i < queue.length; i++) {
            queue[i - 1] = queue[i];
        }
        queue[queue.length - 1] = value;

        return value;
    }
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * MRUQueue obj = new MRUQueue(n);
 * int param_1 = obj.fetch(k);
 */