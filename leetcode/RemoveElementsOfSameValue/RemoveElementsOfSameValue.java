class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }

    void printList() {
        ListNode current = this;
        while (current != null) {
            System.out.print(" " + current.val);
            current = current.next;
        }
        System.out.println();
    }
}

public class RemoveElementsOfSameValue {
    public static void main(String[] args) {
        RemoveElementsOfSameValue test = new RemoveElementsOfSameValue();

        ListNode listOne = new ListNode(3);
        listOne.next = new ListNode(5);
        listOne.next.next = new ListNode(1);
        listOne.next.next.next = new ListNode(3);
        listOne.next.next.next.next = new ListNode(2);
        listOne.next.next.next.next.next = new ListNode(3);
        listOne.next.next.next.next.next.next = new ListNode(3);
        listOne.next.next.next.next.next.next.next = new ListNode(3);

        listOne.printList();
        listOne = test.deleteAll(listOne, 3);    
        listOne.printList();


        ListNode listTwo = new ListNode(2);
        listTwo.next = new ListNode(3);
        listTwo.next.next = new ListNode(1);
        listTwo.next.next.next = new ListNode(2);
        listTwo.next.next.next.next = new ListNode(3);

        listTwo.printList();
        listTwo = test.deleteAll(listTwo, 2);    
        listTwo.printList();
    }

    public ListNode deleteAll(ListNode head, int val) {
        while (head.val == val) {
            head = head.next;
        }

        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            }
            else {
                prev = curr;
            }
            
            curr = curr.next;
        }

        return head;
    }
}