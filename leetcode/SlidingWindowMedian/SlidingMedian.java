public class SlidingMedian {
	
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length - k + 1;
        if (n <= 0) return new double[0];
        double[] result = new double[n];
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(
            new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    return i2.compareTo(i1);
                }
            }
        );
        
        for (int i = 0; i <= nums.length; i++) {
            if (i >= k) {
                result[i - k] = getMedian(minHeap, maxHeap);
            	remove(nums[i - k], minHeap, maxHeap);
            }
            if (i < nums.length) {
                add(nums[i], minHeap, maxHeap);
            }
        }
        
        return result;
    }
    
    private void add(int num, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        if (num < getMedian(minHeap, maxHeap)) {
            maxHeap.add(num);
        }
        else {
            minHeap.add(num);
        }
        if (maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
        }
        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        }
    }
	
    private void remove(int num, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
    	if (num < getMedian(minHeap, maxHeap)) {
    	    maxHeap.remove(num);
    	}
    	else {
    	    minHeap.remove(num);
    	}
    	if (maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
    	}
        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        }
    }
	
    private double getMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
    	if (maxHeap.isEmpty() && minHeap.isEmpty()) return 0;
    	    
    	if (maxHeap.size() == minHeap.size()) {
    	    return ((double)maxHeap.peek() + (double)minHeap.peek()) / 2.0;
    	}
    	else {
            return (double)minHeap.peek();
    	}
    }
}