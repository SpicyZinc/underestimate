/*
A queue of people are waiting to buy ice cream from you. 
Each person buys one ice cream, which sells for $5. 
Each customer is holding a bill of $5, $10 or $20. 
Your initial balance is 0. 
Find whether you will be able to make change for every customer in the queue. You must serve customers in the order they come in. 
For example 
5, 5, 5, 10, 20 -> true, 
5, 5, 10 -> true, 
10, 10 -> false

idea:
直接一个一个情况的算
https://aonecode.com/aplusplus/interviewctrl/getInterview/6410672447442116351

a[0] $5 cnt
a[1] $10 cnt
*/

class SellIceCream {
	public static void main(String[] args) {
		SellIceCream eg = new SellIceCream();

		// int[] bills = new int[] {5, 5, 5, 10, 20};
		// int[] bills = new int[] {5, 5, 10};
		int[] bills = new int[] {10, 10};

		boolean can = eg.sellIceCream(bills);

		System.out.println(can);
	}

	public boolean sellIceCream(int[] bills) {
		int[] a = new int[2];

		for (int m : bills) {
			if (m == 5) {
				a[0]++;
			} else if (m == 10) {
				if (a[0] < 1) {
					return false;
				} else {
					a[0]--;
		    		a[1]++;
				}
			} else if (m == 20) {
				if (a[1] >= 1) {
					a[1]--;
					if (a[0] > 0) {
						a[0]--;
					} else {
						return false;
					}
				} else {
					if (a[0] >= 3) {
						a[0] -= 3;
					} else {
						return false;
					}
				}
			}
		}

		return true;
	}
}

