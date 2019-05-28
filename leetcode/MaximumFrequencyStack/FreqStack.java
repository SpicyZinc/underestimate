/*
Implement FreqStack, a class which simulates the operation of a stack-like data structure.

FreqStack has two functions:
push(int x), which pushes an integer x onto the stack.
pop(), which removes and returns the most frequent element in the stack.
If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.

Example 1:
Input: 
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
Output: [null,null,null,null,null,null,null,5,7,5,4]

Explanation:
After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

pop() -> returns 5, as 5 is the most frequent.
The stack becomes [5,7,5,7,4].

pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
The stack becomes [5,7,5,4].

pop() -> returns 5.
The stack becomes [5,7,4].

pop() -> returns 4.
The stack becomes [5,7].
 
Note:
Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
The total number of FreqStack.push calls will not exceed 10000 in a single test case.
The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.

idea:
priority queue is used to get the most frequent Node,
if node.value equal, pick up the most recent inserted value
*/

class Node {
	int idx;
	int value;
	int frequency;

	public Node(int idx, int value, int frequency) {
		this.idx = idx;
		this.value = value;
		this.frequency = frequency;
	}
}

class FreqStack {
	int cnt;
	Map<Integer, Integer> hm = new HashMap<>();
	PriorityQueue<Node> pq;

	public FreqStack() {
		this.cnt = 0;
		this.hm = new HashMap<>();
		this.pq = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node a, Node b) {
				return a.frequency != b.frequency ? b.frequency - a.frequency : b.idx - a.idx;
			}
		});
	}

	public void push(int x) {
		hm.put(x, hm.getOrDefault(x, 0) + 1);
		Node node = new Node(cnt++, x, hm.get(x));
		pq.offer(node);
	}
	
	public int pop() {
		int val = pq.poll().value;
		hm.put(val, hm.get(val) - 1);

		return val;
	}
}