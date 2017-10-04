package com.learning.basic;
 
public class LRUCacheImplementation {
	Object lock = new Object();
	String[] arr = new String[5];
	public volatile static boolean isSet = false;
	public void put(String data) throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (!isSet) {
					System.out.println(" put it and make it true");
					isSet = true;
					notify();
				} else {
					wait();
				}
			}
		}
	}
	
	public void get() throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (isSet) {
					System.out.println(" take it and make it false");
					isSet = false;
					notify();
				} else {
					wait();
				}
			}
		}
	}
	
	public static void main(String... strings) {
		final LRUCacheImplementation mco = new LRUCacheImplementation();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					mco.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					mco.put("put");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}