/*
Given a linked list, 
swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, 
return the list as 2->1->4->3.

Require constant space

idea:

1. iterative version

a fake header is needed,
and while (n2 != null && n2.next != null) 

faker --> 1 --> 2 --> 3 --> 4 --> 5
  |       | 
  |       |   
  n1      n2

faker --> 2 --> 1 --> 3 --> 4 --> 5
  |             | 
  |             |   
  n1            n2

  so n1 = n2
     n2 = n1.next

2. recursive version
(1) swap the first single pair of nodes
(2) then the rest keep calling swapSinglePair(StartingNodeInnextPair)
    assign it to node.next.next


*/
// Definition for singly-linked list
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class SwapNodesInPairs {
    // recursive version
    public ListNode swapPairs(ListNode head) {  
        if (head == null || head.next == null) {
            return head;
        }  
          
        return swapSinglePair(head);  
    } 
    // 
    private ListNode swapSinglePair(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode next = node.next.next;
        // swap a single pair of nodes
        ListNode temp = node;
        node = node.next;
        node.next = temp;

        node.next.next = swapSinglePair(next);

        return node;
    }
    
    // iterative version
    public ListNode swapPairs(ListNode head) {
        ListNode helper = new ListNode(0);
        helper.next = head;
        ListNode n1 = helper, n2 = head;
        
        while (n2 != null && n2.next != null) {
            ListNode temp = n2.next.next;
            n2.next.next = n1.next;
            n1.next = n2.next;
            n2.next = temp;
            
            n1 = n2;
            n2 = n1.next;
        }
        
        return helper.next;
    }
}