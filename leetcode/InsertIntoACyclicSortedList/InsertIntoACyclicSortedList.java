/*
Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list.
The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value.
After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node.
Otherwise, you should return the original given node.

The following example may help you understand the problem better:

https://leetcode.com/static/images/problemset/InsertCyclicBefore.png 
In the figure above, there is a cyclic sorted list of three elements.
You are given a reference to the node with value 3, and we need to insert 2 into the list.

https://leetcode.com/static/images/problemset/InsertCyclicAfter.png 
The new node should insert between node 1 and node 3. After the insertion,
the list should look like this, and we should still return node 3.

idea:
curr = head.next;
prev = head;
since this is a cyclic list, it does not matter

https://yeqiuquan.blogspot.com/2017/04/lintcode-599-insert-into-cyclic-sorted.html

如果原来list为空 需要造一个新的node, 并且自己和自己连上
要么插入的insertVal 恰好在 某两个数值之间
要么是最小 或 最大值

    3---->4---->5---->6---->9---->12
    <------------------------------

insert 15 or 2

curr 理论上是 > prev, 如果 prev > curr, 说明到了首尾相交处

note,
Node() constructor takes a next as parameter
while (curr != head)
*/

class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};

class InsertIntoACyclicSortedList {
    // Sun May 19 17:23:20 2024
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        
        if (head == null) {
            // make a cycle if it has one node of insertVal 
            node.next = node;
            return node;
        }

        Node curr = head;
        Node next = head.next;

        // 因为cycle 所以没有一圈 就继续循环
        while (next != head) {
            // 3 cases to insert into current -> next
            // 1. val大小在中间
            // 2. inserted (大于等于) -> current -> next
            // 3. current -> next -> inserted (小于等于)
            if (next.val > insertVal && curr.val <= insertVal
                || insertVal >= curr.val && curr.val > next.val
                || curr.val > next.val && next.val >= insertVal
            ) {
                break;
            }
            curr = next;
            next = next.next;
        }

        // found the place, weld
        curr.next = node;
        node.next = next;

        return head;
    }

    // Sat Jul 13 20:53:43 2019
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal, null);

        if (head == null) {
            // because it is cyclic list
            node.next = node;

            return node;
        }

        Node prev = head;
        Node curr = head.next;

        while (curr != head) {
            int prevVal = prev.val;
            int currVal = curr.val;
            // 正常的插入
            // 在首尾中间的插入 要么大于 尾 要么小于 头
            if (prevVal <= insertVal && insertVal <= currVal ||
                prevVal > currVal && (prevVal <= insertVal || currVal >= insertVal)
            ) {
                break;
            }

            prev = curr;
            curr = curr.next;
        }

        prev.next = node;
        node.next = curr;

        return head;
    }
}