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
heap, by default, is min-heap which means the top of the heap is the smallest element
Max-heap small has the smaller half of the numbers
Min-heap large has the larger half of the numbers
for small heap, multiply -1 to utilize min-heap feature

note: large always has one more element then small or equal size
*/

import java.util.*;

class MedianFinder {
	Queue<Long> small = null;
    Queue<Long> large = null;

    /** initialize your data structure here. */
    public MedianFinder() {
        small = new PriorityQueue<Long>();
        large = new PriorityQueue<Long>();
    }
    
    public void addNum(int num) {
        large.add((long) num);
        small.add(-1 * large.poll());
        // either large and small priority queue equal size or large is one element more than small
        // this way makes sure the array split into two halves
        if (large.size() < small.size()) {
            large.add(-1 * small.poll());
        }
    }
    
    public double findMedian() {
        if (large.size() > small.size()) {
            return large.peek();
        } else {
            return ( large.peek() - small.peek() ) / 2.0;
        }
    }

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

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */