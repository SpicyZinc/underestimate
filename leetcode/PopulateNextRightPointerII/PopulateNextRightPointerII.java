/**
Follow up for problem "Populating Next Right Pointers in Each Node".
What if the given tree could be any binary tree? Would your previous solution still work?

Note:
    You may only use constant extra space.

For example,
Given the following binary tree,

        1
       /  \
      2    3
     / \    \
    4   5    7

After calling your function, the tree should look like:

        1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL

	
idea:


*/
public class PopulateNextRightPointerII {
	// iterative
	//
	public void connect(TreeLinkNode root) {
		TreeLinkNode currentBegin = root, prevBegin = null;
		while (currentBegin != null) {
			TreeLinkNode curr = currentBegin;
			while (curr != null) {
				TreeLinkNode next = null;
				while (prevBegin != null) {
					if (prevBegin.left != null && prevBegin.left != curr) {
						next = prevBegin.left;
						break;
					} 
					else if (prevBegin.right != null && prevBegin.right != curr) {
						next = prevBegin.right;
						prevBegin = prevBegin.next;
						break;
					} 
					else {
						prevBegin = prevBegin.next;
					}
				}
				curr.next = next;
				curr = curr.next;
			}
			prevBegin = currentBegin;
			currentBegin = null;
			TreeLinkNode node = prevBegin;
			while (node != null) {
				if (node.left != null) {
					currentBegin = node.left;
					break;
				} 
				else if (node.right != null) {
					currentBegin = node.right;
					break;
				} 
				else {
					node = node.next;
				}
			}
		}
	}

	// recursive
	//
    public void connect(TreeLinkNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
		if (root == null) 
			return;
        if (root.left != null) {
            if (root.right != null) 
				root.left.next = root.right;
			else {
				tryConnect(root, root.left);
			}
        }
        if (root.right != null) {
            tryConnect(root, root.right);
        }
		
        connect(root.right);
        connect(root.left);        
    }
	
	public void tryConnect(TreeLinkNode parent, TreeLinkNode child) {
        TreeLinkNode node = parent.next;
        while (node != null) {
            if (node.left != null) {
                child.next = node.left;   
                break;
            }
            if (node.right != null) {
                child.next = node.right;
                break;
            }
            node = node.next;
        }
    }
}
