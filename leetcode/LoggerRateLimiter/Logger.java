/*
Design a logger system that receive stream of messages along with its timestamps,
each message should be printed if and only if it is not printed in the last 10 seconds.
Given a message and a timestamp (in seconds granularity),
return true if the message should be printed in the given timestamp, otherwise returns false.
It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;

idea:
key structure is hash table
key is message
value is timestamp
simple enough
*/

public class Logger {
    private Map<String, Integer> hm;

    /** Initialize your data structure here. */
    public Logger() {
        hm = new HashMap<>();
    }

    public boolean shouldPrintMessage(Integer timestamp, String message) {
        if (hm.containsKey(message)) {
            int ts = hm.get(message);
            if (timestamp - ts >= 10) {
                // update the hashmap
                hm.put(message, timestamp);
                return true;
            } else {
                return false;
            }
        } else {
            hm.put(message, timestamp);
            return true;
        }
    }
}
