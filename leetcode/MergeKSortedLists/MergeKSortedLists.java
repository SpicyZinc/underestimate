/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity. 

idea:
http://blog.csdn.net/linhuanmars/article/details/19899259

1. each list is represented as ListNode, so PriorityQueue to get the smallest, add it to resultant list,
then add smallest.next to priority queue again.
takes O(k * n) time, n is average length of each list
note: before adding to priority queue, make sure it is not empty

2. Make use of merging two sorted list
keep one as major, the other one is on call whenever finding q.val > p.val
finally merge the one on call into the major one
logk times of merge, each merge takes time as the number of nodes in two lists
n =  n1 + n2 + ... + nk
	 |                |
	  -------k-------
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
	public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || lists == null) {
            return null;
        }
        // priority queue to get smallest list node
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode a, ListNode b) {
                return a.val - b.val;
            }
        });

        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);    
            }
        }
        ListNode merged = new ListNode(0);
        ListNode current = merged;
        while (!pq.isEmpty()) {
            ListNode currMin = pq.poll();
            current.next = currMin;
            current = currMin;
            if (currMin.next != null) {
                pq.add(currMin.next);    
            }
        }
        
        return merged.next;
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
				} else {
					p.next = q;
					// return head.next;
					break;
				}
			} else {
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
}
