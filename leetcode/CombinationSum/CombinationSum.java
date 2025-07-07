/*
Given a set of candidate numbers (C) and a target number (T), 
find all unique combinations in C where the candidate numbers sums to T. 
The same repeated number may be chosen from C unlimited number of times. 

The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7,
A solution set is:
[7]
[2, 2, 3] 

idea:
https://github.com/yuanx/leetcode/blob/master/CombinationSum.java

This question is basically a generalized coin change problem.
quintessential dfs()
note, remaining, pos, stop case, sort first
*/

import java.util.*;

public class CombinationSum {
    public static void main (String[] args) {
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;
        CombinationSum eg = new CombinationSum();
        List<List<Integer>> result = eg.combinationSum(candidates, target);
        System.out.println(result.size() + " combinations:");
        for (List<Integer> path : result) {
            System.out.println(path);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<Integer>(), result);

        return result;
    }

    private void dfs(int[] candidates, int pos, int remaining, List<Integer> path, List<List<Integer>> result) {
        if (remaining < 0) {
            return;
        }

        if (remaining == 0) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = pos; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(candidates, i, remaining - candidates[i], path, result);
            path.remove(path.size() - 1);
        }
    }
}
