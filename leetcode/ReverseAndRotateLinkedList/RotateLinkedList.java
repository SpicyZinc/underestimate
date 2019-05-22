class List {
	int value;
	List next;
	public List(int value) {
		this.value = value;
		next = null;
	}
	
	public void print() {
		List current = this;
		while (current != null) {
			System.out.print(current.value + " ");
			current = current.next;
		}
		System.out.print("NULL");
	}
}

public class RotateLinkedList {
	public static void main(String[] args) {
		List head = new List(1);
		head.next = new List(2);
		head.next.next = new List(3);
		head.next.next.next = new List(4);
		head.next.next.next.next = new List(5);
		head.next.next.next.next.next = new List(6);		
		head.print();
		/**1 2 3 4 5 6***/
		head = rotate(head, 2);
		System.out.print("\nRotate 1st time with 2-size: ");
		head.print();
		/**3 4 5 6 1 2 ***/
		head = rotate(head, 2);
		System.out.print("\nRotate 2nd times with 2-size: ");
		head.print();
		/**5 6 1 2 3 4  ***/
	}
	
	private static List rotate(List head, int k) {
		List current = head;
		List newList = null;

		while (k > 1) {
			current = current.next;
			k--;
		}

		// now current pointer points to the Kth node
		newList = current.next;
		current.next = null; // kth node should be tail because it becomes the new tail, cut it off
		
		// 继续走完剩余的
		// 然后指向 head 完成 rotate
		current = newList;
		while (current.next != null) {
			current = current.next;
		}
		current.next = head;

		return newList;
	}
}