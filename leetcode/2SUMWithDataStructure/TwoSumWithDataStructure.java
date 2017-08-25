/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,

add(1); 
add(3); 
add(5);
find(4) -> true
find(7) -> false


idea:
not much to say, use hashmap as the data structure
key is the inserted value
value is the occurrence of the key

note: edge case
3 + 3 = 6, make sure there are >= two 3's
if there is only one 3, continue
*/


import java.util.*;

public class TwoSumWithDataStructure {
	HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		TwoSumWithDataStructure eg = new TwoSumWithDataStructure();
		eg.add(1);
		eg.add(3);
		eg.add(5);

		System.out.println("find(4) -> " + eg.find(4));
		System.out.println("find(7) -> " + eg.find(7));
	}

	public void add(int number) {
		if (hm.containsKey(number)) {
			hm.put(number, hm.get(number) + 1);
		} else {
			hm.put(number, 1);
		}
	}
 
	public boolean find(int value) {
		for (int one : hm.keySet()) {
			int another = value - one;
			if (hm.containsKey(another)) {
				if (one == another && hm.get(another) == 1) {
					continue;
				}
				return true;
			}
		}
		return false;
	}
}