class NetworkDelayTime {
	public int networkDelayTime(int[][] times, int N, int K) {
		Map<Integer, List<Time>> hm = new HashMap<>();

		for (int[] time : times) {
			int u = time[0];
			int v = time[1];
			int w = time[2];

			if (hm.containsKey(u)) {
				hm.get(u).add(new Time(u, v, w));
			} else {
				List<Time> list = new ArrayList<Time>();
				list.add(new Time(u, v, w));
				hm.put(u, list);
			}
		}

		List<Time> nextNodes = hm.get(K);
		for (Time node : nextNodes) {
			dfs(node, )
		}
	}
}

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