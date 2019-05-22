/*
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.

idea:
need to come back

*/

class Time {
	int u;
	int v;
	int w;

	public Time(int u, int v, int w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}
}


class NetworkDelayTime {

	Map<Integer, Integer> dist;

	public int networkDelayTime(int[][] times, int N, int K) {
		Map<Integer, List<Time>> hm = new HashMap<>();

		for (int[] time : times) {
			int u = time[0];
			int v = time[1];
			int w = time[2];

			if (!hm.containsKey(u)) {
				List<Time> list = new ArrayList<Time>();
				hm.put(u, list);	
			}
			hm.get(u).add(new Time(u, v, w));
		}

		// sort based on time taken from u to v
		for (int node : hm.keySet()) {
			Collections.sort(hm.get(node), (a, b) -> a.w - b.w);
		}
		// 由 key 去 list of targets, 最快的在最前面
		

		dist = new HashMap<>();
		// dijkstra
		// initialize
		for (int i = 1; i <= N; i++) {
			dist.put(i, Integer.MAX_VALUE);
		}
		dfs(hm, K, 0);

		int cost = 0;
		for (int val : dist.values()) {
			if (val == Integer.MAX_VALUE) {
				return -1;
			}
			cost = Math.max(cost, val);
		}

		return cost;
	}


	public void dfs(Map<Integer, List<Time>> hm, int startNode, int costTime) {
		if (costTime >= dist.get(startNode)) {
			return ;
		}

		dist.put(startNode, costTime);
		if (hm.containsKey(startNode)) {
			for (Time next : hm.get(startNode)) {
				dfs(hm, next.v, costTime + next.w);
			}
		}
	}

	// BFS, Djikstra
	public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Map<Integer,Integer>> map = new HashMap<>();
        for(int[] time : times){
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }
        
        //distance, node into pq
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> (a[0] - b[0]));
        
        pq.add(new int[]{0, K});
        
        boolean[] visited = new boolean[N+1];
        int res = 0;
        
        while(!pq.isEmpty()){
            int[] cur = pq.remove();
            int curNode = cur[1];
            int curDist = cur[0];
            if(visited[curNode]) continue;
            visited[curNode] = true;
            res = curDist;
            N--;
            if(map.containsKey(curNode)){
                for(int next : map.get(curNode).keySet()){
                    pq.add(new int[]{curDist + map.get(curNode).get(next), next});
                }
            }
        }
        return N == 0 ? res : -1;
            
    }
}

