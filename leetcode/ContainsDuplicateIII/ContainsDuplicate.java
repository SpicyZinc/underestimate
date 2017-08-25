/*
Given an array of integers, find out whether there are two distinct indices i and j in the array 
such that the difference between nums[i] and nums[j] is at most t 
and the difference between i and j is at most k.

idea:
method 1: treeset
1. floor(n) return the max value ≤ n from the set; return null if no such element
2. ceiling(n) return the min value ≥ n from the set; return null if no such element
n - floor(n) <= t
ceiling(n) - n <= t
exists once is enough

method 2: math induction
http://bookshadow.com/weblog/2015/06/03/leetcode-contains-duplicate-iii/
*/
public class ContainsDuplicate {
    // method 1
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k <= 0 || t < 0) {
            return false;
        }
        TreeSet<Long> ts = new TreeSet<Long>();
        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];
            
            if (ts.floor(num) != null && num - ts.floor(num) <= t || ts.ceiling(num) != null && ts.ceiling(num) - num <= t) {
                return true;
            }
            ts.add(num);
            
            if (i >= k) {
                ts.remove((long) nums[i - k]);
            }
        }
        
        return false;
    }
    // method 2
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if ( k < 1 || t < 0 ) {
            return false;
        }

        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for ( int i = 0; i < nums.length; i++ ) {
            int key = (int) Math.floor( nums[i] / Math.max(1, t) );
            int[] temp = {key, key - 1, key + 1};
            for ( int j = 0; j < temp.length; j++ ) {
                if ( hm.containsKey(temp[j]) && Math.abs(nums[i] - hm.get(temp[i])) <= t ) {
                    return true;
                }
            }
            hm.put(key, nums[i]);
            // maintain a window of size k
            if ( i >= k ) {
                for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
                    hm.remove(entry.getKey());
                    break;
                }
            }
        }

        return false;
    }
}
