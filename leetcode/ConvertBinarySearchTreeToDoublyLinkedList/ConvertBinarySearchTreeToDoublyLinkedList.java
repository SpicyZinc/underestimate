/*
Convert a binary search tree to doubly linked list with in-order traversal.

Example
Given a binary search tree:
    4
   / \
  2   5
 / \
1   3
return 1<->2<->3<->4<->5

idea:
lintcode version, not circular
leetcode version, circular

know in order traversal of a tree
inorder is sorted
把left都弄完了后 才弄right 而且right又是从right.left开始
dfs need to revisit
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        // for circular connection
        // get the smallest element in the tree which will be the head of the doubly linkedlist
        // get the biggest element in the tree which will be the tail of the doubly linkedlist

        Node head = root;
        while (head.left != null) {
            head = head.left;
        }

        Node tail = root;
        while (tail.right != null) {
            tail = tail.right;
        }
        
        dfs(root, null);
        
        // since it's a circular linkedlist set right & left pointers
        // 头尾相连
        tail.right = head;
        head.left = tail;

        return head;
    }
    
    public void dfs(Node node, Node prev) {
        if (node != null) {
            if (node.left != null) {
                dfs(node.left, node);
            }
            
            if (node.right != null) {
                dfs(node.right, node);
            }
            
            // if current node is the left child.
            // Then in the list it should appear before the parent node
            if (prev != null && prev.left == node) {
                /** now this is tricky but imagine a node with entire subtree on right in straight line.
                     the biggest node in this subtree should be after the current node in consideration & just before the parent of the current node.
                     Hence get the right most node and connect left & right pointer */
                // 左边的最右的node 是 仅小于紧挨着 prev的 所以要连接
                Node temp = rightMost(node);
                temp.right = prev;
                prev.left = temp;
            }

            if (prev != null && prev.right == node) {
                /** now this is tricky but imagine a node with entire subtree on left in straight line.
                    the smallest node in this subtree should be before the current node in consideration & just after the parent of the current node.
                    Hence get the left most node and connect left & right pointer */
                Node temp = leftMost(node);
                temp.left = prev;
                prev.right = temp;
            }
        }
    }
    
    private Node leftMost(Node node) {
        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    private Node rightMost(Node node) {
        if (node == null) {
            return null;
        }

        while (node.right != null) {
            node = node.right;
        }

        return node;
    }
}

// Definition of TreeNode
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
// Definition for Doubly-ListNode
class DoublyListNode {
    int val;
    DoublyListNode next, prev;
    DoublyListNode(int val) {
        this.val = val;
        this.next = this.prev = null;
    }
}

public class ConvertBinarySearchTreeToDoublyLinkedList {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }

        // Init stack
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        stack.push(node);

        // Create DoublyListNode header
        DoublyListNode dummy = new DoublyListNode(0);
        DoublyListNode dllNode = dummy;

        while (!stack.isEmpty()) {
            while (node != null && node.left != null) {
                stack.push(node.left);
                node = node.left;
            }
            // add node to doubly linked list
            node = stack.pop();

            DoublyListNode currrent = new DoublyListNode(node.val);
            dllNode.next = currrent;
            currrent.prev = dllNode;
            dllNode = currrent;

            // check right node and add to stack
            node = node.right;
            if (node != null) {
                stack.push(node);
            }
        }

        return dummy.next;
    }

    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }

        DoublyListNode left = null;
        DoublyListNode right = null;
        
        DoublyListNode node = new DoublyListNode(root.val);

        if (root.left != null) {
            left = bstToDoublyList(root.left);
        }
        // weld to current 'node'
        // 双向连 current node prev 指向 left的最右边
        node.prev = rightMost(left);
        // 最右边指向 current node
        if (node.prev != null) {
            node.prev.next = node;
        }

        if (root.right != null) {
            right = bstToDoublyList(root.right);
        }
        node.next = right;
        if (node.next != null) {
            node.next.prev = node;
        }

        // navigate to the left to return the head
        return leftMost(node);
    }

    private DoublyListNode leftMost(DoublyListNode node) {
        if (node == null) {
            return null;
        }

        while (node.prev != null) {
            node = node.prev;
        }

        return node;
    }

    private DoublyListNode rightMost(DoublyListNode node) {
        if (node == null) {
            return null;
        }

        while (node.next != null) {
            node = node.next;
        }

        return node;
    }
}