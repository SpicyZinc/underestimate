/*
There are n bulbs that are initially off. 
You first turn on all the bulbs. 
Then, you turn off every second bulb. 
On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). 
For the nth round, you only toggle the last bulb. 
Find how many bulbs are on after n rounds.

Example:

Given n = 3. 
At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on.

idea:
http://blog.csdn.net/ironyoung/article/details/50358843
[1, n]
1 has 1 * 1 (toggle once)
2 has 1 * 2, 2 * 1 (toggle twice)
3 has 1 * 3, 3 * 1 (toggle twice)
4 has 1 * 4, 2 * 2, 4 * 1 (toggle three times)

the bulb is on or off depends on the number of pairs
if it is odd, off->on
if it is even, off->on->off

The net result is the square number 1, 4, 9 will be on
however, the relationship the square root is the number of bulbs which are on

get the number of square numbers of range [1, n] inclusively

*/
public class BulbSwitcher {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}