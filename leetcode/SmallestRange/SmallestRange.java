/*
You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.
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
priority queue with comparator to have the min value
max to record the max because no O(1) time to get tail element in priority queue
return [min, max]
*/

class SmallestRange {
	class Element {
        int idx;
        int val;
        int row;
        
        public Element(int idx, int val, int row) {
            this.idx = idx;
            this.val = val;
            this.row = row;
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
            int min = nums.get(i).get(0);
            Element ele = new Element(0, min, i);
            pq.offer(ele);
            max = Math.max(max, min);
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
            if (smallest.idx + 1 < nums.get(smallest.row).size()) {
                smallest.idx += 1;
                smallest.val = nums.get(smallest.row).get(smallest.idx);
                pq.offer(smallest);
                // update max
                max = Math.max(max, smallest.val);
            }
        }
        
        return new int[] {start, end};
    }
}