/**
 * idea: 用queue保持相对顺序
 * 用stack reverse 了顺序
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    // @param nestedList a list of NestedInteger
    // @return a list of integer
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        Stack<NestedInteger> stack = new Stack<>();
        for (NestedInteger ni : nestedList) {
            stack.push(ni);
        }
        
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            NestedInteger current = stack.pop();
            if (current.isInteger()) {
                result.add(current.getInteger());
            } else {
                for (NestedInteger ni : current.getList()) {
                    stack.push(ni);
                }
            }
        }
        
        Collections.reverse(result); 

        return result;
    }
}