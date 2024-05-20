/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

idea:
double end queue
Deque interface

method
removeFirst() 
addLast()
*/ 

public class MovingAverage {
    // Tue May 14 21:51:20 2024
    int head = 0;
    int tail = 0;
    int[] list;
    int count = 0;
    int sum = 0;

    public MovingAverage(int size) {
        this.list = new int[size];
    }
    
    public double next(int val) {
        int size = list.length;
        // 满了 先剔除based on head
        if (count >= size) {
            sum -= list[head];
            head = (head + 1) % size;
            count--;
        }

        count++;
        sum += val;

        list[tail] = val;
        tail = (tail + 1) % size;

        return sum * 1.0 / count;
    }

    // Tue May 14 21:36:51 2024
    Deque<Integer> queue;
    int count = 0;
    int sum = 0;
    int size;

    public MovingAverage(int size) {
        this.queue = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        count++;
        sum += val;
        queue.offer(val);

        if (queue.size() > size) {
            sum = sum - queue.removeFirst();
            count--;
        }

        return sum * 1.0 / count;
    }

    // Sat Apr  6 15:45:13 2024
    Deque<Integer> queue = new LinkedList<>();
    int size;
    int sum = 0;
    int count = 0;

    public MovingAverage(int size) {
        this.size = size;
    }
    
    public double next(int val) {
        sum += val;
        count++;
        queue.offer(val);
        if (queue.size() > size) {
            sum -= queue.removeFirst();
            count--;
        }

        return sum * 1.0 / count; 
    }

    // Sun Mar 31 17:26:00 2024
    // circular list
    int[] list;
    int tail;
    int head;
    int count;
    long sum;

    public static void main(String[] args) {
        // ["MovingAverage","next","next","next","next"]
        // [[3],[1],[10],[3],[5]]

        MovingAverage eg = new MovingAverage(3);
        double avg = eg.next(1);
        System.out.println(avg);
        avg = eg.next(10);
        System.out.println(avg);
        avg = eg.next(3);
        System.out.println(avg);
        avg = eg.next(5);
        System.out.println(avg);
    }

    public MovingAverage(int size) {
        list = new int[size];
        tail = -1;
        head = 0;
        count = 0;
        sum = 0;
    }
    
    public double next(int val) {
        int size = list.length;

        if (count >= size) {
            sum -= list[head];
            head = (head + 1) % size;
            // 就是保持count实时反应实际数字
            // otherwise (count > size ? size : count);
            count--;
        }

        tail = (tail + 1) % size;
        list[tail] = val;
    
        sum += val;
        count++;

        return sum * 1.0 / count;
    }



    // 07/08/2018
    int idx;
    int size;
    int windowSum;
    List<Integer> deque;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.idx = 0;
        this.size = size;
        this.windowSum = 0;
        this.deque = new LinkedList<>();
    }
    
    public double next(int val) {
        idx++;
        windowSum += val;
        deque.add(val);

        if (idx <= size) {
            return (double) (windowSum * 1.0 / idx);
        } else {
            windowSum -= deque.get(idx - size - 1);
            return (double) (windowSum * 1.0 / size);
        }
    }

    // Sun Mar 31 16:08:43 2024
    Deque<Integer> dequeue = null;
    int size = 0;
    long sum = 0;

    // constructor
    public MovingAverage(int size) {
        this.dequeue = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        if (dequeue.size() >= size) {
            long head = dequeue.removeFirst();
            sum -= head;
        }
        dequeue.addLast(val);
        sum += val;

        return  sum * 1.0 / dequeue.size();
    }
}
