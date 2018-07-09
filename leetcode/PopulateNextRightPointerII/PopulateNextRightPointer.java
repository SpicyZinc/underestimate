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
use queue typical bfs
*/
public class PopulateNextRightPointer {
    // 07/08/2018
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = queue.poll();
                if (i == size - 1) {
                    node.next = null;
                } else {
                    node.next = queue.peek();
                }
                
                if (node.left != null) {
                    queue.add(node.left);                    
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }
	// recent best 06/03/2018
	public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }
	// iterative
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
    public void connect(TreeLinkNode root) {
		if (root == null) 
			return;
        if (root.left != null) {
            if (root.right != null) {
				root.left.next = root.right;
            }
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

    // dfs recursion
    // http://blog.csdn.net/muscler/article/details/22907093
    // http://blog.csdn.net/fightforyourdream/article/details/16854731
    public void connect(TreeLinkNode root) {  
        if (root == null) {
            return;
        }
          
        // 如果右孩子不为空, 左孩子的next是右孩子.  
        // 反之, 找root next的至少有一个孩子不为空的节点  
        if (root.left != null) {  
            if (root.right != null) {  
                root.left.next = root.right;  
            }  
            else {  
                TreeLinkNode p = root.next;  
                while (p != null && p.left == null && p.right == null)  
                    p = p.next;  
                if (p != null)  
                    root.left.next = p.left == null ? p.right : p.left;  
            }  
        }  
          
        // 右孩子的next 根节点至少有一个孩子不为空的next  
        if (root.right != null) {  
            TreeLinkNode p = root.next;  
            while (p != null && p.left == null && p.right == null)  
                p = p.next;  
            if (p != null)  
                root.right.next = p.left == null ? p.right : p.left;  
        }  
        connect(root.right);      
        connect(root.left);  
    }
}
