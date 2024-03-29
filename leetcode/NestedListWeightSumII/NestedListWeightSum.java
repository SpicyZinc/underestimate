/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.
Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up.
i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)

idea:
https://segmentfault.com/a/1190000005937820

每一层将当前整数加起来, 然后往后遍历多一层就将前面已经加过的数再加一遍, 也就等于乘以了它的层数
levelSum to keep current level sum, and pass to nextLevel; this way, it is the same as multiply the level
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
	public int depthSumInverse(List<NestedInteger> nestedList) {
		int weightedSum = 0;
		int levelSum = 0;

		while (!nestedList.isEmpty()) {
			List<NestedInteger> nextLevel = new ArrayList<>();

			for (NestedInteger ni : nestedList) {
				if (ni.isInteger()) {
					levelSum += ni.getInteger();
				} else {
					nextLevel.addAll(ni.getList());
				}
			}

			weightedSum += levelSum;
			nestedList = nextLevel;
		}

		return weightedSum;
	}

	// Sun May 19 18:50:09 2019
	var depthSumInverse = function(nestedList) {
		let sum = 0;
		let levelSum = 0;

		while (nestedList.length) {
			const size = nestedList.length;
			let next = [];	

			for (let i = 0; i < size; i++) {
				let item = nestedList[i];
				if (Array.isArray(item)) {
					next = [...next, ...item];
				} else {
					levelSum += item;
				}
			}
			// 加过的再加一遍
			sum += levelSum;
			nestedList = next;
		}

		return sum;
	};
}
