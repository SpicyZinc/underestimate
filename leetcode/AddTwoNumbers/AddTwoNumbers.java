/*
You are given two linked lists representing two non-negative numbers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

    7 -> 1 -> 6 == 617
+   5 -> 9 -> 2 == 295

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

idea:
since the number represented by linked list in a reverse order
so create a new list to hold the result, then walk through the list, 
append sum of two digits from l1 and l2 to the newly-created list

this one is different from Add Two Numbers II in that the second one is already reversed
*/

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        next = null;
    }
}

public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers eg = new AddTwoNumbers();
        
        ListNode listA = eg.fromArray(new int[] {4,7,4,7});
        ListNode listB = eg.fromArray(new int[] {5,3,7,4,7,4});
        
        eg.printList(listA);
        eg.printList(listB);
        
        ListNode newList = eg.addTwoNumbers(listA, listB);
        eg.printList(newList);
    }

    public void printList(ListNode node) {
        while (node != null) {
            System.out.printf("%d -> ", node.val);
            node = node.next;
        }
        System.out.print("END\n");
    }

    private ListNode fromArray(int[] array) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        // insertion from the tail
        for (int i = 0; i < array.length; i++) {
            ListNode temp = new ListNode(array[i]);
            current.next = temp;
            current = current.next;
        }

        return head.next;
    }

    // Mon May  3 01:34:27 2021
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = val / 10;

            ListNode node = new ListNode(val % 10);

            current.next = node;
            current = current.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return dummy.next;
    }

    // Thu Jul 11 21:49:00 2019
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        int carry = 0;

        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + carry;
            
            ListNode node = new ListNode(val % 10);
            carry = val / 10;
            
            current.next = node;
            current = current.next;
            
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while (l1 != null) {
            int val = l1.val + carry;
            
            ListNode node = new ListNode(val % 10);
            carry = val / 10;
            
            current.next = node;
            current = current.next;
            
            l1 = l1.next;
        }
        
        while (l2 != null) {
            int val = l2.val + carry;
            
            ListNode node = new ListNode(val % 10);
            carry = val / 10;
            
            current.next = node;
            current = current.next;
            
            l2 = l2.next;
        }
        
        if (carry > 0) {
            current.next = new ListNode(carry);
        }
        
        return dummy.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode curr = null;
        ListNode result = curr;

        while (l1 != null || l2 != null) {
            int val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = val / 10;

            ListNode node = new ListNode(val % 10);

            if (curr == null) {
                curr = node;
                // re-point again, otherwise point to null not working
                result = curr;
            } else {
                curr.next = node;
                curr = curr.next;
            }

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return result;
    }
}