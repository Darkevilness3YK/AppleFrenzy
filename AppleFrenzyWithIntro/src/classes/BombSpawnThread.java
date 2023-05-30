package classes;

public class BombSpawnThread extends Thread {
	private FallingEntity bomb;
	
	public BombSpawnThread(FallingEntity bomb) {
		this.bomb = bomb;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(bomb.getSpawnRateInMillis());
			}
			catch (InterruptedException e) {
				System.out.println("Thread exception on BombsThread class (Thread.sleep(bombs.getSpawnRateInMillis()))");
			}
			// ENG: Once a sleep ends, a new bomb will be created.
			// ESP: Al finalizar cada sleep, se creará una nueva bomba.
			bomb.createNewEntity();
		}
	}
}
