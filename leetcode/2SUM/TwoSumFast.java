import java.util.Arrays;
import java.io.*;

class Stopwatch { 
    private final long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    } 
    // return time (in seconds) since this object was created
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    } 
}

public class TwoSumFast {
    // print distinct pairs (i, j) such that a[i] + a[j] = 0
    public static int printAll(int[] a) {
        int N = a.length;
		int count = 0;
        Arrays.sort(a);
        for (int i = 0; i < N; i++) {
            int j = Arrays.binarySearch(a, -a[i]);
            if (j > i) { 
				System.out.println(a[i] + " " + a[j]);
				count++;
			}
        }
		
		return count;
    } 
 

    public static void main(String[] args) throws IOException { 
	/*
        int[] a = {1, 5, -6, 7, -1, -4, 3};
		int[] b = {-3,-4,-2,0,2,2,-2,1,-1,2,3,-1,-5,-4,-5,1};
        int a_count = printAll(a);
		System.out.println(a_count);
		System.out.println("===");
		int b_count = printAll(b);
		System.out.println(b_count);
	*/	
	
		int[] a = new int[8000];
		int count = 0;
		
		FileReader in = new FileReader(args[0]);
		BufferedReader integerFile = new BufferedReader(in);
		String aLine;
		
		while ((aLine = integerFile.readLine()) != null) {						
			//System.out.println(aLine);
			int aNumber = Integer.parseInt(aLine);
			a[count] = aNumber;
			count++;
		}
		
        Stopwatch timer = new Stopwatch();
        int cnt = printAll(a);
        System.out.printf("Elapsed Time = %f s\n", timer.elapsedTime());
        System.out.println(cnt);
	} 
} 