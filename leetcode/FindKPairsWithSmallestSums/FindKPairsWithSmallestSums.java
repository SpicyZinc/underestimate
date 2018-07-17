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

        Collections.sort(pairs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] + a[1] - b[0] - b[1];
            }
        });

        return k >= pairs.size() ? pairs : pairs.subList(0, k);
    }

    /**
     * Use min_heap to keep track on next minimum pair sum, and we only need to maintain K possible candidates in the data structure.
     * Some observations: For every numbers in nums1, its best partner(yields min sum) always starts from nums2[0] since arrays are all sorted;
     * and for a specific number in nums1,
     * its next candidate should be this (specific number) + nums2[current_associated_index + 1], unless out of boundary;)
     */
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> pairs = new ArrayList<int[]>();

        int m = nums1.length;
        int n = nums2.length;
        if (m == 0 || n == 0 || k == 0) {
            return pairs;
        }
        // 一定程度上的BFS 以它们为基础不断扩展
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] + a[1] - b[0] - b[1];
            }
        });
        // first add k pairs
        // m * n combination, 先把所有的m 和n中的一个作为基础 加到队列中
        for (int i = 0; i < Math.min(m, k); i++) {
            queue.offer(new int[] {nums1[i], nums2[0], 0});
        }
        // queue 的每个element是一个array whose 第三个element是num1中 当前到达的num2中的index
        while (k > 0 && !queue.isEmpty()) {
            int[] minPair = queue.poll();
            pairs.add(new int[] {minPair[0], minPair[1]});
            k--;
            int nextIdxInNum2 = minPair[2] + 1;
            if (nextIdxInNum2 == n) {
                continue;
            }
            queue.offer(new int[] {minPair[0], nums2[nextIdxInNum2], nextIdxInNum2});
        }
        
        return pairs;
    }
}