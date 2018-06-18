/*
Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters
so that all houses could be covered by those heaters.
So, your input will be the positions of houses and heaters seperately,
and your expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.

Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.

Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heaters were placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.

idea:
for each house find the nearest heater, calculate the distance between the house and this heater,
keep track of the bigger one

or
https://discuss.leetcode.com/topic/71460/short-and-clean-java-binary-search-solution

https://www.cnblogs.com/grandyang/p/6181626.html
*/

public class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        
        int minRadius = 0;
        
        int len = heaters.length;
        int i = 0;
        
        for (int housePosition : houses) {
            while (i < len - 1) {
                // no difference, no radius needed, find the smallest difference
                int diffWithPrevHouse = Math.abs(housePosition - heaters[i]);
                int diffWithNextHouse = Math.abs(housePosition - heaters[i + 1]);
                if (diffWithPrevHouse >= diffWithNextHouse) {
                    i++;
                } else {
                    break;
                }
            }
            // 最大的minRadius
            minRadius = Math.max(minRadius, Math.abs(housePosition - heaters[i]));
        }
        
        return minRadius;
    }
}