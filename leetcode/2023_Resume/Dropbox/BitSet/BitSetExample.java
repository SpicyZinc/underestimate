import java.util.*;

public class BitSetExample {
    public static void main(String[] args) {
        BitSet bset = new BitSet(5);
        // assigning value using set()
        bset.set(3);
        bset.set(15);
        bset.set(11);
        bset.set(21);
        bset.set(31);
        bset.set(33);
  
        System.out.println("The constructed bitset is : " + bset);

        System.out.println(bset.nextClearBit(10));
        System.out.println(bset.size());


        System.out.println("======");

        Allocator allocator = new Allocator(10);
        int id1 = allocator.allocate();
        int id2 = allocator.allocate();
        int id3 = allocator.allocate();

        System.out.println("id1 == " + id1);
        System.out.println("id2 == " + id2);
        System.out.println("id3 == " + id3);


        System.out.println("check id3 == " + allocator.check(id3));
        System.out.println("check 11 == " + allocator.check(11));

        allocator.release(id3);
        System.out.println("check id3 == " + allocator.check(id3));
    }
}


class Allocator {
    BitSet bs;
    int size;
    int nextIndex;

    Allocator(int size) {
        this.bs = new BitSet(size);
        this.size = size;
        this.nextIndex = 0;
    }

    public int allocate() {
        // is full
        if (nextIndex == size) {
            return -1;
        }

        int num = nextIndex;
        bs.set(nextIndex);
        nextIndex = bs.nextClearBit(num);

        return num;
    }

    public void release(int id) {
        if (bs.get(id)) {
            bs.clear(id);
            nextIndex = Math.min(nextIndex, id);
        }
    }

    public boolean check(int id) {
        return bs.get(id);
    }
}

class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    int[] next;
    int pos;
    public PhoneDirectory(int maxNumbers) {
        next = new int[maxNumbers];
        for (int i=0; i<maxNumbers; ++i){
            next[i] = (i+1)%maxNumbers;
        }
        pos=0;
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (next[pos]==-1) return -1;
        int ret = pos;
        pos = next[pos];
        next[ret]=-1;
        return ret;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return next[number]!=-1;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if (next[number]!=-1) return;
        next[number] = pos;
        pos = number;
    }
}