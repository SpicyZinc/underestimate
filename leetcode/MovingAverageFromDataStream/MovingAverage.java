/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

idea:
Deque interface

method
removeFirst() 
addLast()
*/ 

public class MovingAverage {
	Deque<Integer> dequeue = null;
	int size = 0;
	long sum = 0;

	// constructor
	public MovingAverage(int size) {
		this.dequeue = new LinkedList<Integer>();
		this.size = size;
	}

	public double next(int val) {
		if (dequeue.size() >= size) {
			long head = dequeue.removeFirst();
			sum -= head;
		}
		dequeue.addLast(val);
		sum += head;

		return (double) (sum / dequeue.size());
	}
}