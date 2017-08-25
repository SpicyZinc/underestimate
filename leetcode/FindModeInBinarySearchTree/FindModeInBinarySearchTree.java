/*
Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
Assume a BST is defined as follows:
The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.

For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.
Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).

idea:
use hashmap to record 
(value <=> times of appearance)

*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class FindModeInBinarySearchTree {
	int biggestFrequency = 0;

    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        if (root == null) {
            return new int[0];
        }
        inorder(root, hm);
        List<Integer> list = new ArrayList<Integer>();
        for (int key : hm.keySet()) {
            if (hm.get(key) == biggestFrequency) {
                list.add(key);
            }
        }
        // covert to integer array
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    // inorder traverse result is sorted result
    private void inorder(TreeNode root, Map<Integer, Integer> hm) {
        if (root.left != null) {
            inorder(root.left, hm);
        }
        hm.put(root.val, hm.getOrDefault(root.val, 0) + 1);
        biggestFrequency = Math.max( biggestFrequency, hm.get(root.val) );
        if (root.right != null) {
            inorder(root.right, hm);
        }
    }
}