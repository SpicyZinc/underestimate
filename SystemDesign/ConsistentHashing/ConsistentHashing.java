// https://blog.csdn.net/jmspan/article/details/51749521
// https://juejin.cn/post/6844904058529005582
// https://ishan-aggarwal.medium.com/consistent-hashing-an-overview-and-implementation-in-java-6b47c718558a


import java.util.*;

class ConsistentHashing {
	private TreeMap<Integer, Integer> tm = new TreeMap<>();

	int[] nums;
	int k;

	int size = 0;

	public ConsistentHashing(int n, int k) {
		this.nums = new int[n];
		for (int i = 0; i < n; i++) {
			this.nums[i] = i;
		}
		this.k = k;

		// spread server on ring
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			int j = random.nextInt(i + 1);
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
	}


	public static ConsistentHashing create(int n, int k) {
		return new ConsistentHashing(n, k);
	}

	public static void main(String[] args) {
		ConsistentHashing ch = ConsistentHashing.create(100, 3);
		List<Integer> shardIds = ch.addMachine(1);
		System.out.println(ch.tm);
		System.out.println(shardIds);
		int machineId = ch.getMachineIdByHashCode(4);
		System.out.println(machineId);

		shardIds = ch.addMachine(2);
		System.out.println(ch.tm);
		System.out.println(shardIds);
		machineId = ch.getMachineIdByHashCode(61);
		System.out.println(machineId);

		machineId = ch.getMachineIdByHashCode(91);
		System.out.println(machineId);
	}

	/**
	 * virtual node, replica, a server node has k copies
	 * @param  machineId an integer
	 * @return a list of shard ids
	 */
	public List<Integer> addMachine(int machineId) {
		List<Integer> shardIds = new ArrayList<>();
		int n = this.nums.length;
		for (int i = 0; i < this.k; i++) {
			int shardId = this.nums[size % n];
			// suppose 3 machines, 3 * k = 3 * 3 假设三个副本 远远小于 n
			size++;
			shardIds.add(shardId);

			this.tm.put(shardId, machineId);
		}

		return shardIds;
	}

	/**
	 * getMachineIdByHashCode
	 * @param  hashcode a hashed value of 存储的对象
	 * @return a machine id
	 */
	public int getMachineIdByHashCode(int hashcode) {
		if (tm.isEmpty()) {
			return 0;
		}
		// 找比hashcode大一点的, 应该是clockwise
		Integer ceilingKey = tm.ceilingKey(hashcode);
		System.out.println("hased value == " + hashcode + " ceilingKey == " + ceilingKey + " ");
		if (ceilingKey != null) {
			return tm.get(ceilingKey);
		}
		return tm.get(tm.firstKey());
	}
}
