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
   A      B      C		D      E
   |    / |    / |    / |    / |
   |   /  |   /  |   /  |  	/  |
   |  /   |  /   |  /   |  /   |
   A'	  B'     C'     D'     E'
*/

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
}

public class CopyListWithRandomPointer {
	// method 1, hashmap passed test 
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }
        
        Map<RandomListNode, RandomListNode> hm = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode curr = head;
        RandomListNode copyDummy = new RandomListNode(0);
        RandomListNode copyCurr = copyDummy;

        while (curr != null) {
            RandomListNode newNode = new RandomListNode(curr.label);
            hm.put(curr, newNode);
            copyCurr.next = newNode;

            copyCurr = copyCurr.next;
            curr = curr.next;
        }
        
        curr = head;
        copyCurr = copyDummy.next;
        while (curr != null) {
            RandomListNode copyRandom = hm.get(curr.random);
            copyCurr.random = copyRandom;
            
            curr = curr.next;
            copyCurr = copyCurr.next;
        }
        
        return copyDummy.next;
    }
    // method 2
    public RandomListNode copyRandomList(RandomListNode head) {
    	if (head == null) {
    		return null;
    	}
    	RandomListNode copyHead = null;  
        RandomListNode copyCur = null;  
        RandomListNode cur = head; 
    	// insert copy
    	while (cur != null) {  
            copyCur = new RandomListNode(cur.label);  
            if (copyHead == null) {  
                copyHead = copyCur;  
            }  
            copyCur.next = cur.next;  
            cur.next = copyCur;  
            cur = cur.next.next;  
        }  
    	// copy random
    	cur = head;  
        copyCur = copyHead;  
        while (cur != null) {  
            if (cur.random != null) {  
                copyCur.random = cur.random.next;  
            }  
            cur = cur.next.next;  
            if (copyCur.next != null) {  
                copyCur = copyCur.next.next;  
            }  
        }
		// recover the origin, and extract the copied
		cur = head;  
        copyCur = copyHead;  
        while (cur != null) {  
            cur.next = cur.next.next;  
            if (copyCur.next != null) {  
                copyCur.next = copyCur.next.next;  
            }  
            cur = cur.next;  
            copyCur = copyCur.next;  
        }

        return copyHead;
    }
}