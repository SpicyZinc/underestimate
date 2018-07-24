/*
Given an array of integers, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. 
Please note that your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

idea:
1. direct thought O(n^2)
this is the only method not need the hashmap

2. hash cannot distinguish two same keys, only save one key
sort first

(1) use hash to store value - index first, save the index otherwise after sorting, index changes
    sort the array, order changes, but old order can be retrieved from hash
    now use for (i=0, j = length - 1; i < j; ) 

(2) use hash to store (target-current, index)
if later hash contains value, which indicates there is a pair

One thing to note:
these two hashes are differently used

*/
import java.util.*;

public class TwoSumImproved {
	public static void main(String[] args) {
		new TwoSumImproved();
	}
	public TwoSumImproved() {
		int[] numbers = {722, 600, 905, 55, 55};
		int target = 110;
		twoSum(numbers, target);
	}
	// passed test
    public int[] twoSum(int[] numbers, int target) {
        int[] ret = new int[2];
		Map<Integer, Integer> eleToIndex = new HashMap<Integer, Integer>();
        for (int i=0; i<numbers.length; i++) {
			if ( !eleToIndex.containsKey(numbers[i]) ) {
				eleToIndex.put(numbers[i], i+1);
			} else {
			    if ( numbers[i] * 2 == target ) { 
    				ret[0] = eleToIndex.get(numbers[i]);
    				ret[1] = i + 1;
    				return ret;
    			}	
			}		
		}
		Arrays.sort(numbers);
		for (int i=0, j=numbers.length-1; i<j;) {
			if (numbers[i] + numbers[j] > target) {
				j--;
            }
			else if (numbers[i] + numbers[j] < target) {
				i++;
			}
			else {
				// after sort, index changes, get index by hashmap
				ret[0] = eleToIndex.get(numbers[i]);
				ret[1] = eleToIndex.get(numbers[j]);
				if (ret[0] > ret[1]) {
					int tmp = ret[0];
					ret[0] = ret[1];
					ret[1] = tmp;
				}
				return ret;
			}
		}
		return ret;
    }

    // passed test
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	    int[] result = new int[2];
 
    	for (int i = 0; i < numbers.length; i++) {
    		if (map.containsKey(numbers[i])) {
    			int index = map.get(numbers[i]);
    			result[0] = index+1 ;
    			result[1] = i+1;
    			break;
    		} 
    		else {
    			map.put(target - numbers[i], i);
    		}
    	}
     
    	return result;
    }
}