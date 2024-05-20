/*
Given a number of cents, write a function to make change with the fewest number of coins,
returning the number of coins for each denomination needed for the given number of cents.make_change(33) -> (3, 1, 0, 1)
[1, 5, 10, 25] 

If you need more classes, simply define them inline. 
*/

class CoinChange {
    public int[] make_change(int amount) {
        final int[] denoms = new int[] {25,10,5,1};
        int[] counts = new int[] {0,0,0,0};
        int numCoin = 0;
        for (int i = 0; i < denoms.length; i++) {
            int denom = denoms[i];
            numCoin = amount / denom;
            counts[i] = numCoin;
            amount %= denom;
        }

        return counts;
    }    
}
