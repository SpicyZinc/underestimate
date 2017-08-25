import java.util.*;

class SegregateEvenAndOddNumbers {
    public static void main(String[] args) {
        SegregateEvenAndOddNumbers eg = new SegregateEvenAndOddNumbers();
        int[] a =  {12, 34, 45, 9, 8, 90, 3};
        for (int aa : a) {
            System.out.print(aa + " ");
        }
        System.out.println();
        eg.segregage(a);
        for (int aa : a) {
            System.out.print(aa + " ");
        }
        System.out.println();
    }

    public void segregage(int[] a) {
        int i = 0;
        int j = a.length - 1;

        while ( i < j ) {
            if ( a[i] % 2 == 0 ) {
                swap(i, j, a);
                j--;
            }
            else {
                i++;
            }
        }
    }

    public void swap(int x, int y, int[] a) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
}