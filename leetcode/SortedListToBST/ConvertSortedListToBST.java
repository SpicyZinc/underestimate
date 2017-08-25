/**
**********
*  not   *
*  pass  *
*  online*
*  test  *
**********

Given a singly linked list where elements are sorted in ascending order, 
convert it to a height balanced BST.

idea:
find the middle point of linkedlist and set it as root of BST
then for left and right parts of linkedlist, keep finding the middle of left part and middle of right part, recursively
 
*/


public class ConvertSortedListToBST {

	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next = new ListNode(5);
		head.print();
		System.out.print("\n=====================\n");
		SortedListToBST aTest = new SortedListToBST();
		TreeNode root = aTest.sortedListToBST(head);
		root.inOrder();
		
	}

    public TreeNode sortedListToBST(ListNode head) {
        int length = 0;
        ListNode cur = head;
		// get the length of linkedlist first
		// not like array, need to walk through the list to get the list length
		// 
        while(cur != null) {
            cur = cur.next;
            length++;
        }
		
        return sortedListToBST(head, 0, length-1);
		// return sortedListToBST(head, 0, length);
    }
    
    public TreeNode sortedListToBST(ListNode head, int start, int end) {
		if(start > end)
			return null;

		if((start == end) && (start == 0))
			return null;
		if((start == end) && (start == 1))
			return new TreeNode(head.val);

		int mid = (start + end)/2;
		ListNode cur = head;
		// without equal sign = mid, cur cannot reach mid
		for(int i=0; i<=mid; i++) {
		// for(int i=0; i<mid; i++) {
			cur = cur.next;
		}

        TreeNode res = new TreeNode(cur.val);
		res.left = sortedListToBST(head, start, mid-1);
        res.right = sortedListToBST(cur.next, mid+1, end);
		
        return res;
    }
}