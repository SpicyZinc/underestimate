/*
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity)
and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing).
You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:
HitCounter counter = new HitCounter();
// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301);

Follow up:
What if the number of hits per second could be very large? Does your design scale?

idea:
use queue, too stale than 5 mins remove it from the queue
https://www.cnblogs.com/grandyang/p/5605552.html

*/

public class HitCounter {
	Queue<Integer> queue;
	/** Initialize your data structure here. */  
	public HitCounter() {
		queue = LinkedList<Integer>();
	}

	/**
	 * Record a hit. 
	 * @param timestamp [description]
	 */
	public void hit(int timestamp) {
		queue.add(timestamp);
	}

	/**
	 * Return the number of hits in the past 5 minutes. 
	 * @param  timestamp [description]
	 * @return           [description]
	 */
	public int getHits(int timestamp) {
		while (!queue.isEmpty() && queue.peek() <= timestamp - 60 * 5) {
			queue.poll();
		}
		return queue.size();
	}

    // follow up
    int[] times;
    int[] hits;
    /** Initialize your data structure here. */  
	public HitCounter() {
		times = new int[300];
		hits = new int[300];
	}

	/**
	 * Record a hit. 
	 * @param timestamp [description]
	 */
    public void hit(int timestamp) {
    	int idx = timestamp % 300;
    	// no hit at this timestamp before or over 5 min
    	if (times[idx] != timestamp) {
    		times[idx] = timestamp;
    		hits[idx] = 1;
    	} else {
    		hits[idx] += 1;
    	}
    }

	/**
	 * Return the number of hits in the past 5 minutes. 
	 * @param  timestamp [description]
	 * @return           [description]
	 */
	public int getHits(int timestamp) {
		int hitCnt = 0;
		for (int i = 0; i < hits.length; i++) {
			// within 5 mins
			if (timestamp - times[i] < 300) {
				hitCnt += hits[i];
			}
		}

		return hitCnt;
	}
}