/*
Alice and Bob take turns playing a game, with Alice starting first.

Initially, there is a number n on the chalkboard. On each player's turn, that player makes a move consisting of:

Choosing any x with 0 < x < n and n % x == 0.
Replacing the number n on the chalkboard with n - x.
Also, if a player cannot make a move, they lose the game.

Return true if and only if Alice wins the game, assuming both players play optimally.

Example 1:
Input: n = 2
Output: true
Explanation: Alice chooses 1, and Bob has no more moves.

Example 2:
Input: n = 3
Output: false
Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.

Constraints:
1 <= n <= 1000

idea:
--> approach : 
--> alice will try to give bob always an odd number .. why ?
--> odd numbers have divisors only odd. So if bob gets only odd number, he is forced to give alice always even number... why?
--> let say bob has an odd number, as odd numbers has divisior only odd number, bob will give odd number - odd divisior = even number to alice
--> in this way alice always get even number and bob always gets odd number
--> at last bob will get 1(odd number) and he looses.

--> if  alice got a even number , she can always give bob a odd number as even numbers have divisor both odd and even ...why ?
--> alice will pick any odd divisior and bob will get even number - odd divisor = odd number
--> if alice got a odd number, she is forced to give bob an even number and bob will always give alice odd number by same above strategy and she will loose.

--> So our problem will depend on the initial value given to alice, if it is even she will win otherwise not.
*/


class DivisorGame {
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}
