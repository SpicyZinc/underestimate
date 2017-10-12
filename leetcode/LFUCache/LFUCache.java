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

imagine a list of piles of different frequencies
the frequency is in an increasing order

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

LinkedHashSet maintains the insertion order
Especially storing all keys with same frequencies in one node

basic structure is doubly linked list of DLLNode, the list is frequency increasing order
each node, I call it FrequencyNode, beside prev and next for DLLNode, it also has frequency and a LinkedHashset of keys with the same frequency
valuesMap contains the basic key - value pair

For example, if cache size is 3, the data access sequence as:
set(2,2), set(1,1), get(2), get(1), get(2), set(3,3), set(4,4)
LFU will eliminate (3, 3)
LRU will eliminate (1, 1)
*/

class LFUCache {
    class DLLNode {
        DLLNode prev;
        DLLNode next;
        int frequency;
        Set<Integer> keys;

        public DLLNode(int frequency) {
            this.prev = null;
            this.next = null;
            this.frequency = frequency;
            this.keys = new LinkedHashSet<Integer>();
        }
    }
    
    int capacity;
    DLLNode head;
    Map<Integer, Integer> valuesMap;
    Map<Integer, DLLNode> nodesMap;
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.head = null;
        this.valuesMap = new HashMap<Integer, Integer>();
        this.nodesMap = new HashMap<Integer, DLLNode>();
    }
    
    public int get(int key) {
        if (valuesMap.containsKey(key)) {
            increaseFrequency(key);
            return valuesMap.get(key);
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        if (!valuesMap.containsKey(key)) {
            // if full, first remove oldest
            if (valuesMap.size() >= capacity) {
                removeOldest();
            }
            addBeforeHead(key);
        }
        // update valuesMap
        valuesMap.put(key, value);
        // if ever touched, change frequency
        increaseFrequency(key);
    }
    
    // increase the frequency of value pointed by key
    // note, besides increase, remove key from old frequency list
    public void increaseFrequency(int key) {
        DLLNode node = nodesMap.get(key);
        node.keys.remove(key);
        // 3 cases
        // node is last one, need to create a new node
        // happen to have a node next to node whose frequency bigger by 1
        // after increase, have to insert in between of DLL
        if (node.next == null) {
            DLLNode newNode = new DLLNode(node.frequency + 1);
            node.next = newNode;
            newNode.prev = node;
            newNode.keys.add(key);
        } else if (node.next.frequency == node.frequency + 1) {
            node.next.keys.add(key);
        } else {
            DLLNode newNode = new DLLNode(node.frequency + 1);
            newNode.keys.add(key);

            DLLNode next = node.next;
            node.next = newNode;
            newNode.prev = node;
            newNode.next = next;
            next.prev = newNode;
        }
        // update nodes map
        nodesMap.put(key, node.next);
        // old node.keys empty, remove node from DLL
        if (node.keys.size() == 0) {
            removeNodeFromDLL(node);
        }
    }
    
    // the oldest key should be head in DLL
    // in head.keys linked hashset, it is the first one added to set
    public void removeOldest() {
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
            removeNodeFromDLL(head);
        }
        valuesMap.remove(oldestKey);
        nodesMap.remove(oldestKey);
    }
    
    // add node to or before head in DLL
    // since this is add new key, frequency == 1, to head self or even before head
    public void addBeforeHead(int key) {
        // 3 cases:
        // the first time to add, DLL is empty
        // add to head
        // head.frequency > 1, before head
        if (head == null) {
            head = new DLLNode(1);
            head.keys.add(key);
        } else if (head.frequency == 1) {
            head.keys.add(key);
        } else {
            DLLNode newNode = new DLLNode(1);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            head.keys.add(key);
        }
        // update nodes map
        nodesMap.put(key, head);
    }
    
    // remove node from DLL, the scaffold of LFU
    public void removeNodeFromDLL(DLLNode node) {
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
