/*
There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
Now given all the cities and fights, together with starting city src and the destination dst,
your task is to find the cheapest price from src to dst with up to k stops.

If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:

The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.

Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:

The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.

Note:
The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.

idea:
Dijkstra's
first build graph

*/

class CheapestFlightsWithinKStops {
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		// build graph first
		int[][] graph = new int[n][n];
		for (int[] flight : flights) {
			graph[flight[0]][flight[1]] = flight[2];
		}
		// Dijkstra's 的精髓就是 保留 最优的解
		// newCost == shorter distance
		Map<String, Integer> best = new HashMap<>();

		// [cost, stops, src]
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
		pq.offer(new int[] {0, 0, src});

		while (!pq.isEmpty()) {
			int[] nearest = pq.poll();
			int cost = nearest[0];
			int stops = nearest[1];
			int city = nearest[2];

			if (stops > K + 1 || cost > best.getOrDefault(stops + "-" + city, Integer.MAX_VALUE)) {
				continue;
			}

			if (city == dst) {
				return cost;
			}

			// for each neighbor
			for (int i = 0; i < n; i++) {
				// if there is a flight between these two cities
				if (graph[city][i] > 0) {
					int newCost = cost + graph[city][i];
					String key = (stops + 1) + "-" + i;
					if (newCost < best.getOrDefault(key, Integer.MAX_VALUE)) {
						pq.offer(new int[] {newCost, stops + 1, i});
						best.put(key, newCost);
					}
				}
			}
		}

		return -1;
	}
}