/*
http://www.programcreek.com/2013/03/leetcode-lru-cache-java/

idea:
http://www.codewalk.com/2012/04/least-recently-used-lru-cache-implementation-java.html

http://www.acmerblog.com/leetcode-lru-cache-lru-5745.html
best: http://www.cnblogs.com/feiling/p/3426967.html

http://blog.csdn.net/whuwangyi/article/details/15495845


get == visited 
set == inserted
either visited or inserted will hoist the value to list head

lookup (get):
根据键值查询hashmap，若命中，则返回节点，否则返回null
从双向链表中删除命中的节点，将其重新插入到表头

insert (set):
如果cache满了，删除双向链表的尾节点，同时删除Hashmap对应的记录
将新的节点关联到Hashmap
将新的节点插入到双向链表头部

delete:
从双向链表和Hashmap中同时删除对应的记录。
*/
// self written passed test
public class LRUCache {
	private class DLLNode {
		int key;
		int value;
		DLLNode prev;
		DLLNode next;

		public DLLNode(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	int capacity;
	int currentSize;
	DLLNode head;
	DLLNode tail;
	HashMap<Integer, DLLNode> hm = new HashMap<Integer, DLLNode>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        currentSize = 0;
    }
    
    public int get(int key) {
        if (hm.containsKey(key)) {
        	DLLNode existingNode = hm.get(key);
        	remove(existingNode);
        	insertAtHead(existingNode);

        	return existingNode.value;
        }
        else {
        	return -1;
        }
    }
    
    public void set(int key, int value) {
        if (hm.containsKey(key)) {
        	DLLNode existingNode = hm.get(key);
        	existingNode.value = value;
        	remove(existingNode);
        	insertAtHead(existingNode);
        	// no need to update hm since DLLNode object address is not changed, only pointers pointing to it changed or its contents (prev, next) get updated
        }
        else {
        	DLLNode newNode = new DLLNode(key, value);
        	if (currentSize >= capacity) {
        		// remove tail from hm
        		hm.remove(tail.key);
        		// remove tail from DLLNode
        		tail = tail.prev;
        		if (tail != null) {
        			tail.next = null;
        		}
        		
        		// add new to DLLNode
        		insertAtHead(newNode);
        		// add new to hm
        		hm.put(key, newNode);
        	}
        	else {
        		// add new to DLLNode
        		insertAtHead(newNode);
        		// add new to hm
        		hm.put(key, newNode);

        		currentSize++;
        	}
        }
    }

    public void remove(DLLNode node) {
    	DLLNode prev = node.prev;
    	DLLNode next = node.next;
    	// update next
    	if (prev != null) {
    		prev.next = next;
    	}
    	else {
    		head = next;
    	}
    	// update prev
    	if (next != null) {
    		next.prev = prev;
    	}
    	else {
    		tail = prev;
    	}
    }

    public void insertAtHead(DLLNode node) {
    	node.next = head;
    	node.prev = null;
    	if (head != null) {
    		head.prev = node;
    	}
    	head = node;

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
//         }
//         else {
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

import java.util.*;

public class LRUCache {
	private HashMap<Integer, DoubleLinkedListNode> map = new HashMap<Integer, DoubleLinkedListNode>();
	private DoubleLinkedListNode head;
	private DoubleLinkedListNode end;
	private int capacity;
	private int len;
 
	public LRUCache(int capacity) {
		this.capacity = capacity;
		len = 0;
	}
 
	public int get(int key) {
		if (map.containsKey(key)) {
			DoubleLinkedListNode latest = map.get(key);
			removeNode(latest);
			setHead(latest);

			return latest.value;
		} 
		else {
			return -1;
		}
	}
 
	public void set(int key, int value) {
		if (map.containsKey(key)) {
			DoubleLinkedListNode oldNode = map.get(key);
			oldNode.value = value;
			removeNode(oldNode);
			setHead(oldNode);
		} 
		else {
			DoubleLinkedListNode newNode = new DoubleLinkedListNode(key, value);
			if (len < capacity) {
				map.put(key, newNode);
				setHead(newNode);
				len++;
			} 
			else {
				// remove from hashmap
				map.remove(end.key);
				// remove from DLL
				end = end.pre;
				if (end != null) {
					end.next = null;
				}
 
				setHead(newNode);
				map.put(key, newNode);
			}
		}
	}

	public void removeNode(DoubleLinkedListNode node) {
		DoubleLinkedListNode cur = node;
		DoubleLinkedListNode pre = cur.pre;
		DoubleLinkedListNode post = cur.next;
 
		if (pre != null) {
			pre.next = post;
		} 
		else {
			head = post;
		}
 
		if (post != null) {
			post.pre = pre;
		} 
		else {
			end = pre;
		}
	}
 
	public void setHead(DoubleLinkedListNode node) {
		node.next = head;
		node.pre = null;
		if (head != null) {
			head.pre = node;
		}
		head = node;

		if (end == null) {
			end = node;
		}
	}

	public static void main(String[] args) {
		LRUCache lru = new LRUCache(2);
		System.out.println(lru.get(2));

		lru.set(1, 10);
		lru.set(2, 20);
		System.out.println(lru.get(1));

		lru.set(3, 30);
		System.out.println(lru.get(2));
	}
}
 
class DoubleLinkedListNode {
	public int key;
	public int value;
	public DoubleLinkedListNode pre;
	public DoubleLinkedListNode next;
 
	public DoubleLinkedListNode(int key, int value) {
		this.key = key;
		this.value = value;
	}
}


// public class LRUCache {
//     private int capacity;
//     private Map<Integer, Entry> nodes;
//     private int currentSize;
//     private Entry first;
//     private Entry last;
    
//     public LRUCache(int capacity) {
//         this.capacity = capacity;
//         currentSize = 0;
//         nodes = new HashMap<Integer, Entry>();
//     }
    
//     public int get(int key) {
//         Entry node = nodes.get(key);
//         if (node != null){
//             moveToHead(node);
//             return node.value;
//         } else {
//             return -1;
//         }
//     }
    
//     public void set(int key, int value) {
//         Entry node = nodes.get(key);
//         if(node == null){
//             if(currentSize >= capacity){
//                 nodes.remove(last.key);
//                 removeLast();
//             } else {
//                 currentSize ++;
//             }
//             node = new Entry();
//         }
        
//         if(currentSize == 1){
//             first = node;
//             last = node;
//         }
        
//         node.key = key;
//         node.value = value;
//         moveToHead(node);
//         nodes.put(key, node);
//     }
    
//     private void moveToHead(Entry node){
//         if(node == first)
//             return;
        
//         // delete current node from doubly linked list
//         if(node.pre != null){
//             node.pre.next = node.next;
//         }
//         if(node.next != null){
//             node.next.pre = node.pre;
//         }
        
//         if(last == node){
//             last = node.pre;
//         }
        
//         if(first != null){
//             node.next = first;
//             first.pre = node;
//         }
//         first = node;
//         node.pre = null;
        
//     }
    
//     private void removeLast(){
//         if(last != null){
//             if(last.pre != null){
//                 last.pre.next = null;
//             } else {
//                 first = null;
//             }
//             last = last.pre;
//         }
//     }

//     public static void main(String[] args) {
// 		LRUCache lru = new LRUCache(2);
// 		System.out.println(lru.get(2));

// 		lru.set(1, 10);
// 		lru.set(2, 20);
// 		System.out.println(lru.get(1));

// 		lru.set(3, 30);
// 		System.out.println(lru.get(2));
// 		System.out.println(lru.get(1));
// 		System.out.println(lru.get(3));

// 	}
// }

// class Entry{
//     Entry pre;
//     Entry next;
//     int key;
//     int value;
// }
