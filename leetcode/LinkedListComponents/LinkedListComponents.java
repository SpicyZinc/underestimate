/*
We are given head, the head node of a linked list containing unique integer values.
We are also given the list G, a subset of the values in the linked list.
Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.

Example 1:
Input:
head: 0->1->2->3
G = [0, 1, 3]
Output: 2
Explanation: 
0 and 1 are connected, so [0, 1] and [3] are the two connected components.

Example 2:
Input: 
head: 0->1->2->3->4
G = [0, 3, 1, 4]
Output: 2
Explanation: 
0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.

Note:
If N is the length of the linked list given by head, 1 <= N <= 10000.
The value of each node in the linked list will be in the range [0, N - 1].
1 <= G.length <= 10000.
G is a subset of all values in the linked list.

idea: tried to borrow idea from similar string group, but failed, no time to debug
just use group, straight, convert G to hashset
*/
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

class LinkedListComponents {
	public int numComponents(ListNode head, int[] G) {
		if (head == null || G.length == 0) {
			return 0;
		}

		Set<Integer> hs = new HashSet<Integer>();
		for (int i : G) {
			hs.add(i);
		}

		int cnt = 0;
		ListNode current = head;
		while (current != null) {
			if (hs.contains(current.val) && (current.next == null || !hs.contains(current.next.val))) {
				cnt++;
			}
			current = current.next;
		}

		return cnt;
	}

	public int numComponents(ListNode head, int[] G) {
		if (head == null || G.length == 0) {
			return 0;
		}

		ListNode current = head;
		int cnt = 0;

		Map<Integer, List<Integer>> graph = new HashMap<>();
		while (current != null && current.next != null) {
			graph.put(current.val, new ArrayList<Integer>(current.next.val));
			graph.put(current.next.val, new ArrayList<Integer>(current.val));

			current = current.next;
			cnt++;
		}

		int n = G.length;
		Map<Integer, Boolean> visited = new HashMap<>();
		for (int val : G) {
			visited.put(val, false);
		}

		int result = 0;
		for (int i = 0; i < n; i++) {
			if (!visited.get(G[i])) {
				dfs(graph, visited, G[i]);
				result++;
			}
		}

		return result;
	}

	public void dfs(Map<Integer, List<Integer>> graph, Map<Integer, Boolean> visited, int val) {
		if (!graph.containsKey(val)) {
			return;
		}
		if (visited.get(val)) {
			return;
		}

		visited.put(val, true);
		for (int next : graph.get(val)) {
			dfs(graph, visited, next);
		}
	}
}