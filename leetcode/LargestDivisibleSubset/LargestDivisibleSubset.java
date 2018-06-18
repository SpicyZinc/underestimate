/*
Given a set of distinct positive integers,
find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:
nums: [1,2,3]
Result: [1,2] (of course, [1,3] will also be ok)

Example 2:
nums: [1,2,4,8]
Result: [1,2,4,8]

idea:
dp
output the biggest subset, so need the pre[]
dp[] == the biggest divisible subset size of till nums[i]
pre[] == element index before (<) current element which makes the biggest subset size 
element which can divide current element
so have pre[] to construct the result

or
dfs()

Note:
if b > a && b % a == 0
if c > b && c % b == 0 
it must c % a == 0
*/

public class LargestDivisibleSubset {
	// dfs(), TLE, note no return
	List<Integer> answer;
    public List<Integer> largestDivisibleSubset(int[] nums) {
		List<Integer> path = new ArrayList<Integer>();
		
		if (nums == null || nums.length == 0) {
			return path;
		}

		Arrays.sort(nums);

		int[] maxSize = new int[1];
		dfs(nums, 0, maxSize, path);

		return answer;
	}

	public void dfs(int[] nums, int pos, int[] maxSize, List<Integer> path) {
		if (path.size() > maxSize[0]) {
			maxSize[0] = path.size();
			answer = new ArrayList<Integer>(path);
			// no return
		}
        
        if (pos == nums.length) {
			return;
		}

		for (int i = pos; i < nums.length; i++) {
			if (path.size() == 0 || path.size() > 0 && nums[i] % path.get(path.size() - 1) == 0) {
				path.add(nums[i]);
				dfs(nums, i + 1, maxSize, path);
				path.remove(path.size() - 1);
			}
		}
	}

	// dp
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        int n = nums.length;
        Arrays.sort(nums);

        int[] dp = new int[n];
        int[] pre = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(pre, -1);

        int maxSize = 1;
        int maxIdx = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    pre[i] = j;
                }
            }
            if (maxSize < dp[i]) {
                maxSize = dp[i];
                maxIdx = i;
            }
        }

        for (int i = maxIdx; i >= 0; i = pre[i]) {
            ans.add(nums[i]);
        }

        return ans;
    }
}