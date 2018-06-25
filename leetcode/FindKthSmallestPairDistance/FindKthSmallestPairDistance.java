/*
Given an integer array, return the k-th smallest distance among all the pairs.
The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0 
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.

Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.

idea:
1. use priority queue, TLE
direct save all distance no matter it is duplicate

2. bucket sort
3. binary
https://www.cnblogs.com/grandyang/p/8627783.html
https://protegejj.gitbooks.io/algorithm-practice/content/719-find-k-th-smallest-pair-distance.html
*/

class FindKthSmallestPairDistance {
	// passed
    public int smallestDistancePair(int[] nums, int k) {
		int size = 1000000;
		int[] buckets = new int[size];

		int len = nums.length;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				int distance = Math.abs(nums[i] - nums[j]);
				buckets[distance]++;
			}
		}

		for (int i = 0; i < size; i++) {
			if (buckets[i] >= k) {
				return i;
			}
			k -= buckets[i];
		}

		return -1;
	}
	// TLE
	public int smallestDistancePair(int[] nums, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return b - a;
			}
		});
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				int distance = Math.abs(nums[i] - nums[j]);
				pq.offer(distance);
				if (pq.size() > k) {
					pq.poll();
				}
			}
		}

		return pq.peek();
	}
	// TLE
	public int smallestDistancePair(int[] nums, int k) {
		List<Integer> list = new ArrayList<Integer>();
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				int distance = Math.abs(nums[i] - nums[j]);
                list.add(distance);
			}
		}

        Collections.sort(list);
        if (list.size() <= k) {
            return list.get(list.size() - 1);
        }
        
        return list.get(k - 1);
	}
	// 3rd method, TLE again
	private class PairDiff {
		int index1;
		int index2;
		int diff;

		public PairDiff(int index1, int index2, int diff) {
			this.index1 = index1;
			this.index2 = index2;
			this.diff = diff;			
		}
	}

	public int smallestDistancePair(int[] nums, int k) {
        PriorityQueue<PairDiff> pq = new PriorityQueue<PairDiff>(new Comparator<PairDiff>() {
        	@Override
        	public int compare(PairDiff a, PairDiff b) {
        		return a.diff - b.diff;
        	}
        });

        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            pq.add(new PairDiff(i - 1, i, nums[i] - nums[i - 1]));
        }

        int res = 0;
        for (int i = 0; i < k; i++) {
            PairDiff curDiff = pq.remove();
            int index1 = curDiff.index1;
            int index2 = curDiff.index2;
            
			res = curDiff.diff;

            if (index2 + 1 < nums.length) {
                pq.add(new PairDiff(index1, index2 + 1, nums[index2 + 1] - nums[index1]));
            }
        }

        return res;
    }
}