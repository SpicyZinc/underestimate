/*
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

idea:
1. best method, typical dfs()

2. recursion
http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
for loop(n), each element swap itself with the rest of the array including itself
keep swapping but go to next element by i+1
swap back after one operation in the loop(n)

3. iteration (need to reconsider)
http://www.programcreek.com/2013/02/leetcode-permutations-java/
Loop through the array, in each iteration, a new number is added to different locations of results of previous iteration. 
Start from an empty List.
*/

import java.util.*;

public class Permutations {
    public static void main(String[] args) {
        Permutations eg = new Permutations();
        int[] nums = new int[] {1, 2, 3, 1};
        List<List<Integer>> result = eg.permute(nums);

        for (List<Integer> path : result) {
            System.out.println(path.toString());
        }
    }
    // 2025
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        dfs(nums, 0, result);
        return result;
    }

    public void dfs(int[] nums, int pos, List<List<Integer>> result) {
        if (pos == nums.length - 1) {
            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                path.add(num);
            }
            result.add(path);
            return;
        }

        for (int i = pos; i < nums.length; i++) {
            swap(nums, pos, i);
            dfs(nums, pos + 1, result);
            swap(nums, pos, i);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // correct method
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        dfs(nums, 0, result);
        
        return result;
    }
    // 是通过 swap() 来实现 permute 不断地 (for loop) 把 所有数字 换到某个位置 对剩余的数字继续做同样的事
    public void dfs(int[] nums, int pos, List<List<Integer>> result) {
        int size = nums.length;

        if (pos == size - 1) {
            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                path.add(num);
            }
            result.add(path);

            return;
        }

        for (int i = pos; i < size; i++) {
            swap(nums, pos, i);
            dfs(nums, pos + 1, result);
            swap(nums, pos, i);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // list.contains(), only applies to unique array to get permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), nums);

        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> path, int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // note how to avoid duplicate elements
            if (path.contains(nums[i])) {
                continue;
            }

            path.add(nums[i]);
            dfs(result, path, nums);
            path.remove(path.size() - 1);
        }
    }

    // method 3, iteration, not follow yet
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // start from an empty list
        result.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            // list of list in current iteration of the array nums
            List<List<Integer>> current = new ArrayList<>();
     
            for (List<Integer> path : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < path.size() + 1; j++) {
                    // + add nums[i] to different locations
                    path.add(j, nums[i]);
                    current.add(new ArrayList<Integer>(path));   
                    // - remove nums[i] add
                    path.remove(j);
                }
            }
     
            result = new ArrayList<List<Integer>>(current);
        }
     
        return result;
    }
}

// js code
const dfs = (path, remaining, result) => {
    if (remaining.length === 0) {
        result.push([...path]);
    }

    for (let i = 0; i < remaining.length; i++) {
        path.push(remaining[i]);
        dfs(path, [...remaining.slice(0, i), ...remaining.slice(i + 1)], result);
        path.pop(remaining[i])
    }
}

var permute = function(nums) {
    const result = [];
    dfs([], nums, result)
    return result;
};

