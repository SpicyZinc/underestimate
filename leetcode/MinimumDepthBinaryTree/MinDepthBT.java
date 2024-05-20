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
    // Tue Apr  9 01:45:32 2024
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if (left == 0 || right == 0) {
            return left + right + 1;
        } else {
            return Math.min(left, right) + 1;
        }
    }

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
    public int minDepth0(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            if (root.left != null && root.right != null) {
                return 1 + Math.min(minDepth(root.left), minDepth(root.right));
            } else {
                return 1 + minDepth(root.right) + minDepth(root.left);
            }
        }
    }
    // iterative method 1
    public int minDepth1(TreeNode root) {
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
                levelCnt.offer(level + 1);
            }
            if (current.right != null) {
                nodes.offer(current.right);
                levelCnt.offer(level + 1);
            }
        }

        return 0;
    }
    // iterative method 2
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
       
        List<TreeNode> previousLayer = new ArrayList<>();
        previousLayer.add(root);
        int count = 1;

        while (!previousLayer.isEmpty()) {           
            List<TreeNode> curr = new ArrayList<>();
            // 没有 poll() 所以需要一个新的 new ArrayList<TreeNode>(curr);
            for (TreeNode n : previousLayer) {
                if (n.left == null && n.right == null) {
                    return count;
                }
                if (n.left != null) {
                    curr.add(n.left);
                }
                if (n.right != null) {
                    curr.add(n.right);
                }
            }
            count++;
            previousLayer = new ArrayList<TreeNode>(curr);
        }
        
        return count;
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int d = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                TreeNode node = q.poll();
                // reached leaves
                if (node.left == null && node.right == null) {
                    return d;
                }
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            d++;
        }

        return d;
    }
}
