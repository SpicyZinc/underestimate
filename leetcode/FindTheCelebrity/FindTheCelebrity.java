/*
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity.
The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one.
The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B.
You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B.
Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party.
Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

idea:
https://www.cnblogs.com/grandyang/p/5310649.html

1. extra boolean candidates array, at first, all should be celebrity candidates, set them to be true
2. 02/07/2019, without extra array
*/

public class FindTheCelebrity extends Relation {
	// 02/07/2019
	public int findCelebrity(int n) {
		for (int i = 0; i < n; i++) {
			// test i each person can be celebrity
			int j = 0;
			for (; j < n; j++) {
				// not the same person
				if (i != j && (knows(i, j) || !knows(j, i))) {
					// break, i cannot be celebrity
					break;
				}
			}
			// if reach here
			if (j == n) {
				// i must be celebrity
				return i;
			}
		}

		return -1;
	}

	public int findCelebrity(int n) {
		boolean[] candidates = new boolean[n];
		Arrays.fill(candidates, true);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (candidates[i] && i != j) {
					// if i knows j or j not know i, i is not celebrity
					// because all the other (j) know celebrity (i) and celebrity (i) not know any of them (j)
					if ( knows(i, j) || !knows(j, i) ) {
						candidates[i] = false;
						break;
					} else {
						candidates[j] = false;
					}
				}
			}
			if (candidates[i]) {
				return i;
			}
		}

		return -1;
	}
}