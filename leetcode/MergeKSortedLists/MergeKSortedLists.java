/*
Merge k sorted linked lists and return it as one sorted list.
Analyze and describe its complexity.

Example:
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6


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
        
        List<ListNode> lists = new ArrayList<ListNode>();
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
    // 2025 merge sort
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeSort(lists, 0, lists.length - 1);
    }

    private ListNode mergeSort(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = mergeSort(lists, left, mid);
        ListNode l2 = mergeSort(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        current.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // Mon Apr  1 23:41:31 2024
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head);
            }
        }

        ListNode dummy = new ListNode(0);
        // pointer
        ListNode current = dummy;

        while (!pq.isEmpty()) {
            ListNode currentMin = pq.poll();
            current.next = currentMin;
            // weld, 连到一起
            current = current.next;
            // note 必须current.next
            if (current.next != null) {
                pq.offer(current.next);
            }
        }

        return dummy.next;
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

        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        ListNode dummyMerged = new ListNode(0);
        ListNode current = dummyMerged;

        while (!pq.isEmpty()) {
            ListNode currMin = pq.poll();
            current.next = currMin;
            current = current.next;

            if (currMin.next != null) {
                pq.add(currMin.next);    
            }
        }
        
        return dummyMerged.next;
    }

    // method 2
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = lists[0];

        for (int i = 1; i < lists.length; i++) {
            merge2SortedList(dummyHead, lists[i]);
        }
        
        return dummyHead.next;
    }
    // helper merge2SortedList
    // merge into one list passed as parameter to save space
    // also this pseudo list is placed a head at the front of 
    public void merge2SortedList(ListNode dummyHead, ListNode q) {
        ListNode prev = dummyHead;
        ListNode p = dummyHead.next;
        
        if (p == null) {
            dummyHead.next = q;
        }

        while (p != null && q != null) {
            // stay as it does at head list
            if (p.val < q.val) {
                if (p.next != null) {
                    // update prev
                    prev = p;
                    // update p
                    p = p.next;
                } else {
                    p.next = q;
                    break;
                }
            } else {
                prev.next = q;
                // update prev
                prev = q;

                ListNode tmp = q.next;
                q.next = p;
                // update q with q.next;
                q = tmp;
            }
        }
    }


    // Tue Apr  2 00:38:05 2024
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = lists[0];

        for (int i = 1; i < lists.length; i++) {
            merge2SortedLists(dummy, lists[i]);
        }

        return dummy.next;
    }

    // 重复利用 dummy
    public void merge2SortedLists(ListNode dummy, ListNode q) {
        ListNode current = dummy;
        ListNode p = dummy.next;

        if (p == null) {
            dummy.next = q;
        }
        // p, q two pointers
        // current weld them together to dummy 

        while (p != null && q != null) {
            if (p.val < q.val) {
                if (p.next != null) {
                    current = p;
                    // advance p
                    p = p.next;
                } else {
                    p.next = q;
                    // dont forget
                    break;
                }
            } else {
                current.next = q;
                current = q;

                ListNode temp = q.next;
                // weld
                q.next = p;
                // advance q
                q = temp;
            }
        }
    }
}
