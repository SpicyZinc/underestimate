/*
Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree.
Find all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key of given BST.
Return all the keys in ascending order.

Example
If k1 = 10 and k2 = 22, then your function should return [12, 20, 22].
    20
   /  \
  8   22
 / \
4   12

idea:
Through recursive in order traversal, if the value in the [k1, k2], add to the result list
decide when to enlarge search area 
*/

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}


public class SearchRangeInBinarySearchTree {
    /**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> result = new ArrayList<Integer>();
        dfs(result, root, k1, k2);
        return result;
    }
    
    public void dfs(List<Integer> result, TreeNode node, int k1, int k2) {
        if (node == null) {
            return;
        }
        // can enlarge search area
        if (node.val > k1) {
            dfs(result, node.left, k1, k2);
        }
        if (node.val >= k1 && node.val <= k2) {
            result.add(node.val);
        }
        if (node.val < k2) {
            dfs(result, node.right, k1, k2);
        }
    }
}