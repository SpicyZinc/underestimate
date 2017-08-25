/*
For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.
Now given a string representing n, you should return the smallest good base of n in string format. 

Example 1:
Input: "13"
Output: "3"
Explanation: 13 base 3 is 111.

Example 2:
Input: "4681"
Output: "8"
Explanation: 4681 base 8 is 11111.

Example 3:
Input: "1000000000000000000"
Output: "999999999999999999"
Explanation: 1000000000000000000 base 999999999999999999 is 11.

Note:
The range of n is [3, 10^18].
The string representing n is always valid and will not have leading zeros.

idea:
http://blog.csdn.net/guoyuhaoaaa/article/details/54782315
无论如何最后该aim的表示形式一定是全‘1’的, 如果是最小的base那么也就意味着最长的‘1’串.
可以固定一个‘1’串来找是否有合适的base可以满足, 而在固定‘1’串找base的时候可以使用二分查找来节省时间.
如果当前的‘1’串都不合适, 那么我们就可以减少‘1’串的长度, 知道找到为止, 这时候‘1’串对应的base一定是最小的base.
由于aim的范围是在[3, 10^18], 那么‘1’串最长无非是base为2的时候
*/

public class SmallestGoodBase {
    public String smallestGoodBase(String n) {
        long num = 0;
        for (char c : n.toCharArray()) num = num * 10 + c - '0';
        
        long x = 1;
        for (int p = 2; p < 100; p++) {
            if ((x << p) < num) {
                long k = helper(num, p);
                if (k != -1) {
                	return String.valueOf(k);
                }
            }
        }
        return String.valueOf(num - 1);
    }
    
    private long helper(long num, int p) {
        long l = 1, r = (long)(Math.pow(num, 1.0/p) + 1);
        while (l < r) {
            long mid = l + (r - l) / 2;
            long sum = 0, cur = 1;
            for (int i = 0; i <= p; i++) {
                sum += cur;
                cur *= mid;
            }
            if (sum == num) return mid;
            else if (sum > num) r = mid;
            else l = mid + 1;
        }
        return -1;
    }
}