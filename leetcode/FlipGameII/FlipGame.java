/*
You are playing the following Flip Game with your friend:
Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--".
The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.

idea:
backtracking
starting player plays first
no way for another player to win by recurse on the string after starting player played, then the starting player win
都试过一遍来 有一种机会能win 就win了
*/

public class FlipGame {
	public static void main(String[] args) {
		// String s = "++++";
		String s = "+-+-+--+--++++";
		FlipGame eg = new FlipGame();
		boolean ifWin = eg.canWin(s);
		System.out.println(ifWin);
	}

	public boolean canWin(String s) {
		if (s == null || s.length() == 0) {
			return false;
		}

		for (int i = 1; i < s.length(); i++) {
			char prev = s.charAt(i - 1);
			char curr = s.charAt(i);
			if (prev == '+' && prev == curr) {
				String flipped = s.substring(0, i - 1) + "--" + s.substring(i + 1);
				boolean canAnotherWin = canWin(flipped);
				if (!canAnotherWin) {
					return true;
				}
			}
		}

		return false;
	}
}