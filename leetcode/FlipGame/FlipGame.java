/*
You are playing the following Flip Game with your friend:
Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--".
The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:
[  "--++", "+--+", "++--" ]
If there is no valid move, return an empty list [].

idea:
convert string to char array, find a two consecutive "++", replace it with "--", then reset them back
*/
public class FlipGame {
	public List<String> generatePossibleNextMoves(String s) {
		List<String> result = new ArrayList<String>();
		if (s.length() == 0 || s == null) {
			return result;
		}
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length - 1; i++) {
			if (chars[i] == '+' && chars[i + 1] == '+') {
				chars[i] = '-';
				chars[i + 1] = '-';
			}
			result.add(new String(chars));
			chars[i] = '+';
			chars[i + 1] = '+';
		}

		return result;
	}
}