/*
You have a queue of integers, you need to retrieve the first unique integer in the queue.

Implement the FirstUnique class:

FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
void add(int value) insert value to the queue.
 


Example 1:
Input: 
["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
Output: 
[null,2,null,2,null,3,null,-1]
Explanation: 
FirstUnique firstUnique = new FirstUnique([2,3,5]);
firstUnique.showFirstUnique(); // return 2
firstUnique.add(5);            // the queue is now [2,3,5,5]
firstUnique.showFirstUnique(); // return 2
firstUnique.add(2);            // the queue is now [2,3,5,5,2]
firstUnique.showFirstUnique(); // return 3
firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
firstUnique.showFirstUnique(); // return -1

Example 2:
Input: 
["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
[[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
Output: 
[null,-1,null,null,null,null,null,17]
Explanation: 
FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
firstUnique.showFirstUnique(); // return -1
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
firstUnique.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
firstUnique.showFirstUnique(); // return 17

Example 3:
Input: 
["FirstUnique","showFirstUnique","add","showFirstUnique"]
[[[809]],[],[809],[]]
Output: 
[null,809,null,-1]
Explanation: 
FirstUnique firstUnique = new FirstUnique([809]);
firstUnique.showFirstUnique(); // return 809
firstUnique.add(809);          // the queue is now [809,809]
firstUnique.showFirstUnique(); // return -1

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^8
1 <= value <= 10^8
At most 50000 calls will be made to showFirstUnique and add.

*/

class Item {
    int value;
    int index;
    int count;

    public Item(int value, int index, int count) {
        this.value = value;
        this.index = index;
        this.count = count;
    }
}

class FirstUnique {
    // Thu Apr 27 16:39:05 2023
    Map<Integer, Integer> freq = new HashMap<>();
    Queue<Integer> queue = new LinkedList<>();

    public FirstUnique(int[] nums) {
        for (int num : nums) {
            add(num);
        }
    }

    public int showFirstUnique() {
        // based on queue order, pop up those numbers appearing count > 1
        while (!queue.isEmpty() && freq.get(queue.peek()) > 1) {
            queue.poll();
        }
        // queue guarantee that the top peek
        return queue.isEmpty() ? -1 : queue.peek();
    }

    public void add(int value) {
        freq.put(value, freq.getOrDefault(value, 0) + 1);
        queue.offer(value);
    }

    // My own version, TLE
    Map<Integer, Item> hm;
    int total = 0;

    public FirstUnique(int[] nums) {
        hm = new HashMap<>();
        total = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (hm.containsKey(num)) {
                Item item = hm.get(num);
                item.count += 1;
            } else {
                hm.put(num, new Item(num, i, 1));
            }
        }
    }

    public int showFirstUnique() {
        List<Item> itemsOnce = new ArrayList<>();

        for (Map.Entry<Integer, Item> entry : hm.entrySet()) {
            Item item = entry.getValue();
            if (item.count == 1) {
                itemsOnce.add(item);
            }
        }

        if (itemsOnce.size() > 0) {
            int minIdx = itemsOnce.get(0).index;
            Item min = itemsOnce.get(0);

            for (Item item : itemsOnce) {
                if (minIdx > item.index) {
                    minIdx = item.index;
                    min = item;
                }
            }

            return min.value;
        }

        return -1;
    }
    
    public void add(int value) {
        if (hm.containsKey(value)) {
            Item item = hm.get(value);
            item.count += 1;
        } else {
            hm.put(value, new Item(value,  total - 1, 1));
        }
    }
}