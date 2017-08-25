/*
You are playing the following Bulls and Cows game with your friend: 
You write a 4-digit secret number and ask your friend to guess it, 
each time your friend guesses a number, you give a hint, 
the hint tells your friend how many digits are in the correct positions (called "bulls") 
and how many digits are in the wrong positions (called "cows"), 
your friend will use those hints to find out the secret number.

For example:
Secret number:  1807
Friend's guess: 7810
Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)

Write a function to return a hint according to the secret number and friend's guess, 
use A to indicate the bulls and B to indicate the cows, in the above example, your function should return 1A3B.

You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.


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
        if ( secret.length() != guess.length() ) {
            return "";
        }
        int bulls = 0;
        int cows = 0;
        int[] digits = new int[10];
        for ( int i = 0; i < secret.length(); i++ ) {
        	char secret_ch = secret.charAt(i);
        	char guess_ch = guess.charAt(i);

        	if ( secret_ch == guess_ch ) {
        		bulls++;
        	}
        	else {
        		digits[secret_ch-'0']++;
        		digits[guess_ch-'0']--;
        		if ( digits[secret_ch-'0'] <= 0) {
        			cows++;
        		}
        		if ( digits[guess_ch-'0'] >= 0 ) {
        			cows++;
        		}
        	}
        }
        return bulls+"A"+cows+"B";
    }
}