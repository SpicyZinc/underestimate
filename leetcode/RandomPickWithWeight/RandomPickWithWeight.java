/*
Given an array w of positive integers, where w[i] describes the weight of index i,
write a function pickIndex which randomly picks an index in proportion to its weight.

Note:
1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.

Example 1:
Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]

Example 2:
Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.

idea:
https://www.cnblogs.com/grandyang/p/9784690.html
the rest is search insert index
*/

class RandomPickWithWeight {
	// Mon May  6 00:14:30 2019
	int[] sum;
	Random random;
    
    public Solution(int[] w) {
        this.sum = new int[w.length];
        this.sum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            this.sum[i] = this.sum[i - 1] + w[i];
        }
        
        this.random = new Random();
    }

    public int pickIndex() {
        int sumWeights = sum[sum.length - 1];
        int r = random.nextInt(sumWeights) + 1;
        
        // search for inserted position for r
        int left = 0;
        int right = sum.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (sum[mid] < r) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }


	Random random;
	int[] sum;

	public Solution(int[] w) {
		this.random = new Random();
		this.sum = w;

		for (int i = 1; i < w.length; i++) {
			this.sum[i] = this.sum[i - 1] + w[i];
		}
	}

	public int pickIndex() {
		int len = sum.length;
		int sumWeights = sum[len - 1];

		int r = random.nextInt(sumWeights) + 1;

		// search insert position
		int left = 0;
		int right = len - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (sum[mid] < r) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return left;
	}
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */