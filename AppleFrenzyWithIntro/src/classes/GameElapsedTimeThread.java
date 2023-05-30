package classes;


public class GameElapsedTimeThread extends Thread {
	GameWindow gw;
	MainThread mt;
	
	int s = -1, m = 0, h = 0;
	String seconds = "00", minutes = "00", hours = "00";
	
	public GameElapsedTimeThread(GameWindow gw, MainThread mt) {
		this.gw = gw;
		this.mt = mt;
	}
	
	public void restartClock() {
		s = -1; m = 0; h = 0;
		seconds = "00"; minutes = "00"; hours = "00";
		gw.lblClock.setText(hours + ":" + minutes + ":" + seconds);
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e2) {
				System.out.println("Thread exception on GameElapsedTime class... ");
			}
			if (mt.isGameRunning()) {
				s++;
				if (s == 60) {
					m++;
					s = 0;
				}
				if (m == 60) {
					h++;
					m = 0;
				}
				
				if (s <= 9)
					seconds = "0" + String.valueOf(s);
				else
					seconds = String.valueOf(s);
				if (m <= 9)
					minutes = "0" + String.valueOf(m);
				else
					minutes = String.valueOf(m);
				if (h <= 9)
					hours = "0" + String.valueOf(h);
				else
					hours = String.valueOf(h);
				
				gw.lblClock.setText(hours + ":" + minutes + ":" + seconds);
			}
		}
	}
}

