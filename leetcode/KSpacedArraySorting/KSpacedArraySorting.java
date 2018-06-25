/*
An array is ordered from small to large every k digits, ie arr[i] <= arr[i + k] <= arr[i + 2 * k] <= .... Sort the array from small to large.
We expect you to write an algorithm whose time complexity is O(n * logk).

Notice
0 <= arr[i] <= 2^31 - 1
1 <= |arr| <= 10^5
1 <= k <= 10^5

Example
Given arr = [1,2,3], k = 1, return [1,2,3].
Explanation:
The sorted array is [1,2,3].

Given arr = [4,0,5,3,10], k = 2, return [0,3,4,5,10].
Explanation:
The sorted array is [0,3,4,5,10].

idea:
variation of merge k sorted arrays or k sorted lists
*/

public class KSpacedArraySorting {
    /**
     * @param arr: The K spaced array
     * @param k: The param k
     * @return: Return the sorted array
     */
     
    class Node {
        int index;
        int value;
        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public int[] getSortedArray(int[] arr, int k) {
        if (arr.length == 0 || arr == null || k >= arr.length) {
            return arr;
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                return a.value - b.value;
            }
        });
        // 1st group of k elements ceil(len / k) groups
        // initialize
        for (int i = 0; i < k; i++) {
            pq.offer(new Node(i, arr[i]));
        }

        int[] sorted = new int[arr.length];
        int i = 0;
        while (!pq.isEmpty()) {
            Node min = pq.poll();
            sorted[i++] = min.value;
            int nextMinIndex = min.index + k;
            if (nextMinIndex < arr.length) {
                pq.offer(new Node(nextMinIndex, arr[nextMinIndex]));
            }
        }
        return sorted;
    }
}