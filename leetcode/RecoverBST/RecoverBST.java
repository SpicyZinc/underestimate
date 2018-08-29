/*
Two elements of a binary search tree (BST) are swapped by mistake.
Recover the tree without changing its structure.

Example 1:
Input: [1,3,null,null,2]
   1
  /
 3
  \
   2
Output: [3,1,null,null,2]

   3
  /
 1
  \
   2

Example 2:
Input: [3,1,4,null,null,2]
  3
 / \
1   4
   /
  2
Output: [2,1,4,null,null,3]
  2
 / \
1   4
   /
  3

Follow up:
A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?

idea:
1. space complexity is O(n), not required O(1) constant space
in order traversal bst, and save nodes' values into array,
sort them, then evaluate them

2. inorder traverse BST, find the pair of swapped nodes, return as TreeNode[] of length 2
use swap(TreeNode a, TreeNode b) to swap their values.

How to find this pair of nodes
这一对节点
先出现的那个肯定是比其中序(inorder)后继大的
后出现的那个节点的后继, 肯定不大于先出现这个错误节点的值.

best explanation,
use inorder traversal
https://leetcode.com/problems/recover-binary-search-tree/discuss/32535/No-Fancy-Algorithm-just-Simple-and-Powerful-In-Order-Traversal
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

	// direct method with O(n) space
	public void recoverTree(TreeNode root) {
		if (root == null) {
			return ;
		}
		
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		List<Integer> values = new ArrayList<Integer>();

		inOrderPrintToArray(root, values, nodes);
        
        Collections.sort(values, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        });
        
		for (int i = 0; i < values.size(); i++) {
			nodes.get(i).val = values.get(i);
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

	// method 1
	TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    TreeNode[] dislocations = new TreeNode[2];

    public void recoverTree(TreeNode root) {
        inorder(root);
        // note, swap value, not reference
        int temp = dislocations[0].val;
        dislocations[0].val = dislocations[1].val;
        dislocations[1].val = temp;
    }
    
    public void inorder(TreeNode node) {
        if (node == null) {
			return;
		}

        inorder(node.left);
        
        // 在 inorder traversal 中 这两个dislocations 是前后紧挨的
        // 1 3 2 4 => 3 and 2
        // supposed prev.val < node.val
        if (dislocations[0] == null && prev.val >= node.val) {
            dislocations[0] = prev;
        }
        
        if (dislocations[0] != null && prev.val >= node.val) {
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
            } else {
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

	// need to go back and think
	public void recoverTree(TreeNode root) {
        TreeNode n1 = null;
        TreeNode n2 = null;    
        TreeNode current = root, prev = null, a = null;
        boolean first = true;
		
        while (current != null) {
            if (current.left == null) {
                if (a != null && a.val > current.val) {
                    if (first) {
                        n1 = a;
                        first = false;
                    } else {
                        n2 = current;
                    }
                }
                if (n1 != null && a == n1) {
					n2 = current;
                }
                a = current;
                current = current.right;
            } else {
                prev = current.left;
                while (prev.right != null && prev.right != current)
                    prev = prev.right;
                if (prev.right == null) {
                    prev.right = current;
                    current = current.left;
                } else {
                    if (a != null && a.val > current.val) {
                        if (first) {
                            n1 = a;
                            first = false;
                        } else {
                            n2 = current;
                        }
                    }
                    if (n1 != null && a == n1) {
						n2 = current;
                    }
                    a = current;
                    prev.right = null;
                    current = current.right;
                }
            }
        }
        assert(n1 != null && n2 != null);

        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }
}