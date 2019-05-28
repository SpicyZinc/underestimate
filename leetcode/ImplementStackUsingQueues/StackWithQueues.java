/*
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.

Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

idea:
1 (By making push operation costly)
This method makes sure that newly entered element is always at the front of 'q1', so that pop operation just dequeues from 'q1'. 
'q2' is used to put every new element at front of 'q1'.

x is the element to be pushed and s is stack
push(s, x)
  1) Enqueue x to q2
  2) One by one dequeue everything from q1 and enqueue to q2
  3) Swap the names of q1 and q2 
    Swapping of names is done to avoid one more movement of all elements from q2 to q1

pop(s)
  1) Dequeue an item from q1 and return it.

2 (By making pop operation costly)
In push operation, the new element is always enqueued to q1. 
In pop() operation, if q2 is empty then all the elements except the last, are moved to q2. 
Finally the last element is dequeued from q1 and returned.

push(s, x)
  1) Enqueue x to q1 (assuming size of q1 is unlimited).

pop(s)  
  1) One by one dequeue everything except the last element from q1 and enqueue to q2
  2) Dequeue the last item of q1, the dequeued item is result, store it.
  3) Swap the names of q1 and q2
  4) Return the item stored in step 2.
    Swapping of names is done to avoid one more movement of all elements from q2 to q1
*/

import java.util.*;

// this is implementation of 2nd method
class MyStack {
    Queue<Integer> normalQueue = new LinkedList<Integer>();
    Queue<Integer> reverseQueue = new LinkedList<Integer>();
    
    // Push element x onto stack.
    public void push(int x) {
        if (!reverseQueue.isEmpty()) {
            normalQueue.offer(reverseQueue.poll());
        }
        normalQueue.offer(x);
    }
 
    // Removes the element on top of the stack.
    public void pop() {
        move();
        reverseQueue.poll();
    }
 
    // Get the top element.
    public int top() {
        move();
        return reverseQueue.peek();
    }
 
    // Return whether the stack is empty.
    public boolean empty() {
        return normalQueue.isEmpty() && reverseQueue.isEmpty();
    }
    // helper method move()
    private void move() {
        if (reverseQueue.isEmpty()) {
            while (normalQueue.size() > 1) {
                reverseQueue.offer(normalQueue.poll());
            }
            Queue<Integer> tmp = reverseQueue;
            reverseQueue = normalQueue;
            normalQueue = tmp;
        }
    }
}
// self 1st implementation without swap()
class MyStack {
    Queue<Integer> One;
    Queue<Integer> Two;
    /** Initialize your data structure here. */
    public MyStack() {
        One = new LinkedList<>();
        Two = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        // for pop(), so prepare
        // 要想 pop 省事 push到 queue tail 的 x 要 想办法放到队列第一位
        // how 只要不空 移到另外一个 放好 x 再移回来
        while (!One.isEmpty()) {
            Two.add(One.poll());
        }
        One.add(x);
        while (!Two.isEmpty()) {
            One.add(Two.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return One.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return One.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return One.isEmpty() && Two.isEmpty();     
    }
}

// self 2nd implementation
class MyStack {
    
    Queue<Integer> queue;
    Queue<Integer> forReverse;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
        forReverse = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        while (!forReverse.isEmpty()) {
            queue.add(forReverse.poll());
        }
        queue.add(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (!forReverse.isEmpty()) {
            return forReverse.poll();
        }

        while (queue.size() > 1) {
            forReverse.add(queue.poll());
        }

        int val = queue.poll();
        
        Queue<Integer> temp = queue;
        queue = forReverse;
        forReverse = temp;

        return val;
    }
    
    /** Get the top element. */
    public int top() {
        if (!forReverse.isEmpty()) {
            return forReverse.peek();
        }
        
        while (queue.size() > 1) {
            forReverse.add(queue.poll());
        }

        int val = queue.peek();
        
        Queue<Integer> temp = queue;
        queue = forReverse;
        forReverse = temp;

        return val;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty() && forReverse.isEmpty();
    }
}