/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n. 

Example
Given n = 4 and k = 2, a solution is:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4]
]

idea:
http://blog.csdn.net/u010500263/article/details/18435495
i = value
i + 1 passed to recursive dfs() to avoid duplicate e.g. [1, 1], [2, 2]
i + 1 is value <= n

iteration:
http://gongxuns.blogspot.com/2012/12/leetcodecombinations.html
*/
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(n, k, 1, new ArrayList<Integer>(), result);
        
        return result;
    }
    
    public void dfs(int n, int k, int val, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == k) {
            result.add(new ArrayList<Integer>(path));

            return;
        }

        for (int i = val; i <= n; i++) {
            path.add(i);
            dfs(n, k, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}