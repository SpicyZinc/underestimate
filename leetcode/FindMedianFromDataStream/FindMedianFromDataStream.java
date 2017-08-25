/*

Median is the middle value in an ordered integer list.
If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:
add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2

idea:
by default, heap is min-heap which means the smallest element at the top of the heap
for large heap, which contains all half elements less or equal to all elements in the small heap
while small heap, even the smallest element greater than the all elements in the large heap.
for large heap, multiply -1 to utilize min-heap.
*/

import java.util.*;
class MedianFinder {
    private Queue<Long> small = new PriorityQueue();
    private Queue<Long> large = new PriorityQueue();

    public void addNum(int num) {
    	large.offer((long)num);
    	small.offer(-large.poll());
    	if ( large.size() < small.size() ) {
    		large.offer(-small.poll());
    	}
    }

    public double findMedian() {
    	if ( large.size() > small.size() ) {
    		return large.peek();
    	}
    	else {
    		return ( large.peek() - small.peek() ) / 2.0;
    	}
    }
}

// Your MedianFinder object will be instantiated and called as such:
public class FindMedianFromDataStream {
	public static void main(String[] args) {
		MedianFinder mf = new MedianFinder();
		mf.addNum(1);
		mf.addNum(2);
		double m1 = mf.findMedian();
		System.out.println(m1);
		mf.addNum(3);
		double m2 = mf.findMedian();
		System.out.println(m2);
	}	
}