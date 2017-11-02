/*
Two elements of a binary search tree (BST) are swapped by mistake.
Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

idea:
1. space complexity is O(n), not required O(1) constant space
in order traversal bst, and save nodes' values into array,
sort them, then evaluate them

2. inorder traverse BST, find this pair of nodes, return as TreeNode[] of length 2
use swap(TreeNode a, TreeNode b) to swap their values.
How to find this pair of nodes

这一对节点先出现的那个肯定是比其中序后继大的, 后出现的那个节点的后继, 肯定不大于先出现这个错误节点的值.

	constant space solution 就是用递归的方式进行inorder traverse, 
	然后在遍历的过程中记录一个前驱节点, 然后比较前驱节点和当前节点的值, 将结果存到TreeNode array, 最后交换一下就行了.
	
	in order traverse不存,只存前一个node, 比较current与前一个的值.
	如果只有一次不满足 current.val >= previous.val, swap their values.
	如果有两次不满足, 记录第一次的 previous 和第二次的current, swap their values
*/

import java.util.*;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) {
		val = x;
		left = null;
		right = null;
	}
}
// this O(n) space passed the test
public class RecoverBST {
	public static void main(String[] args) {
		RecoverBST wrongBST = new RecoverBST();
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(1);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		System.out.println("Wrong BST:");
		wrongBST.inOrderPrint(root);
		
		System.out.println("\nCorrect BST:");
		wrongBST.recoverBST(root);
		wrongBST.inOrderPrint(root);		
	}

	// direct method with O(n) space cost
	public void recoverTree(TreeNode root) {
		if (root == null) {
			return ;
		}
		
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		List<Integer> values = new ArrayList<Integer>();

		inOrderPrintToArray(root, values, nodes);
		int[] nums = buildIntArray(values);
		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			nodes.get(i).val = nums[i];
		}
	}

	public void inOrderPrintToArray(TreeNode root, List<Integer> values, List<TreeNode> nodes) {
		if (root.left != null) {
			inOrderPrintToArray(root.left, values, nodes);
		}
		
		values.add(root.val);
		nodes.add(root);
		
		if (root.right != null) {
			inOrderPrintToArray(root.right, values, nodes);
		}		
	}
	
	private int[] buildIntArray(List<Integer> integers) {
		int[] ints = new int[integers.size()];
		int i = 0;
		for (Integer n : integers) {
			ints[i++] = n;
		}
		return ints;
	}
	// method 1
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    TreeNode[] dislocations = new TreeNode[] { null, null };

	public void recoverTree(TreeNode root) {
		if (root == null) {
			return ;
		}

		inorder(root);

		int tmp = dislocations[0].val;
		dislocations[0].val = dislocations[1].val;
		dislocations[1].val = tmp;

		return;
	}

	public void inorder(TreeNode node) {
		if (node == null) {
			return;
		}

		inorder(node.left);

		if ( dislocations[0] == null && prev.val >= node.val) {
			 dislocations[0] = prev;
		}
		if ( dislocations[0] != null && prev.val >= node.val) {
			dislocations[1] = node;
		}
		prev = node;

		inorder(node.right);
	}
	// method 2
	public void recoverTree(TreeNode root) {
		if (root == null) {
			return ;
		}
        TreeNode[] dislocations = new TreeNode[2];
        inorderTraverse(root, null, dislocations);
		int tmp = dislocations[0].val;
		dislocations[0].val = dislocations[1].val;
		dislocations[1].val = tmp;

        return;
	}
	
	public TreeNode inorderTraverse(TreeNode root, TreeNode pre, TreeNode[] res) {
        if (root == null) {
			return pre;
		}
		
        TreeNode last;
        last = inorderTraverse(root.left, pre, res);
        if (last == null) {
            last = root;
        }
        if (root.val < last.val) {
            if (res[0] == null) {
                res[0] = last;
                res[1] = root;
            }
            else {
                res[1] = root;
            }
        }
        last = inorderTraverse(root.right, root, res);
		
        return last == null ? root : last;
    }
		
	public void inOrderPrint(TreeNode root) {
		if (root.left != null) {
			inOrderPrint(root.left);
		}
		
		System.out.print(root.val);
		
		if (root.right != null) {
			inOrderPrint(root.right);
		}		
	}
}