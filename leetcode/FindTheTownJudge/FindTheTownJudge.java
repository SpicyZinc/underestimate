/*
In a town, there are N people labeled from 1 to N. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:
The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given trust, an array of pairs trust[i] = [a, b] representing that the person labeled a trusts the person labeled b.

If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.

Example 1:
Input: N = 2, trust = [[1,2]]
Output: 2

Example 2:
Input: N = 3, trust = [[1,3],[2,3]]
Output: 3

Example 3:
Input: N = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1

Example 4:
Input: N = 3, trust = [[1,2],[2,3]]
Output: -1

Example 5:
Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
Output: 3
 

Note:
1 <= N <= 1000
trust.length <= 10000
trust[i] are all different
trust[i][0] != trust[i][1]
1 <= trust[i][0], trust[i][1] <= N

idea:
treat every person as judge,
check against all others (cntTrustI and isJudge)
see if this person is the judge
*/

class FindTheTownJudge {
	// save time and waste space
	public int findJudge(int N, int[][] trust) {
		int[] count = new int[N + 1];
		for (int[] t : trust) {
			count[t[0]]--;
			count[t[1]]++;
		}

		for (int i = 1; i <= N; ++i) {
			if (count[i] == N - 1) {
				return i;
			}
		}

		return -1;
	}

	public int findJudge(int N, int[][] trust) {
		for (int i = 1; i <= N; i++) {
			boolean isJudge = true;
			int cntTrustI = 0;

			for (int[] pair : trust) {
				int a = pair[0];
				int b = pair[1];
				// 别人都信他
				if (i == b) {
					cntTrustI++;
				}
				// 不信任何人
				if (i == a) {
					isJudge = false;
					break;
				}
			}

			if (isJudge && cntTrustI == N - 1) {
				return i;
			}
		}

		return -1;
	}
}