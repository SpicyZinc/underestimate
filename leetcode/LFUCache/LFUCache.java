/*
Design and implement a data structure for Least Frequently Used (LFU) cache.
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present.
When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item.
For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 ); // capacity is 2
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

idea:
http://bookshadow.com/weblog/2016/11/22/leetcode-lfu-cache/

key is page address
value is value

head --- FreqNode1 ---- FreqNode2 ---- ... ---- FreqNodeN
              |               |                       |               
            first           first                   first             
              |               |                       |               
           KeyNodeA        KeyNodeE                KeyNodeG           
              |               |                       |               
           KeyNodeB        KeyNodeF                KeyNodeH           
              |               |                       |               
           KeyNodeC         last                   KeyNodeI           
              |                                       |      
           KeyNodeD                                 last
              |
            last

Especially storing all keys with same frequencies in one node,
LinkedHashSet maintains the insertion order

if one of the keys in that node got hit once more, 
it will be moved into a new node with (frequency + 1) if the node exits or it will be wrapped into a newly created node with (frequency + 1)


basic structure is doubly linked list of DLLNode, the list is frequency increasing order
each node, I call it FrequencyNode, beside prev and next for DLLNode, it also has frequency and a LinkedHashset of keys with the same frequency
valuesMap contains the basic key - value pair
*/

public class LFUCache {
    private class FrequencyNode {
        int frequency;
        FrequencyNode prev = null, next = null;
        LinkedHashSet<Integer> keys;

        public FrequencyNode(int frequency) {
            this.frequency = frequency;
            this.prev = null;
            this.next = null;
            this.keys = new LinkedHashSet<Integer>();
        }
    }

    public int capacity;
    public FrequencyNode head;
    public HashMap<Integer, Integer> valueHash = null;
    public HashMap<Integer, FrequencyNode> frequencyHash = null;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        head = null;
        valueHash = new HashMap<Integer, Integer>();
        frequencyHash = new HashMap<Integer, FrequencyNode>();
    }

    public int get(int key) {
        if (valueHash.containsKey(key)) {
            increaseFrequency(key);
            return valueHash.get(key);
        }
        else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!valueHash.containsKey(key)) {
            // if already full, remove oldest first
            if (valueHash.size() >= capacity) {
                // remove the least frequent and least recently used one
                removeOldest();
            }
            // if not full
            addToHead(key);
        }
        // add to valueHash
        valueHash.put(key, value);
        // if ever touched, increase the frequency
        increaseFrequency(key);
    }

    private void increaseFrequency(int key) {
        FrequencyNode node = frequencyHash.get(key);
        // since this key's frequency changed, should be removed from LinkedHashSet
        node.keys.remove(key);
        // if node next is null, meaning this is the last node
        if (node.next == null) {
            FrequencyNode newNode = new FrequencyNode(node.frequency + 1);
            node.next = newNode;
            newNode.prev = node;
            newNode.keys.add(key);
        }
        else if (node.next.frequency == node.frequency + 1) {
            node.next.keys.add(key);
        }
        else { // insert in between
            FrequencyNode newNode = new FrequencyNode(node.frequency + 1);
            newNode.keys.add(key);

            FrequencyNode next = node.next;
            node.next = newNode;
            newNode.prev = node;
            newNode.next = next;
            next.prev = newNode;
            // both working
            // // first new code
            // newNode.prev = node;
            // newNode.next = node.next;
            // // then before and after code
            // node.next.prev = newNode;
            // node.next = newNode;

        }
        frequencyHash.put(key, node.next);
        // frequency node keys size == 0, remove it
        if ( node.keys.size() == 0 ) {
            remove(node);
        }
    }

    // the oldest should the first (head) FrequencyNode in the doubly linked list
    // in the linked hashset last one
    private void removeOldest() {
        int oldestKey = 0;
        // the first one
        for (int n : head.keys) {
            oldestKey = n;
            break;
        }
        // remove from head
        head.keys.remove(oldestKey);
        // if keys size() == 0
        // remove the whole keys
        if (head.keys.size() == 0) {
            remove(head);
        }
        valueHash.remove(oldestKey);
        frequencyHash.remove(oldestKey);
    }

    // add to the basic doubly linked list
    // case 1, first to add, the linked list is empty
    // case 2, head.frequency > 1
    // case 3, directly add to head
    private void addToHead(int key) {
        if (head == null) {
            head = new FrequencyNode(1);
            head.keys.add(key);
        }
        else if (head.frequency > 1) {
            FrequencyNode newNode = new FrequencyNode(1);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            newNode.keys.add(key);
        }
        else {
            head.keys.add(key);
        }
        // head is pointing to the new FrequencyNode
        frequencyHash.put(key, head);
    }

    private void remove(FrequencyNode node) {
        // update node.next and node.prev
        // if node is the head
        if (node.prev == null) {
            // let head point to the node.next
            head = node.next;
        } else {
            node.prev.next = node.next;
        }
        // if node is the last
        if (node.next != null) {
            node.next.prev = node.prev;
        }
    }
}