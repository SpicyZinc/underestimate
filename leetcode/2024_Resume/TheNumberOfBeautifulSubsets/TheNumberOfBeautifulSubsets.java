/*
You are given an array nums of positive integers and a positive integer k.
A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
Return the number of non-empty beautiful subsets of the array nums.
A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.

Example 1:
Input: nums = [2,4,6], k = 2
Output: 4
Explanation: The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
It can be proved that there are only 4 beautiful subsets in the array [2,4,6].

Example 2:
Input: nums = [1], k = 1
Output: 1
Explanation: The beautiful subset of the array nums is [1].
It can be proved that there is only 1 beautiful subset in the array [1].

Constraints:
1 <= nums.length <= 20
1 <= nums[i], k <= 1000

idea:
key is how to generate subsets
https://leetcode.com/problems/the-number-of-beautiful-subsets/solutions/3363862/c-java-python-evolve-brute-force-to-dp-explained-7-approaches/
need to come back
*/

class TheNumberOfBeautifulSubsets {

    public int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();
        f(0,nums,res,new ArrayList<>(),k);
        return res.size();
    }
    public void f(int ind,int[] nums,List<List<Integer>> res,List<Integer> ds,int k){
        if(ind==nums.length){
            if(ds.size()>0){
                res.add(new ArrayList<>(ds));
            }
            return;
        }
        if(!(ds.contains(nums[ind]-k) )){
            ds.add(nums[ind]);
            f(ind+1,nums,res,ds,k);    
            ds.remove(ds.size()-1);
        }
        f(ind+1,nums,res,ds,k);
    }

    // not passed, wrong answer with duplicate
    int count = 0;

    public int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        dfs(0, nums, k, new HashSet<>());
        return count - 1;
    }

    public void dfs(int i, int[] nums, int k, Set<Integer> current) {
        count++;

        for (int j = i; j < nums.length; j++) {
            if (!current.contains(nums[j] - k)) {
                current.add(nums[j]);
                dfs(j + 1, nums, k, current);
                current.remove(nums[j]);
            }
        }
    }
}