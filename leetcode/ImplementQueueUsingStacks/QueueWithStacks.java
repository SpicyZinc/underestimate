/*
Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.

idea:
http://www.geeksforgeeks.org/queue-using-stacks/

enQueue(q,  x)
  1) Push x to stack1 (assuming size of stacks is unlimited).

deQueue(q)
  1) If both stacks are empty then error.
  2) If stack2 is empty; while stack1 is not empty, push everything from satck1 to stack2.
  3) Pop the element from stack2 and return it.
*/

class QueueWithStacks {
    // 07/18/2018
    // make push costly
    Stack<Integer> One;
    Stack<Integer> Two;

    /** Initialize your data structure here. */
    public MyQueue() {
        One = new Stack<Integer>();
        Two = new Stack<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!One.isEmpty()) {
            Two.push(One.pop());
        }

        One.push(x);
        
        while (!Two.isEmpty()) {
            One.push(Two.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return One.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return One.peek();     
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return One.isEmpty() && Two.isEmpty();
    }




    private Stack<Integer> stack1 = new Stack<Integer>(); // back of queue
    private Stack<Integer> stack2 = new Stack<Integer>(); // front of queue

    // Push element x to the back of queue.
    public void push(int x) {
        stack1.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if ( stack2.isEmpty() ) {
            while ( !stack1.isEmpty() ) {
                stack2.push( stack1.pop() );
            }   
        }

        stack2.pop();
    }

    // Get the front element.
    public int peek() {
        if ( stack2.isEmpty() ) {
            while ( !stack1.isEmpty() ) {
                stack2.push( stack1.pop() );
            }   
        }

        return stack2.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    // return the number of items in the queue.
    public int size() {
        return stack1.size() + stack2.size();     
    }
}