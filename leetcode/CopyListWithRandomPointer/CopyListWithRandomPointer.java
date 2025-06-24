/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
Return a deep copy of the list.

idea:
http://www.programcreek.com/2012/12/leetcode-copy-list-with-random-pointer/
1. use hashmap to keep origin - copy pair, 
loop through the origin, create copy node, save to hashmap;
meanwhile populate next in the copy list

loop again, populate random pointer

http://blog.csdn.net/fightforyourdream/article/details/16879561
2. A----->B----->C----->D----->E
   A      B      C      D      E
   |    / |    / |    / |    / |
   |   /  |   /  |   /  |   /    |
   |  /   |  /   |  /   |  /   |
   A'       B'     C'     D'     E'
*/

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}

class CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        Map<Node, Node> hm = new HashMap<>();

        Node curr = head;
        Node dummyCopy = new Node();
        Node currCopy = dummyCopy;

        while (curr != null) {
            Node node = new Node();
            node.val = curr.val;
            currCopy.next = node;
            
            hm.put(curr, node);
            
            curr = curr.next;
            currCopy = currCopy.next;
        }

        curr = head;

        while (curr != null) {
            // 这个是不用 currCopy step forward
            Node copy = hm.get(curr);
            copy.random = hm.get(curr.random);
            
            curr = curr.next;
        }

        return dummyCopy.next;
    }

    // hashmap
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }

        Map<Node, Node> hm = new HashMap<Node, Node>();
        Node curr = head;
        Node dummy = new Node(0);
        Node copyCurrr = dummy;

        while (curr != null) {
            Node newNode = new Node();
            newNode.val = curr.val;

            hm.put(curr, newNode);
            copyCurrr.next = newNode;

            copyCurrr = copyCurrr.next;
            curr = curr.next;
        }

        curr = head;
        copyCurr = dummy.next;
        while (curr != null) {
            Node copyRandom = hm.get(curr.random);
            copyCurr.random = copyRandom;
            
            curr = curr.next;
            copyCurr = copyCurr.next;
        }

        return dummy.next;
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node copyHead = null;
        Node copyCurr = null;
        Node curr = head; 
        // insert copy
        while (curr != null) {
            copyCurr = new Node();
            copyCurr.val = curr.val;
            if (copyHead == null) {
                copyHead = copyCurr;
            }
            copyCurr.next = curr.next;
            curr.next = copyCurr;
            curr = curr.next.next;
        }
        // copy random
        curr = head;
        copyCurr = copyHead;
        while (curr != null) {
            if (curr.random != null) {
                copyCurr.random = curr.random.next;
            }
            curr = curr.next.next;
            if (copyCurr.next != null) {
                copyCurr = copyCurr.next.next;
            }
        }
        // recover the origin, and extract the copied
        curr = head;
        copyCurr = copyHead;
        while (curr != null) {
            curr.next = curr.next.next;
            if (copyCurr.next != null) {
                copyCurr.next = copyCurr.next.next;
            }
            curr = curr.next;
            copyCurr = copyCurr.next;
        }

        return copyHead;
    }
}
