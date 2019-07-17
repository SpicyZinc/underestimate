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
		Map<Character, Character> hm = new HashMap<>();
		hm.put('0', '0');
		hm.put('1', '1');
		hm.put('8', '8');
		hm.put('6', '9');
		hm.put('9', '6');
		
		int i = 0;
		int j = num.length() - 1;
		// must i <= j, equal is to deal with odd parity length of num, both i and j end at the same position
		while (i <= j) {
			char left = num.charAt(i);
			char right = num.charAt(j);

			if (!hm.containsKey(left) || right != hm.get(left)) {
				return false;
			}
			
			i++;
			j--;
		}
		
		return true;
	}
}