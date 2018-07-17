/*
Given two 1d vectors, implement an iterator to return their elements alternately.
Example:
Input:
v1 = [1,2]
v2 = [3,4,5,6] 
Output: [1,3,2,4,5,6]

Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic".
For example:
Input:
[1,2,3]
[4,5,6,7]
[8,9]
Output: [1,4,8,2,5,9,3,6,7].

idea:
取两个list长度最大值 
https://segmentfault.com/a/1190000003786218
*/

public class ZigzagIterator {
	int idx;
	List<Integer> list;

	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
		idx = 0;

		list = new ArrayList<>();
		int l1 = v1.size();
		int l2 = v2.size();
		int n = Math.max(l1, l2);

		for (int i = 0; i < n; i++) {
			if (i < l1) {
				list.add(v1.get(i));
			}
			if (i < l2) {
				list.add(v2.get(i));
			}
		}
	}

	public int next() {
		return list.get(idx++);
	}

	public boolean hasNext() {
		return idx < list.size();
	}

    // follow up
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */