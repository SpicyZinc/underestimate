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
https://segmentfault.com/a/1190000003903509
double ended queue

Each element in the queue is being inserted and then removed at most once. 
Therefore, the total number of insert and delete operations is 2n. It is an O(n) solution.

sliding window length = (length - 1) - (k - 1) + 1 = length - k + 1

maintain a queue less than or equal to k, this guarantees window of size k
*/
public class SlidingWindowMaximum {
    // best solution
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums == null) return nums;
        int[] ans = new int[nums.length - k + 1];
        List<Integer> dequeue = new LinkedList<Integer>();
        // dequeue stores indices of nums, and corresponding values are in decreasing order. This way, the head in queue is always the max
        for (int i = 0; i < nums.length; i++) {
            // 1. remove left most element from queue so queue size is k
            // i - queue.get(0) + 1 = k so queue.get(0) + k > i
            if (!dequeue.isEmpty() && dequeue.get(0) + k <= i) dequeue.remove(0);
            // 2. before inserting i into queue, remove indices pointing to value smaller than nums[i] from queue tail to make sure descending order            
            while (!dequeue.isEmpty() && nums[dequeue.get(dequeue.size() - 1)] < nums[i]) {
                dequeue.remove(dequeue.size() - 1);
            }
            dequeue.add(i);
            // 3. i + 1 is the length, start to collect max
            if (i + 1 >= k) {
                ans[i + 1 - k] = nums[dequeue.get(0)];
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