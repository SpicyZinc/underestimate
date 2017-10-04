/*
A thread can be created in two ways: 
1) By extending Thread class 
2) By implementing Runnable interface
*/

// constructor
// Thread(Runnable target, String name)

import java.util.*;

class MultiThreadRunnableDemo implements Runnable {
	public void run() {
		System.out.println("the thread implemented Runnable is running");
	}

	public static void main(String[] args) {
		MultiThreadRunnableDemo aTest = new MultiThreadRunnableDemo();
		Thread thread = new Thread(aTest);
		thread.start();
	}
}

class MultiThreadThreadDemo extends Thread {
	public void run() {
		System.out.println("the thread subclassed Thread is running");
	}

	public static void main(String[] args) {
		MultiThreadThreadDemo aTest = new MultiThreadThreadDemo();
		aTest.start();
	}
}

class Count implements Runnable {
   	Thread mythread ;
   	Count() { 
   		mythread = new Thread(this, "my runnable thread");
   		System.out.println("my thread created" + mythread);
   		mythread.start();
   	}
   	public void run() {
     	  try {
      			for (int i=0; i<10; i++) {
      				System.out.println("Printing the count " + i);
      				Thread.sleep(1000);
      			}
     	  }
   	  	catch(InterruptedException e) {
   	  	   	System.out.println("my thread interrupted");
   	  	}
   	  	System.out.println("mythread run is over" );
   	}
}
class RunnableExample {
    public static void main(String args[]) {
       	Count count = new Count();
       	try {
          	while (count.mythread.isAlive()) {
          	  	System.out.println("Main thread will be alive till the child thread is live"); 
          	  	Thread.sleep(1500);
          	}
       	}
       	catch(InterruptedException e) {
       	    System.out.println("Main thread interrupted");
       	}
       	System.out.println("Main thread run is over");
    }
}