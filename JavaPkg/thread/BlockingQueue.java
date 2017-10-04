package JavaPkg.thread;

import java.util.*;

public class BlockingQueue {
	private int capacity;
	private List queue = new LinkedList();

	public BlockingQueue(capacity) {
		this.capacity = capacity;
	}

	public synchronized void enqueue(Object item) throws InterruptedException {
		while (queue.size() == capacity) {
			wait();
		}
		if (queue.size() == 0) {
			notifyAll();
		}
		queue.add(item);
	}

	public synchronized Object dequeue() throws InterruptedException {
		while (queue.size() == 0) {
			wait();
		}
		if (queue.size() == capacity) {
			notifyAll();
		}
		return queue.remove(0);
	}
}