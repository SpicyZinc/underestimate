/*
You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.

Return any valid arrangement of pairs.

Note: The inputs will be generated such that there exists a valid arrangement of pairs.

Example 1:
Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
Output: [[11,9],[9,4],[4,5],[5,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 9 == 9 = start1 
end1 = 4 == 4 = start2
end2 = 5 == 5 = start3

Example 2:
Input: pairs = [[1,3],[3,2],[2,1]]
Output: [[1,3],[3,2],[2,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 3 == 3 = start1
end1 = 2 == 2 = start2
The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.

Example 3:
Input: pairs = [[1,2],[1,3],[2,1]]
Output: [[1,2],[2,1],[1,3]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 2 == 2 = start1
end1 = 1 == 1 = start2

Constraints:
1 <= pairs.length <= 105
pairs[i].length == 2
0 <= starti, endi <= 109
starti != endi
No two pairs are exactly the same.
There exists a valid arrangement of pairs.

idea:
very similar to destination JFK ReconstructItinerary

similar to graph
course schedule II
alien dictionary
*/

class ValidArrangementOfPairs {
    public int[][] validArrangement(int[][] pairs) {
        Map<Integer, List<Integer>> hm = new HashMap<>();
        Map<Integer, Integer> degree = new HashMap<>();
        
        int n = pairs.length;
        for (int i = 0; i < n; i++) {
            int[] pair = pairs[i];
            int from = pair[0];
            int to = pair[1];

            hm.computeIfAbsent(from, x -> new ArrayList<>()).add(to);
            degree.put(from, degree.getOrDefault(from, 0) - 1);
            degree.put(to, degree.getOrDefault(to, 0) + 1);
        }

        // find start point which is only 1 degree, in our case, -1
        int start = -1;
        for (int key : degree.keySet()) {
            if (degree.get(key) == -1) {
                start = key;
                break;
            }
        }
        // if not found , just pick the 1st one
        if (start == -1) {
            start = pairs[0][0];
        }

        List<Integer> path = new ArrayList<>();
        dfs(hm, degree, start, path);
        int[][] result = new int[n][2];
        for (int i = 0; i < path.size() - 1; i++){
            result[i][0] = path.get(i);
            result[i][1] = path.get(i + 1);
        }

        return result;
    }

    public void dfs(Map<Integer, List<Integer>> hm, Map<Integer, Integer> degree, int start, List<Integer> path) {
        List<Integer> neighbors = hm.get(start);
        while (neighbors != null && !neighbors.isEmpty()) {
            int next = neighbors.remove(0);
            dfs(hm, degree, next, path);
        }
        path.add(0, start);
    }
}
