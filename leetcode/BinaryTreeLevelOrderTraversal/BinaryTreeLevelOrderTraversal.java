/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

idea:
1. BFS, two queues current level and next level
2. BFS, one queue based
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> curr = new LinkedList<TreeNode>();
        Queue<TreeNode> next = new LinkedList<TreeNode>();

        List<Integer> layer = new ArrayList<Integer>();

        curr.add(root);        
        while (!curr.isEmpty()) {       
            TreeNode currentNode = curr.poll();
            layer.add(currentNode.val);

            if (currentNode.left != null) next.add(currentNode.left);
            if (currentNode.right != null) next.add(currentNode.right);
            
            // if curr level is empty, time to swap curr level with next level
            // reset next level
            if (curr.isEmpty()) {
                curr = next;
                next = new LinkedList<TreeNode>();
                result.add(layer);
                layer = new ArrayList<Integer>();
            }
        }
        
        return result;
    }

    // method 2
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            List<Integer> layer = new ArrayList<Integer>();
            int size = queue.size();
            
            // finish one layer
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                layer.add(node.val);
                
                queue.add(node.left);
                queue.add(node.right);
            }
            if (layer.size() > 0) {
                result.add(layer);
            }
        }
        
        return result;
    }
}