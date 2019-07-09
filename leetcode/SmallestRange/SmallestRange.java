/*
You have k lists of sorted integers in ascending order.
Find the smallest range that includes at least one number from each of the k lists.
We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].

Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-10^5 <= value of elements <= 10^5.
For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.

idea:
首先把所有list中最小的取出来构建成Element
然后放入PriorityQueue
同时记入max

这个max和pq.poll() 的val 就是 range
不断更新range 通过贡献min的那个list
要更新max

最后就是[min, max]

minimum window that satisfies some criteria
*/

class SmallestRange {
	// Thu May  9 22:36:43 2019
	public int[] smallestRange(List<List<Integer>> nums) {
		// 把所有的nums 都整合到一块 每个 element 包含 value 和 它原来所在的位置
		int n = nums.size();
		List<int[]> allNums = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int num : nums.get(i)) {
				allNums.add(new int[] {num, i});
			}
		}
		// sort ascending
		Collections.sort(allNums, (a, b) -> a[0] - b[0]);

		int[] result = new int[2];
		
		int minRange = Integer.MAX_VALUE;
		// key is position, value is frequency of this position 这个所属原list的位置 这个位置出现的次数
		Map<Integer, Integer> allPositions = new HashMap<>();

		int cntPos = 0;
		int left = 0;

		for (int right = 0; right < allNums.size(); right++) {
			int[] pair = allNums.get(right);
			int val = pair[0];
			// the position in original nums
			int pos = pair[1];

			allPositions.put(pos, allPositions.getOrDefault(pos, 0) + 1);
			if (allPositions.get(pos) == 1) {
				cntPos++;
			}
	
			while (cntPos == n && left <= right) {
				int[] leftPair = allNums.get(left);

				int leftVal = leftPair[0];
				int leftPos = leftPair[1];

				if (minRange > val - leftVal) {
					minRange = val - leftVal;

					result = new int[] {leftVal, val};
				}

				// 开始移动 left side of window
				left++;
				if (allPositions.containsKey(leftPos)) {
					allPositions.put(leftPos, allPositions.get(leftPos) - 1);
					if (allPositions.get(leftPos) == 0) {
						cntPos--;
					}
				}
			}
		}

		return result;
	}

	// 01/22/2019
	public int[] smallestRange(List<List<Integer>> nums) {
		int len = nums.size();
		List<int[]> allNums = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			for (int num : nums.get(i)) {
				allNums.add(new int[] {num, i});
			}
		}
		// sort the newly created list
		Collections.sort(allNums, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
			}
		});

		int left = 0;
		int listCnt = 0;
		int diff = Integer.MAX_VALUE;
		Map<Integer, Integer> hm = new HashMap<>();

		int[] result = new int[2];

		for (int right = 0; right < allNums.size(); right++) {
			int[] pair = allNums.get(right);
			int val = pair[0];
			int idx = pair[1];

			// 这个 list's idx 说明这个list出现过了
			hm.put(idx, hm.getOrDefault(idx, 0) + 1);
			if (hm.get(idx) == 1) {
				listCnt++;
			}

			while (listCnt == len && left <= right) {
				int[] leftPair = allNums.get(left);

				if (val - leftPair[0] < diff) {
					diff = val - leftPair[0];
					result = new int[] {leftPair[0], val};
				}

				// move window
				left++;
				if (hm.get(leftPair[1]) >= 1) {
					hm.put(leftPair[1], hm.get(leftPair[1]) - 1);
					if (hm.get(leftPair[1]) == 0) {
						listCnt--;
					}
				}
			}
		}

		return result;
	}

	// method 2
	class Element {
		int idx;
		int val;
		int listId;
		
		public Element(int idx, int val, int listId) {
			this.idx = idx;
			this.val = val;
			this.listId = listId;
		}
	}

	public int[] smallestRange(List<List<Integer>> nums) {
		PriorityQueue<Element> pq = new PriorityQueue<Element>(new Comparator<Element>() {
			@Override
			public int compare(Element a, Element b) {
				return a.val - b.val;
			}
		});

		int max = Integer.MIN_VALUE;
		int start = -1;
		int end = -1;
		int range = Integer.MAX_VALUE;

		for (int i = 0; i < nums.size(); i++) {
			int minOfList = nums.get(i).get(0);
			pq.offer(new Element(0, minOfList, i));

			max = Math.max(max, minOfList);
		}

		while (pq.size() == nums.size()) {
			Element smallest = pq.poll();

			if (max - smallest.val < range) {
				range = max - smallest.val;
				start = smallest.val;
				end = max;
			}
			// add the next element of that array which pops the smallest to pq
			// how? manually update it by changing its property
			// 如果贡献smallest的list还没有到底的话
			if (smallest.idx + 1 < nums.get(smallest.listId).size()) {
				smallest.idx += 1;
				smallest.val = nums.get(smallest.listId).get(smallest.idx);
				pq.offer(smallest);
				// update max
				max = Math.max(max, smallest.val);
			}
		}

		return new int[] {start, end};
	}
}