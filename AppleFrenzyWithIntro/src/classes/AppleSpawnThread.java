package classes;

public class AppleSpawnThread extends Thread {
	private FallingEntity redApple;
	
	public AppleSpawnThread(FallingEntity redApple) {
		this.redApple = redApple;
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(redApple.getSpawnRateInMillis());
			}
			catch (InterruptedException e) {
				System.out.println("Thread exception on ApplesThread class (Thread.sleep(apples.getSpawnRateInMillis()))");
			}
			// ENG: Once a sleep ends, a new red apple will be created.
			// ESP: Al finalizar cada sleep, se creará una nueva manzana roja.
			redApple.createNewEntity(); 
		}
	}
}
