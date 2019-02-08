/*
Given an integer array with no duplicates.
A maximum tree building on this array is defined as follow:
The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].

idea:
find max, use max to build root node
root.left = buildTree();
root.right = buildTree();
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    public TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int maxPos = start;
        int max = nums[maxPos];

        for (int i = start; i <= end; i++) {
            if (max < nums[i]) {
                maxPos = i;
                max = nums[i];
            }
        }

        TreeNode root = new TreeNode(max);
        root.left = buildTree(nums, start, maxPos - 1);
        root.right = buildTree(nums, maxPos + 1, end);

        return root;
    }

    // did not understand
 	// 1.如果 A[i] 小于栈顶的元素，则无脑 push
	// 2.如果 A[i] 大于栈顶的元素，那么把栈顶元素 pop 出来成为 son 节点，同时比较 A[i] 和 新的栈顶元素，分情况讨论
	// a.如果 A[i] 较小，那么让 A[i] 成为 son 的 father 节点，同时 push A[i] 节点进栈
	// b.如果 A[i] 较大，那么 pop 出栈顶元素成为 son 的 father 节点。同时把 father push 回栈。注意这个时候 A[i] 实际上并没有参与树的构建，所以我用到了 i--，在下一层 for 循环再来判断这个 A[i]

	// for 循环结束后，栈为单调递减栈，这个时候把栈里所有非正无穷大的元素 pop 出来最后 return root
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return null;
        }
        
        int len = nums.length;
        // decreasing stack
        TreeNode[] stack = new TreeNode[len];
        int head = -1;

        for (int i = 0; i <= len; i++) {
            int val = i == len ? Integer.MAX_VALUE : nums[i];
            TreeNode current = new TreeNode(val);

            if (head == -1 || stack[head].val > val) {
                stack[++head] = current;
            } else {
            	// no duplicate
                while (head != -1 && stack[head].val < val) {
                    TreeNode child = stack[head--];
                    if (head != -1 && stack[head].val < val) {
                        stack[head].right = child;
                    } else {
                        current.left = child;
                    }
                }
                stack[++head] = current;
            }

            if (i == len) {
                return current.left;
            }
        }

        return null;
    }

    public TreeNode maxTree(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();

        for (int i = 0; i <= A.length; i++) {
            int value = i == A.length ? Integer.MAX_VALUE : A[i];
            TreeNode right = new TreeNode(value);

            while (!stack.isEmpty() && stack.peek().val < value) {
                TreeNode mid = stack.pop();
                TreeNode left = !stack.isEmpty() ? stack.peek() : null;
                right.left = mid;
            }
            
            if (!stack.isEmpty() && stack.peek().val > value) {
                stack.peek().right = right;
            }

            stack.push(right);
        }

        return stack.pop().left;
    }
}