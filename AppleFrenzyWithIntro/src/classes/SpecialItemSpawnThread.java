package classes;

public class SpecialItemSpawnThread extends Thread {
	// ENG: SPAWN RATE FOR SPECIAL ITEMS IN MILLIS //
	// ESP: TIEMPO DE GENERACIÓN PARA LOS ITEMS ESPECIALES EN MILISEGUNDOS //
	private int spawnRateForSpecialItemsInMillis = 1000; // 1000 = 1 sec.
	
	// ENG: SPECIAL ITEM LIST - THESE ITEMS ARE GOING TO SPAWN ON THE SCREEN AS A SPECIAL ITEM//
	// ESP: LISTA DE ITEMS ESPECIALES - ESTOS ITEMS SE GENERARÁN EN LA PANTALLA COMO ITEMS ESPECIALES //
	private SpecialItem greenApple, goldenApple, rainbowApple, rottenApple, skullApple;
	
		public SpecialItemSpawnThread(SpecialItem greenApple, SpecialItem goldenApple, SpecialItem rainbowApple, SpecialItem rottenApple, SpecialItem skullApple) {
			this.greenApple = greenApple;
			this.goldenApple = goldenApple;
			this.rainbowApple = rainbowApple;
			this.rottenApple = rottenApple;
			this.skullApple = skullApple;
		}
		
		public void run() {
			while (true) {
				try {
					Thread.sleep(spawnRateForSpecialItemsInMillis);
				}
				catch (InterruptedException e) {
					System.out.println("Thread exception on ApplesThread class (Thread.sleep(apples.getSpawnRateInMillis()))");
				}
				
				/* ENG: after this thread finishes the Thread.sleep instruction, a random number between 0 and 40 is generated.
				 * Then, the cases define the probability of spawning an specific special item.
				 * ESP: después de que finalice la instrucción Thread.sleep, un número aleatorio entre 0 y 40 es generado.
				 * Luego, los cases (casos) definen la probabilidad de spawnear un item especial específico.
				 * */
				switch ((int)(Math.random() * (40))) {
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
						greenApple.createNewEntity(); // 6/40 = 3/20
						break;
					case 6:
					case 7:
						goldenApple.createNewEntity(); // 2/40 = 1/20
						break;
					case 8:
						rainbowApple.createNewEntity(); // 1/40
						break;
					case 9:
					case 10:
					case 11:
					case 12:
						rottenApple.createNewEntity(); // 4/40 = 1/10
						break;
					case 13:
					case 14:
						skullApple.createNewEntity(); // 2/40 = 1/20
						break;
					default:
						break;
				}
			}
		}
}
