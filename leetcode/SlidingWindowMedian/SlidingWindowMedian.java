/*
Median is the middle value in an ordered integer list.
If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
Examples: 
[2,3,4] , the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
You can only see the k numbers in the window. Each time the sliding window moves right by one position.
Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------                -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

idea:
1. direct thought
2. use minHeap and maxHeap, https://leetcode.com/submissions/detail/44226667/
tried to debug why heap is empty after first element gets in,
how could I did not think of poll() in getMedian() instead of peek()
because getMedian() called frequently, heap is empty.
note:
Input:
[2147483647,2147483647]
2
Output:
[-1.00000]
Expected:
[2147483647.00000]
( (double) (minHeap.peek() + maxHeap.peek()) / 2.0 ) this could be overflow
*/

public class SlidingWindowMedian {
	// method 1, TLE 
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length < k) {
        	return new double[] {findMedian(nums, 0, nums.length)};
        }
        List<Double> medians = new ArrayList<Double>();
        for (int i = 0; i + k <= nums.length; i++) {
        	medians.add( findMedian(nums, i, i + k) );
        }
        double[] result = new double[medians.size()];
        for (int i = 0; i < medians.size(); i++) {
        	result[i] = medians.get(i);
        }
        return result;
    }

    private double findMedian(int[] nums, int start, int end) {
    	int[] numArray = new int[end - start];
    	for (int i = start; i < end; i++) {
    		numArray[i-start] = nums[i];
    	}
    	Arrays.sort(numArray);
    	double median;
		if (numArray.length % 2 == 0) {
		    median = ((double)numArray[numArray.length/2] + (double)numArray[numArray.length/2 - 1])/2;
		} else {
		    median = (double) numArray[numArray.length/2];
		}

		return median;
    }

    // method 2

    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return b.compareTo(a);
        }
    });

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (k > n) {
            return new double[] {0};
        }
        
        double[] medians = new double[n - k + 1];
        for (int i = 0; i <= n; i++) {
            if (i >= k) {
                medians[i - k] = getMedian();
                remove(nums[i - k]);
            }
            if (i < n) {
                add(nums[i]);
            }
        }
        return medians;
    }
    
    public void add(int num) {
        if (num < getMedian()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        // balance two heaps
        balance();
    }
    
    
    public void remove(int num) {
        if (num < getMedian()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        // balance two heaps
        balance();
    }
    
    // always minHeap equal with or one more than the maxHeap
    public void balance() {
        if (minHeap.size() < maxHeap.size()) {
            minHeap.add(maxHeap.poll());
        }
        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.add(minHeap.poll());
        }
    }
    
    public double getMedian() {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            return 0;
        }
        if (minHeap.size() > maxHeap.size()) {
            return (double) minHeap.peek();
        } else {
            return ( (double) minHeap.peek() + (double) maxHeap.peek() ) / 2.0;
        }
    }
}