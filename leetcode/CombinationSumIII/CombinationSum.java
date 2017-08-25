/*
Find all possible combinations of k numbers that add up to a number n,
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Example 1:
Input: k = 3, n = 7
Output: [[1,2,4]]

Example 2:
Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]

idea:
a typical DFS,
something to note: each recursion when found a satisfied temp, create a new ArrayList<Integer>();
*/

public class CombinationSum {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer>temp = new ArrayList<Integer>();
        dfs(res, 1, 0, k, n, temp);
        return res;
    }
    
    public void dfs(List<List<Integer>> res, int cur, int sum, int k, int n, List<Integer> temp) {
        if (sum > n || temp.size() > k) {
            return;
        }
        if (temp.size() == k && sum == n) {
            res.add(new ArrayList<Integer>(temp));
        }
        else {
            for (int i = cur; i <= 9; i++) {
                temp.add(i);
                dfs(res, i + 1, sum + i, k, n, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
