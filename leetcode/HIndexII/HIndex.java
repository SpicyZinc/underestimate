/*
Follow up for H-Index: 
What if the citations array is sorted in ascending order? Could you optimize your algorithm?

Hint:
Expected runtime complexity is in O(log n) and the input is sorted.

idea:
sort the array first
encountering point, the maximum h index point
the biggest number of elements having that big index
it is a compromise, 
some case h-index could be even bigger, but no that many elements
some case h-index could be small, so satisfying number is too many
*/

public class HIndex {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int maxCit = 0;
        for ( int i = 0; i < citations.length; i++ ) {
            int cit = Math.min(citations[i], citations.length - i);
            if ( cit >= maxCit ) {
                maxCit = cit;
                continue;
            }
            // once min from the length of remaining elements, break
            break;
        }
        return maxCit;
    }
}