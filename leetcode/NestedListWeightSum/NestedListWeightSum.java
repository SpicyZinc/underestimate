/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Input: [[1,1],2,[1,1]]
Output: 10 
Explanation: Four 1's at depth 2, one 2 at depth 1.

Example 2:
Input: [1,[4,[6]]]
Output: 27 
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.

idea:
1. recursion
2. iteration
queue is used
	two queues
	one queue to keep each NestedInteger
	one queue to keep depth for each NestedInteger
*/

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
public interface NestedInteger {
    // Constructor initializes an empty nested list.
    public NestedInteger();

    // Constructor initializes a single integer.
    public NestedInteger(int value);

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class NestedListWeightSum {
	// method 1
	public int depthSum(List<NestedInteger> nestedList) {
		return dfs(nestedList, 1);
	}

	public int dfs(List<NestedInteger> nestedList, int depth) {
		if (nestedList == null || nestedList.size() == 0) {
			return 0;
		}

		int sum = 0;
		for (NestedInteger ni : nestedList) {
			if (ni.isInteger()) {
				sum += ni.getInteger() * depth;
			} else {
				sum += dfs(ni.getList(), depth + 1);
			}
		}

		return sum;
	}

	// method 2
	public int depthSum(List<NestedInteger> nestedList) {
		int sum = 0;

		Queue<NestedInteger> niQueue = new LinkedList<NestedInteger>();
		Queue<Integer> depthQueue = new LinkedList<Integer>();

		for (NestedInteger ni : nestedList) {
			niQueue.offer(ni);
			// in beginning, depth is 1
			// then gradually add +1
			depthQueue.offer(1);
		}

		while (!niQueue.isEmpty()) {
			// make sure each NestedInteger (if Integer) is associated correct depth
			// have poll() at the same time
			NestedInteger current = niQueue.poll();
			int depth = depthQueue.poll();

			if (current.isInteger()) {
				sum += current.getInteger() * depth;
			} else {
				for (NestedInteger ni : current.getList()) {
					niQueue.offer(ni);
					depthQueue.offer(depth + 1);
				}
			}
		}

		return sum;
	}
}