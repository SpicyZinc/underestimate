/*
Design a data structure that supports all following operations in average O(1) time.
Note: Duplicate elements are allowed.

RandomizedCollection() Initializes the RandomizedCollection object.

bool insert(int val) Inserts an item val into the multiset (collection) if not present. Returns true if the item was not present, false otherwise.

bool remove(int val) Removes an item val from the multiset (collection) if present. Returns true if the item was present, false otherwise.
Note that if val has multiple occurrences in the multiset (collection) we only remove one of them.

int getRandom() Returns a random element from the current multiset (collection) of elements (it's guaranteed that at least one element exists when this method is called).
The probability of each element being returned is linearly related to the number of same values the multiset (collection) contains.


Example:
// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();

idea:
ArrayList to keep all elements
HashMap<Integer, HashSet<Integer>> hm, duplicate number corresponds to a list of positions

note: last element is easy to deal with, so remove it
leetcode 381
*/

class RandomizedCollection {
    // Mon Jul 15 00:33:31 2019
    List<Integer> list;
    Map<Integer, Set<Integer>> hm;
    Random random;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        list = new ArrayList<>();
        hm = new HashMap<>();
        random = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        // update list
        list.add(val);
        // update hashmap
        hm.computeIfAbsent(val, x -> new HashSet<>()).add(list.size() - 1);
        // 第一次加上之后 positions 个数才为 1 说明 insert successfully
        // add 第二次或更多次数之后 size > 1 以此来表明 早就有 这个 val
        return hm.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (hm.containsKey(val)) {
            Set<Integer> positions = hm.get(val);
            // just a position to remove by iterator()
            int posToRemove = positions.iterator().next();
            // remove from HashSet
            positions.remove(posToRemove);
            // update hashmap
            if (positions.size() == 0) {
                hm.remove(val);
            } else {
                hm.put(val, positions);
            }

            int lastIndex = list.size() - 1;
            if (posToRemove < lastIndex) {
                // remove from list by moving last value to the removed position
                int lastItem = list.get(lastIndex);
                list.set(posToRemove, lastItem);
                // last value leaves old position and goes to the new position
                // update the last
                Set<Integer> lastPositions = hm.get(lastItem);
                // 移走老位置
                lastPositions.remove(lastIndex);
                // 加上新位置
                lastPositions.add(posToRemove);
            }

            // finally, remove last from list
            list.remove(lastIndex);

            return true;
        }

        return false;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */