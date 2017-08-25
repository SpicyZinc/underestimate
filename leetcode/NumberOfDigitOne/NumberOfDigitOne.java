/*
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

idea:
https://segmentfault.com/a/1190000007056348

suppose n = abcde

if(c==0) ans = ab*100;
if(c==1) ans = ab*100+cd+1
if(c>1) ans = (ab+1)*100

http://blog.csdn.net/u012243115/article/details/47951421

对这个数字的每一位求存在1的数字的个数. 从个位开始到最高位.
举个例子, 求百位上的1
n = 54215, 它的百位上是2, 大于1, 从xx100到xx199的百位上都是1, 这里xx从0到54, 即100->199, 1100->1199...54100->54199, 这些数的百位都是1, 因此百位上的1总数是55*100
如果n是54125, 这时由于它的百位是1, 先看xx100到xx199, 其中xx是0到53, 即54*100, 然后看54100到54125, 这是26个. 所以百位上的1的总数是54*100 + 26.
如果n是54025, 那么只需要看xx100到xx199中百位上的1, 这里xx从0到53, 总数为54*100
求其他位的1的个数的方法是一样的. 

this is a problem to write down as many numbers as possible, and find the rule
use (x+8) / 10 >= 1 to judge if x >= 2
*/

public class NumberOfDigitOne {
    // passed oj
    public int countDigitOne(int n) {
        int res = 0;
        long base = 1;
        long left, right;

        while (n >= base) {
            left = n / base;
            right = n % base;

            if (left % 10 > 1) {
                res += (left / 10 + 1) * base;
            }
            else if (left % 10 == 1) {
                res += (left / 10) * base + (right + 1);
            }
            else {
                res += (left / 10) * base;
            }
            base *= 10;
        }

        return res;
    }
    // self written
    public int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }

        int res = 0;
        int base = 1;
        int left = 0, right = 0;
        int q = n;
        while (q > 0) {
            right = q % 10;
            left = q / 10;
            if (right == 0) {
                res += left * base;
            }
            else if (right == 1) {
                res += left * base + n % base + 1;
            }
            else {
                res += (left + 1) * base;
            }
            q /= 10;
            base *= 10;
        }

        return res;
    }
    // method not understand
    public int countDigitOne(int n) {
        int res = 0, a = 1, b = 1;
        while (n > 0) {
            res += (n + 8) / 10 * a;
            if ( n % 10 == 1 ) {
                res += b;
            }
            b += n % 10 * a;
            a *= 10;
            n /= 10;
        }

        return res;
    }
}