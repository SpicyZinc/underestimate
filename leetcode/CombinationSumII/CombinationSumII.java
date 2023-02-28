/*
Given candidate set 10,1,2,7,6,1,5 and target 8,
A solution set is:
[1, 7]
[1, 2, 5]
[2, 6]
[1, 1, 6]
there are duplicate elements in the array

idea:
1. DFS
http://blog.csdn.net/linhuanmars/article/details/20828631
typical DFS search
something to note: 
in for loop, 'i' steps forward not 'start' steps forward

2. backtrack
http://gongxuns.blogspot.com/2012/12/leetcode-combination-sum-ii.html
*/
import java.util.*;
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        if (num == null || num.length == 0) {
            return ret;
        }
        if (target == 0) {
            return ret;
        }
        
        Arrays.sort(num);
        helper(num, target, 0, item, ret);
        
        return ret;
    }
    
    public void helper(int[] num, int target, int start, List<Integer> item, List<List<Integer>> ret) {
        if (target < 0) {
            return ;
        }
        if (target == 0) {
            ret.add(new ArrayList<>(item));
            return ;
        }
        for (int i = start; i < num.length; i++) {
            if (i > start && num[i] == num[i - 1]) {
                continue;
            }
            item.add(num[i]);
            helper(num, target - num[i], start + 1, item, ret) ;
            item.remove(item.size() - 1);
        }
    }
    // method 2
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        Arrays.sort(num);
 
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int[] backtrack = new int[num.length + 1];
 
        backtrack[0] = -1;
        solve(num, 0, target, backtrack, result, 0);
        return result;
    }
 
    public void solve(int[] num, int sum, int target, int[] backtrack, ArrayList<ArrayList<Integer>> result, int n){
        // backtrack records the indexes in num array
        // what is my next number's index? start trying from backtrack[n] + 1 to num's end
        if (target == sum) {
            //add everything in backtrack to result
            ArrayList<Integer> ret = new ArrayList<Integer>();
            for (int i = 1; i <= n; i++) {
                ret.add(num[backtrack[i]]);
            }
            result.add(ret);
        }
 
        if (target < sum) {
            return;
        }
        for (int i = backtrack[n] + 1; i < num.length; i++) {
            backtrack[n+1] = i;
            solve(num, sum+num[i], target, backtrack, result, n+1);
 
            while (i+1 < num.length && num[i+1] == num[i]) {
                i++;
            }
        }
    }
}
