/*
Median is the middle value in an ordered integer list.
If the size of the list is even, there is no middle value.
So the median is the mean of the two middle value.

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
保证smaller half 的半个部分的heap 始终有一个多的元素

note: larger always has one more element than or equal size with smaller
this is wrong, opposite is right*/

import java.util.*;

class MedianFinder {
    //2025
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // re-balance the heaps
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }
    // 02/07/2019
    PriorityQueue<Long> maxHeap;
    PriorityQueue<Long> minHeap;

    // initialize your data structure here.
    public MedianFinder() {
        maxHeap = new PriorityQueue<Long>();
        minHeap = new PriorityQueue<Long>();
    }

    public void addNum(int num) {
        // maxHeap store smaller number, so 有更小的要放进来
        if (maxHeap.size() == 0 || num <= -1 * maxHeap.peek()) {
            maxHeap.offer(-1 * (long) num);
        } else {
            minHeap.offer((long) num);
        }
    
        balance();
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return -1 * maxHeap.peek();
        } else {
            return (minHeap.peek() - maxHeap.peek()) / 2.0;
        }
    }
    
    private void balance() {
        while (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(-1 * minHeap.poll());
        }
           
        while (minHeap.size() < maxHeap.size() - 1) {
            minHeap.offer(-1 * maxHeap.poll());
        }
    }


    // 12/04/2018
    // small always equal or 1 bigger than large
    Queue<Long> small = null;
    Queue<Long> large = null;

    // initialize your data structure here.
    public MedianFinder() {
        small = new PriorityQueue<Long>();
        large = new PriorityQueue<Long>();
    }

    public void addNum(int num) {
        small.add(-1 * (long) num);
        large.add(-1 * small.poll());
        if (large.size() > small.size()) {
            small.add(-1 * large.poll());
        }
    }
    
    public double findMedian() {
        if (small.size() > large.size()) {
            return -1 * small.peek();
        } else {
            return (large.peek() - small.peek()) / 2.0;
        }
    }

    Queue<Long> small = null;
    Queue<Long> large = null;

    // initialize your data structure here.
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

    // Tue Apr 18 01:07:29 2023
    Queue<Integer> maxHeap = null;
    Queue<Integer> minHeap = null;

    // initialize your data structure here.
    public MedianFinder() {
        maxHeap = new PriorityQueue<Integer>((a, b) -> b.compareTo(a));
        minHeap = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {
        if (!maxHeap.isEmpty() && num > maxHeap.peek()) {
            minHeap.offer(Integer.valueOf(num));
        } else {
            maxHeap.offer(num);
        }
        balance();

        // always first add to maxHeap, then poll(), dont underestimate the poll()
        // because poll() will get max from the smaller group, 把它放到它属于的 minHeap
        // maxHeap.add(Integer.valueOf(num));
        // minHeap.add(maxHeap.poll());
        // balance
        // if (minHeap.size() > maxHeap.size()) {
        //     maxHeap.add(minHeap.poll());
        // }
    }
    
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return (double) maxHeap.peek();
        } else {
            return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2.0;
        }
    }

    private void balance() {
        while (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
           
        while (minHeap.size() < maxHeap.size() - 1) {
            minHeap.offer(maxHeap.poll());
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
