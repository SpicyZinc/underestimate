/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
Find the two elements that appear only once.

For example:
Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. 
So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. 
Could you implement it using only constant space complexity?

idea:
1. hashmap employed to solve this problem
2. hashset used to solve this problem, I cannot think of at first, the answer is very clever
3. bit operation, to two groups, one is bit set, one is not bit set
*/

import java.util.*;
public class SingleNumber {
	// method 1, hashmap
    public int[] singleNumber(int[] nums) {
    	LinkedList<Integer> ll = new LinkedList<Integer>();
    	HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for ( int i = 0; i < nums.length; i++ ) {
        	if ( hm.containsKey( nums[i] ) ) {
        		hm.put( nums[i], hm.get( nums[i] ) + 1 );
        	}
        	else {
        		hm.put( nums[i], 1 );
        	}
        }

		for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
		    Integer key = entry.getKey();
		    Integer value = entry.getValue();
		    if ( value == 1 ) {
		    	ll.add( key );
		    }
		}
		int[] ans = new int[ll.size()];
		for ( int i = 0; i < ans.length; i++ ) {
			ans[i] = ll.get(i);
		}

		return ans;
    }
    // method 2, hashset
    public int[] singleNumber(int[] nums) {
		int[] ans = new int[2];
		if ( nums.length == 0 || nums == null ) {
			return ans;
		}

		HashSet<Integer> hs = new HashSet<Integer>();
		for ( int i = 0; i < nums.length; i++ ) {
			if ( !hs.add(nums[i]) ) {
				hs.remove(nums[i]);
			}
		}
		Object[] test = hs.toArray();
	    ans[0] = (int)test[0];
	    ans[1] = (int)test[1];

		return ans;
    }
    // method 3, bit operation
    public int[] singleNumber(int[] nums) {
    	// pass 1,
    	// get the XOR of the two numbers we need to find
	    int record = 0;
	    for (int num : nums) {
	        record ^= num;
	    }
	    // Get its last set bit
	    record &= -record;
	    // pass 2,
	    int[] ans = {0, 0}; 
	    for (int num : nums) {
	        if ((num & record) == 0) { // the bit is not set
	            ans[0] ^= num;
	        }
	        else { // the bit is set
	            ans[1] ^= num;
	        }
	    }

	    return ans;
	}
}

Your idea of diff &= -diff is very elegent! And yes, it does not need to XOR for both group in the second pass. 
XOR for one group suffices. 

Hi, steven10. I think using the rightmost 1-bit is just for ease of coding (diff &= -diff will leave the rightmost 1-bit). 
In fact, you can use any 1-bit. This 1-bit implies that the two single numbers are different at this bit. 
Then we use this bit to split all the remaining numbers into two groups. 
Suppose the two single numbers are a and b and they differ in the third bit (a is 1 at this bit while b is 0). 
After splitting, numbers with 1 in the third bit will fall in the group of a while the remaining ones fall in the group of b. 
Till now, we will be able to get a and b via a simple within-group xor.

Thanks for your much more detailed explanation. I makes more sense to me now. 
I just wonder how you guys come up with diff &= -diff. It is not quite readable. 
The person who mentiond in the first reply "diff = diff & ~(diff-1)" is actually more readable. 


It is actually not easy to realize that ~(diff - 1) == -diff. You may refer to the Two's complement.

Just a bit shorter, using -diff instead of ~(diff - 1) and indexing instead of if-else.