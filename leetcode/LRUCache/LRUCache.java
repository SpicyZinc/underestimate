/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache(2); // capacity

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4


idea:

http://www.cnblogs.com/feiling/p/3426967.html
http://www.programcreek.com/2013/03/leetcode-lru-cache-java/

// use LinkedHashMap
http://www.codewalk.com/2012/04/least-recently-used-lru-cache-implementation-java.html
http://blog.csdn.net/whuwangyi/article/details/15495845


get == visited 
set == inserted
either visited or inserted will hoist the value to list head

lookup (get):
from hashmap根据key查询, 若命中, 则返回节点, 否则返回null
from 双向链表中删除命中的节点, 将其重新插入到表头

insert (set): first delete then insert
如果cache满了, 删除双向链表的尾节点, 同时删除Hashmap对应的记录
将新的节点关联到Hashmap
将新的节点插入到双向链表头部

delete:
从双向链表和Hashmap中同时删除对应的记录
*/

class DLLNode {
    int key;
    int value;
    DLLNode prev;
    DLLNode next;
    
    public DLLNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    
    int capacity;
    int currSize;
    DLLNode head;
    DLLNode tail;
    Map<Integer, DLLNode> hm = new HashMap<Integer, DLLNode>();
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.currSize = 0;
    }
    
    public int get(int key) {
        if (hm.containsKey(key)) {
            DLLNode node = hm.get(key);
            // update position in list
            // remove, then add before head
            remove(node);
            insertAtHead(node);
            return node.value;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (hm.containsKey(key)) {
            DLLNode node = hm.get(key);
            node.value = value;
            // update position in list
            // remove, then add before head
            remove(node);
            insertAtHead(node);
            // no need to update hm since DLLNode object address is not changed
        	// only pointers pointing to it changed or its contents (prev, next) get updated
        } else {
            // 1. cache is full
            // 2. cache is not full
            DLLNode newNode = new DLLNode(key, value);
            if (currSize >= capacity) {
                // remove tail from map
                hm.remove(tail.key);
                // remove tail from list
                tail = tail.prev;
                if (tail != null) tail.next = null;
                
                // add to map
                hm.put(key, newNode);
                // add to list
                insertAtHead(newNode);
            } else {
                hm.put(key, newNode);
                insertAtHead(newNode);
                currSize++;
            }
        }
    }
    
    // remove node from Double linked list
    public void remove(DLLNode node) {
        DLLNode prev = node.prev;
        DLLNode next = node.next;
        // update next
        if (prev != null) {
            prev.next = next;
        } else {
            head = next;
        }
        // update prev
        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev;
        }
    }
    
    // insert node before head
    public void insertAtHead(DLLNode node) {
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }
        // head points to node
        head = node;
        // update tail
        if (tail == null) {
            tail = head;
        }
    }
}

// public class LRUCache extends LinkedHashMap<Integer, Integer> {
//     private int capacity;
    
//     public LRUCache(int capacity) {
//         super(capacity+1, 1.0f, true);  // "true" for access order
//         this.capacity = capacity;
//     }
    
//     public int get(int key) {
//         if (super.get(key) == null) {
//             return -1;
//         } else {
//             return ((int) super.get(key));
//         }
//     }
    
//     public void set(int key, int value) {
//         super.put(key, value);
//     }
    
//     protected boolean removeEldestEntry(Entry entry) {
//         return (size() > this.capacity);
//     }
// }
