/*
Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person. 

Person A will NOT friend request person B (B != A) if any of the following conditions are true:
age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.
Also, people will not friend request themselves.

How many total friend requests are made?

Example 1:
Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.

Example 2:
Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.

Example 3:
Input: [20,30,100,110,120]
Output: 3
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.

Notes:
1 <= ages.length <= 20000.
1 <= ages[i] <= 120.

idea:
*/


class FriendsOfAppropriateAges {
	// TLE, 73 / 83 test cases passed
	public int numFriendRequests(int[] ages) {
		int n = ages.length;

		Map<Integer, Integer> hm = new HashMap<>();

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			// avoid duplicate and save time
			if (hm.containsKey(ages[i])) {
				cnt += hm.get(ages[i]);
				continue;
			}

			for (int j = 0; j < n; j++) {
				if (i != j && canMakeRequest(ages[i], ages[j])) {
					cnt++;
				}
			}

			map.put(ages[i], cnt);
		}

		return cnt;
	}

	// can a make friend request 
	public boolean canMakeRequest(int a, int b) {
		boolean caseOne = b <= 0.5 * a + 7;
		boolean caseTwo = b > a;
		boolean caseThree = b > 100 && a < 100;

		return !caseOne && !caseTwo && !caseThree;
	}

	// with hashmap
	public int numFriendRequests(int[] ages) {
		int n = ages.length;

		Map<Integer, Integer> hm = new HashMap<>();

		int cnt = 0;
		Arrays.sort(ages);
		for (int i = 0; i < n; i++) {
			// avoid duplicate calculation and save time
			if (hm.containsKey(ages[i])) {
				cnt += hm.get(ages[i]);
				continue;
			}

			int sum = 0;
			for (int j = 0; j < n; j++) {
				if (i != j && canMakeRequest(ages[i], ages[j])) {
					sum++;
				}
			}

			hm.put(ages[i], sum);
			cnt += sum;
		}

		return cnt;
	}

	// can a make friend request 
	public boolean canMakeRequest(int a, int b) {
		boolean caseOne = b <= 0.5 * a + 7;
		boolean caseTwo = b > a;
		boolean caseThree = b > 100 && a < 100;

		return !caseOne && !caseTwo && !caseThree;
	}
}