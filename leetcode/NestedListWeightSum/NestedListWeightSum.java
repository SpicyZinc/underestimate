/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

idea:
1. recursion
2. iteration
queue is used
	two queues
	one queue to keep each NestedInteger
	one queue to keep depth for each NestedInteger
*/

public class NestedListWeightSum {
	// method 1
	public int depthSum(List<NestedInteger> nestedList) {
	    return dfs(nestedList, 1);
	}

	public int dfs(List<NestedInteger> nestedList, int depth) {
		if (nestedList == null || nestedList.size() == 0) {
			return 0;
		}
		// sum in the recursion
		int sum = 0;
		for (NestedInteger ni : nestedList) {
			if (ni.isInteger()) {
				sum += ni.getInteger() * depth;
			}
			else {
				sum += dfs(ni.getList(), depth + 1);
			}
		}

		return sum;
	}

	// method 2
	public int depthSum(List<NestedInteger> nestedList) {
		int sum = 0;
	    Queue<NestedInteger> niQueue = new LinkedList<NestedInteger>();
	    Queue<Integer> depQueue = new LinkedList<Integer>();
	    for (NestedInteger ni : nestedList) {
	    	niQueue.offer(ni);
	    	// in beginning, depth is 1
	    	// then gradually add +1
	    	depQueue.offer(1);
	    }

	    while (!niQueue.isEmpty()) {
	    	// make sure each NestedInteger (if Integer) is associated correct depth
	    	// have to be poll() at the same time
	    	NestedInteger current = niQueue.poll();
	    	int depth = depQueue.poll();

	    	if (current.isInteger()) {
	    		sum += current.getInteger() * depth;
	    	}
	    	else {
	    		for ( NestedInteger ni : current.getList() ) {
	    			niQueue.offer(ni);
	    			depQueue.offer(depth + 1);
	    		}
	    	}
	    }

	    return sum;
	}
}