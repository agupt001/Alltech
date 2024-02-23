package multiThreading;

public class MediumThread implements Runnable {

	@Override
	public void run() {

		// Thread implementation
		for (int i = 1; i < 11; i++) {

			int mult = MainThread.multInput * i;
			System.out.println("Medium Thread -> " + MainThread.multInput + " * " + i + " = " + mult);

		}
		
	}
}
