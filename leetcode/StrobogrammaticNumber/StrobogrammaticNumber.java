/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to determine if a number is strobogrammatic. The number is represented as a string.
For example, the numbers "69", "88", and "818" are all strobogrammatic.

idea:
https://segmentfault.com/a/1190000003787462
make a hashmap since only a couple of numbers are strobogrammatic 8->8, 0->0, 1->1, 6->9, 9->6

two pointers, left and right
right char not existing in hashmap
or left char not equal to the right char as key's value
return false
*/

public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
    	HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
    	hm.put(8, 8);
    	hm.put(0, 0);
    	hm.put(1, 1);
    	hm.put(6, 9);
    	hm.put(9, 6);

    	int left = 0;
    	int right = num.length() - 1;
    	// must left <= right, equal is to deal with odd parity length of num, both left and right end at the same position
    	while (left <= right) {
    		char leftChar = num.charAt(left);
    		char rightChar = num.charAt(right);
    		if ( !hm.containsKey(rightChar) || leftChar != map.get(rightChar) ) {
    			return false;
    		}
    		left++;
    		right--;
    	}

    	return true;
    }
}