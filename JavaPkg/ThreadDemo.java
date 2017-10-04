/*
multiple thread to calculate (1 * 2) / (1 + 2)
*/

import java.util.*;

public class ThreadDemo {
	public static void main(String[] args) {
		Add add = new Add();
		Multiply multiply = new Multiply();

		try {
			add.join();
			multiply.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		double result = (double) multiply.value / add.value;

		System.out.println(result);
	}
}

class Add extends Thread {
	int value;
	Add() {
    	super("add thread");
    	System.out.println("add thread created" + this);
    	start();
   	}
	public void run() {
		value = 1 + 2;
	}
}

class Multiply extends Thread {
	int value;
	Multiply() {
    	super("multiply thread");
    	System.out.println("multiply thread created" + this);
    	start();
   	}
	public void run() {
		value = 1 * 2;
	}
}