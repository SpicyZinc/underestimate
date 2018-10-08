/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:
Consider implement these two helper functions:
	getPredecessor(N), which returns the next smaller node to N.
	getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, it makes the problem much easier.
Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
You would need two stacks to track the path in finding predecessor and successor node separately.

idea:
https://buttercola.blogspot.com/2015/09/leetcode-closest-binary-search-tree_8.html

how to maintain a queue of size K
when size < k, keep adding
when size >= k, compare head with current, poll() then add, or break


2. https://www.cnblogs.com/grandyang/p/5247398.html
use two stacks, precedessor and successor
precedessor存小于目标值的数
successor存大于目标值的数
开始初始化pre和suc的时候, 要分别将最接近目标值的稍小值和稍大值压入pre和suc,
then 循环k次, 每次比较pre和suc的栈顶元素, 看谁更接近目标值, 将其存入结果res中, 然后更新取出元素的栈, 依次类推直至取完k个数返回
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ClosestBinarySearchTreeValue {
	// iteratively inorder traverse tree
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		Queue<Integer> list = new LinkedList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();

		while (root != null) {
			stack.push(root);
			root = root.left;
		}

		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			if (list.size() < k) {
				list.offer(node.val);
			} else {
				int first = list.peek();
				if (Math.abs(first - target) > Math.abs(node.val - target)) {
					list.poll();
					list.offer(node.val);
				} else {
					break;
				}
			}

			if (node.right != null) {
				node = node.right;
				while (node != null) {
					stack.push(node);
					node = node.left;
				}
			}
		}

		return (List<Integer>) list;
	}

	// recursively inorder traverse tree
	// note this is bst, so compare and remove from position 0
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		List<Integer> result = new ArrayList<Integer>();
		inorder(root, target, k, result);
		return result;
	}

	public void inorder(TreeNode node, double target, int k, List<Integer> result) {
		if (node == null) {
			return;
		}
		
		inorder(node.left, target, k, result);

		if (result.size() < k) {
			result.add(node.val);
		} else if (Math.abs(node.val - target) < Math.abs(result.get(0) - target)) {
			result.remove(0);
			result.add(node.val);
		}

		inorder(node.right, target, k, result);
	}

	// use two stacks
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) return result;

		Stack<TreeNode> precedessor = new Stack<TreeNode>();
		Stack<TreeNode> successor = new Stack<TreeNode>();
		populatePredecessor(root, target, precedessor);
		populateSuccessor(root, target, successor);

		for (int i = 0; i < k; i++) {
			if (precedessor.isEmpty()) {
				result.add(successor.pop());
			} else if (successor.isEmpty()) {
				result.add(precedessor.pop());
			} else if (Math.abs(precedessor.peek() - target) < Math.abs(successor.peek() - target)) {
				result.add(precedessor.pop());
			} else {
				result.add(successor.pop());
			}
		}

		return result;
	}

    private void populatePredecessor(TreeNode root, double target, Stack<Integer> precedessor) {
        if (root == null) {
        	return;
        }

		populatePredecessor(root.left, target, precedessor);
        if (root.val > target) {
        	return;
        }
		precedessor.push(root.val);
		populatePredecessor(root.right, target, precedessor);
    }
    
    private void populateSuccessor(TreeNode root, double target, Stack<Integer> successor) {
        if (root == null) {
        	return;
        }
        
        populateSuccessor(root.right, target, successor);
        if (root.val <= target) {
        	return;
        }
        successor.push(root.val);
        populateSuccessor(root.left, target, successor);
    }
}