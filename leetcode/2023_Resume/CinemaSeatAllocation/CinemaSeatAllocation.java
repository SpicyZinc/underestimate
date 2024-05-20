/*
A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row, labelled from 1 to 10 as shown in the figure above.
Given the array reservedSeats containing the numbers of seats already reserved, for example, reservedSeats[i] = [3,8] means the seat located in row 3 and labelled with 8 is already reserved.
Return the maximum number of four-person groups you can assign on the cinema seats. A four-person group occupies four adjacent seats in one single row. Seats across an aisle (such as [3,3] and [3,4]) are not considered to be adjacent, but there is an exceptional case on which an aisle split a four-person group, in that case, the aisle split a four-person group in the middle, which means to have two people on each side.


Example 1:
https://assets.leetcode.com/uploads/2020/02/14/cinema_seats_3.png
Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
Output: 4
Explanation: The figure above shows the optimal allocation for four groups, where seats mark with blue are already reserved and contiguous seats mark with orange are for one group.

Example 2:
Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
Output: 2

Example 3:
Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
Output: 4

Constraints:
1 <= n <= 10^9
1 <= reservedSeats.length <= min(10*n, 10^4)
reservedSeats[i].length == 2
1 <= reservedSeats[i][0] <= n
1 <= reservedSeats[i][1] <= 10
All reservedSeats[i] are distinct.

idea:
hashmap
        // Greedy approach
        // 1-indexed
        // First possibility: Split seating at  [2, 3, 4, 5]
        // Second possibility: Split seating at [            6, 7, 8, 9]
        // Only possibility:  Center four seats [      4, 5, 6, 7]
*/

class CinemaSeatAllocation {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> rowToSeatRes = new HashMap<>();
    
        for (int[] row : reservedSeats) {
            int rowNumber = row[0];
            int reservedSeat = row[1];

            rowToSeatRes.computeIfAbsent(rowNumber, x -> new HashSet<Integer>()).add(reservedSeat);
        }
            
        // if no reserved seat in a row, then this row has 2 families seat as case 1 and case 2
        int numberOfFamilies = (n - rowToSeatRes.size()) * 2;
            
        // Check possible family seating in each row 
        for (Set<Integer> set : rowToSeatRes.values()) {
            boolean flag = false;
            
            // Check first possibility
            if (!set.contains(2) &&
            !set.contains(3) &&
            !set.contains(4) &&
            !set.contains(5)) {
                numberOfFamilies++;
                flag = true;
            }
            
            // Check second possibility
            if (!set.contains(6) &&
            !set.contains(7) &&
            !set.contains(8) &&
            !set.contains(9)) {
                numberOfFamilies++;
                flag = true;
            }
            
            // Check middle seats only if first two are not used
            if (!flag) {
                if (!set.contains(4) &&
                !set.contains(5) &&
                !set.contains(6) &&
                !set.contains(7))
                numberOfFamilies++;
            }
        }
        
        return numberOfFamilies;
    }
}


// js
/**
 * @param {number} n
 * @param {number[][]} reservedSeats
 * @return {number}
 */
var maxNumberOfFamilies = function(n, reservedSeats) {
    const map = new Map()

    for (const [row, col] of reservedSeats) {
        const cols = map.get(row) || new Set()
        cols.add(col)
        map.set(row, cols)
    }

    let result = (n - map.size) * 2
    
    for (const cols of map.values()) {
        const isFirstFree = ![2, 3, 4, 5].some(col => cols.has(col))
        const isLastFree = ![6, 7, 8, 9].some(col => cols.has(col))
        const isMidFree = ![4, 5, 6, 7].some(col => cols.has(col))

        result += Math.max(isFirstFree + isLastFree, isMidFree)
    }

    return result
};