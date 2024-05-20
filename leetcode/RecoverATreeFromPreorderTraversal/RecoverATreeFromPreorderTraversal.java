/*
We run a preorder depth-first search (DFS) on the root of a binary tree.
At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.
If a node has only one child, that child is guaranteed to be the left child.
Given the output traversal of this traversal, recover the tree and return its root.

Example 1:
https://assets.leetcode.com/uploads/2019/04/08/recover-a-tree-from-preorder-traversal.png
Input: traversal = "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]

Example 2:
https://assets.leetcode.com/uploads/2019/04/11/screen-shot-2019-04-10-at-114101-pm.png
Input: traversal = "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]

Example 3:
https://assets.leetcode.com/uploads/2019/04/11/screen-shot-2019-04-10-at-114955-pm.png
Input: traversal = "1-401--349---90--88"
Output: [1,401,null,349,88,90]

Constraints:
The number of nodes in the original tree is in the range [1, 1000].
1 <= Node.val <= 10^9

idea:

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class RecoverATreeFromPreorderTraversal {
    // "1-2--3--4-5--6--7"
    public TreeNode recoverFromPreorder(final String traversal) {
        final Stack<TreeNode> stack = new Stack<>();
        int level = 0;
        int size = traversal.length();

        for (int i = 0; i < size;) {
            int num = 0;
            // num could be multiple digits, while next '-'
            while (i < size && Character.isDigit(traversal.charAt(i))) {
                num = num * 10 + traversal.charAt(i) - '0';
                i++;
            }
            // pop until same level
            while (stack.size() > level) {
                stack.pop();
            }
            // Create a node
            TreeNode node = new TreeNode(num);

            if (!stack.isEmpty()) {
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }
            // reset to count how many '-'
            level = 0;
            while (i < size && traversal.charAt(i) == '-') {
                level++;
                i++;
            }
            // Note, where is this
            stack.push(node);
        }

        while (stack.size() > 1) {
            stack.pop();
        }

        return stack.pop();
    }
}
