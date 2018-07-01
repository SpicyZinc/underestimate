/*
given a1,b1,a2,b2,a3,b3
transform into a1,a2,a3,b1,b2,b3

idea:
this contains two problems
1. put elements at odd index to front, move elements at even index to back
2. put all odd numbers to front, all even numbers to back

5,6,8,2,3,9,4,7 => 5 8 3 4 6 2 9 7
5,6,8,2,3,9,4,7 => 5 3 9 7 6 8 2 4
*/

public class OddBeforeEven {
	public static void main(String[] args) {
		int[] a = {5,6,8,2,3,9,4,7};
		OddBeforeEven eg = new OddBeforeEven();
		eg.move(a);
		eg.moveByParity(a);
		for (int num : a) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	// move number at odd or even index
	public void move(int[] nums) {
		for (int i = 1; i <= nums.length / 2; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				swap(nums, j - 1, j);
			}
		}
	}
	// move odd value before even value
	// whenever run into odd number
	// via swap() 把它移到上一个 odd 之后
	public void moveByParity(int[] nums) {
		int lastOddPos = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] % 2 == 1) {
				for (int j = i; j > lastOddPos; j--) {
					swap(nums, j, j - 1);
				}
				lastOddPos++;
			}
		}
	}

	// move odd value before even value
	// front and back two pointers
	public void partitionArray(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        
        while (i < j) {
            if (nums[i] % 2 == 0 && nums[j] % 2 != 0) {
                swap(nums, i, j);
                i++;
                j--;
            }
            if (nums[i] % 2 != 0) {
                i++;
            } else if (nums[j] % 2 == 0) {
                j--;
            }
        }
    }

	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}