/*
Return any binary tree that matches the given preorder and postorder traversals.
Values in the traversals pre and post are distinct positive integers.

Example 1:
Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]

Note:
1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists.
If there exists multiple answers, you can return any of them.

idea:
recursion
post range [postStart, postEnd]
pre [preStart]
在pre中第一个是root 在post中最后一个是root 在post root之前的是left and right

In post[], the rightmost element is the root of tree (equally, the leftmost element in pre[]).
The element following the root in pre[] must be left child of the root.
That is, 1 is root and 2 is its left child. Since 2 is the root of the left subtree,
all elements in front of 2 in post[] must be in the left subtree also.
Thus, preorder : 1 (2 4 5) (3 6); postorder: (4 5 2) (6 3) 1.
*/

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class ConstructBinaryTreefromPreorderAndPostorderTraversal {
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		return constructFromPrePostFrom(pre, new int[1], post, 0, post.length - 1);
	}

	private TreeNode constructFromPrePostFrom(
		int[] pre,
		int[] preStart,
		int[] post,
		int postStart,
		int postEnd
	) {
		if (postStart > postEnd) {
			return null;
		}

		int rootVal = post[postEnd];
		TreeNode root = new TreeNode(rootVal);
		preStart[0]++;
		
		if (preStart[0] == pre.length || postStart == postEnd) {
			return root;
		}
		// left 子树中的root value
		int leftVal = pre[preStart[0]];
		// left 子树的 post traversal index
		int leftSubtreeIdx = postStart;
		for (; leftSubtreeIdx <= postEnd; leftSubtreeIdx++) {
			if (post[leftSubtreeIdx] == leftVal) {
				break;
			}
		}

		root.left = constructFromPrePostFrom(pre, preStart, post, postStart, leftSubtreeIdx);
		root.right = constructFromPrePostFrom(pre, preStart, post, leftSubtreeIdx + 1, postEnd - 1);

		return root;
	}
}