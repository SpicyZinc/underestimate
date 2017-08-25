/*
idea:
http://blog.csdn.net/perfect8886/article/details/20874913

1. use queue to level traversal the tree
for each level, levelHead is enough to loop through this level since it is list

2. use next pointer in each tree node to level traversal 
time and space complexity are O(n) and O(1)
These two are iterative methods

3. recursive methods

*/

// Definition for binary tree with next pointer.
class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}

public class PopulatingNextRightPointersInEachNodeII {
    // use queue passed test
    public void connect(TreeLinkNode root) { 
        if (root == null) {
            return ;
        }

        Queue<TreeLinkNode> currentLevel = new LinkedList<TreeLinkNode>();
        Queue<TreeLinkNode> nextLevel = new LinkedList<TreeLinkNode>();

        currentLevel.offer(root);
        TreeLinkNode levelHead = null;
        while ( !currentLevel.isEmpty() ) {
            TreeLinkNode node = currentLevel.poll();
            if (levelHead != null) {
                levelHead.next = node;
            }
            levelHead = node;
            if (node.left != null) {
                nextLevel.add(node.left);
            }
            if (node.right != null) {
                nextLevel.add(node.right);
            }
            if (currentLevel.isEmpty()) {
                currentLevel = nextLevel;
                nextLevel = new LinkedList<TreeLinkNode>();
                levelHead = null;
            }
        }
    }
    // use next pointer passed test
    // while in the current level, populate next pointer in the next level
    // when coming to the next level, it is already connected by next pointer
    // so it is easy to walk through by current = current.next
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
    // dfs recursion
    // http://blog.csdn.net/muscler/article/details/22907093
    // http://blog.csdn.net/fightforyourdream/article/details/16854731
    public void connect(TreeLinkNode root) {  
        if (root == null) {
            return;
        }
          
        // 如果右孩子不为空，左孩子的next是右孩子。  
        // 反之，找root next的至少有一个孩子不为空的节点  
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