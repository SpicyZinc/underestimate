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
https://www.programcreek.com/wp-content/uploads/2013/03/LRU-Cache-650x296.png

http://www.cnblogs.com/feiling/p/3426967.html

use LinkedHashMap
http://www.codewalk.com/2012/04/least-recently-used-lru-cache-implementation-java.html
http://blog.csdn.net/whuwangyi/article/details/15495845


get == visit 
set == insert
either visit or insert will hoist the value to list head

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

class LRUCache {
    Map<Integer, DLLNode> hm;
    
    DLLNode head;
    DLLNode tail;
    
    int currentSize;
    int capacity;
    
    public LRUCache(int capacity) {
        this.hm = new HashMap<Integer, DLLNode>();

        this.head = null;
        this.tail = null;
        
        this.currentSize = 0;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (hm.containsKey(key)) {
            DLLNode node = hm.get(key);
            // update DLL
            remove(node);
            insertAtHead(node);
            return node.val;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (hm.containsKey(key)) {
            DLLNode node = hm.get(key);
            node.val = value;
            // update DLL
            remove(node);
            insertAtHead(node);
            // no need to update hm since DLLNode object address is not changed
            // only pointers pointing to it changed or its contents (prev, next) get updated
        } else {
            DLLNode newNode = new DLLNode(key, value);
            if (currentSize >= capacity) {
                // delete tail node 腾出空间
                // 1. remove from hashmap
                hm.remove(tail.key);
                // 2. remove from DLL
                remove(tail);
                // why not increase counter
                // because offset remove() and add()
            } else {
                currentSize++;
            }
            
            // insert at head;
            insertAtHead(newNode);
            // add to map
            hm.put(key, newNode);
        }
    }

    // remove node from Double linked list
    private void remove(DLLNode node) {
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
    private void insertAtHead(DLLNode node) {
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        // update tail 无非就是head 和 tail 都检查下
        if (tail == null) {
            tail = head;
        }
    }
}

class DLLNode {
    int key;
    int val;
    DLLNode prev;
    DLLNode next;
    
    public DLLNode(int key, int value) {
        this.key = key;
        this.val = value;
        prev = null;
        next = null;
    }
}

// use linked hashmap
public class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;
    
    public LRUCache(int capacity) {
        super(capacity + 1, 1.0f, true);  // "true" for access order
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (super.get(key) == null) {
            return -1;
        } else {
            return ((int) super.get(key));
        }
    }
    
    public void set(int key, int value) {
        super.put(key, value);
    }
    
    protected boolean removeEldestEntry(Entry entry) {
        return (size() > this.capacity);
    }
}
