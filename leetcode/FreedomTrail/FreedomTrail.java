/*
In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring",
and use the dial to spell a specific keyword in order to open the door.

Given a string ring, which represents the code engraved on the outer ring and another string key,
which represents the keyword needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.

Initially, the first character of the ring is aligned at 12:00 direction.
You need to spell all the characters in the string key one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction and then by pressing the center button.

At the stage of rotating the ring to spell the key character key[i]:
You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step.
The final purpose of the rotation is to align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step.
After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the spelling.

Example:
https://leetcode.com/static/images/problemset/ring.jpg

Input: ring = "godding", key = "gd"
Output: 4
Explanation:
	For the first key character 'g', since it is already in place, we just need 1 step to spell this character. 
	For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
	Also, we need 1 more step for spelling.
	So the final output is 4.

Note:
Length of both ring and key will be in range 1 to 100.
There are only lowercase letters in both strings and might be some duplcate characters in both strings.
It's guaranteed that string key could always be spelled by rotating the string ring.

idea:
http://blog.jerkybible.com/2017/03/18/LeetCode-514-Freedom-Trail/

1. dfs, easy to understand
but HashMap<String, Map<Integer, Integer>> cache = new HashMap<>() used to avoid unnecessary repeat
note, this uses rotate array or rotate string 3 steps to rotate
123436 => 343612
that's why it's called rotate string

2. dynamic programming
*/

public class FreedomTrail {
	Map<String, Map<Integer, Integer>> cache = new HashMap<>();

    public int findRotateSteps(String ring, String key) {
		if (ring.length() == 0 || ring == null || key.length() == 0 || key == null) {
			return 0;
		}
		return findRotateSteps(ring, key, 0);
    }

    public int findRotateSteps(String ring, String key, int index) {
        if (index == key.length()) {
        	return 0;
        }
        String current = ring;
        if (cache.containsKey(current) && cache.get(current).containsKey(index)) {
        	return cache.get(current).get(index);
        }
        char c = key.charAt(index);
        int frontPos = current.indexOf(c);
        int backPos = current.lastIndexOf(c);
        // steps needed to rotate to the corresponding pos plus the pressing button step
        int stepsToRotateToFrontPost = 1 + frontPos + findRotateSteps(stringAfterRotate(ring, frontPos), key, index + 1);
        // don't forget to ring - backPos
        int stepsToRotateToBackPos = 1 + (ring.length() - backPos) + findRotateSteps(stringAfterRotate(ring, backPos), key, index + 1);
        int steps = Math.min(stepsToRotateToFrontPost, stepsToRotateToBackPos);
        Map<Integer, Integer> indexToSteps = cache.getOrDefault(ring, new HashMap<Integer, Integer>());
        indexToSteps.put(index, steps);
        cache.put(current, indexToSteps);

        return steps;
    }

    private String stringAfterRotate(String ring, int index) {
    	String front = reverse(ring.substring(0, index));
    	String back = reverse(ring.substring(index));
    	return reverse(front + back);
    }

    private String reverse(String str) {
    	char[] chars = str.toCharArray();
    	int len = str.length();
    	for (int i = 0; i < len / 2; i++) {
    		char temp = chars[i];
    		chars[i] = chars[len - 1 - i];
    		chars[len - 1 - i] = temp;
    	}
    	return new String(chars);
    }
}