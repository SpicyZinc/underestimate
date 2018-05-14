public class SinglyLinkedList {
	public void removeFirst() {
		if (head == null) {
			return;
		} else {
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = head.next;
			}
		}
    }

	public void removeLast() {
		if (tail == null) {
			return;
		} else {
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				SinglyLinkedListNode previousToTail = head;
				while (previousToTail.next != tail) {
					previousToTail = previousToTail.next;
				}
				tail = previousToTail;
				tail.next = null;
			}
		}
	}

	public void removeNext(SinglyLinkedListNode previous) {
		if (previous == null) {
			removeFirst();
		} else if (previous.next == tail) {
			tail = previous;
			tail.next = null;
		} else if (previous == tail) {
			return;
		} else {
			previous.next = previous.next.next;
		}
	}
}