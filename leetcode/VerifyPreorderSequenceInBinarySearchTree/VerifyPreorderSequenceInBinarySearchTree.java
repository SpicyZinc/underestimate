/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
You may assume each number in the sequence is unique.

Consider the following binary search tree:
     5
    / \
   2   6
  / \
 1   3

Example 1:
Input: [5,2,6,1,3]
Output: false

Example 2:
Input: [5,2,1,3,6]
Output: true

Follow up:
Could you do it using only constant space complexity?

idea:
那么当我们碰到一个比之前结点大的值如何找到他的父结点呢?
可以借助一个栈,
即如果当前结点比栈顶元素小,
就入栈,
如果当前值大于栈顶值i,
则让所有比当前结点小的值都出栈,
直到栈顶元素比当前结点大,
则最后一个出栈的比当前结点小的值就是当前结点的父结点,
我们只要在栈元素出栈的时候更新最小下界,
再将当前元素入栈即可

https://www.cnblogs.com/grandyang/p/5327635.html
*/

class VerifyPreorderSequenceInBinarySearchTree {
	public boolean verifyPreorder(int[] preorder) {
		if (preorder.length == 0) {
			return true;
		}

		Stack<Integer> stack = new Stack<>();
		int currSmallest = Integer.MIN_VALUE;

		for (int value : preorder) {
			// anything less than currSmallest, return false
			if (value < currSmallest) {
				return false;
			}
			// find an increasing value, which means a right tree node
			// find this right tree node's parent node
			while (!stack.isEmpty() && stack.peek() < value) {
				currSmallest = stack.pop();
			}

			stack.push(value);
		}

		return true;
	}
}