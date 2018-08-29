/*
Given a binary tree, find the maximum path sum.
For this problem,
a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The path must contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,
       1
      / \
     2   3
Return 6.

idea:
http://blog.csdn.net/fightforyourdream/article/details/16894069

*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeMaximumPathSum {
    int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        getMaxPathSum(root);
        return max;
    }
    // 这个就是 严格 求 最大 一条路径 不拐弯
    public int getMaxPathSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftPathSum = getMaxPathSum(node.left);
        int rightPathSum = getMaxPathSum(node.right);
        int maxPathSum = Math.max(node.val, node.val + Math.max(leftPathSum, rightPathSum));
        // update real max value
        // max value can be the current max, can be returnVal, can be the arcValue which means no need to go to parent
        // till here I have the biggest value since there could be negative values in the tree
        max = Math.max(max, Math.max(maxPathSum, leftPathSum + node.val + rightPathSum));
        return maxPathSum;
    }

	// version with explanation and without global max
	public int maxPathSum(TreeNode root) {
    	int[] max = {Integer.MIN_VALUE};
    	getMaxPathAcrossRootToParent(root, max);
    	return max[0];
    }

    private int getMaxPathAcrossRootToParent(TreeNode root, int[] max) {
    	if (root == null) return 0;
    	int maxLeftSubTree = getMaxPathAcrossRootToParent(root.left, max);
    	int maxRightSubTree = getMaxPathAcrossRootToParent(root.right, max);
    	int arch = maxLeftSubTree + root.val + maxRightSubTree;
        
    	// "maxArchAcrossRootToParent" is return value for recursive call 
    	// notice the return value is not max[0]
    	// there are 3 cases to get maxArchAcrossRootToParent, thus this is how we get maxArchAcrossRootToParent
    	// 1 从左子树到root再到parent  
        // 2 从右子树到root再到parent  
        // 3 直接从root到parent  
        // 注意arch那条路 in line 28 是不可能走到parent, 因为那条路已经经过root了, 除非折回来重复走 但这就不是path了 是不允许的
    	int maxArchAcrossRootToParent = Math.max( root.val, Math.max( maxLeftSubTree, maxRightSubTree ) + root.val );
    	// update max[0]
    	max[0] = Math.max( max[0], Math.max(arch, maxArchAcrossRootToParent) );

    	return maxArchAcrossRootToParent;
    }
}