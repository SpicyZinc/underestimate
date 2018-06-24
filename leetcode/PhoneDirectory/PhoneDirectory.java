/*
Design a Phone Directory which supports the following operations:
get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.

Example:
// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);
// It can return any available phone number. Here we assume it returns 0.
directory.get();
// Assume it returns 1.
directory.get();
// The number 2 is available, so return true.
directory.check(2);
// It returns 2, the only number that is left.
directory.get();
// The number 2 is no longer available, so return false.
directory.check(2);
// Release number 2 back to the pool.
directory.release(2);
// Number 2 is available again, return true.
directory.check(2);

idea:
max
queue stores all available numbers
usedNumber hashset stores all unavailable numbers
*/

import java.util.*;

public class PhoneDirectory {
	public static void main(String[] args) {
		PhoneDirectory directory = new PhoneDirectory(3);
		int number = directory.get();
		System.out.println(number); // 0
		number = directory.get();
		System.out.println(number); // 1
		boolean ifExist = directory.check(2);
		System.out.println(ifExist); // true
		number = directory.get();
		System.out.println(number); // 2
		ifExist = directory.check(2); // The number 2 is no longer available, so return false.
		System.out.println(ifExist);
		directory.release(2); // Release number 2 back to the pool.
		ifExist = directory.check(2); // Number 2 is available again, return true.
		System.out.println(ifExist);
	}

    int max;
    Set<Integer> usedNumber;
    Queue<Integer> queue;

	/**
	 * Initialize your data structure here
	 * @param  maxNumbers - The maximum numbers that can be stored in the phone directory.
	 * @return
	 */
    public PhoneDirectory(int maxNumbers) {
        max = maxNumbers - 1;
        usedNumber = new HashSet<Integer>();
        queue = new LinkedList<Integer>();
        for (int i = 0; i < maxNumbers; i++) {
            queue.offer(i);
        }
    }

    /**
     * Provide a number which is not assigned to anyone
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (queue.isEmpty()) {
            return -1;
        }
 
        int number = queue.poll();
        usedNumber.add(number);

        return number;
    }
 
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !usedNumber.contains(number) && number <= max;
    }
 
    /** Recycle or release a number. */
    public void release(int number) {
        if (usedNumber.contains(number)) {
            usedNumber.remove(number);
            queue.offer(number);
        }
    }
}