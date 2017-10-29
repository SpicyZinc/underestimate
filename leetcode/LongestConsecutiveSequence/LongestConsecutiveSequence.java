/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

idea:
A. sacrifice space for time
since this is to get consecutive, manually increment and decrement target

1. sort the array, loop it,
if any two adjacent integers are NOT consecutive,
update the maxLength and maxLength start, reset the start
note: if any two adjacent integers are equal, increase both start and end by 1

2. put all integers into HashSet
for each integer in array i, keep i-- and keep i++, this will find the consecutive sequence, and record the length
if use HashSet, also can use recursive
3. HashMap, but need a visited boolean array to mark which element already visited before
visited[] is equal to hashset.remove();
for each num
	i, check whether num - 1 is in the map  (while loop)
	ii, check whether num + 1 is in the map  (while loop)

4. if only want the length of Longest Increase Sequence (LIS), dp
original order not change
Let arr[0..n-1] be the input array and L(i) be the length of the LIS till index i such that arr[i] is part of LIS and arr[i] is the last element in LIS,
*/

import java.util.*;


class LongestConsecutiveSequence {
	public static void main(String[] args) {
		LongestConsecutiveSequence eg = new LongestConsecutiveSequence();
		// int[] a = {1,2,3,5,7,8,9,10};
		int[] a = {100, 4, 200, 1, 3, 2};

		int[] result = eg.find(a);
		for (int i : result) {
			System.out.println(i);
		}
	}
	// method 1
	public int longestConsecutive(int[] nums) {
		if (nums.length == 0 || nums == null) {
			return 0;
		}

		Arrays.sort(nums);

		int maxLen = 1;
		int maxStart = 0;
		int start = 0;
		int end = 0;

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1] + 1) {
				end = i;
			} else if (nums[i] == nums[i - 1]) {
				start++;
				end++;
			} else {
				int len = end - start + 1;
				if (maxLen < len) {
					maxLen = len;
					maxStart = start;
				}
				// update start and end
				start = i;
				end = i;
			}
		}

		return Math.max(maxLen, end - start + 1);
	}
	// method 2
	public int longestConsecutive(int[] nums) {
		Set<Integer> hs = new HashSet<Integer>();
		for (int i : nums) {
			hs.add(i);
		}

		int max = 0;
		for (int num : nums) {
			int cnt = 1;
			int curr = num;
			hs.remove(curr);
			while (hs.contains(curr - 1)) {
				hs.remove(curr - 1);
				curr--;
				cnt++;
			}
			curr = num;
			while (hs.contains(curr + 1)) {
				hs.remove(curr + 1);
				curr++;
				cnt++;
			}

			max = Math.max(max, cnt);
		}

		return max;
	}
	// method 2, iterative method can be converted to recursive method 
    public int longestConsecutive(int[] nums) {
        Set<Integer> hs = new HashSet<Integer>();
        for (int v : nums) {
			hs.add(v);
        }
			
        int ans = 0;
        for (int num : nums) {
			if (hs.contains(num)) {
				ans = Math.max(ans, getCount(hs, num, false) + getCount(hs, num + 1, true));
			}
        }
				
        return ans;
    }

	public int getCount(Set<Integer> hs, int v, boolean asc) {
		int count = 0;
		while (hs.contains(v)) {
			hs.remove(v);
            count++;
            if (asc) {
				v++;
            } else {
				v--;
			}
        }
		
        return count;
	}
	
	// method 3
	public int longestConsecutive(int[] nums) {
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int index = 0;
		for (int i : nums) {
			hm.put(i, index++);
		}

		int[] visited = new int[nums.length];
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (visited[i] == 1) {
				continue;
			}

			visited[i] = 1;
			int cnt = 1;
			int temp = nums[i];
			while (hm.containsKey(temp - 1)) {
				visited[hm.get(temp-1)] = 1;
				cnt++;
				temp--;
			}
			temp = nums[i];
			while (hm.containsKey(temp + 1)) {
				visited[hm.get(temp+1)] = 1;
				cnt++;
				temp++;
			}

			max = Math.max(max, cnt);
		}

		return max;
	}
}