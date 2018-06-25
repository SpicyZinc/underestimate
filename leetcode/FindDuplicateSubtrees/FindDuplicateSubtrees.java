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
不能用object reference 就是 像 而不是 address same
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class FindDuplicateSubtrees {
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		List<TreeNode> result = new ArrayList<TreeNode>();
		Map<String, Integer> hm = new HashMap<String, Integer>();
		preorderSerial(root, hm, result);

		return result;
	}

	private String preorderSerial(TreeNode cur, Map<String, Integer> hm, List<TreeNode> result) {
		if (cur == null) {
			return "#";
		}

		String serial = cur.val + "," + preorderSerial(cur.left, hm, result) + "," + preorderSerial(cur.right, hm, result);

		hm.put(serial, hm.getOrDefault(serial, 0) + 1);
		if (hm.get(serial) == 2) {
			result.add(cur);
		}

		return serial;
	}
}

	