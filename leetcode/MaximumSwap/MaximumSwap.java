/*
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number.
Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:
Input: 9973
Output: 9973
Explanation: No swap.

idea:
from left, find the right most and greatest digit, swap them.
why need to be right most because leave the ones equal to the greatest where they are, this is greedy algorithm
e.g. 1993
*/

class MaximumSwap {
	public int maximumSwap(int num) {
		String number = "" + num;
		int[] positions = new int[10];
		// i from 0 is a must, record the right most position
		for (int i = 0; i < number.length(); i++) {
			positions[number.charAt(i) - '0'] = i;
		}

		for (int i = 0; i < number.length(); i++) {
			// for each digit, get right most and greatest digit bigger than it
			// greatest, so starting from 9
			char current = number.charAt(i);
			for (int j = 9; j > current - '0'; j--) {
				if (positions[j] > i) {
					return Integer.parseInt( swap(number, i, positions[j]) );
				}
			}
		}

		return num;
	}

	// 102 / 111 test cases passed
	public int maximumSwap(int num) {
		String number = "" + num;
        if (number.length() <= 1 || number == null) {
            return num;
        }
		int i = 0;
		int pos = findPos(number, i);
		if (pos > 0) {
			return Integer.parseInt(swap(number, i, pos));
		}
		while (pos == -1) {
			i++;
			pos = findPos(number, i);
            // 98368
            if (pos > 0) {
				return Integer.parseInt(swap(number, i, pos));
			}
			if (i == number.length() - 1) break; 
		}

		return num;
	}

	public int findPos(String s, int pos) {
		char c = s.charAt(pos);
		char max = c;
		int maxPos = -1;
		for (int i = pos + 1; i < s.length(); i++) {
			char restChar = s.charAt(i);
            // 1993
			if (max <= restChar && i > maxPos) {
				max = restChar;
				maxPos = i;
			}
		}
		return maxPos;
	}

	public String swap(String s, int i, int j) {
		char[] arr = s.toCharArray();
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp; 

		return new String(arr);
	}
}