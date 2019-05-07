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
https://www.cnblogs.com/grandyang/p/6108158.html

Starting from first debt debt[0], look for all other debts debt[i] (i >= 1) which have opposite sign to debt[0].
Then each such debt[i] can make one transaction debt[i] += debt[0] to clear the person with debt debt[0].
From now on, the person with debt debt[0] is dropped out of the problem and we recursively drop persons one by one until everyone's debt is cleared
meanwhile updating the minimum number of transactions during DFS.

note: recursion in for loop
in essence, it is dfs()

建立一个图 每个人 欠 被欠的钱 用 hashmap
A owes B $5
C need return from D $5
可以抹平 这个 就让 A 拿出 $5 相当于给这个大pool 虽然不是A直接欠C
这两个balance account 就可以清走了
*/

import java.util.*;

class OptimalAccountBalancing {
	public static void main(String[] args) {
		OptimalAccountBalancing eg = new OptimalAccountBalancing();
		int[][] transactions = {
			// {0,1,10},
			// {2,0,5},
			{0,1,10},
			{1,0,1},
			{1,2,5},
			{2,0,5},
		};

		int minimum = eg.minTransfers(transactions);

		System.out.println(minimum);
	}

	// Mon May  6 21:49:27 2019
	// output who gives who money to settle the debt
	class Debt {
		int val;
		int person;

		public Debt(int val, int person) {
			this.val = val;
			this.person = person;
		}
	}

	public int minTransfers(int[][] transactions) {
		Map<Integer, Integer> balance = new HashMap<>();

		for (int[] transaction : transactions) {
			int lender = transaction[0];
			int borrower = transaction[1];
			int money = transaction[2];

			balance.put(lender, balance.getOrDefault(lender, 0) - money);
			balance.put(borrower, balance.getOrDefault(borrower, 0) + money);
		}

		List<Debt> debts = new ArrayList<>();

		for (Map.Entry<Integer, Integer> entry : balance.entrySet()) {
			int person = entry.getKey();
			int debt = entry.getValue();

			if (debt != 0) {
				debts.add(new Debt(debt, person));
			}
		}

		return getMinTransactions(debts, 0, 0, new ArrayList<String>());
	}
	// 从第一开始 用 [pos + 1, size - 1] 的抹平 debt
	public int getMinTransactions(List<Debt> debts, int pos, int cnt, List<String> result) {
		int size = debts.size();

		while (pos < size && debts.get(pos).val == 0) {
			pos++;
		}

		if (pos == size) {
			List<String> copy = new ArrayList<>(result);
			for (String s : copy) {
				System.out.println(s);
			}
		}

		int minCnt = Integer.MAX_VALUE;
		for (int i = pos + 1; i < size; i++) {
			if (debts.get(pos).val * debts.get(i).val < 0) {

				result.add(debts.get(i).person + "->" + debts.get(pos).person + " " + debts.get(i).val);
				debts.get(i).val += debts.get(pos).val;

				minCnt = Math.min(minCnt, getMinTransactions(debts, pos + 1, cnt + 1, result));

				debts.get(i).val -= debts.get(pos).val;
				result.remove(result.size() - 1);
			}
		}

		return minCnt == Integer.MAX_VALUE ? cnt : minCnt;
	}



	// public int minTransfers(int[][] transactions) {
	// 	Map<Integer, Long> balance = new HashMap<Integer, Long>();
	// 	for (int[] transaction : transactions) {
	// 		int lender = transaction[0];
	// 		int borrower = transaction[1];
	// 		int money = transaction[2];

	// 		balance.put(lender, balance.getOrDefault(lender, 0L) - money);
	// 		balance.put(borrower, balance.getOrDefault(borrower, 0L) + money);
	// 	}
	// 	// record all non-zero balance
	// 	List<Long> list = new ArrayList<Long>();
	// 	for (long val : balance.values()) {
	// 		if (val != 0) {
	// 			list.add(val);
	// 		}
	// 	}
	// 	// convert list to array
	// 	long[] debtAccounts = new long[list.size()];
	// 	int i = 0;
	// 	for (long val : list) {
	// 		debtAccounts[i++] = val;
	// 	}

	// 	return dfs(debtAccounts, 0, 0);
	// }

	// public int dfs(long[] debts, int start, int cnt) {
	// 	while (start < debts.length && debts[start] == 0) {
	// 		start++;
	// 	}

	// 	int minTransactions = Integer.MAX_VALUE;
	// 	for (int i = start + 1; i < debts.length; i++) {
	// 		// skip same value or same sign debt
	// 		if (debts[start] * debts[i] < 0) {
	// 			// add debts[start]
	// 			debts[i] += debts[start];
	// 			minTransactions = Math.min(minTransactions, dfs(debts, start + 1, cnt + 1));
	// 			// remove debts[start]
	// 			debts[i] -= debts[start];
	// 		}
	// 	}

	// 	return minTransactions == Integer.MAX_VALUE ? cnt : minTransactions;
	// }
}