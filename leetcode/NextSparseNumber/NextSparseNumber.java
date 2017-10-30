/*
A number is Sparse if there are no two adjacent 1s in its binary representation.
Given a number n, find the smallest Sparse number which greater than or equal to n.
eg. 5 (binary representation: 101) is sparse, but 6 (binary representation: 110) is not sparse.

Example
Given n = 6, return 8
Next Sparse Number is 8

Given n = 4, return 4
Next Sparse Number is 4

Given n = 38, return 40
Next Sparse Number is 40

Given n = 44, return 64
Next Sparse Number is 64

idea:
	<------
01010001011101
	||
01010001100000
	||
01010010000000

*/

public class nextSparseNumber {
	public int nextSparseNum(int x) {
		// binary representation of x in bits[]
		// note, it is reversely
		List<Integer> bits = new ArrayList<Integer>();
		// convert from integer to binary representation
		while (x != 0) {
		    bits.add(x & 1);
			x >>= 1;
		}
		// there may be extra bit in result, so add one extra bit
		bits.add(0);

		int n = bits.size();
		// The position till which all bits are finalized
		int posFinalized = 0;

		// Start from second bit (next to LSB)
		for (int i = 1; i < n - 1; i++) {
			int prev = bits.get(i - 1);
			int curr = bits.get(i);
			int next = bits.get(i + 1);
			// if current bit and its previous bit are 1, but next bit is not 1
			if (curr == 1 && prev == 1 && next != 1) {
				bits.set(i + 1, 1);
				// Make all bits before current bit as 0 to make sure that we get the smallest next number
				for (int j = i; j >= posFinalized; j--) {
					bits.set(j, 0);
				}
				// Store position of the finalized bit set so that this bit and bits before it are not changed next time
				posFinalized = i + 1;
			}
		}
		// convert from binary to integer
		int nextSparse = 0;
		for (int i = 0; i < n; i++) {
			nextSparse += bits.get(i) * (1 << i);
		}

		return nextSparse;
	}
}