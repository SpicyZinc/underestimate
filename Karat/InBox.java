/*
# Conv 1
# Conv 2
# Conv 3
# Conv 4 
------------Conv4 -> [1, 5, 10, etc]

receive(4, 15)

list()
# Conv 4
# Conv 1
# Conv 2
# Conv 3

receive(2, 16)

list():
# 2, 4, 1, 3

receive(4, 16)

remove(1)

list():
# 4, 2, 3

class Inbox() {
	receive(convId, msgId)
	list()
	get_messages(convId) -> get_messages(4) -> [15, 16]
	remove(convId)
}

idea:
LRU variation
use list
*/

import java.util.*;


class Conversation {
    int id;
    List<Integer> messages;

    Conversation prev;
    Conversation next;
    
    public Conversation(int id) {
        this.id = id;
        this.messages = new ArrayList<>();

        this.prev = null;
        this.next = null;
    }
}

public class InBox {
	public static void main(String[] args) {
		Conversation conv1 = new Conversation(1);
		Conversation conv2 = new Conversation(2);
		Conversation conv3 = new Conversation(3);
		Conversation conv4 = new Conversation(4);

		conv4.messages.add(1);
		conv4.messages.add(5);
		conv4.messages.add(10);

		InBox eg = new InBox();
		eg.list.add(conv1);
		eg.list.add(conv2);
		eg.list.add(conv3);
		eg.list.add(conv4);

		eg.hm.put(1, conv1);
		eg.hm.put(2, conv2);
		eg.hm.put(3, conv3);
		eg.hm.put(4, conv4);

		eg.receive(4, 15);
		List<Integer> result = eg.list();
		System.out.println(result); // 4 1 2 3

		eg.receive(2, 16);
		result = eg.list();
		System.out.println(result); // 2 4 1 3

		eg.receive(4, 16);
		eg.remove(1);
		result = eg.list();

		System.out.println(result); // 4 2 3
	}

	Map<Integer, Conversation> hm = new HashMap<>();
	List<Conversation> list = new ArrayList<>();

    public void receive(int convId, int msgId) {
        if (hm.containsKey(convId)) {
            Conversation conv = hm.get(convId);
            // update hashmap
            conv.messages.add(msgId);
            // top of list
            list.remove(conv);

            // add before the head;
	        list.add(0, conv);
        } else {
            Conversation conv = new Conversation(convId);
            conv.messages.add(msgId);
            hm.put(convId, conv);
	        
	        // add before the head;
	        list.add(0, conv);
        }
    }
    
    public List<Integer> list() {
        List<Integer> result = new ArrayList<>();
        
        for (Conversation conv : list) {
            result.add(conv.id);
        }
        
        return result;
    }
    

    public void remove(int convId) {
        if (!hm.containsKey(convId)) {
            return;
        }
        
        Conversation toRemove = hm.get(convId);
        list.remove(toRemove);
        hm.remove(convId);
    }

      
    public List<Integer> getMessages(int convId) {
        List<Integer> result = new ArrayList<>();
        if (!hm.containsKey(convId)) {
            return result;
        }
         
        return hm.get(convId).messages;
    }
}