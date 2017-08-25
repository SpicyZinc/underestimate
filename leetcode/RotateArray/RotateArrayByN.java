class RotateArrayByN {
	public static void main(String[] args) {
		RotateArrayByN test = new RotateArrayByN();
		int[] arr = {1, 2, 3, 4, 5, 6, 7};
		for (int i : arr) {
			System.out.print(i + " ");
		}
		test.leftRotate(arr, 2);
		System.out.println();
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public void leftRotate(int[] a, int d) {
		for (int i = 0; i < d; i++) {
			leftRotatebyOne(a);
		}
	}

	public void leftRotatebyOne(int[] a) {
		int temp = a[0];
		int i;
		for (i = 0; i < a.length - 1; i++) {
			a[i] = a[i + 1];
		}
		a[i] = temp;
	}
}