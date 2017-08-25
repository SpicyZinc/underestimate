/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity. 

idea:
http://blog.csdn.net/linhuanmars/article/details/19899259

1. The direct thought is to scan head of every list and find the minNode, remove it, add to resultant list, add minNode.next to K lists' arraylist
note: curr.next = minNode OR new ListNode(minNode.val)
my thought is right, yes, it connects the minNode list to resultant list, but resultant list will be updated by .next operation
we only care about minNode list's head, after head although it is not what want, .next will get rid of them.
Also, resultant list is longer than any list, so finally will get all of them

run-time:
add every list together, total length is n
every time adding a node to result list needs to scan the length of ArrayList, which might be less than k,
so n nodes takes O(k * n) time  

2. Make use of merging two sorted list
merge two sorted lists
keep one as major, the other one is on call whenever finding q.val > p.val
finally merge the one on call into the major one

logk times of merge, merge takes time as the number of nodes in two lists
n =  n1 + n2 + ... + nk
	 |                |
	  -------k-------

3. Make use of merging two sorted list
logk times of merge, merge takes time as the number of nodes in two lists
n =  n1 + n2 + ... + nk
	 |                |
	  -------k-------

merge two sorted lists
keep one as major, the other one is on call whenever finding q.val > p.val
finally merge the one on call into the major one

merge two sorted lists with creating extra space to store the merged one
compared to MergeKSortedListsII, this version does not save space,
it creates another list to hold the merged list, not like MergeKSortedListsII merge, which is to the head list
*/

import java.util.*;

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class MergeKSortedLists {
	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		a.next = new ListNode(3);
		a.next.next = new ListNode(4);
		
		ListNode b = new ListNode(2);
		b.next = new ListNode(5);
		
		ListNode c = new ListNode(6);
		
		ArrayList<ListNode> lists = new ArrayList<ListNode>();
		lists.add(a);
		lists.add(b);
		lists.add(c);
		
		MergeKSortedLists eg = new MergeKSortedLists();
		ListNode result = eg.mergeKLists_self(lists);
		ListNode h = result;
		while (h != null) {
			System.out.print(h.val + "  ");
			h = h.next;
		}
	}
	// recent self written
	public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || lists == null) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });
        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (!pq.isEmpty()) {
            ListNode temp = pq.poll();
            if (temp.next != null) {
                pq.add(temp.next);
            }
            current.next = temp;
            current = current.next;
        }
        
        return dummy.next;
    }
	// method 1 version with PriorityQueue to find minNode, passed test
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists.size() == 0 || lists == null ) {
			return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
			@Override
			public int compare(ListNode a, ListNode b) {
				return a.val - b.val;
			}
		});
        // generate heap based on lists
        for (ListNode list : lists) {
            if (list != null) {
				pq.add(list);
            }
        }
                
        ListNode head = new ListNode(0);
		ListNode curr = head;
		
        while (pq.size() != 0) {
            ListNode temp = pq.poll();
            if (temp.next != null) {
				// every time when adding, because of heap, head.val minimum will be at the front of queue
            	pq.add(temp.next);
            }
            curr.next = temp;
            curr = curr.next;
        }
		
        return head.next;
    }
	// why this method not pass the test
	// java.lang.NullPointerException
    public ListNode mergeKLists_self(ArrayList<ListNode> lists) {
		if (lists.size() == 0 || lists == null) {
            return null;
        }
		int size = lists.size();
		ListNode res = new ListNode(0);
		ListNode curr = res;
		int n = 0;
		while (size > 0) {
			n++;
			int min = Integer.MAX_VALUE;
			int index = 0; // record each loop min appears position
			for (int i = 0; i < lists.size(); i++) {				
				ListNode head = lists.get(i);
				if (head.val < min) {
					min = head.val;
					index = i;
				}
			}
			// this is much more neat than the comment-out one
			// remove one, minus one immediately
			ListNode minNode = lists.remove(index);
			size--;
			if (minNode.next != null) {
				lists.add(minNode.next);
				// add one, put size back to before status
				size++;
			}
			// ATTENTION: these two ways are different
			// curr.next = new ListNode(min);
			// added to result list
			curr.next = minNode;
			curr = curr.next;
		}
		System.out.println("Run-Time is must be the no. of nodes: " + n);
		return res.next;
    }

    // method 2 without creating extra space, timeout
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists.size() == 0) {
			return null;
		}
		
		ListNode head = new ListNode(0);
		head.next = lists.get(0);
		// head is already taken out of the arraylist
		for (int i = 1; i < lists.size(); i++) {
			merge2SortedList(head, lists.get(i));
		}
		
		return head.next;
	}
	// helper merge2SortedList
	// merge into one list passed as parameter to save space
	// also this pseudo list is placed a head at the front of 
	public void merge2SortedList(ListNode head, ListNode q) {
		ListNode p = head.next;
		ListNode prev = head;
		
		if (p == null) {
			head.next = q;
		}
		
		while (p != null && q != null) {
			// stay as it does at head list
			if (p.val < q.val) {
				if (p.next != null) {
					// p.next = q;
					// update prev
					prev = p;
					// update p
					p = p.next;
				}
				else {
					p.next = q;
					// return head.next;
					break;
				}
			}
			else {
				// memorize q position
				ListNode tmp = q.next;
				prev.next = q;
				q.next = p;
				// update prev
				prev = q;
				// update q
				q = tmp;
			}
		}
	}

	// method 3 with creating extra space, timeout
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists.size() == 0) {
			return null;
		}
		
		ListNode head = new ListNode(0);
		head.next = lists.get(0);
		// head is already taken out of the arraylist
		for (int i=1; i<lists.size(); i++) {
			merge2SortedList(head, lists.get(i));
		}
		
		return head.next;
	}
	
	// helper merge2SortedList 
	public void merge2SortedList(ListNode p, ListNode q) {
		ListNode merge = new ListNode(0);
		ListNode current = merge;
		while (p != null && q != null) {
			if (p.val < q.val) {
				current.next = p;
				p = p.next;
				current = current.next;
			}
			else {
				current.next = q;
				q = q.next;
				current = current.next;
			}
		}
		if (p != null) {
			current.next = p;
		}
		if (q != null) {
			current.next = q;
		}
		p = current.next;
	}
}
