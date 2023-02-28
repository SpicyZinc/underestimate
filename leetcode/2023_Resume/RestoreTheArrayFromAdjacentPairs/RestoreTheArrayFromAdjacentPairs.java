/*
There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.
You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.

It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
Return the original array nums. If there are multiple solutions, return any of them.

Example 1:
Input: adjacentPairs = [[2,1],[3,4],[3,2]]
Output: [1,2,3,4]
Explanation: This array has all its adjacent pairs in adjacentPairs.
Notice that adjacentPairs[i] may not be in left-to-right order.

Example 2:
Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
Output: [-2,4,1,-3]
Explanation: There can be negative numbers.
Another solution is [-3,1,4,-2], which would also be accepted.

Example 3:
Input: adjacentPairs = [[100000,-100000]]
Output: [100000,-100000]
 

Constraints:
nums.length == n
adjacentPairs.length == n - 1
adjacentPairs[i].length == 2
2 <= n <= 10^5
-10^5 <= nums[i], ui, vi <= 10^5
There exists some nums that has adjacentPairs as its pairs.

idea:
dfs or bfs
but have to find the head or tail first
*/


class RestoreTheArrayFromAdjacentPairs {
    // Tue Feb 21 01:46:54 2023
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] pair : adjacentPairs) { // Loop through the input and build the corresponding graph. 
            adj.computeIfAbsent(pair[0], l -> new ArrayList<>()).add(pair[1]);
            adj.computeIfAbsent(pair[1], r -> new ArrayList<>()).add(pair[0]);
        }

        // the original array should be of length + 1
        int n = adjacentPairs.length + 1;
        int[] ans = new int[n];

        for (Map.Entry<Integer, List<Integer>> entry : adj.entrySet()) {
            if (entry.getValue().size() == 1) { // locate start or end
                ans[0] = entry.getKey(); // start from that start or end
                break;
            }
        }


        int k = 0;
        Integer prev = null; // prev is initialized as a dummy value

        while (k < n - 1) {
            for (int next : adj.remove(ans[k])) { // locate the corresponding pair.
                if (prev == null || next != prev) { // Not the already found neighbor?
                    prev = ans[k]; // prev pointer move to current element.
                    ans[++k] = next; // save next element.  
                    break;
                }
            }
        }

        return ans;
    }

    // DFS
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < adjacentPairs.length; i++) {
            map.computeIfAbsent(adjacentPairs[i][0], l -> new ArrayList<>()).add(adjacentPairs[i][1]);
            map.computeIfAbsent(adjacentPairs[i][1], r -> new ArrayList<>()).add(adjacentPairs[i][0]);
        }

        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                dfs(entry.getKey(), set, map,list);
                break;
            }
        }

        int[] res = new int[set.size()];
        int i = 0;
        for (Integer v : list) {
            res[i++] = v;
        }

        return res;
    }

    private void dfs(Integer key, Set<Integer> set, Map<Integer, List<Integer>> map,List<Integer> list) {
        if (set.contains(key)) {
            return;
        }
        set.add(key);
        list.add(key);
        for (Integer v : map.get(key)) {
            dfs(v, set, map,list);
        }
    }
}
