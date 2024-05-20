/*
You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer,
which may or may not point to a separate doubly linked list.
These child lists may have one or more children of their own, and so on,
to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list.
You are given the head of the first level of the list.

Example:
Input:
1---2---3---4---5---6--NULL
        |
        7---8---9---10--NULL
            |
            11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL

Explanation for the above example:
Given the following multilevel doubly linked list:
https://assets.leetcode.com/uploads/2018/10/12/multilevellinkedlist.png
We should return the following flattened doubly linked list:
https://assets.leetcode.com/uploads/2018/10/12/multilevellinkedlistflattened.png

idea:
https://www.cnblogs.com/grandyang/p/9688522.html

dfs() 先把child都traverse 然后再连接起来 记着next prev
然后继续再走 遇到有child的node 再flatten()
*/

// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
}

class FlattenAMultilevelDoublyLinkedList {
    public Node flatten(Node head) {
        Node curr = head;
        while (curr != null) {
            if (curr.child != null) {
                // save next as right
                Node right = curr.next;
                // process child
                curr.next = flatten(curr.child);
                curr.next.prev = curr;
                curr.child = null;
                // continue going and finish the latest current
                // so that it can connect to others
                while (curr.next != null) {
                    curr = curr.next;
                }
                // reconnect next
                if (right != null) {
                    curr.next = right;
                    curr.next.prev = curr;    
                }
            }
            curr = curr.next;
        }

        return head;
    }
}
