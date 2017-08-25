/*
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]

The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:
Given nums1 = [1,2], nums2 = [3],  k = 3 

Return: [1,3],[2,3]

All possible pairs are returned from the sequence:
[1,3],[2,3]

idea:
the direct thought is loop first k elements in two arrays
which are already k * k pairs, only need k pairs.
so sort them by comparator.
how to only loop through k, Math.min(nums.length, k);
Note: possibility is that there are not enough pairs
if this is the case, return all

different idea:
http://www.programcreek.com/2015/07/leetcode-find-k-pairs-with-smallest-sums-java/
*/

public class FindKPairsWithSmallestSums {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> pairs = new ArrayList<int[]>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (k == 0 || len1 == 0 || len2 == 0 ) {
            return pairs;
        }
        for (int i = 0; i < Math.min(len1, k); i++) {
            for (int j = 0; j < Math.min(len2, k); j++) {
                int[] temp = new int[] {nums1[i], nums2[j]};
                pairs.add(temp);
            }
        }

        List<int[]> result = new ArrayList<int[]>();
        Collections.sort(pairs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] + a[1] - b[0] - b[1];
            }
        });
        if (k <= pairs.size()) {
            for (int i = 0; i < k; i++) {
                result.add(pairs.get(i));
            }
            return result;
        }
        else {
            return pairs;
        }
    }

    /**
     * Use min_heap to keep track on next minimum pair sum, and we only need to maintain K possible candidates in the data structure.
     * Some observations: For every numbers in nums1, its best partner(yields min sum) always starts from nums2[0] since arrays are all sorted;
     * and for a specific number in nums1,
     * its next candidate should be this (specific number) + nums2[current_associated_index + 1], unless out of boundary;)
     */
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->a[0]+a[1]-b[0]-b[1]);
        List<int[]> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }
        for (int i=0; i<nums1.length && i<k; i++) {
            que.offer(new int[]{nums1[i], nums2[0], 0});
        }
        while (k-- > 0 && !que.isEmpty()) {
            int[] cur = que.poll();
            res.add(new int[]{cur[0], cur[1]});
            if (cur[2] == nums2.length - 1) {
                continue;
            }
            que.offer(new int[]{cur[0], nums2[cur[2]+1], cur[2]+1});
        }
        return res;
    }
}