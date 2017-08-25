/*
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.

idea:
All O`one Data Structure
http://bookshadow.com/weblog/2016/10/18/leetcode-all-oone-data-structure/


keyDict: HashMap key to keyNode containing value
valueDict: Doubly linked list value to ValueNode (list of Node)
head: pointing to Max ValueNode
tail: pointing to Min ValueNode

head --- ValueNode1 ---- ValueNode2 ---- ... ---- ValueNodeN --- tail 
              |               |                       |               
            first           first                   first             
              |               |                       |               
           KeyNodeA        KeyNodeE                KeyNodeG           
              |               |                       |               
           KeyNodeB        KeyNodeF                KeyNodeH           
              |                                       |               
           KeyNodeC                                KeyNodeI           
              |                                                       
           KeyNodeD      

*/

public class AllOne {
    // Initialize your data structure here. 
    DoublyLinkedList customList;
    HashMap<String, Node> map;
    public AllOne() {
        customList = new DoublyLinkedList();
        map = new HashMap<String, Node>();
    }
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            n.val++;
            // Greater than Tail then remove it and add in front of tail
            if (n.val >= customList.tail.val) {
                customList.remove(n);
                customList.insertFromTail(n);
            }
        }
        else {
            // New nodes are inserted from head because they have minimum count
            Node n = new Node(key, 1);
            map.put(key, n);
            customList.insertFromHead(n);
        }
    }
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            if (n.val == 1) {
                map.remove(key);
                customList.remove(n);
            }
            else {
                // Less than head. Remove it and add it at the back of the head
                n.val--;
                if (n.val <= customList.head.val) {
                    customList.remove(n);
                    customList.insertFromHead(n);
                }
            }
        }
    }
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (customList.tail != null) {
            return customList.tail.key;
        }
        return "";
    }

    // Returns one of the keys with Minimal value. 
    public String getMinKey() {
        if (customList.head != null) {
            return customList.head.key;
        }
        return "";
    }

    class Node {
        String key;
        int val;
        Node next;
        Node prev;

        Node(String key , int val) {
            this.key = key;
            this.val = val;
            next = null;
            prev = null;
        }
    }

    class DoublyLinkedList {
        Node head;
        Node tail;

        public DoublyLinkedList() {
            head = null;
            tail = null;
        }

        public void insertFromHead(Node newNode) {
            if (head == null) {
                tail = newNode;
                head = newNode;
            }
            else {
                newNode.next = head;
                head.prev = newNode;
                head = head.prev;
            }
        }
        public void insertFromTail(Node newNode) {
            if (tail == null) {
                head = newNode;
                tail = newNode;
            }
            else {
                newNode.prev = tail;
                tail.next = newNode;
                tail = tail.next;
            }
        }
        public void removeFromHead() {
            if (head == null) {
                return;
            }
            else if (head.next == null && head.prev == null) {
                head = null;
            }
            else {
                head = head.next;
                head.prev = null;
            }
        }
        public void removeFromTail() {
            if (tail == null) {
                return;
            }
            else if (tail.next == null && tail.prev == null) {
                tail = null;
                head = null;
            }
            else {
                Node temp = tail;
                tail = tail.prev;
                tail.next = null;
                temp.prev = null;
            }
        }
        public void remove(Node node) {
            if (node == tail) {
                removeFromTail();
                return;
            }
            if (node == head) {
                removeFromHead();
                return;
            }
            Node temp = head;
            while (temp.next != null) {
                if (temp.next == node) {
                    Node next = node.next;
                    temp.next = next;
                    next.prev = temp;
                    break;
                }
                temp = temp.next;
            }
        }
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */