/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

idea:
A. sacrifice space for time
since this is to get consecutive, manually increment and decrement target

1. sort the array, loop it,
if any two next-to integers are NOT consecutive,
update the maxLength and maxLength start, reset the start
2. HashSet
put all integers into HashSet
for each integer in array i, keep i-- and keep i++, this will find the consecutive sequence, and record the length
if use HashSet, also can use recursive
3. HashMap, but need a visited boolean array to mark which element already visited before
visited[] is equal to hashset.remove();
for each num
	i, check whether num - 1 is in the map  (while loop)
	ii, check whether num + 1 is in the map  (while loop)

4. if only want the length of Longest Increase Sequence (LIS), dynamic programming
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
	public int[] find(int[] a) {
		Arrays.sort(a);
		int maxLength = 1;
		int tempLength = 1;
		int maxStart = 0;
		int start = 0;
		int end = 0;

		for (int i = 1; i < a.length; i++) {
			if (a[i] == a[i-1] + 1) {
				end = i;
			}
			else {
				tempLength = end - start + 1;
				if (maxLength < tempLength) {
					maxLength = tempLength;
					maxStart = start;
				}
				start = i;
				end = i;
			}
		}
		int[] result = {maxStart, maxLength};

		return result;
	}
	// method 2
	public int longestConsecutive(int[] nums) {
		HashSet<Integer> hs = new HashSet<Integer>();
		for (int i : nums) {
			hs.add(i);
		}

		int max = 0;
		for (int i : nums) {
			int cnt = 1;
			int temp = i;
			hs.remove(temp);
			while (hs.contains(temp - 1)) {
				hs.remove(temp - 1);
				cnt++;
				temp--;
			}
			temp = i;
			while (hs.contains(temp + 1)) {
				hs.remove(temp + 1);
				cnt++;
				temp++;
			}

			max = Math.max(max, cnt);
		}

		return max;
	}
	// method 2', iterative method can be converted to recursive method 
    public int longestConsecutive(int[] num) {
        Set<Integer> hs = new HashSet<Integer>();
        for (int v : num) {
			hs.add(v);
        }
			
        int ans = 0;        
        for (int v : num) {
			if (hs.contains(v)) {
				ans = Math.max(ans, getCount(hs, v, false) + getCount(hs, v+1, true));
			}
        }
				
        return ans; 
    }
	public int getCount(Set<Integer> hs, int v, boolean asc){
        int count = 0;
        while (hs.contains(v)) {
            hs.remove(v);
            count++;
            if (asc) {
				v++; 
            }
			else {
				v--;
			}
        }
		
        return count;
    }
    
	// method 3
	public int longestConsecutive(int[] nums) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
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

	// method 4, DP
	public int LongestIncreaseSubsequenceLength(int[] nums) {
		int n = nums.length;
		if (n == 0 || nums == null) {
			return 0;
		}
		int[] LIS = new int[n];
		LIS[0] = 1;
		int max = 1;
		for (int i = 1; i < n; i++) {
		    LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					LIS[i] = Math.max(LIS[i], LIS[j] + 1);
					max = Math.max(max, LIS[i]);
				}
			}
		}

		return max;
	}
}