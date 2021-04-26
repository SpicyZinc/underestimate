/*
We are playing the Guess Game. The game is as follows:
I pick a number from 1 to n. You have to guess which number I picked.
Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
Example:
n = 10, I pick 6.
Return 6.

idea:
binary search
my number 指出题人的 number
1 高于 your guess, you refers to me 做题人
recursion or iteration
*/

/* The guess API is defined in the parent class GuessGame.
	@param num, your guess
	@return -1 if my number is lower, 1 if my number is higher, otherwise return 0
	  int guess(int num); */

public class GuessNumberHigherOrLower extends GuessGame {
	public int guessNumber(int n) {
		int start = 1;
		int end = n;
		
		while (start < end) {
			int myGuess = start + (end - start) / 2;
			int guessResult = guess(myGuess);

			if (guessResult == 1) {
				start = myGuess + 1;
			} else if (guessResult == -1) {
				end = myGuess - 1;
			} else {
				return myGuess;
			}
		}
		
		return start;
	}

	// recursion
	public int guessNumber(int n) {
		return guessNumber(1, n);
	}

	public int guessNumber(int low, int high) {
		int mid = low + (high - low) / 2;
		int guessed = guess(mid);
		if (guessed == 1) {
			return guessNumber(mid + 1, high);
		} else if (guessed == -1) {
			return guessNumber(low, mid - 1);
		} else {
			return mid;
		}
	}
}