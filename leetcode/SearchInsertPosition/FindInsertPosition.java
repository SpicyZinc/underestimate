/*
recursive binary search version
return index of smallest number greater than target
where the target is inserted

Attention: end is the length of array
interesting
*/

public class FindInsertPosition {
	
	public static void main(String[] args) {
		FindInsertPosition aTest = new FindInsertPosition();
		int[] a = {1,3,5,6};
		int target = Integer.parseInt(args[0]);
		System.out.println("target insertion position is " + aTest.FindInsertionPoint(a, target));
	}

	public int FindInsertionPoint(int[] A, int T) {
		int end = A.length;
		return FindInsertionPointBS(A, 0, end, T);
	}

	public int FindInsertionPointBS(int A[], int start, int end, int T) {
		if (start == end) return end;
		int mid = start + (end - start) / 2;
		if (A[mid] > T) {
			return FindInsertionPointBS(A, start, mid, T);
		}
		else {
			return FindInsertionPointBS(A, mid + 1, end, T);
		}
	}
}


