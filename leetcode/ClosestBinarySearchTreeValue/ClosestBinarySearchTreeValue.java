/*
Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target. If there are multiple answers, print the smallest.
Note: Given target value is a floating point. You are guaranteed to have only one unique value in the BST that is closest to the target.

idea:
1. recursion
2. iteration

    1000
50       1050
target = 990


http://www.programcreek.com/2014/05/leetcode-closest-binary-search-tree-value-java/


Most solutions with high vote no longer works because they failed to cope with the constraint "If there are multiple answers, print the smallest." For example, [4,2,5,1,3] and target = 3.5.

In a BST, we can't guarantee the order of visit for the floor node or ceiling node, so we store both of them and compare after the iterative search.
*/
public class ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        int ceiling = Integer.MAX_VALUE;
        int floor = Integer.MIN_VALUE;

        TreeNode node = root;
        while (node != null) {
            if (node.val > target) {
                if (node.val < ceiling) ceiling = node.val;
                // 开始变小了 所以之前要存住 ceiling, 而且也是 > 
                node = node.left;
            } else if (node.val < target){
                if (node.val > floor) floor = node.val;
                node = node.right;
            } else {
                ceiling = node.val;
                break;
            }
        }

        return target - floor <= ceiling - target ? floor : ceiling;
    }

    // Sat May 18 01:55:19 2024
    int closest = Integer.MAX_VALUE;

    public int closestValue(TreeNode root, double target) {
        if (root == null) return -1;
        if (Math.abs(root.val - target) <= Math.abs(closest - target)) {
            if (Math.abs(root.val - target) == Math.abs(closest - target)) {
                closest = Math.min(root.val, closest);
            } else {
                closest = root.val;
            }
        }
        // 去哪里 小的 就 去left 这样就diff 就更closest
        if (root.val < target) {
            closestValue(root.right, target);
        } else {
            closestValue(root.left, target);
        }

        return closest;
    }


    // recursion
    double min = Double.MAX_VALUE;
    int closestVal = 0;

    public int closestValue(TreeNode root, double target) {
        dfs(root, target);
        return closestVal;
    }

    public void dfs(TreeNode node, double target) {
        if (node == null) {
            return;
        }

        double diff = Math.abs(node.val - target); 
        if (diff < min) {
            min = diff;
            closestVal = node.val;
        }

        if (target < node.val) {
            dfs(node.left, target);
        } else {
            dfs(node.right, target);
        }
    }
}
