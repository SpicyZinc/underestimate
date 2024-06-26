/*
Initially, there is a Robot at position (0, 0).
Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

The move sequence is represented by a string. And each move is represent by a character.
The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

Example 1:
Input: "UD"
Output: true
Example 2:
Input: "LL"
Output: false

idea:
too simple
*/
public class RobotReturnToOrigin {
	public boolean judgeCircle(String moves) {
		if (moves.length() == 0 || moves == null) {
			return false;
		}
		int[] letters = new int[26];
		for (int i = 0; i < moves.length(); i++) {
			char move = moves.charAt(i);
			if (move == 'L' || move == 'U') {
				letters[move - 'A']++;
			}
			if (move == 'R' || move == 'D') {
				if (move == 'R') letters['L' - 'A']--;
				if (move == 'D') letters['U' - 'A']--;
			}
		}
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] != 0) return false;
		}

		return true;
	}

	public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;

        for (char move : moves.toCharArray()) {
            if (move == 'U') {
            	y++;
            } else if (move == 'D') {
            	y--;
            } else if (move == 'L') {
            	x--;
            } else if (move == 'R') {
            	x++;
            }
        }
        
        return x == 0 && y == 0;
    }
}