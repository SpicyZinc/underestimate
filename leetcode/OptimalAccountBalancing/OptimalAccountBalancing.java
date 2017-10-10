/*
A group of friends went on holiday and sometimes lent each other money.
For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride.
We can model each transaction as a tuple (x, y, z) which means person x gave person y $z.
Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID),
the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:
A transaction will be given as a tuple (x, y, z). Note that x ? y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.

Example 1:
Input:
[[0,1,10], [2,0,5]]
Output:
2
Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.

Example 2:
Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
Output:
1
Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.

idea:
https://fisherlei.blogspot.com/2017/07/leetcode-optimal-account-balancing.html

Sarting from first debt debt[0], look for all other debts debt[i] (i >= 1) which have opposite sign to debt[0].
Then each such debt[i] can make one transaction debt[i] += debt[0] to clear the person with debt debt[0].
From now on, the person with debt debt[0] is dropped out of the problem and we recursively drop persons one by one until everyone's debt is cleared
meanwhile updating the minimum number of transactions during DFS.

note: recursion in for loop
*/

import java.util.*;

class OptimalAccountBalancing {
	public static void main(String[] args) {
		OptimalAccountBalancing eg = new OptimalAccountBalancing();
		int[][] transactions = {
			{0,1,10},
			{2,0,5},
			// {0,1,10},
			// {1,0,1},
			// {1,2,5},
			// {2,0,5},
		};
		int minimum = eg.minTransfers(transactions);
	}

	long[] debts;
	public int minTransfers(int[][] transactions) {
		Map<Integer, Long> balance = new HashMap<Integer, Long>();
		for (int[] transaction : transactions) {
			int lender = transaction[0];
			int borrower = transaction[1];
			int money = transaction[2];

			balance.put(lender, balance.getOrDefault(lender, 0L) - money);
			balance.put(borrower, balance.getOrDefault(borrower, 0L) + money);
		}
		// record all non-zero balance
		List<Long> list = new ArrayList<Long>();
		for (long val : balance.values()) {
			if (val != 0) {
				list.add(val);
			}
		}
		debts = new long[list.size()];
		int i = 0;
		for (long val : list) {
			debts[i++] = val;
		}

		return dfs(0, 0);
	}

	public int dfs(int index, int cnt) {
		while (index < debts.length && debts[index] == 0) {
			index++;
		}
		int minTransactions = Integer.MAX_VALUE;
		long prev = 0;
		for (int i = index + 1; i < debts.length; i++) {
			// skip same value or same sign debt
			if (debts[i] != prev && debts[index] * debts[i] < 0) {
				// add debts[index]
				debts[i] += debts[index];
				minTransactions = Math.min(minTransactions, dfs(index + 1, cnt + 1));
				// remove debts[index]
				debts[i] -= debts[index];
				prev = debts[i];
			}
    	}

		return minTransactions == Integer.MAX_VALUE ? cnt : minTransactions;
	}
}