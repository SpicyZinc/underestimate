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
This question is basically a generalized coin change problem.
very typical and classical

note: remaining, pos, stop case

https://github.com/yuanx/leetcode/blob/master/CombinationSum.java
*/

import java.util.*;

public class CombinationSum {
    public static void main (String[] args) {
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;
        CombinationSum eg = new CombinationSum();
        List<List<Integer>> result = eg.combinationSum(candidates, target);
        System.out.println(result.size() + " combinations:");
        for (int i=0; i<result.size(); i++) {
            List<Integer> tmp = result.get(i);
            for (int j = 0; j < tmp.size(); j++) {
                System.out.print(tmp.get(j) + "  ");
            }
            System.out.print("\n");
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, new ArrayList<Integer>(), result);
        return result;
    }
    
    private void dfs(int[] candidates, int remaining, int pos, List<Integer> path, List<List<Integer>> result) {
        if (remaining < 0) {
            return;
        }

        if (remaining == 0) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = pos; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(candidates, remaining - candidates[i], i, path, result);
            path.remove(path.size() - 1);
        }
    }
}