/*
Suppose you have a long flowerbed in which some of the plots are planted and some are not.
However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n,
return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True

Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False

Note:
The input array won't violate no-adjacent-flowers rule.
The input array size is in the range of [1, 20000].
n is a non-negative integer which won't exceed the input array size.

idea:
for those qualified plots, fill out with one
*/

public class CanPlaceFlowers {
	public static void main(String[] args) {
		CanPlaceFlowers eg = new CanPlaceFlowers();
		int[] flowerbed = {0,0,1,0,1};
		int n = 1;
		boolean can = eg.canPlaceFlowers(flowerbed, n);
		System.out.println(can);
	}
	// self written, because did not fill out 1, so patch and patch
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 1) {
            return flowerbed[0] == 0 && n <= 1 || flowerbed[0] == 1 && n == 0;
        }
		int cnt = 0;
		int planted = 0;
		int last = -1;
		for (int i = 0; i < flowerbed.length - 1; i++) {
			if (flowerbed[i] == 0 && flowerbed[i + 1] == flowerbed[i]) {
				cnt++;
				if (i == 0) {
					last = 0;
				}
			}
			else {
				cnt += 1;
				if (cnt >= 2 && last == 0) {
					planted += cnt / 2;
				}
				else if (cnt >= 3 && last == -1) {
					planted += cnt % 2 == 0 ? (cnt - 2)/2 : (cnt - 1)/2;
				}
				// reset
				cnt = 0;
				last = -1;
			}
		}
		if (flowerbed[flowerbed.length - 1] == 0) {
			if (cnt == 1) {
				planted += 1;
			}
			else if (cnt == flowerbed.length - 1) {
			    return (cnt + 2)/2 >= n;
			}
			else if (cnt >= 2 && cnt != flowerbed.length - 1) {
				cnt += 1;
				planted += cnt / 2;
			}
		}
		return planted >= n;
	}
	// correct way
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		int planted = 0;
		int i = 0;
		while (i < flowerbed.length) {
			if (flowerbed[i] == 0) {
				if ( (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) ) {
					flowerbed[i] = 1;
					planted++;
				}
			}
			i++;
		}
		return planted >= n;
	}
}