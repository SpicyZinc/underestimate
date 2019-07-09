/*
All O`one Data Structure
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1.
If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.

idea: 
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

这是个 很好 的数据结构 
可以用来找到 top k frequent items
出现次数 1->2->3->4
有点像 LRU 那个不记录次数
*/

// Sun Jun 30 00:19:51 2019
class Node {
	int val;
	Set<String> keys;
	Node next;
	Node prev;

	public Node(int val) {
		this.val = val;
		this.keys = new LinkedHashSet();
		this.prev = null;
		this.next = null;
	}
}

class AllOne {
	// Initialize your data structure here. 
	Map<String, Node> map;
	Node head;
	Node tail;

	public AllOne() {
		this.map = new HashMap<>();
		this.head = head;
		this.tail = tail;
	}

	public void inc(String key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			// first 无脑 remove it from node.keys
			// 因为key 对应的 value +1 应该向后移一位
			node.keys.remove(key);

			// 如果空 或是 没有这个 value 就要先 创建 and insert
			if (node.next == null || node.next.val != node.val + 1) {
				insertAfterNode(node, node.val + 1);
			}
			// 有更好了 直接加
			node.next.keys.add(key);
			// update map
			map.put(key, node.next);

			// 如果原来就只有一个 node.keys 只有一个 移走了
			if (node.keys.isEmpty()) {
				if (node == head) {
					head = node.next;
				}
				// 把这个node delete
				node.next.prev = node.prev;
				if (node.prev != null) {
					node.prev.next = node.next;
				}
			}
			// update tail
			if (tail == node) {
				tail = node.next;
			}
		} else {
			if (head == null || head.val > 1) {
				Node node = new Node(1);
				insertBeforeHead(node);
			}

			head.keys.add(key);
			map.put(key, head);
		}
	}
	
	/** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
	public void dec(String key) {
		if (!map.containsKey(key)) {
			return;
		}

		Node node = map.get(key);
		// first 无脑 remove it from node.keys
		node.keys.remove(key);
		// 无论怎样key 已经不对应了 先移除 再 put
		map.remove(key);

		// if it's head, update head and tail
		if (node.val == 1) {
			if (node.keys.isEmpty()) {
				if (head == node) {
					head = node.next;
				}
				// val == 1 一定是 head
				if (head != null) {
					head.prev = null;
				}
				if (tail == node) {
					tail = null;
				}
			}
		} else {
			// add new node at prev position
			if (node.prev == null || node.prev.val != node.val - 1) {
				insertBeforeNode(node, node.val - 1);
			}
			// add new key to the newNode or prev node
			node.prev.keys.add(key);
			// update node
			map.put(key, node.prev);
			
			// 根据情况 keys 的个数 看是否 delete this 'node'
			// update tail
			if (node.keys.isEmpty()) {
				if (tail == node) {
					tail = node.prev;
				}
				node.prev.next = node.next;
				if (node.next != null) {
					node.next.prev = node.prev;
				}
			}
			// 如果碰巧 node 原来是 head, 现在少了一个
			// 根据 head 指向最小的 次数 head 应该倒退一位
			// update head
			if (head == node) {
				head = node.prev;
			}
		}
	}

	public String getMaxKey() {
		return tail == null ? "" : tail.keys.iterator().next();
	}
	
	/** Returns one of the keys with Minimal value. */
	public String getMinKey() {
		return head == null ? "" : head.keys.iterator().next();
	}

	public void insertBeforeNode(Node node, int val) {
		Node newNode = new Node(val);
		newNode.next = node;
		newNode.prev = node.prev;
		node.prev = newNode;
		if (newNode.prev != null) {
			newNode.prev.next = newNode;
		}
	}

	public void insertAfterNode(Node node, int val) {
		Node newNode = new Node(val);
		newNode.prev = node;
		newNode.next = node.next;
		node.next = newNode;
		if (newNode.next != null) {
			newNode.next.prev = newNode;
		}
	}

	private void insertBeforeHead(Node node) {
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

	// remove node from Double linked list
	private void remove(Node node) {
		Node prev = node.prev;
		Node next = node.next;
		
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
}



class Node {
	String key;
	int val;
	Node next;
	Node prev;

	Node(String key , int val) {
		this.key = key;
		this.val = val;
		this.next = null;
		this.prev = null;
	}
}

class DoublyLinkedList {
	Node head;
	Node tail;

	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
	}

	public void insertBeforeHead(Node node) {
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

	public void appendAfterTail(Node newNode) {
		if (tail == null) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.prev = tail;
			tail.next = newNode;
			tail = tail.next;
		}
	}

	public void removeFromHead() {
		if (head == null) {
			return;
		} else if (head.next == null && head.prev == null) {
			head = null;
		} else {
			head = head.next;
			head.prev = null;
		}
	}

	public void removeFromTail() {
		if (tail == null) {
			return;
		} else if (tail.next == null && tail.prev == null) {
			tail = null;
			head = null;
		} else {
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

		Node curr = head;
		while (curr.next != null) {
			if (curr.next == node) {
				Node next = node.next;
				curr.next = next;
				next.prev = curr;
				break;
			}
			curr = curr.next;
		}
	}
}

class AllOne {
	// Initialize your data structure here. 
	DoublyLinkedList customList;
	Map<String, Node> map;

	public AllOne() {
		customList = new DoublyLinkedList();
		map = new HashMap<>();
	}
	/** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
	public void inc(String key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			node.val++;
			// Greater than Tail then remove it and append after tail
			if (node.val >= customList.tail.val) {
				customList.remove(node);
				customList.appendAfterTail(node);
			}
		} else {
			// New nodes are inserted before head because they have minimum count
			Node node = new Node(key, 1);
			map.put(key, node);
			customList.insertBeforeHead(node);
		}
	}
	/** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
	public void dec(String key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			if (node.val == 1) {
				map.remove(key);
				customList.remove(node);
			} else {
				// Less than head. Remove it and add it at the back of the head
				node.val--;
				if (node.val <= customList.head.val) {
					customList.remove(node);
					customList.insertBeforeHead(node);
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
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 