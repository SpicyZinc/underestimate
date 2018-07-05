/*
Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.
For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar,
but "star" is not similar to "tars", "rats", or "arts".
Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.
Notice that "tars" and "arts" are in the same group even though they are not similar.
Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?

Example 1:
Input: ["tars","rats","arts","star"]
Output: 2

Note:
A.length <= 2000
A[i].length <= 1000
A.length * A[i].length <= 20000
All words in A consist of lowercase letters only.
All words in A have the same length and are anagrams of each other.
The judging time limit has been increased for this question.

idea:
quintessential problem
use visited to mark all connected elements
in dfs() check if visited, if, skip
so all not visited 说明被以前某个起始点通过dfs都visit了
所以能dfs 几次就是几个group

*/

class SimilarStringGroups {
    public int numSimilarGroups(String[] A) {
        int n = A.length;
        List<List<Integer>> group = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            group.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(A[i], A[j])) {
                    group.get(i).add(j);   
                    group.get(j).add(i);   
                }
            }
        }

        int cnt = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(group, visited, -1, i);
                cnt++;
            }
        }

        return cnt;
    }

    public void dfs(List<List<Integer>> group, boolean[] visited, int u, int v) {
        if (visited[v]) {
            return;
        }

        visited[v] = true;
        for (int next : group.get(v)) {
            // u - v no need to visit v - u
            if (next != u) {
                dfs(group, visited, v, next);
            }
        }
    }

    // almost the same as meta string, buddy string
    public boolean isSimilar(String word1, String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); ++i)
            if (word1.charAt(i) != word2.charAt(i))
                diff++;
        return diff <= 2;
    }
}