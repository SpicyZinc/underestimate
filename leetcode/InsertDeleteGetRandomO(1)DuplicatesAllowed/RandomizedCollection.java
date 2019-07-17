/*
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.

insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements.
The probability of each element being returned is linearly related to the number of same value the collection contains.


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
		// 加上之后 positions 个数才为 1 说明 insert successfully
		return hm.get(val).size() == 1;
	}
	
	/** Removes a value from the collection. Returns true if the collection contained the specified element. */
	public boolean remove(int val) {
		if (hm.containsKey(val)) {
			Set<Integer> positions = hm.get(val);
			int posToRemove = positions.iterator().next();
			// remove from HashSet
			positions.remove(posToRemove);

			if (posToRemove < list.size() - 1) {
				// remove from list by moving last value at the removed position
				int last = list.get(list.size() - 1);
				list.set(posToRemove, last);
				// last leaves old position and goes to the new position
				// update the last
				Set<Integer> lastPositions = hm.get(last);
				// 加上新位置
				lastPositions.remove(list.size() - 1);
				// 移走老位置
				lastPositions.add(posToRemove);
			}
				
			// remove last from list at final
			list.remove(list.size() - 1);
			if (positions.size() == 0) {
				hm.remove(val);
			} else {
				hm.put(val, positions);
			}

			return true;
		} else {
			return false;
		}
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