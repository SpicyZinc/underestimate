/*
Given a binary tree
    struct TreeLinkNode {
		TreeLinkNode *left;
		TreeLinkNode *right;
		TreeLinkNode *next;
    }

Populate each next pointer to point to its next right node. 
If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.

Note:
    You may only use constant extra space.
    You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).

For example,
Given the following perfect binary tree,

         1
       /  \
      2    3
     / \  / \
    4  5  6  7

After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL

idea:
http://blog.csdn.net/fightforyourdream/article/details/14514165
current.left
current.right
		current ---> current.next
	   /       \
	  /        \
	left       right
only have to deal with left and right in one current

1. recursion
2. iteration
3. iteration
*/
class TreeLinkNode {
    int val;
    TreeLinkNode left;
	TreeLinkNode right; 
	TreeLinkNode next;
    TreeLinkNode(int x) { 
		val = x; 
	}
}

public class PopulateNextRightPointer {
    // self recent
    public void connect(TreeLinkNode root) {

        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                if (i == size - 1) {
                    node.next = null;
                } else {
                    node.next = queue.peek();
                }
                
                queue.add(node.left);
                queue.add(node.right);
            }
        }
    }

	// method 1
    public void connect(TreeLinkNode root) {
        if (root == null || (root.left == null && root.right == null)) {
			return;
        }
		
		TreeLinkNode rsibling = root.next;
		root.left.next = root.right;
		if (rsibling != null) {
			root.right.next = rsibling.left;
		}
		connect(root.left);
		connect(root.right);
	}
	// self written version passed test
	public void connect(TreeLinkNode root) {
        // 空节点就直接返回  
        if (root == null) {  
            return;  
        }      
        // 左节点非空, 连接右节点  
        if (root.left != null) {  
            root.left.next = root.right;  
        }
        // 借助root.next处理5连6的情况  
        if (root.right != null && root.next != null) {  
            root.right.next = root.next.left;  
        } 
        // recursively call connect()
        connect(root.left);  
        connect(root.right);  
    } 

	// method 2
	public void connect(TreeLinkNode root) {
		TreeLinkNode currentBegin = root; 
		TreeLinkNode prevBegin = null;
		while (currentBegin != null) {
			TreeLinkNode curr = currentBegin;
			while (curr != null) {
				if (prevBegin == null) {
					curr.next = null;
					curr = curr.next;
				} else {
					curr.next = prevBegin.right;
					curr = curr.next;
					prevBegin = prevBegin.next;
					if (prevBegin != null) {
						curr.next = prevBegin.left;
						curr = curr.next;
					} else {
						curr.next = null;
						curr = null;
					}
				}
			}
			prevBegin = currentBegin;
			currentBegin = currentBegin.left;
		}
	}
	// method 3
	public void connect(TreeLinkNode root) {
		TreeLinkNode leftWall = root;
        while (leftWall != null) {
            TreeLinkNode across = leftWall;
            while (across != null) {
                if (across.left != null) {
                    across.left.next = across.right;
                }
                if (across.right != null && across.next != null) {
                    across.right.next = across.next.left;
                }
				
                across = across.next;
            }
			
            leftWall = leftWall.left;
        }
    }
    // best and typical iterative 
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

    // general method for both binary tree and perfect binary tree
	public void connect(TreeLinkNode root) {
		if (root == null) {
            return;
        }   
        
        TreeLinkNode current = root;
        TreeLinkNode nextLevelHead = null;
        TreeLinkNode nextLevelEnd = null;
        
        while (current != null) {
            if (current.left != null) {
                if (nextLevelHead == null) {
                    nextLevelHead = current.left;
                    nextLevelEnd = nextLevelHead;
                }
                else {
                    nextLevelEnd.next = current.left;
                    nextLevelEnd = nextLevelEnd.next;
                
                }
            }
            
            if (current.right != null) {
                if (nextLevelHead == null) {
                   nextLevelHead = current.right;
                   nextLevelEnd = nextLevelHead;
                }
                else {
                   nextLevelEnd.next = current.right;
                   nextLevelEnd = nextLevelEnd.next;
                }
            }
            
            current = current.next;
            if (current == null) {
                current = nextLevelHead;
                nextLevelHead = null;
                nextLevelEnd = null;
            }
        }
    }
}