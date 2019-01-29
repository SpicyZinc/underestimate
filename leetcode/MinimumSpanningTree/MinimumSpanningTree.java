/*
Given a list of Connections, which is the Connection class
(the city name at both ends of the edge and a cost between them),
find some edges, connect all the cities and spend the least amount.

Return the connects if can connect all the cities, otherwise return empty list.

Example
Gievn the connections = ["Acity","Bcity",1], ["Acity","Ccity",2], ["Bcity","Ccity",3]
Return ["Acity","Bcity",1], ["Acity","Ccity",2]

note,
Return the connections sorted by the cost, or sorted city1 name if their cost is same,
or sorted city2 if their city1 name is also same.

idea:

*/

class Connection {
	public String city1, city2;
	public int cost;

	public Connection(String city1, String city2, int cost) {
		this.city1 = city1;
		this.city2 = city2;
		this.cost = cost;
	}
}

public class MinimumSpanningTree {
	int size = 0;
	Map<String, Integer> nameToId = new HashMap<>(); 

	public List<Connection> lowestCost(List<Connection> connections) {
		List<Connection> result = new ArrayList<>();

		int n = connections.size();
		int[] f = new int[n * 2];

		for (int i = 0; i < f.length; i++) {
			f[i] = -1;
		}

		// sort
		Collections.sort(connections, new Comparator<Connection>() {
			@Override
			public int compare(Connection a, Connection b) {
				if (a.cost != b.cost) {
					return a.cost - b.cost;
				}
				if (a.city1.equals(b.city1)) {
					return a.city2.compareTo(b.city2);
				} else {
					return a.city1.compareTo(b.city1);
				}
			}
		});

		for (Connection connection : connections) {
			int c1 = mapNameToId(connection.city1);
			int c2 = mapNameToId(connection.city2);

			int fc1 = find(c1, f);
			int fc2 = find(c2, f);

			if (fc1 != fc2) {
				result.add(connection);

				connect(c1, c2, f);
			}
		}

		if (result.size() == size - 1) {
			return result;
		} else {
			return new ArrayList<>();
		}
	}

	public int mapNameToId(String name) {
		if (nameToId.containsKey(name)) {
			return nameToId.get(name);
		} else {
			nameToId.put(name, size++);
			return size - 1;
		}
	}

	public void connect(int a, int b, int[] f) {
		a = find(a, f);
		b = find(b, f);

		if (f[a] < f[b]) {
			f[a] += f[b];
			f[b] = a;
		} else {
			f[b] += f[a];
			f[a] = b;
		}
	}

	public int find(int i, int[] f) {
		if (f[i] < 0) {
			return i;
		}

		f[i] = find(f[i], f);
		return f[i];
	}
}