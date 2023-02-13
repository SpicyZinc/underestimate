/*
idea:

Loop all fruit c in tree,
Note that a and b are the last two different types of fruit that we met,
c is the current fruit type,
so it's something like "....aaabbbc..."

Case 1 c == b:
fruit c already in the basket,
and it's same as the last type of fruit
cur += 1
count_b += 1

Case 2 c == a:
fruit c already in the basket,
but it's not same as the last type of fruit
cur += 1
count_b = 1
a = b, b = c

Case 3 c != b && c!= a:
fruit c not in the basket,
cur = count_b + 1
count_b = 1
a = b, b = c

Of course, in each turn we need to update res = max(res, cur)
*/
class FruitIntoBaskets {
    public int totalFruit(int[] tree) {
        // track last two fruits seen
        int lastSeenFruit = -1;
        int secondToLastSeenFruit = -1;
        int lastSeenFruitCount = 0;

        int currMax = 0;
        int max = 0;
        
        for (int fruit : tree) {
            if (fruit == lastSeenFruit || fruit == secondToLastSeenFruit) {
                currMax++;
            } else {
                currMax = lastSeenFruitCount + 1; // last fruit + new fruit
            }
            
            if (fruit == lastSeenFruit) {
                lastSeenFruitCount++;
            } else {
                lastSeenFruitCount = 1; 
            }
            
            if (fruit != lastSeenFruit) {
                secondToLastSeenFruit = lastSeenFruit;
                lastSeenFruit = fruit;
            }
            
            max = Math.max(max, currMax);
        }
        
        return max;
    }
}
