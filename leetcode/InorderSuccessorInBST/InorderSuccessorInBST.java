/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
Note: If the given node has no in-order successor in the tree, return null.

Example 1:
Input: root = [2,1,3], p = 1
  2
 / \
1   3

Output: 2

Example 2:
Input: root = [5,3,6,2,4,null,null,1], p = 6
      5
     / \
    3   6
   / \
  2   4
 /   
1

Output: null

idea:
convert to array by inorder traversal, find it.

*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class InorderSuccessorInBST {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        List<TreeNode> list = new ArrayList<>();
        inorder(root, list);
        TreeNode successor = null;
        TreeNode node = null;
        for (int i = 0; i < list.size() - 1; i++) {
            TreeNode curr = list.get(i);
            TreeNode next = list.get(i + 1);
            if (curr.val == p.val) {
                successor = next;
            }
        }
        
        return successor;
    }
    
    public void inorder(TreeNode node, List<TreeNode> list) {
        if (node != null) {
            inorder(node.left, list);
            list.add(node);
            inorder(node.right, list);
        }
    }
}