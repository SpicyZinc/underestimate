/*
N couples sit in 2N seats arranged in a row and want to hold hands.
We want to know the minimum number of swaps so that every couple is sitting side by side.
A swap consists of choosing any two people, then they stand up and switch seats.

The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order,
the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.

Example 1:
Input: row = [0, 2, 1, 3]
Output: 1
Explanation: We only need to swap the second (row[1]) and third (row[2]) person.

Example 2:
Input: row = [3, 2, 0, 1]
Output: 0
Explanation: All couples are already seated side by side.
Note:

len(row) is even and in the range of [4, 60].
row is guaranteed to be a permutation of 0...len(row)-1.

idea
swap()
use hash to remember value to index
in swap remember to update
*/
class CouplesHoldingHands {
	public int minSwapsCouples(int[] row) {
		int n = row.length;
		int[] positions = new int[n];
		// positions to save value -> index pair
		for (int i = 0; i < n; i++) {
			positions[row[i]] = i;
		}
		int cnt = 0;
		// walk the row through every two seats
		for (int i = 0; i < n; i += 2) {
			int oneSpouse = row[i];
			int anotherSpouse = (oneSpouse % 2 == 0 ? oneSpouse + 1 : oneSpouse - 1);
			// if not sitting side by side by checking if index is consecutive
			if (i + 1 != positions[anotherSpouse]) {
				swap(row, positions, i + 1, positions[anotherSpouse]);
				cnt++;
			}
		}
        
        return cnt;
	}

	public void swap(int[] row, int[] positions, int x, int y) {
		int partner = row[x];
		// update positions[]
		positions[row[x]] = y;
		positions[row[y]] = x;
		// update row[]
		row[x] = row[y];
		row[y] = partner;
	}
}