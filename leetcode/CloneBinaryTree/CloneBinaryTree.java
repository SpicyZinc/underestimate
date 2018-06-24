/*
For the given binary tree, return a deep copy of it.
Example
Given a binary tree:
     1
   /  \
  2    3
 / \
4   5

return the new binary tree with same structure and same value:
     1
   /  \
  2    3
 / \
4   5

idea:
1. BFS with queue, for each node, make a copy, Map to keep (origin - copy)
2. BFS with queue, for each node, make a copy, another queue to keep all the copied nodes
3. recursion
*/

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class CloneBinaryTree {
    /**
     * @param root: The root of binary tree
     * @return: root of new tree
     */
    public TreeNode cloneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        Map<TreeNode, TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        map.put(root, new TreeNode(root.val));
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                TreeNode newLeftNode = new TreeNode(node.left.val);
                map.put(node.left, newLeftNode);
                map.get(node).left = newLeftNode;
            }
            if (node.right != null) {
                queue.offer(node.right);
                TreeNode newRightNode = new TreeNode(node.right.val);
                map.put(node.right, newRightNode);
                map.get(node).right = newRightNode;
            }
        }
        
        return map.get(root);
    }
	// through BFS, make copy, via two queues
	public TreeNode cloneTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		
		Queue<TreeNode> origin = new LinkedList<TreeNode>();
		Queue<TreeNode> cloned = new LinkedList<TreeNode>();

		TreeNode clonedTree = new TreeNode(root.val);

		origin.offer(root);
		cloned.offer(clonedTree);

		while (!origin.isEmpty()) {
			TreeNode originNode = origin.poll();
			TreeNode clonedNode = cloned.poll();

			if (originNode.left != null) {
				clonedNode.left = new TreeNode(originNode.left.val);
				origin.offer(originNode.left);
				cloned.offer(clonedNode.left);
			}

			if (originNode.right != null) {
				clonedNode.right = new TreeNode(originNode.right.val);
				origin.offer(originNode.right);
				cloned.offer(clonedNode.right);
			}
		}

		return clonedTree;
	}

    // recursion
	public TreeNode cloneTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		TreeNode newNode = new TreeNode(root.val);
		newNode.left = cloneTree(root.left);
		newNode.right = cloneTree(root.right);

		return newNode;
	}
}