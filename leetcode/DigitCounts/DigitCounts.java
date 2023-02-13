/*
Count the number of k's between 0 and n. k can be 0 - 9.

Example
if n = 12, k = 1 in [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
we have FIVE 1's (1, 10, 11, 12)

idea:
find rule
compare each digit against k
== k
> k
< k
note: k == 0
记住如何求某个位置上的数

https://www.cnblogs.com/EdwardLiu/p/4274497.html
当某一位的数字小于i时, 那么该位出现i的次数为: 更高位数字x当前位数
当某一位的数字等于i时, 那么该位出现i的次数为: 更高位数字x当前位数+低位数字+1
当某一位的数字大于i时, 那么该位出现i的次数为: (更高位数字+1)x当前位数
*/

public class DigitCounts {
    /*
     * @param : An integer
     * @param : An integer
     * @return: An integer denote the count of digit k in 1..n
     */
	public int digitCounts(int k, int n) {
        if (k == 0 && n == 0) {
            return 1;
        }
        
        int count = 0;
        int base = 1;

        // calculate each digit, this while loop
        while (n / base >= 1) {
            int currDigit = (n / base) % 10;
            int numberBeforeCurr = n / (base * 10);
            int numberafterCurr = n % base;

            if (currDigit == k) {
                count += numberBeforeCurr * base + numberafterCurr + 1;
            } else if (currDigit < k) {
                count += numberBeforeCurr * base;
            } else {
                count += (numberBeforeCurr + 1) * base;
            }

            base *= 10;
            // k == 0, no need to calculate other digits, enough
            if (k == 0) {
                return count;
            }
        }

        return count;
    }
	// direct method
	public int digitCounts(int k, int n) {
        int cnt = 0;
        for (int i = 0; i <= n; i++) {
            int num = i;
            while (num > 0) {
                if (num % 10 == k) {
                    cnt++;
                }
                num /= 10;
            }
        }
        
        if (k == 0) {
            cnt++;
        }

        return cnt;
    }
}