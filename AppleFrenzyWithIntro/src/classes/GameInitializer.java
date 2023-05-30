package classes;
import java.awt.EventQueue;

//////////////////////////////////////
///////  GRAFICACI�N 5SA 2022  ///////
///////  JUEGO: APPLE FRENZY   ///////
//////////////////////////////////////
/* 
 * INTEGRANTES - EQUIPO 2:
 * Estrella Ortiz, Estefany Anahi
 * Flores Campos, Ana Teresa
 * Herrera Medina, Roger Diego
 * Mar�n Segura, Oscar Mario
 * Ordo�ez Pool, Alan Jair
 */

public class GameInitializer {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameIntroWindow giw = new GameIntroWindow();
					giw.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
