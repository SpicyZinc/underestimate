/*
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements.
The probability of each element being returned is linearly related to the number of same value the collection contains.

idea:
ArrayList to keep all elements
HashMap<Integer, HashSet<Integer>> hm, duplicate number corresponds to a list of positions

note: remove last element is easy to deal with
*/

public class RandomizedCollection {
    List<Integer> list;
    Map<Integer, HashSet<Integer>> hm;
    Random r;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        list = new ArrayList<Integer>();
        hm = new HashMap<Integer, HashSet<Integer>>();
        r = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        HashSet<Integer> indexes = hm.get(val);
        if (indexes == null) {
            indexes = new HashSet<Integer>();
            hm.put(val, indexes);
        }
        list.add(val);
        indexes.add(list.size() - 1);

        return indexes.size() == 1;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        HashSet<Integer> indexes = hm.get(val);
        if (indexes == null) {
            return false;
        }
        else {
            int index = indexes.iterator().next();
            // remove from hashset
            indexes.remove(index);
            if (index < list.size() - 1) {
                // remove from list by setting in the list
                int last = list.get(list.size() - 1);
                list.set(index, last);
                // deal with victim last
                // last leaves old position and goes to the new position
                HashSet<Integer> victim = hm.get(last);
                victim.remove(list.size() - 1);
                victim.add(index);
            }
            // remove victim at last minute
            list.remove(list.size() - 1);
            if (indexes.size() == 0) {
                hm.remove(val);
            }
            else {
                hm.put(val, indexes);    
            }
            return true;
        }
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int randomIndex = r.nextInt(list.size());
        return list.get(randomIndex);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */