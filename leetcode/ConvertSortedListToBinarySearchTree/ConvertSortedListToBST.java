/*
Convert Sorted List to Binary Search Tree

idea:
1. convert the list to an array, then use Convert Sorted Array to Binary Search Tree
2. find mid, pass in length as parameter in recursion function

*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null; }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ConvertSortedListToBST  {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        
        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        return sortedListToBST(head, len);
    }

    public TreeNode sortedListToBST(ListNode head, int len) {
        if (len <= 0) {
            return null;
        }

        ListNode curr = head;
        int overMidOne = (len + 1) / 2;
        int leftLast = overMidOne - 1;
        // use leftLast to reach last of left part
        while (leftLast > 0) {
            leftLast--;
            curr = curr.next;
        }
        ListNode middle = curr;
        
        ListNode right = curr.next;
        curr.next = null;
        
        TreeNode root = new TreeNode(middle.val);
        root.left = sortedListToBST(head, overMidOne - 1);
        root.right = sortedListToBST(right, len - overMidOne);
        
        return root;
    }
}