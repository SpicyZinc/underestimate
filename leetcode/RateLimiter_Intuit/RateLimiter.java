import java.util.*;

public class RateLimiter {
    private final long interval;
    private final int maxLimit;
    private final Map<String, Queue<Long>> requestsMap;

    public RateLimiter(long interval, int maxLimit) {
        this.interval = interval;
        this.maxLimit = maxLimit;
        this.requestsMap = new HashMap<>();
    }

    public synchronized boolean isAllowed(String key) {
        long currentTime = System.currentTimeMillis();

        // Get or create the request queue for the given key
        Queue<Long> requestTimes = requestsMap.computeIfAbsent(key, k -> new LinkedList<>());

        // Remove outdated requests
        while (!requestTimes.isEmpty() && (currentTime - requestTimes.peek()) > interval) {
            requestTimes.poll();
        }

        // Check if the number of requests in the interval exceeds the limit
        if (requestTimes.size() >= maxLimit) {
            return false;
        }

        // Add the current request timestamp
        requestTimes.add(currentTime);
        return true;
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter(1000, 5); // 1 second interval, max 5 requests

        // Simulate requests
        String key = "user1";
        for (int i = 0; i < 10; i++) {
            if (rateLimiter.isAllowed(key)) {
                System.out.println("Request " + (i + 1) + " allowed");
            } else {
                System.out.println("Request " + (i + 1) + " denied");
            }
        }
    }
}
