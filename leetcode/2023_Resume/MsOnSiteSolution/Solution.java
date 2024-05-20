import java.util.*;
// put 6
// get 1 -> 1 -> (1st position)
// put 6 -> kick off 2, insert 6 -> 6 1 3 4 5
// get 2 -> null
// put 6 -> kick off 5 -> 7 6 1 3 4



class DLLNode {
    int key;
    int value;
    DLLNode next;
    DLLNode prev;

    public DLLNode(int key, int value) {
        this.key=key;
        this.value=value;
        this.next = null;
        this.prev = null;
    }
}

class LRU {
    int key;
    DLLNode head;
    DLLNode tail;
    Map<Integer, DLLNode> hm;

    int size = 0;
    int capacity = 0;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.hm = new HashMap<>();
    }

    public void print() {
        DLLNode current = head;
        String s = "";
        while (current != null) {
            s += current.value + "->";
            current = current.next;

        
        }

        System.out.println(" list is " + s);
    }

    public int get(int key) {
        if (hm.containsKey(key)) {
            DLLNode node = hm.get(key);
            remove(node);
            insertBeforeHead(node);
            return  node.value;
        }

        return -1;  
    }

    public void put(int key, int value) {
        if (hm.containsKey(key)) {
            DLLNode node = hm.get(key);
            remove(node);
            insertBeforeHead(node);
        } else {
            DLLNode node = new DLLNode(key, value);
            if (size >= capacity) {
        		System.out.println(tail.key + " "  + tail.value);
                hm.remove(tail.key);
                remove(tail);
                for (Map.Entry<Integer, DLLNode> entry : hm.entrySet()) {
                	int key1 = entry.getKey();
                	int value1 = entry.getValue().value;
                	System.out.println("key value " + key1 + " " + value1);
                }
            } else {
                size++;
            }
            insertBeforeHead(node);
            hm.put(key, node);
        }
    }

    public void remove(DLLNode node) {
        DLLNode next = node.next;
        DLLNode prev = node.prev;

        if (prev != null) {
            prev.next = next;
        } else {
            head = next;
        }

        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev;
        }
    }


    public void insertBeforeHead(DLLNode node) {
        node.next = head;
        node.prev = null;
        if (head !=  null) {
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
    }
}


public class Solution {
    public static void main(String [] args) {
        // you can write to stdout for debugging purposes, e.g.
        System.out.println("This is a debug message");

        LRU lru = new LRU(5);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        lru.put(5, 5);
        lru.print();
                System.out.println("====== 5 full");

        int value1 = lru.get(1);
        lru.print();
        System.out.println("====== after get 1");
        // System.out.println("get (1) == " + value1);

        lru.put(6, 6);

        System.out.println("====== after put 6");

        // lru.print();

        // int value2 = lru.get(2);
        // System.out.println("get (2) == " + value2);
        // lru.put(7, 7);
    }
}
