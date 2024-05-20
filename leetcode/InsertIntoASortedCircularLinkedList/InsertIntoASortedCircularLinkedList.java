/*
Given a Circular Linked List node, which is sorted in non-descending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list.
The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.

If the list is empty (i.e., the given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the originally given node.

Example 1:
https://assets.leetcode.com/uploads/2019/01/19/example_1_before_65p.jpg
Input: head = [3,4,1], insertVal = 2
Output: [3,4,1,2]
Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.

Example 2:
Input: head = [], insertVal = 1
Output: [1]
Explanation: The list is empty (given head is null). We create a new single circular list and return the reference to that single node.

Example 3:
Input: head = [1], insertVal = 0
Output: [1,0]

Constraints:
The number of nodes in the list is in the range [0, 5 * 10^4].
-10^6 <= Node.val, insertVal <= 10^6

idea:
找到应该插入的位置
分三种情况, 连接前后node
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class InsertIntoASortedCircularLinkedList {
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
}
