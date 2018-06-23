/*
Imagine you have a special keyboard with the following keys:
Key 1: (A): Prints one 'A' on screen.
Key 2: (Ctrl-A): Select the whole screen.
Key 3: (Ctrl-C): Copy selection to buffer.
Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.
Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.
Example 1:
Input: N = 3
Output: 3
Explanation: 
We can at most get 3 A's on screen by pressing following key sequence:
A, A, A
Example 2:
Input: N = 7
Output: 9
Explanation: 
We can at most get 9 A's on screen by pressing following key sequence:
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
Note:
1 <= N <= 50
Answers will be in the range of 32-bit signed integer.

idea:
recursion
N steps at least N of A
全选 Ctrl A 复制 Ctrl C 要用去两步
剩下的 N - 2 里 有 print 和 Ctrl V
actually N - 1, N two steps needed to copy and paste, set aside at least 2 steps
N - 2 steps cannot be all print, [1, N - 3]
so loop print times through the rest [1, N - 3]

or DP
*/


class FourKeysKeyboard {
	public static void main(String[] args) {
		FourKeysKeyboard eg = new FourKeysKeyboard();
		System.out.println(eg.maxA(7));
	}

	public int maxA(int N) {
		int max = N;
		for (int i = 1; i <= N - 3; i++) {
			// 别忘了加上它本身
			int numberOfA = maxA(i) * ((N - 2 - i) + 1);
			max = Math.max(max, numberOfA);
		}

		return max;
	}

	// dp[i] i steps, # of A can be printed out
	public int maxAs(int N) {
		int[] dp = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			// at least i of A can be printed out
			dp[i] = i;
			for (int j = 1; j < i - 2; j++) {
				dp[i] = Math.max(dp[i], dp[j] * (i - 1 - j));
			}
		}

		return dp[N];
	}
}