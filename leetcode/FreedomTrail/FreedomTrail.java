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
but Map<String, Map<Integer, Integer>> cache = new HashMap<>() used to avoid unnecessary repeat
note, this uses rotate array or rotate string, 3 steps to rotate
123436 => 343612
that's why it's called rotate string

2. dynamic programming
*/

public class FreedomTrail {
	// key is ring
    // value is another map of key is index in ring, value is steps
    Map<String, Map<Integer, Integer>> cache = new HashMap<>();

    public int findRotateSteps(String ring, String key) {
        if (ring.length() == 0 || key.length() == 0) {
            return 0;
        }
        
        return findRotatedSteps(ring, key, 0);
    }
    
    public int findRotatedSteps(String ring, String key, int index) {
        if (index == key.length()) {
            return 0;
        }
        if (cache.containsKey(ring) && cache.get(ring).containsKey(index)) {
            return cache.get(ring).get(index);
        }

        char c = key.charAt(index);
        int firstPos = ring.indexOf(c);
        int lastPos = ring.lastIndexOf(c);
        // the total steps by starting rotating to first position
        // includes firstPos + press (1) + steps to finish the rest
        // note, firstPos, not firstPos + 1
        int stepsToFirstPos = firstPos + 1 + findRotatedSteps(rotate(ring, firstPos), key, index + 1);
        int stepsToLastPos = (ring.length() - lastPos) + 1 + findRotatedSteps(rotate(ring, lastPos), key, index + 1);
        int minSteps = Math.min(stepsToFirstPos, stepsToLastPos);
        Map<Integer, Integer> indexToSteps = cache.getOrDefault(ring, new HashMap<Integer, Integer>());
        indexToSteps.put(index, minSteps);
        cache.put(ring, indexToSteps);
        
        return minSteps;
    }
    
    // helper to rotate a string by pos
    // 3 steps to rotate string, typical way
    public String rotate(String s, int pos) {
        String front = reverse(s.substring(0, pos));
        String back = reverse(s.substring(pos));
        return reverse(front + back);
    }
    // helper to reverse a string
    public String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // method 2
    // dp[i][j] key starts from i, ring stars from j, min steps to get key by dialing ring
    public int findRotateSteps(String ring, String key) {
        int r = ring.length();
        int k = key.length();
        int[][] dp = new int[k + 1][r];

        for (int i = k - 1; i >= 0; i--) {
            for (int j = 0; j < r; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int x = 0; x < r; x++) {
                    if (key.charAt(i) == ring.charAt(x)) {
                        int diff = Math.abs(j - x);
                        int minSteps = Math.min(diff, r - diff);
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][x] + minSteps);
                    }
                }
            }
        }

        return dp[0][0] + k;
    }
}