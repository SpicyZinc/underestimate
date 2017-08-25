/*
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

idea:
iterative method 1 is the same idea as level print tree
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class MinDepthBT {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }
        
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    // even simpler way
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        else {
            if (root.left != null && root.right != null) {
                return 1 + Math.min(minDepth(root.left), minDepth(root.right));
            } 
            else {
                return 1 + minDepth(root.right) + minDepth(root.left);
            }
        }
    }
    // iterative method 1
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        Queue<Integer> levelCnt = new LinkedList<Integer>();

        nodes.offer(root);
        levelCnt.offer(1);

        while (!nodes.isEmpty()) {
            TreeNode current = nodes.poll();
            int level = levelCnt.poll();

            if (current.left == null && current.right == null) {
                return level;    
            }
            if (current.left != null) {
                nodes.offer(current.left);
                levelCnt.offer(level+1);
            }
            if (current.right != null) {
                nodes.offer(current.right);
                levelCnt.offer(level+1);
            }
        }

        return 0;
    }
    // iterative method 2
    public int minDepth(TreeNode root) {
       	if (root == null) } {
    		    return 0;
        }
       
        ArrayList<TreeNode> last = new ArrayList<TreeNode>();
        last.add(root);
        int count = 1;
        while (!last.isEmpty()) {           
            ArrayList<TreeNode> curr = new ArrayList<TreeNode>();
            for (TreeNode n : last) {
                if (n.left==null && n.right==null) return count;
                if (n.left!=null) curr.add(n.left);
                if (n.right!=null) curr.add(n.right);
            }
            count++;
            last = new ArrayList<TreeNode>(curr);
        }
        
        return count;
    }
}