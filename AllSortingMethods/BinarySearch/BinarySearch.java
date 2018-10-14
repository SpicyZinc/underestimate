import java.util.*;

class BinarySearch {
	public static void main(String[] args) {
		int[] a = new int[] {0,1,2,3,4,5,6,7};
		int result = binarySearch(a, 3);
		System.out.println(result);
	}
	public static int binarySearch(int[] arr, int target) {
    	int start = 0;
    	int end = arr.length - 1;

    	while ( start <= end ) {
    		int mid = (start + end) / 2;
    		if (arr[mid] > target) {
    			end = mid - 1;
    		}
            else if (arr[mid] < target) {
                start = mid + 1;
            }
            else {
                return mid;
    		}
    	}

        return -1;
    }

    // work with duplicates in the sorted array
    private List<Integer> binarySearch2(Integer[] A, Integer x) {
        List<Integer> result = new ArrayList<Integer>();
        int low = 0, high = A.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (A[mid].equals(x)) {
                if (mid > 0) {
                    // 看前一个元素是否＝目标元素
                    if (A[mid - 1].equals(x)) {
                        for (int i = mid - 1; i >= 0; i--) {
                            if (A[i].equals(x)) {
                                result.add(i);
                            }
                            else {
                                break;
                            }
                        }
                    }
                }
                result.add(x);
                if (mid < high) {
                    // 看后一个元素是否＝目标元素
                    if (A[mid + 1].equals(x)) {
                        for (int i = mid + 1; i <= high; i++) {
                            if (A[i].equals(x)) {
                                result.add(i);
                            }
                            else {
                                break;                                
                            } 
                        }
                    }
                }
                return result;
            } else if (x < A[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }
}