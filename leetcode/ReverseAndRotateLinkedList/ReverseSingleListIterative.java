/**
http://crackinterviewtoday.wordpress.com/2010/03/24/reverse-a-single-linked-list-iterative-procedure/
*/

class ListNode {
    int value;
    ListNode next;
    
    public ListNode(int value) {
        this.value = value;
        next = null;
    }   
}

class SingleLinkedList {
    public ListNode head;
    
    public void add(int value) {
        ListNode newNode = new ListNode(value);
        newNode.next = head;
        head = newNode;     
    }
    public ListNode getList() {
        return head;
    } 
    public void setList(ListNode aNode) {
        head = aNode;
    }
    
    public void printList() {
        ListNode current = head;
        
        while (current != null) {
            System.out.printf("%d ", current.value);
            current = current.next;
        }
        System.out.println();
    }
}

public class ReverseSingleListIterative {
    public ReverseSingleListIterative() {}

    public ListNode reverseList(ListNode headerNode){
        ListNode prevNode = null;
        ListNode currNode = headerNode;
        ListNode nextNode = null;

        while (currNode != null) {
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }

        return prevNode;
    }

    public static void main(String[] args) {
        SingleLinkedList newList = new SingleLinkedList();
        newList.add(1);
        newList.add(2);
        newList.add(3);
        newList.add(4);
        newList.add(5);
        System.out.println("List before reversal");
        newList.printList();

        ListNode headerNode = newList.getList();
        ReverseSingleListIterative reverseListIter = new ReverseSingleListIterative();
        headerNode = reverseListIter.reverseList(headerNode);
        newList.setList(headerNode);
        System.out.println("List after reversal");
        newList.printList();
    }
}
