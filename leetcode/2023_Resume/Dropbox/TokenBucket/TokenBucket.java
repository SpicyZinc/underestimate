// ReentrantLock
// ReentrantLock

public class TokenBucket {
    int CAPACITY;
    int FILL_RATE;

    List<Integer> buckets;
    int lastFillTs;

    Lock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();

    public TokenBucket(int capacity, int fillRate) {
        this.CAPACITY = capacity;
        this.FILL_RATE = fillRate;
        this.buckets = new ArrayList<>();
        this.lastFillTs = System.currentTimeMillis();
    }

    public void fill() throws InterruptedException {
        lock.lock();

        while (buckets.size() == CAPACITY) {
            System.out.println("Full");
            notFull.await();
        }

        int now = System.currentTimeMillis();
        int numTokensToAdd = Math.min(CAPACITY - bucket.size(), (now - lastFillTs) / 1000 * FILL_RATE);
        // 放入 the number of numTokensToAdd random number
        for (int i=0; i < numTokensToAdd; i++) {
            buckets.add((int) (Math.random() * 100) + 1);  
        }
        notEmpty.signalAll();

        lock.unlock();
    }

    public List<Integer> get(int n) throws InterruptedException {
        if (n <= 0 || n > CAPACITY) {
            throw new IllegalArgumentException("");
        }

        List<Integer> result = new ArrayList<>();
        int i = 0;
        while (i < n) {
            lock.lock();

            while (buckets.size() <= 0) {
                System.out.prinln("Empty");
                notEmpty.await();
            }
            result.add(buckets.get(buckets.size() - 1));
            buckets.remove(buckets.size() - 1);
            i++;

            notFull.signalAll();
            lock.unlock();
        }

        return result;
    }
}
