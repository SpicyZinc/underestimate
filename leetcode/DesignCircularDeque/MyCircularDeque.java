/*
Design your implementation of the circular double-ended queue (deque).
Your implementation should support following operations:

MyCircularDeque(k): Constructor, set the size of the deque to be k.
insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
getRear(): Gets the last item from Deque. If the deque is empty, return -1.
isEmpty(): Checks whether Deque is empty or not. 
isFull(): Checks whether Deque is full or not.
 

Example:

MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be 3
circularDeque.insertLast(1);            // return true
circularDeque.insertLast(2);            // return true
circularDeque.insertFront(3);           // return true
circularDeque.insertFront(4);           // return false, the queue is full
circularDeque.getRear();            // return 2
circularDeque.isFull();             // return true
circularDeque.deleteLast();         // return true
circularDeque.insertFront(4);           // return true
circularDeque.getFront();           // return 4
 

Note:
All values will be in the range of [0, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in Deque library.

idea:
head 从后面开始
tail 从前面开始

head = (head - 1 + k) % k
tail = (tail + 1) % k
*/

class MyCircularDeque {
	int k;
	int[] list;
	int head;
	int tail;
	int size;

	/** Initialize your data structure here. Set the size of the circular deque to be k. */
	public MyCircularDeque(int k) {
		this.k = k;
		this.list = new int[k];

		this.head = k - 1;
		this.tail = 0;
		this.size = 0;
	}

	/** Adds an item at the front of Deque. Return true if the operation is successful. */
	public boolean insertFront(int value) {
		if (size == k) {
			return false;
		}

		list[head] = value;
		head = (head - 1 + k) % k;
		size++;
		
		return true;
	}

	/** Adds an item at the rear of Deque. Return true if the operation is successful. */
	public boolean insertLast(int value) {
		if (size == k) {
			return false;
		}

		list[tail] = value;
		tail = (tail + 1) % k;
		size++;
		
		return true;
	}
	
	/** Deletes an item from the front of Deque. Return true if the operation is successful. */
	public boolean deleteFront() {
		if (size == 0) {
			return false;
		}
		
		head = (head + 1) % k;
		size--;
		
		return true;
	}
	
	/** Deletes an item from the rear of Deque. Return true if the operation is successful. */
	public boolean deleteLast() {
		if (size == 0) {
			return false;
		}
		
		tail = (tail - 1 + k) % k;
		size--;
		
		return true;
	}
	
	/** Get the front item from the deque. */
	public int getFront() {
		if (size == 0) {
			return -1;
		}

		return list[(head + 1) % k];
	}
	
	/** Get the last item from the deque. */
	public int getRear() {
		if (size == 0) {
			return -1;
		}

		return list[(tail - 1 + k) % k];
	}
	
	/** Checks whether the circular deque is empty or not. */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/** Checks whether the circular deque is full or not. */
	public boolean isFull() {
		return size == k;
	}
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */