/*
idea:
http://rleetcode.blogspot.com/2014/01/candy-java.html
similar to Trapping Rain Water: one-dimentional DP

declare candies array represent how many candies each child should have
then loop from left to right to make the candies number of each child meet the requirment, 
then loop from right to left make the candies array meet the requirement
finally, calculate the total candies number

time complexity: O(2*n) = O(n)
space complexity: O(n)

note:
loop through array, each rating could be bigger or less than its right or left element
note 2nd loop candies[i] <= candies[i+1]

*/
public class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        
        int[] candies = new int[ratings.length];
        // every child should has at least one candy
        for (int i=0; i<candies.length; i++) {
            candies[i]=1;
        }
        // if child i has rating higher than i-1, which should be 1 bigger than its left neighbour
        for (int i=1; i<ratings.length; i++) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }
        // if child i has rating higher than its right neighbour, 
        // but the candies array is not representing this situation correctly, then correct it.
        for (int i=ratings.length-2; i>=0; i--) {
            if (ratings[i] > ratings[i+1] && candies[i] <= candies[i+1]) {
                candies[i] = candies[i+1] + 1;
            }
        }
        
        int total = 0;
        // calculate the total candies needed
        for (int i=0; i<candies.length; i++) {
            total += candies[i];
        }
        
        return total;
    }
}