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
double ended queue
https://segmentfault.com/a/1190000003903509

双向队列中存着 目前为止 降序的 最大值的index
它的长度不一定对应于 window of k 因为有可能加入一些小的数 最大值还是原来的
[1]
[3]
[3, -1]
[3, -1, -3]
[5]
[5, 3]
[6]
[7]


Each element in the queue is being inserted and then removed at most once. 
The total number of insert and delete operations is 2n; therefore, it is an O(n) solution.

sliding window length = (length - 1) - (k - 1) + 1 = length - k + 1
maintain a queue less than or equal to k, this guarantees window of size k
*/
import java.util.*;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        SlidingWindowMaximum eg = new SlidingWindowMaximum();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        eg.maxSlidingWindow(nums, k);
    }
    // 08/26/2018
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums == null) {
            return new int[0];
        }
        
        LinkedList<Integer> deque = new LinkedList<>();
        int[] maxs = new int[nums.length - k + 1];
        
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.add(i);
            if (i + 1 >= k) {
                maxs[i + 1 - k] = nums[deque.peek()];
            }
            // System.out.println(deque);
            System.out.println(convert(nums, deque));
        }
        
        return maxs;
    }

    private List<Integer> convert(int[] nums, LinkedList<Integer> indices) {
        List<Integer> values = new LinkedList<>();
        for (int i = 0; i < indices.size(); i++) {
            values.add( nums[indices.get(i)] );
        }

        return values;
    }

    // best solution
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums == null) {
            return nums;
        }
        
        int n = nums.length;
        int[] maxs = new int[n - k + 1];
        // double end queue stores the indices of values in nums,
        // and corresponding values are in decreasing order. This way, the head in queue is always the max
        List<Integer> dequeue = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            // 1. remove left most element from queue so queue size is k
            // i - queue.get(0) + 1 = k so queue.get(0) + k > i
            if (!dequeue.isEmpty() && dequeue.get(0) + k == i) {
                dequeue.remove(0);
            }
            // 2. before inserting i into queue, remove indices pointing to value smaller than nums[i] from queue tail
            // to guarantee descending order
            while (!dequeue.isEmpty() && nums[dequeue.get(dequeue.size() - 1)] < nums[i]) {
                dequeue.remove(dequeue.size() - 1);
            }
            dequeue.add(i);
            // length of sliding window >= k, start collecting max
            if (i + 1 >= k) {
                maxs[i - k + 1] = nums[dequeue.get(0)];
            }
        }

        return maxs;
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
    // priority queue version
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums == null) {
            return new int[] {};    
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        int[] result = new int[nums.length - k + 1];
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
 
            if (pq.size() >= k) {
                pq.remove(nums[i - k]);                
            }

            pq.add(num);
            // 一定是这里先达到 k
            if (pq.size() >= k) {
                result[idx++] = pq.peek();
            }
        }
        
        return result;
    }

}