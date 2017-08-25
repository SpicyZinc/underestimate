/*
idea:
very similar to Remove Element leetcode

remember:
i for all elements in A array
j for unique elements in A array

for example
1 1 1 2 3
1 2 1 2 3
1 2 3 2 3

j controls 1 2 3
i controls 1 1 1 2 3
*/

public class RemoveDuplicatesBest {
    public int removeDuplicates(int[] A) {
		if (A.length < 2) {
			return A.length;
		}
	 
		int j = 0;
		int i = 1;
		while (i < A.length) {
			if (A[i] != A[j]) {
				j++;
				A[j] = A[i];
			}
			i++;
		}
	 
		return j + 1;
	}
}