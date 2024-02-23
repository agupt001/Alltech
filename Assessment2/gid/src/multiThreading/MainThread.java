package multiThreading;

import java.util.Scanner;

public class MainThread {
	
	// Store the number in static variable to access it in thread classes
	public static int multInput;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Give the number for multiplcation table");
		multInput = sc.nextInt();
		sc.close();
		
		// Create thread objects
		SlowThread slowT = new SlowThread();
		MediumThread mediumT = new MediumThread();
		FastThread fastT = new FastThread();
		
		// Create Threads and set priorities
		Thread slowThread = new Thread(slowT);
		slowThread.setPriority(Thread.MIN_PRIORITY);
		
		Thread mediumThread = new Thread(mediumT);
		mediumThread.setPriority(Thread.NORM_PRIORITY);
		
		Thread fastThread = new Thread(fastT);
		fastThread.setPriority(Thread.MAX_PRIORITY);
		
		// Start the Threads
		slowThread.start();
		mediumThread.start();
		fastThread.start();
		
		try {
			// Wait for all the child threads to finish
			slowThread.join();
			mediumThread.join();
			fastThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Execute main thread
		for(int i=1; i<11; i++) {
			
			int mult = multInput*i;
			System.out.println("Main Thread -> "+multInput+" * "+i+" = "+mult);
			
		}
		
	}

}
