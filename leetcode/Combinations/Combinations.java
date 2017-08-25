/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n. 

idea:
http://blog.csdn.net/u010500263/article/details/18435495
i=index
index+1 passed to recursive dfs() to avoid duplicate e.g. [1, 1], [2, 2]
guarantee that one position chooses index, next position chooses index+1
conforms to definition of combination

iteration:
http://gongxuns.blogspot.com/2012/12/leetcodecombinations.html
*/
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(n, k, 0, new ArrayList<Integer>(), result);
        
        return result;
    }
    private void dfs(int n, int k, int index, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == k) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = index; i < n; i++) {
            path.add(i + 1);
            dfs(n, k, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}