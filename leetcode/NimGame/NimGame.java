/*
You are playing the following Nim Game with your friend: 
There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. 
The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. 
Write a function to determine whether you can win the game given the number of stones in the heap.

For example, if there are 4 stones in the heap, then you will never win the game: 
no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.

idea:
very simple, check if this number can be exactly divided by 4.
if can, impossible to win the game
if not, possible to win the game. Note, it only means you can possible win, but how many stones you remove matters

1 Win
2 Win
3 Win
4 Lost
5 Win
6 Win
7 Win
8 Lost
9 Win
10 Win
*/
public class NimGame {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}