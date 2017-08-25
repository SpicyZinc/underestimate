/*
You are given two jugs with capacities x and y liters.
There is an infinite amount of water supply available.
You need to determine whether it is possible to measure exactly z liters using these two jugs.

If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.

Operations allowed:

Fill any of the jugs completely with water.
Empty any of the jugs.
Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
Example 1: (From the famous "Die Hard" example)

Input: x = 3, y = 5, z = 4
Output: True
Example 2:

Input: x = 2, y = 6, z = 5
Output: False


idea:
https://www.hrwhisper.me/leetcode-water-jug-problem/

die hard problem
the problem can be simplified to be
if z can be divided by gcd(x, y)


设有集合S = {m = xa + yb, n > 0}，d是集合S中最小的元素 d = ua + vb
x也是S中的元素，且 x = qd + r，x不被d整除， 那么 r > 0 and r < d

r = x - qd
  = xa + yb - qd
  = xa + yb -q(ua + vb)
  = (x - qu)a + (y-qb)b
那么r也是属于S，并且r < d和d是最小的元素这个假设矛盾，那么我们可以得到S中的元素一定是最小元素d的倍数，也就是能被d整除。

|a|是属于S的，因为a>0，令x=1,y=0, a<0令x=-1,y=0。
同理|b|也属于S。我们题目里面a,b都是大于0的。
d是最小元素，且同时要整除a, b，那么d的范围只能只1 <= d <= gcd(a,b)
我们先得到1<=d<=gcd(a,b)这个结论，我们接下来继续确定d的取值。

d=ua+vb,从gcd(a,b)的定义来看d是能被gcd(a,b)整除的，因为ua能被gcd(a,b)整除，且vb也能
那么d>=gcd(a,b)
1) 1<=d<=gcd(a,b)
2) d >= gcd(a,b)
那么d = gcd(a,b)
所以我们得到S中最小元素d=gcd(a,b)
结合上面的结论得到S中所有元素都是gcd(a,b)的倍数。


*/


public class WaterAndJug {
    public boolean canMeasureWater(int x, int y, int z) {
        return (x+y == z) || (x+y > z) && (z % gcd(x, y) == 0);
    }

    public int gcd(int x, int y) {
    	if (y == 0) {
    		return x;
    	}

    	return gcd(y, x % y);
    }
}