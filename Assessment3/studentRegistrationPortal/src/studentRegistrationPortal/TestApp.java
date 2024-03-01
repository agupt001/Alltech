package studentRegistrationPortal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TestApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Get thread count
		System.out.println("Enter the number of threads");
		int threadCount = sc.nextInt();

		// Get singleton object
		RegistrationPortal regObj = RegistrationPortal.getRegPortal();

		// Create a hashmap to store thread vs student count
		HashMap<Integer, Integer> threadStdCountMap = new HashMap<Integer, Integer>();

		// Maintain a list of threads to call join later
		List<Thread> threads = new ArrayList<Thread>();

		// Get the user input for thread count and student count
		for (int i = 0; i < threadCount; i++) {

			System.out.println("Enter the number of students to register for Thread " + (i));
			int studentCount = sc.nextInt();

			threadStdCountMap.put(i, studentCount);

		}

		// Iterate through each thread count and student count Hashmap
		for (Integer threadNum : threadStdCountMap.keySet()) {

			// Create a new thread for each thread count
			Thread thread = new Thread(() -> {
				for (int j = 0; j < threadStdCountMap.get(threadNum); j++) {

					// Generate id and name for students
					String id = "id-" + (threadNum + 1) + "-" + (j + 1);
					String name = "name-" + (j + 1);

					// Create a student and call the register method
					Student student = new Student(id, name);
					regObj.register(student);

				}
			});
			threads.add(thread); // Store threads in the threadList
			thread.start(); // Start the threads

		}

		// Iterate through threads list and call join to wait for all the threads to
		// finish
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Get the student list and print all the students
		for (Student student : regObj.getRegisteredStudents()) {
			System.out.println(student);
		}

		sc.close();

	}

}
