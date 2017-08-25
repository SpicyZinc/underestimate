/*
Next Permutation
which rearranges numbers into the lexicographically next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place, do not allocate extra memory. 

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

idea:
http://blog.unieagle.net/2012/10/16/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Anext-permutation/
http://blog.csdn.net/linhuanmars/article/details/20434115

Take [2,3,1] as an example,
1.Find out the first ascending pair from the end of the list.
2<3.
Assuming the index of first element as i, the latter as j. Each time when j=i+1, reset the j to the end of list and i--.
Turn to step 4 if not found.
2.if i >= 0, swap the number at indexes i and j.
After this done, the elements after i is also in descending order.
3.Reverse the elements after i. And return.
4.If no ascending pair is found, that means the given list is well sorted in the descending order. 
In this case, just reverse the whole list as required in this problem.

离散数学书标准算法:
先每位排序，从最小开始，从左到右，
设当前处理位 ＝ 最后一位；
A: 从当前处理位开始，向左扫描，找到数值比它小的一位i，交换，i 后边所有位排序
，从小到大，从左到右，重设当前处理位 ＝ 最后一位；若找不到数值比它小的，当前
处理位左移一位， go to A; 如果无法左移，done。

example:
123,
当前处理位 ＝ 最后一位,指向3, 向左扫描，找到比它小的一位,值是2，交换，得
132, 排序3后的所有位，
得132,
当前处理位重设为最后一位，
指向2,向左扫描，找到比它小的一位,值是1,交换得231,排序2后的所有位,得213,
当前处理位重设为最后一位，
指向3,向左扫描，找到比它小的一位，值是1, 交换得231，排序3后的所有位,仍得231,
当前处理位重设为最后一位，
指向1, 向左扫描，无法找到比它小的一位，
当前处理位 左移一位，
指向3,向左扫描，找到比它小的一位,值是2,交换得321,排序3后的所有位,得312,
当前处理位重设为最后一位，
指向2，向左扫描，找到比它小的一位,值是1,交换得321,排序2后的所有位,得321,
当前处理位重设为最后一位,
指向1,
向左扫描，无法找到比1小的一位,
当前处理位 左移一位, 指向2,
向左扫描，无法找到比2小的一位,
当前处理位 左移一位, 指向3,
向左扫描, 无法找到比3小的一位,
无法左移, done.(就本题而言，done 可以 换成 reverse)
*/
public class NextPermutation {
    public void nextPermutation(int[] num) {
        for (int i = num.length - 2; i >= 0; i--) {
		    // find the last one which is greater than num[i]
			// i is the index of first one of the ascending pair
            if (num[i+1] > num[i]) {
                for (int j = num.length - 1; j > i; j--) {
                    if (num[j] > num[i]) {
						swap(num, i, j);
                        break;
                    }
                }
                // reverse the items from i + 1 to num.size() - 1
                reverse(num, i + 1, num.length - 1);
                return;
            }
        }
        // no acending order found, reverse all the numbers
        reverse(num, 0, num.length - 1);
    }
	// method to reverse an array
	// just remember, it is classic
	private void reverse(int[] num, int start, int end) {
		if (start > end) return;
		if (end > num.length) return;
		
		int mid = (start + end) / 2;
		while (start <= mid) {
			int tmp = num[start];
			num[start] = num[end];
			num[end] = tmp;
			start++;
			end--;
		}
	}
	// method swap
	private void swap(int[] a, int x, int y) {
		int tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}
}