/*
You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:

numberOfBoxesi is the number of boxes of type i.
numberOfUnitsPerBoxi is the number of units in each box of the type i.
You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.

Return the maximum total number of units that can be put on the truck.

Example 1:
Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
Output: 8
Explanation: There are:
- 1 box of the first type that contains 3 units.
- 2 boxes of the second type that contain 2 units each.
- 3 boxes of the third type that contain 1 unit each.
You can take all the boxes of the first and second types, and one box of the third type.
The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.

Example 2:
Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
Output: 91

Constraints:
1 <= boxTypes.length <= 1000
1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
1 <= truckSize <= 106

idea:
sort by numberOfUnitsPerBoxi descending
then greedy
*/
class MaximumUnitsOnATruck {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);

        int cntUnit = 0;
        for (int[] item : boxTypes) {
            int numberOfBoxes = item[0];
            int numberOfUnitsPerBox = item[1];

            if (truckSize >= numberOfBoxes) {
                cntUnit += numberOfUnitsPerBox * numberOfBoxes;
                truckSize -= numberOfBoxes;
            } else {
                // note maybe truckSize is not enough, use truckSize
                cntUnit += numberOfUnitsPerBox * truckSize;
                // return early
                return cntUnit;
            }
        }

        return cntUnit;
    }
}
