/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place, do not allocate extra memory. 

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

idea:
https://segmentfault.com/a/1190000003766260
https://www.cnblogs.com/grandyang/p/4428207.html
http://blog.csdn.net/linhuanmars/article/details/20434115
https://bangbingsyb.blogspot.com/2014/11/leetcode-next-permutation.html

离散数学书标准算法:
先每位排序, 从最小开始, 从左到右, 

设当前处理位为最后一位;

A: 从当前处理位开始, 向左扫描, 找到数值比它小的一位i, 交换, i(exclusive)后边所有位排序
从小到大, 从左到右;
重设当前处理位为最后一位;
若找不到数值比它小的, 当前处理位左移一位, go to A;
如果无法左移, done, find the next permutation

example:
123 当前处理位设为最后一位,
指向3, 向左扫描, 找到比它小的一位,值是2, 交换得132,排序3后的所有位, 得132,

132 当前处理位重设为最后一位, 
指向2, 向左扫描, 找到比它小的一位,值是1, 交换得231,排序2后的所有位, 得213

213 当前处理位重设为最后一位, 
指向3,向左扫描, 找到比它小的一位, 值是1, 交换得231, 排序3后的所有位, 仍得231

231 当前处理位重设为最后一位, 
指向1, 向左扫描, 无法找到比它小的一位

231 当前处理位 左移一位, 
指向3,向左扫描, 找到比它小的一位,值是2, 交换得321, 排序3后的所有位, 得312

312 当前处理位重设为最后一位,
指向2, 向左扫描, 找到比它小的一位,值是1, 交换得321, 排序2后的所有位, 得321,

321 当前处理位重设为最后一位,
指向1, 向左扫描, 无法找到比1小的一位,
当前处理位 左移一位, 指向2,
向左扫描, 无法找到比2小的一位,
当前处理位 左移一位, 指向3,
向左扫描, 无法找到比3小的一位,
无法左移, done(就本题而言, done 可以 换成 reverse)


通过观察原数组可以发现
如果从末尾往前看
数字逐渐变大
到了2时才减小的
然后我们再从后往前找第一个比2大的数字
是3
那么我们交换2和3
再把此时3后面的所有数字转置一下即可

步骤如下：

1　　2　　7　　4　　3　　1

1　　2　　7　　4　　3　　1

1　　3　　7　　4　　2　　1

1　　3　　1　　2　　4　　7

这样想
从后往前找
找找找
找到一个下降点
说明在这个下降点之前 数列是 升序的 这就为以后 直接 reverse()  而可以变为降序的 埋下了 充分的理由
但是先要交换 这个下降点 和 比它大的第一数
交换后
sort 后面所有的 这是仍然是 升序的 只需要 reverse()

这样做完全符合 下一个 permutation

*/
public class NextPermutation {
    // Fri May 10 04:42:23 2024
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        int i = n - 1;

        while (i >= 1) {
            if (nums[i - 1] < nums[i]) {
                break;
            }
            i--;
        }
        int decreasedPos = i - 1;
        // find the 1st one from the back greater than nums[i - 1]

        // 至少第一元素 下降
        if (decreasedPos >= 0) {
            i = n - 1;
            // 重新从后面扫描 找到第一个大于nums[decreasedPos]的pos
            while (i >= 0) {
                if (nums[i] > nums[decreasedPos]) {
                    break;
                }
                i--;
            }

            swap(nums, decreasedPos, i);
        }

        reverse(nums, decreasedPos + 1, n - 1);
    }

    public void reverse(int[] nums, int i, int j) {
        int mid = i + (j - i) / 2;

        while (i <= mid) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    // Wed Jun 19 00:45:03 2019
    public void nextPermutation(int[] nums) {
        int size = nums.length;
        
        int i = size - 1;
        
        while (i >= 1) {
            if (nums[i] > nums[i - 1]) {
                break;
            }
            i--;
        }

        // not forget to check
        if (i >= 1) {
            int decreaseVal = nums[i - 1];
            int right = size - 1;

            while (right > i - 1 && nums[right] <= decreaseVal) {
                right--;
            }

            swap(nums, i - 1, right);    
        }
        
        reverse(nums, i, size - 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }

        if (end > nums.length) {
            return;
        }

        int mid = start + (end - start) / 2;

        while (start <= mid) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;

            start++;
            end--;
        }
    }


    // easy understand
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        // 先找到下降点
        while (i >= 0) {
            if (nums[i + 1] > nums[i]) {
                break;
            }
            i--;
        }

        // if i still in nums[], partially descending
        // 再找比下降点高一点的value
        if (i >= 0) {
            int j = n - 1;
            // 找到比下降点 value 增加一点点的 value
            // 才符合 next permutation
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        // actually make it ascending by reverse()
        // 从后往前看 ascending
        // 从前往后看 descending
        // if want to sort it to be ascending
        // just reverse() is enough
        reverse(nums, i + 1, n - 1);
    }

    public void nextPermutation(int[] num) {
        for (int i = num.length - 2; i >= 0; i--) {
            if (num[i + 1] > num[i]) {
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
        // no ascending order found, reverse all the numbers
        reverse(num, 0, num.length - 1);
    }
    // method to reverse an array
    // just remember, this is classic
    private void reverse(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }

        if (end > nums.length) {
            return;
        }

        int mid = start + (end - start) / 2;

        while (start <= mid) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;

            start++;
            end--;
        }
    }

    private void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }
}