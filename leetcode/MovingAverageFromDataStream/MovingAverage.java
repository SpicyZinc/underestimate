/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

idea:
double end queue
Deque interface

method
removeFirst() 
addLast()
*/ 

public class MovingAverage {
	// 07/08/2018
	int idx;
    int size;
    int windowSum;
    List<Integer> deque;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.idx = 0;
        this.size = size;
        this.windowSum = 0;
        this.deque = new LinkedList<>();
    }
    
    public double next(int val) {
        idx++;
        windowSum += val;
        deque.add(val);

        if (idx <= size) {
            return (double) (windowSum * 1.0 / idx);
        } else {
            windowSum -= deque.get(idx - size - 1);
            return (double) (windowSum * 1.0 / size);
        }
    }


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