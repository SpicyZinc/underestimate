/*
Insert Delete GetRandom O(1)
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.

idea:
Leetcode 380

if the element to remove is NOT the last element in the array,
拿最后一个元素来补缺

ArrayList + HashMap
ArrayList is container for all values
HashMap is for value <-> position in the list

Note: how to remove element from array:
that is BECAUSE remove() Shifts any subsequent elements to the left (subtracts one from their indices).
1. set last element to currently removed element's position
2. remove last element from array

note: DO NOT do this twice for last element, which means
if (pos < list.size() - 1) {}

only using List also works, but time complexity increases
*/

public class RandomizedSet {
    // Sun May 19 03:30:07 2024
    List<Integer> list;
    Map<Integer, Integer> hm;

    public RandomizedSet() {
        this.list = new ArrayList<>();
        this.hm = new HashMap<>();
    }
    
    public boolean insert(int val) {
        if (hm.containsKey(val)) {
            return false;
        } else {
            list.add(val);
            hm.put(val, list.size() - 1);
            return true;
        }
    }
    
    public boolean remove(int val) {
        if (hm.containsKey(val)) {
            int pos = hm.get(val);
            hm.remove(val);

            int last = list.size() - 1;
            int lastVal = list.get(last);

            list.set(pos, lastVal);
            list.remove(last);

            if (pos < last) {
                hm.put(lastVal, pos);
            }

            return true;
        } else {
            return false;
        }
    }
    
    public int getRandom() {
        int size = list.size();
        Random r = new Random();
        int idx = r.nextInt(size);
        return list.get(idx);
    }

    // Tue Jun  8 23:59:01 2021
    // Runtime: 134 ms
    Random r;
    Map<Integer, Integer> hm;
    List<Integer> list;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        r = new Random();
        hm = new HashMap<Integer, Integer>();
        list = new ArrayList<Integer>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (hm.containsKey(val)) {
            return false;
        } else {
            list.add(val);
            hm.put(val, list.size() - 1);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (hm.containsKey(val)) {
            int posToRemove = hm.get(val);
            int lastPosition = list.size() - 1;
            // at first, remove from hashmap
            hm.remove(val);
            // update list
            int lastElement = list.get(lastPosition);
            list.set(posToRemove, lastElement);
            list.remove(lastPosition);
            // update hashmap element's value and position
            if (posToRemove < lastPosition) {
                hm.put(lastElement, posToRemove);
            }

            return true;
        }
        
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int randomIndex = r.nextInt(list.size());
        return list.get(randomIndex);
    }

    // Runtime: 285 ms
    Random r;
    List<Integer> list;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        r = new Random();
        list = new ArrayList<Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (list.contains(val)) {
            return false;
        } else {
            list.add(val);
            return true;
        }
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (list.contains(val)) {
            list.remove(new Integer(val));
            return true;
        } else {
            return false;
        }
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int idx = r.nextInt(list.size());
        return list.get(idx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

