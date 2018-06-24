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
know how to in order traversal of a tree
in order of bst is sorted
把left都弄完了后 才弄right 而且right又是从right.left开始
*/

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
class DoublyListNode {
    int val;
    DoublyListNode next, prev;
    DoublyListNode(int val) {
        this.val = val;
        this.next = this.prev = null;
    }
}


public class ConvertBinarySearchTreeToDoublyLinkedList {
    /*
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
        DoublyListNode dNode = dummy;

        while (!stack.isEmpty()) {
            while (node != null && node.left != null) {
                stack.push(node.left);
                node = node.left;
            }
            // add node
            node = stack.pop();
            DoublyListNode curr = new DoublyListNode(node.val);
            dNode.next = curr;
            curr.prev = dNode;

            dNode = dNode.next;
            // dNode = curr; // also works
            
            // check right node and add to stack
            node = node.right;
            if (node != null) {
                stack.push(node);
            }  
        }
        
        return dummy.next;
    }
}