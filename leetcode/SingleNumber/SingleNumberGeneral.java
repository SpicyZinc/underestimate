/*
idea:


*/
import java.util.*;

public class SingleNumberGeneral {
	public static void main(String[] args) {
		new SingleNumberGeneral();
	}

	public SingleNumberGeneral() {
        int[] a = {2, 3, 3, 1, 1};
		int[] b = {2, 3, 3, 3};

		System.out.println( "Array a the singleNumber appearing once: " + singleNumber(a) );
	    System.out.println( "Array b the singleNumber appearing once: " + singleNumber(b) );

        System.out.println( "Array a the singleNumber via hashmap appearing once: " + singleNumberViaHashmap(a) );
    }

    public int singleNumber(int[] A) {
        Arrays.sort(A);

        int appear_cnt = 1;
        for (int i = 0; i < A.length-1; i++) {
            if (A[i] == A[i+1]) {
                appear_cnt++;
            }
            else {
                if (appear_cnt < 2) {
                    return A[i];
                }
                // reset appear_cnt
                appear_cnt = 1;
            }
        }

        return A[A.length-1];
    }

    // use hashmap
    public int singleNumberViaHashmap(int[] A) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            Integer tmp = hm.get(A[i]);
            if (tmp == null) {
                hm.put(A[i], 1);
            }
            else {
                hm.put(A[i], tmp + 1);
            }            
        }

        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
            // System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        return -1;
    }
}