import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

class Pair {
    int i;
    int j;
    Pair(int x,int y) {
        i = x;
        j = y;
    }
}

public class FourNumbersEqualsSum {
    public static void main(String[] args) {
        int num[]={1, 2, 3, 4, 12, 43, 32, 53, 8, -10, 4};
        get4Numbers(num, 17);
        get4Numbers(num, 55);
    }
    public static void get4Numbers(int a[], int sum) {
        int len = a.length;
    	int cnt = 0;

        Map<Integer, Pair> sums = new HashMap<Integer, Pair>();
    	for (int i = 0; i < len; i++) {
            // now let's put in 'sums' hashtable all possible sums
            // a[i] + a[k] where k < i
            for (int k = 0; k < i; k++){
                sums.put(a[i] + a[k], new Pair(i, k));
            }
        }
    	
        for (int i = 0; i < len; i++) {
            // 'sums' hastable holds all possible sums a[k] + a[l]
            // where k and l are both less than i
    		
    		for (int j = i+1; j < len; j++) {
            //for (int j = 0; j < i; j++) {
    		
                int current = a[i] + a[j];
                int rest = sum - current;
                // Now we need to find if there're different numbers k and l
                // such that a[k] + a[l] == rest and k < i and l < i
                // but we have 'sums' hashtable prepared for that
    			Collection c = sums.values();			
    			Iterator itr = c.iterator();
    			
    			while(itr.hasNext()){			
    				//if (sums.containsKey(rest)) {
    				Pair p = sums.get(rest);
    				if(itr.next() == p)				                
    					// found it					
    					if(i<j && j<p.j && p.j<p.i)
    						System.out.println(++cnt + " [" + a[i] + " " + a[j] + " " + a[p.j] + " " + a[p.i] + "] = " + sum);
    					/*
    					else if(i<p.j && p.j<j && j<p.i)
    						System.out.println(++cnt + " [" + a[i] + " " + a[p.j] + " " + a[j] + " " + a[p.i] + "] = " + sum);
    					else if(i<p.j && p.j<p.i && p.i<j)
    						System.out.println(++cnt + " [" + a[i] + " " + a[p.j] + " " + a[p.i] + " " + a[j] + "] = " + sum);	
    					*/
    			}
            }
    	}	
    }
}
