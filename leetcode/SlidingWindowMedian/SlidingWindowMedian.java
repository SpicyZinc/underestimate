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
https://blog.csdn.net/MebiuW/article/details/54408831
maxHeap stores smaller half
minHeap stores greater half
minHeap equal or one more than maxHeap in the term of size
第一个 就是 i >= k 实际是 k+1 size 要减去一个了
然后slide window
how
减一个
加一个

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
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b.compareTo(a));

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] median = new double[n - k + 1];
        for (int i = 0; i < n; i++) {
            add(nums[i]);
            // When the index reaches size of k, we can find the median and remove the first element in the window
            if (i + 1 >= k) {
                median[i - k + 1] = findMedian();
                remove(nums[i - k + 1]);
            }
        }

        return median;
    }

    private double findMedian(){
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek() / 2.0 + maxHeap.peek() / 2.0;
    }

    // This method adds the next element in the sliding window in the appropriate heap and balances the heaps
    private void add(int num) {
        if (maxHeap.size() == 0 || maxHeap.peek() >= num) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);    
        }

        balanceHeaps();
    }

    // This method removes the first element in the sliding window from the appropriate heap and balances the heaps
    private void remove(int num) {
        if (num > maxHeap.peek()) {
            minHeap.remove(num);
        } else {
            maxHeap.remove(num);    
        }

        balanceHeaps();
    }    
    
    // This method keeps the height of the 2 heaps same
    private void balanceHeaps(){
        if (maxHeap.size() == minHeap.size())
            return;
        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.add(maxHeap.poll());
        else if (maxHeap.size() < minHeap.size())
            maxHeap.add(minHeap.poll());        
    }


    // 01/30/2019
    // lintcode version
    PriorityQueue<Integer> maxHeap, minHeap;
    public List<Integer> medianSlidingWindow(int[] A, int k) {
        List<Integer> res = new ArrayList<Integer>();
        int n = A.length;
        if (n == 0) {
            return res;
        }
        
        maxHeap = new PriorityQueue<Integer>(n, Collections.reverseOrder());
        minHeap = new PriorityQueue<Integer>(n);
        
        int i;
        for (i = 0; i < n; i++) {
            if (maxHeap.size() == 0 || A[i] <= maxHeap.peek()) {
                maxHeap.offer(A[i]);
            } else {
                minHeap.offer(A[i]);
            }
            
            balance();
            if (i - k >= 0) {
                if (A[i - k] > maxHeap.peek()) {
                    minHeap.remove(A[i - k]);
                } else {
                    maxHeap.remove(A[i - k]);
                }
            }
            
            balance();
            
            if (i >= k - 1) {
                res.add(maxHeap.peek());
            }
        }
        
        return res;
    }
    
    private void balance() {
        while (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        
        while (minHeap.size() < maxHeap.size() - 1) {
            minHeap.offer(maxHeap.poll());
        }
    }


	// method 1, TLE
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length < k) {
        	return new double[] {findMedian(nums, 0, nums.length)};
        }
        List<Double> medians = new ArrayList<Double>();
        for (int i = 0; i + k <= nums.length; i++) {
        	medians.add(findMedian(nums, i, i + k));
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
        int len = numArray.length;
		if (len % 2 == 0) {
		    median = ((double)numArray[len / 2] + (double)numArray[len / 2 - 1]) / 2;
		} else {
		    median = (double) numArray[len / 2];
		}

		return median;
    }

    // method 2
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> b.compareTo(a));
    
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
    
    public double getMedian() {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            return 0;
        }
        
        if (minHeap.size() > maxHeap.size()) {
            return (double) minHeap.peek();
        } else {
            return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2.0;
        }
    }
    
    public void add(int num) {
        if (num < getMedian()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        balance();
    }
    
    public void remove(int num) {
        if (num < getMedian()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balance();
    }
    // balance two heaps
    // always minHeap equal with or one more than the maxHeap
    public void balance() {
        if (minHeap.size() < maxHeap.size()) {
            minHeap.add(maxHeap.poll());
        }

        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.add(minHeap.poll());
        }
    }
}