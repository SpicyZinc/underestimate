class ConsistentHashingByArray {
    /*
     * @param n: a positive integer
     * @param k: a positive integer
     * @return: a ConsistentHashingByArray object
     */
    private int[] shardId2Machine;
    private int k;

    public static ConsistentHashingByArray create(int n, int k) {
        // Write your code here
        ConsistentHashingByArray solution = new ConsistentHashingByArray();
        solution.shardId2Machine = new int[n];
        solution.k = k;
        return solution;
    }

    /*
     * @param machine_id: An integer
     * @return: a list of shard ids
     */
    public List<Integer> addMachine(int machine_id) {
        // write your code here
        int n = this.shardId2Machine.length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < this.k; i++) {
            int shardId = new Random().nextInt(n);
            while (this.shardId2Machine[shardId] != 0) {
                shardId++;
                shardId %= n;
            }
            shardId2Machine[shardId] = machine_id;
            result.add(shardId);
        }
        return result;
    }

    /*
     * @param hashcode: An integer
     * @return: A machine id
     */
    public int getMachineIdByHashCode(int hashcode) {
        // write your code here
        int n = this.shardId2Machine.length;
        // found closest machine
        while (this.shardId2Machine[hashcode] == 0) {
            hashcode++;
            hashcode %= n;
        }
        return this.shardId2Machine[hashcode];
    }
}