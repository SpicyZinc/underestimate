/*
Given an array of integers and an integer k, 
find out whether there are two distinct indices i and j in the array 
such that nums[i] = nums[j] and the difference between i and j is at most k.

idea:
use hash table for sure
not matter contains the element or not, always push to hash table,
the hash table is to keep record of value <-> position relation and value could be duplicate

*/

public class ContainsDuplicate  {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
        	int current = nums[i];
        	if ( hm.containsKey( current ) ) {
        		int index = hm.get( current );
        		int diff = Math.abs(index - i);

        		if ( diff <= k ) {
        			return true;
        		}
        	}
        	
        	hm.put(current, i);
    
        }

        return false;
    }
}