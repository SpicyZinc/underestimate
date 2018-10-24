/*
We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever.
For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T.
Traveling by buses only, what is the least number of buses we must take to reach our destination?
Return -1 if it is not possible.

Example:
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation: 
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

Note:
1 <= routes.length <= 500.
1 <= routes[i].length <= 500.
0 <= routes[i][j] < 10 ^ 6.

idea:
bfs
build each stop where a list of buses could stop
*/
class BusRoutes {
	public int numBusesToDestination(int[][] routes, int S, int T) {
		Set<Integer> visited = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		Map<Integer, List<Integer>> hm = new HashMap<>();

		int result = 0;

		if (S == T) {
			return 0;
		}

		for (int i = 0; i < routes.length; i++) {
			// stops for bus i
			int[] stops = routes[i];
			for (int stop : stops) {
				List<Integer> buses = hm.getOrDefault(stop, new ArrayList<>());
				buses.add(i);
				hm.put(stop, buses);
			}
		}

		queue.offer(S);

		while (!queue.isEmpty()) {
			// take the bus
			result++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int currStop = queue.poll();
				List<Integer> buses = hm.get(currStop);
				for (int bus : buses) {
					if (!visited.contains(bus)) {
						visited.add(bus);
						// current bus all stops
						for (int stop : routes[bus]) {
							if (stop == T) {
								return result;
							}
							queue.offer(stop);
						}
					}
				}
			}
		}

		return -1;
	}
}