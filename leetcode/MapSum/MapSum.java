/*
Implement a MapSum class with insert, and sum methods.

For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value.
If the key already existed, then the original key-value pair will be overridden to the new one.

For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.

Example 1:
Input: insert("apple", 3), Output: Null
Input: sum("ap"), Output: 3
Input: insert("app", 2), Output: Null
Input: sum("ap"), Output: 5

idea:
directly use hashmap<String, Integer>
*/

class MapSum {
	Map<String, Integer> hm;
	/** Initialize your data structure here. */
	public MapSum() {
		hm = new HashMap<String, Integer>();
	}

	public void insert(String key, int val) {
		hm.put(key, val);
	}

	public int sum(String prefix) {
		int sum = 0;
		for (Map.Entry<String, Integer> entry : hm.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			if (key.startsWith(prefix)) {
				sum += value;
			}
		}
		return sum;
	}
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */