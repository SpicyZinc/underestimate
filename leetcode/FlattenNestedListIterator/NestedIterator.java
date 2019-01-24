/*
Given a nested list of integers, implement an iterator to flatten it.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].


idea:
http://www.cnblogs.com/grandyang/p/5358793.html
http://blog.csdn.net/l294265421/article/details/51203616, looks complicated
1. stack
push the last in nestedList to stack first
2. queue
3. dfs to hack 
*/


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack = new Stack<NestedInteger>();
    public NestedIterator(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            return;
        }
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger top = stack.peek();
            if (top.isInteger()) {
                return true;
            } else {
                stack.pop();
                List<NestedInteger> temp = top.getList();
                for (int i = temp.size() - 1; i >= 0; i--) {
                    stack.push(temp.get(i));
                }
            }
        }

        return false;
    }

    // hacker way with DFS
    List<Integer> result;
    int index;
    public NestedIterator(List<NestedInteger> nestedList) {
        result = new ArrayList<Integer>();
        index = 0;
        dfs(nestedList, result);
    }

    @Override
    public Integer next() {
        return result.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index != result.size();
    }

    public void dfs(List<NestedInteger> list, List<Integer> result) {
        for (NestedInteger temp : list) {
            if (temp.isInteger()) {
                result.add(temp.getInteger());
            } else {
                dfs(temp.getList(), result);
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */