/**
idea:
one contains bits that appear 3k+1 times in array A, two contains bits that appear 3k+2 times in array A.
*/

public class SingleNumberII {
	public static void main(String[] args) {
		new SingleNumberII();
	}

	public SingleNumberII() {
		int[] a = {2, 3, 3, 3};

		System.out.println( "The number only appearing once is: " + singleNumber(a) );
		System.out.println( "The number only appearing once is via singleNumberII_36: " + singleNumberII_36(a) );
	}
    public int singleNumber(int[] A) {
        int one = 0, two = 0;
        for (int i = 0; i < A.length; i++) {
            int one_ = (one ^ A[i]) & ~two;
            int two_ = A[i] & one | ~A[i] & two;
            one = one_;
            two = two_;
        }
        return one;
    }
}