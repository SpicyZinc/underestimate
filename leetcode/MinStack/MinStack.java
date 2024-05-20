/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

idea:
1. minStack always keeps same number of elements in actual stack
是对应 dynamic actual stack 最小的 存入 minStack
2. minStack only keeps the minimum so far, actual stack keeps all
3. one stack, when run into a number smaller than current min, push current min, update min with the smaller, push the smaller; old min over current min saved twice
你得有个步骤更新 现在最小的 value 所以多存一个
4. NodeWithMin class
*/
import java.util.*;

class MinStack {
	// method 3
	int min;
	Stack<Integer> stack;

	/** initialize your data structure here. */
	public MinStack() {
		min = Integer.MAX_VALUE;
		stack = new Stack<>();
	}

	public void push(int x) {
		// prepare for the pop()
		// push extra 2nd min
		// note equal
		// 保留下 second min for further use, otherwise no way to retrieve second min
		if (x <= min) {
			stack.push(min);
			min = x;
		}
		stack.push(x);
	}
	
	public void pop() {
		int poped = stack.pop();
		if (poped == min) {
			min = stack.pop();
		}
	}
	
	public int top() {
		return stack.peek();
	}
	
	public int getMin() {
		return min;
	}

	// method 1
	Stack<Integer> actual = new Stack<Integer>();
	Stack<Integer> min = new Stack<Integer>();

	public void push(int x) {
		if (actual.isEmpty() && min.isEmpty()) {
			actual.push(x);
			min.push(x);
		} else {
			actual.push(x);
			int y = min.peek();
			min.push(y > x ? x : y);
		}
	}

	public void pop() {
		actual.pop();
		min.pop();
	}

	public int top() {
		return actual.peek();
	}

	public int getMin() {
		return min.peek();
	}

	// method 2
	private Stack<Integer> minStack = new Stack<Integer>();
	private Stack<Integer> stack = new Stack<Integer>();

	public void push(int x) {
		if (minStack.isEmpty() || x <= minStack.peek()) {
			minStack.push(x);
		}
		stack.push(x);
	}

	public void pop() {
		if (stack.peek().equals(minStack.peek())) {
			minStack.pop();
		}
		stack.pop();
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		return minStack.peek();
	}

	// method 4
	Stack<NodeWithMin> stack = null;
	/** initialize your data structure here. */
	public MinStack() {
		stack = new Stack<NodeWithMin>();
	}
	
	public void push(int x) {
		int min = x;
		if (!stack.isEmpty()) {
			min = Math.min(x, getMin());
		}
		stack.push(new NodeWithMin(min, x));
	}
	
	public void pop() {
		stack.pop();
	}
	
	public int top() {
		return stack.peek().val;
	}
	
	public int getMin() {
		return stack.peek().min;
	}
 
	public static void main(String[] args) {
		MinStack ms = new MinStack();
		ms.push(15);
		System.out.println("So far min == " + ms.getMin());
		System.out.println("So far min == " + ms.stack);
		ms.push(10);
		System.out.println("So far min == " + ms.getMin());
		System.out.println("So far min == " + ms.stack);
		ms.push(20);
		System.out.println("So far min == " + ms.getMin());
		System.out.println("So far min == " + ms.stack);
		ms.push(30);
		System.out.println("So far min == " + ms.getMin());
		System.out.println("So far min == " + ms.stack);
		ms.push(5);
		System.out.println("So far min == " + ms.getMin());
		System.out.println("So far min == " + ms.stack);
	}
}

class NodeWithMin {
	int min;
	int val;

	public NodeWithMin(int min, int val) {
		this.min = min;
		this.val = val;
	}
}