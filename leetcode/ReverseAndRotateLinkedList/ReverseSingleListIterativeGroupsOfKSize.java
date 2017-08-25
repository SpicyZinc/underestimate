
public class ReverseSingleListIterativeGroupsOfKSize{
    public ReverseSingleListIterativeGroupsOfKSize(){
    }

    public ListNode reverseList(ListNode headerNode, int k){
        ListNode prevNode = null;
        ListNode currNode = headerNode;
        ListNode nextNode = null;
		int count = 0;

        while (currNode != null && count < k)
        {
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
			count++;
        }
		
		if(nextNode != null)
			headerNode.next = reverseList(nextNode, k);

        return prevNode;
    }

    public static void main(String[] args){
        /**
		Constructing Single Linked List:
            1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 
		*/
        SingleLinkedList newList = new SingleLinkedList();
        newList.add(1);
        newList.add(2);
        newList.add(3);
        newList.add(4);
        newList.add(5);
		newList.add(6);
        newList.add(7);
        newList.add(8);
        System.out.println("List before reversal");
        newList.printList();

        ListNode headerNode = newList.getList();
        ReverseSingleListIterativeGroupsOfKSize reverseListIter = new ReverseSingleListIterativeGroupsOfKSize();
        headerNode = reverseListIter.reverseList(headerNode, 3);
        newList.setList(headerNode);
        System.out.println("List after reversal");
        newList.printList();
    }
}

class ListNode{
	int value;
	ListNode next;
	
	public ListNode(int value){
		this.value = value;
		next = null;
	}	
}

class SingleLinkedList{
	public ListNode head;
	
	public void add(int value){
		ListNode newNode = new ListNode(value);
		newNode.next = head;
		head = newNode;		
	}
	public ListNode getList(){
		return head;
	} 
	public void setList(ListNode aNode){
		head = aNode;
	}
	
	public void printList(){
		ListNode current = head;
		
		while(current != null){
			System.out.printf("%d ", current.value);
			current = current.next;
		}
		System.out.println();
	}
}
