/*
Remember the story of Little Match Girl?
By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks.
You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length.
Your output will either be true or false, to represent whether you can save this little girl or not.

Example 1:
Input: [1,1,2,2,2]
Output: true
Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.

Example 2:
Input: [3,3,3,3,4]
Output: false
Explanation: You cannot find a way to form a square with all the matchsticks.

Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.

idea:
https://discuss.leetcode.com/topic/72107/java-dfs-solution-with-explanation/2
DFS 
*/
public class MatchsticksToSquare {
	static int CNT = 4;
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) {
        	return false;
        }
        int sum = 0;
        for (int num : nums) {
        	sum += num;
        }
        if (sum % 4 != 0) {
        	return false;
        }

        return dfs(nums, new int[CNT], 0, sum / CNT);
    }


    private boolean dfs(int[] nums, int[] sums, int index, int sideLength) {
    	if (index == nums.length) {
    		if (sums[0] == sideLength && sums[1] == sideLength && sums[2] == sideLength) {
    			return true;
    		}
    		return false;
    	}

    	for (int i = 0; i < CNT; i++) {
    		if (sums[i] + nums[index] > sideLength) {
    			continue;
    		}
    		sums[i] += nums[index];
    		if (dfs( nums, sums, index + 1, sideLength )) {
    			return true;
    		}
    		sums[i] -= nums[index];
    	}

    	return false;
    }
}



