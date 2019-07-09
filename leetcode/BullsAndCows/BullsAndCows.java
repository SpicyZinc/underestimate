/*
You are playing the following Bulls and Cows game with your friend:
You write down a number and ask your friend to guess what the number is.
Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly
in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows").
Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
Please note that both secret number and friend's guess may contain duplicate digits.

Example 1: 
Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.

Example 2: 
Input: secret = "1123", guess = "0111"
Output: "1A1B"
Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.

Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.

idea:
use digits - '0' as index in the array of length of 10
digits[0]
digits[1]
digits[2]
digits[3]
digits[4]
...
digits[8]
digits[9]
*/

public class BullsAndCows {
	public String getHint(String secret, String guess) {
		if (secret.length() != guess.length()) {
			return "";
		}

		int bulls = 0;
		int cows = 0;
		int[] digits = new int[10];

		for (int i = 0; i < secret.length(); i++) {
			char secretChar = secret.charAt(i);
			char guessChar = guess.charAt(i);

			if (secretChar == guessChar) {
				bulls++;
			} else {
				digits[secretChar - '0']++;
				digits[guessChar - '0']--;

				if (digits[secretChar - '0'] <= 0) {
					cows++;
				}
				if (digits[guessChar - '0'] >= 0) {
					cows++;
				}
			}
		}

		return bulls + "A" + cows + "B";
	}
}