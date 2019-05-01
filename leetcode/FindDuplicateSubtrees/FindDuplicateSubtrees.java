/*
Given a binary tree, return all duplicate subtrees.
For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1: 
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.

idea:
无非就是 traversal plus hashmap
hashmap needs key, so serialize the tree as key
不能用 object reference 就是 像 而不是 address same
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class FindDuplicateSubtrees {
	// Sun Apr 28 18:59:12 2019
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		List<TreeNode> result = new ArrayList<>();
		// key is serialized from root to current node
		// value is the count
		Map<String, Integer> hm = new HashMap<>();
		serialize(root, hm, result);

		return result;
	}
	// preorder traversal
	public String serialize(TreeNode node, Map<String, Integer> hm, List<TreeNode> result) {
		if (node == null) {
			return "#";
		}

		String key = node.val + "," + serialize(node.left, hm, result) + "" + serialize(node.right, hm, result);
		hm.put(key, hm.getOrDefault(key, 0) + 1);

		if (hm.get(key) == 2) {
			result.add(node);
		}

		return key;
	}
}

	