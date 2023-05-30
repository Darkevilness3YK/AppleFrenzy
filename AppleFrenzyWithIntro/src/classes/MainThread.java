package classes;
import java.awt.Color;
import java.net.URL;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;


public class MainThread extends Thread {
	// ENG: ENTITIES AND ANOTHER OBJECTS THAT WILL BE IN-GAME //
	// ESP: ENTIDADES Y OTROS OBJETOS QUE ESTARÁN EN EL JUEGO //
	private GameWindow gw;
	private FallingEntity redApple, bomb;
	private Basket b;
	
	// ENG: GAME ELAPSED TIME THREAD - THREAD FOR MEASURING THE TIME PLAYED BEFORE DYING //
	// HILO DE TIEMPO TRANSCURRIDO - HILO PARA MEDIR EL TIEMPO JUGADO ANTES DE MORIR //
	private GameElapsedTimeThread gett;
		
	// ENG: SPECIAL ITEMS //
	// ESP: ITEMS ESPECIALES //
	private SpecialItem greenApple, goldenApple, rainbowApple, rottenApple, skullApple;
	
	// ENG: THREADS THAT WILL SPAWN FALLING ENTITIES //
	// ESP: HILOS QUE GENERARÁN ENTIDADES QUE CAEN //
	private AppleSpawnThread ast;
	private BombSpawnThread bst;
	private SpecialItemSpawnThread sist;
	
	// ENG: SOME FALLING ENTITIES ATTRIBUTES //
	// ESP: ALGUNOS ATRIBUTOS DE ENTIDADES QUE CAEN //
	private int originalRedAppleSpawnRate = 0; // tiempo original de generación de una manzana roja (sirve para reestablecerlo cuando se reinicia el juego)
	private int redAppleLastSavedSpawnRate = 0; // variable que guardará el último tiempo guardado para generar manzanas rojas
	
	private int originalBombSpawnRate = 0; // tiempo original de generación de una bombs (sirve para reestablecerlo cuando se reinicia el juego)
	private int bombLastSavedSpawnRate = 0; // variable que guardará el último tiempo guardado para generar bombas
	
	// ENG: MAIN THREAD ATTRIBUTES AND CONSTANTS //
	// ESP: ATRIBUTOS Y CONSTANTES DEL HILO PRINCIPAL (ESTA CLASE) //
	private boolean playerWannaPlay = true; // si esto es false, el hilo principal finaliza y termina el programa
	private boolean isGameRunning = true; // si esto es false, se accederá al cuadro de game over
	private Sound bgMusic1; // música de fondo 1 del juego.
	
	private int score = 0; // puntuación
	
	private final float INITIAL_GAME_SPEED = 1.5f; // constante de velocidad inicial del juego
	private float gameSpeed = INITIAL_GAME_SPEED; // inicializar la velocidad del juego con la inicial
	private final float MAX_SPEED = 5.98f; // máxima velocidad que puede tener la variable gameSpeed
	
	private final int MAX_APPLE_SPAWN_RATE = 350; // ENG: MAX (or more) means LOWER on the context of this variable. ESP: MAX (máximo o más) significa MENOS en el contexto de esta variable
	private boolean spawnRateChanged = true; // verifica cuándo se cambia el tiempo de generación de una manzana y una bomba
	
	private Sound redAppleTouchedGround; // sonido que se reproducirá si una manzana roja tocó el suelo
	
	private int redBlinkTimeForScore = 0; // tiempo en que el lblScore de la clase GameWindow quedará en letras rojas
	private int redBlinkTimeForLives = 0; // tiempo en que el lblLivesLeft de la clase GameWindow quedará en letras rojas
	private int frenzyTime = 0; // tiempo de frenesí. Pasarán diversas cosas si este valor es superior a 0.
	private int curseTime = 0; // tiempo de maldición. Pasarán diversas cosas si este valor es superior a 0.
	
	private DecimalFormat toTwoDecimals = new DecimalFormat("0.00"); // objeto que permite redondear un valor a 2 decimales.
	
	// ENG: CONSTANTS FOR ITEMS //
	// ESP: CONSTANTES PARA ITEMS
	private final float GAME_SPEED_INCREASE_CONSTANT = 0.02f; // constante de incremento de velocidad del juego
	private final int POINTS_FOR_CATCHING_RED_APPLE = 1; // puntos por atrapar una manzana roja
	private final int POINTS_FOR_CATCHING_GREEN_APPLE = 2; // puntos por atrapar una manzana verde
	private final int POINTS_FOR_CATCHING_GOLDEN_APPLE = 5; // puntos por atrapar una manzana dorada
	private final int POINTS_FOR_CATCHING_ROTTEN_APPLE = -10; // puntos por atrapar una manzana podrida
	
	private final int LIVES_FOR_CATCHING_GREEN_APPLE = 1; // vidas por agarrar una manzana verde
	private final int LIVES_FOR_CATCHING_ROTTEN_APPLE = -2; // vidas por agarrar una manzana podrida
	
	// ENG: LIVES SETTINGS //
	// ESP: AJUSTES DE VIDAS //
	private final int MAX_LIVES = 10; // máximo de vidas
	private int lives = MAX_LIVES; // inicializar las vidas con el máximo
	
	// ENG: GAME OVER SETTINGS (THEY SHOW IN THE GAME OVER SCREEN) //
	// ESP: AJUSTES DE GAME OVER (SE MUESTRAN EN LA PANTALLA DE GAME OVER) //
	private String gameOverMessage = ""; // mensaje de game over (motivo por el que perdiste)
	private String gameOverTip = ""; // tip (consejo) a mostrar en el cuadro de game over
	private Sound gameOverSound; // sonido que se reproducirá cuando haya un game over
		
	public MainThread(GameWindow gw, FallingEntity redApple, FallingEntity bomb, Basket b, SpecialItem greenApple, SpecialItem goldenApple, SpecialItem rainbowApple, SpecialItem rottenApple, SpecialItem skullApple, URL bgMusic1URL, URL redAppleTouchedGroundURLSound, URL gameOverURLSound) {
		this.gw = gw;
		this.redApple = redApple;
		this.bomb = bomb;
		this.b = b;
		this.greenApple = greenApple;
		this.goldenApple = goldenApple;
		this.rainbowApple = rainbowApple;
		this.rottenApple = rottenApple;
		this.skullApple = skullApple;
		
		this.bgMusic1 = new Sound(bgMusic1URL); // inicializar la música del juego
		this.bgMusic1.playAndLoopSound();
		this.redAppleTouchedGround = new Sound(redAppleTouchedGroundURLSound); // inicializar el sonido de manzana roja cuando toca el suelo obteniendo el archivo de sonido de la URL
		this.gameOverSound = new Sound(gameOverURLSound); // inicializar el sonido de Game Over obteniendo el archivo de sonido de la URL
		
		originalRedAppleSpawnRate = redApple.getSpawnRateInMillis();
		redAppleLastSavedSpawnRate = originalRedAppleSpawnRate;
		
		originalBombSpawnRate = bomb.getSpawnRateInMillis();
		bombLastSavedSpawnRate = originalBombSpawnRate;
		
		// ENG: INITIALIZING THE THREADS FOR ALL THE ENTITIES THAR ARE GOING TO FALL FROM THE TOP OF THE GAME WINDOW //
		// ESP: INICIALIZANDO LOS HILOS PARA TODAS LAS ENTIDADES QUE VAN A CAER DESDE LA PARTE SUPERIOR DE LA VENTANA //
		ast = new AppleSpawnThread(redApple);
		bst = new BombSpawnThread(bomb);
		sist = new SpecialItemSpawnThread(greenApple, goldenApple, rainbowApple, rottenApple, skullApple);
		
		// ENG: INITIALIZING THE THREAD FOR THE GAME ELAPSED TIME //
		// ESP: INICIALIZAR EL HILO PARA EL TIEMPO DE JUEGO TRANSCURRIDO //
		gett = new GameElapsedTimeThread(gw, this);
	}
	
	public void run() {
		// ENG: run the threads previously initialized //
		// ESP: correr los hilos previamente inicializados
		ast.start();
		bst.start();
		sist.start();
		gett.start();
		
		while (playerWannaPlay) {
			while (isGameRunning) {
				try {
					Thread.sleep((long)(10/gameSpeed)); // ENG: less sleep time means faster falling speed! ESP: ¡menos tiempo de sleep significa caída más rápida!
				}
				catch (InterruptedException e) {
				}
				
				// ENG: The constant (int)(gameSpeed * 10) % 3 == 0 makes each certain time the red apples and bombs spawn faster. //
				// ESP: La constante (int)(gameSpeed * 10) % 3 == 0 hace que cada cierto tiempo las manzanas rojas y las bombas se generen más rápido //
				if ((int)(gameSpeed * 10) % 3 == 0 && !spawnRateChanged) { // ENG: this is a constant. Don't touch it unless you know how this works! ESP: esta es una constante. ¡No tocar a menos que sepas cómo funciona!
					/* ENG: The spawn rate of red apples and bombs will increase if:
					 * 1. the red apple spawn rate is above the max-red-apple-spawn-rate value.
					 * 2. frenzy time is over (on 0).
					 * ESP: El tiempo de generación de las manzanas rojas y las bombas incrementarán si:
					 * 1. el tiempo de generación de la manzana roja está por debajo del valor máximo.
					 * 2. el tiempo de frenesí está acabado (en 0).
					 */
					if (redApple.getSpawnRateInMillis() >= MAX_APPLE_SPAWN_RATE && frenzyTime <= 0 && curseTime <= 0) {
						redApple.setSpawnRateInMillis(redApple.getSpawnRateInMillis() - 55);
						redAppleLastSavedSpawnRate = redApple.getSpawnRateInMillis();
						bomb.setSpawnRateInMillis(bomb.getSpawnRateInMillis() - 80);
						bombLastSavedSpawnRate = bomb.getSpawnRateInMillis();
						spawnRateChanged = true;
					}
					//System.out.println("NEW RED APPLE SPAWN RATE: " + redApple.getSpawnRateInMillis());
					//System.out.println("NEW BOMB SPAWN RATE: " + bomb.getSpawnRateInMillis());
				}
				if ((int)(gameSpeed * 10) % 3 == 1) { // ENG: this is a constant. Don't touch it unless you know how this works! ESP: esta es una constante. ¡No tocar a menos que sepas cómo funciona!
					spawnRateChanged = false;
				}
				
				////// ENG: CONDITIONALS IF THE BASKET HAS CATCHED SOMETHING //////
				////// ESP: CONDICIONALES POR SI LA MANZANA ATRAPÓ ALGO  //////
				
				//// ENG: RED APPLE CATCHED ////
				//// ESP: MANZANA ROJA ATRAPADA ////
				if (b.isRedAppleCatched()) {
					increaseScoreIn(POINTS_FOR_CATCHING_RED_APPLE); // ENG: increases score. ESP: incrementa la puntuación.
					
					/* ENG: The game speed will increase only if there's frenzy time left and
					 * if the game speed is below the max speed value.
					 * ESP: La velocidad del juego incrementará solo si queda tiempo de frenesí y
					 * si la velocidad del juego está por debajo del valor máximo.
					 */
					if (frenzyTime <= 0) {
						increaseGameSpeedIn((GAME_SPEED_INCREASE_CONSTANT * POINTS_FOR_CATCHING_RED_APPLE));
					}
					
					redApple.playSoundWhenCatched();
				}
				
				//// ENG: BOMB CATCHED ////
				//// ESP: BOMBA ATRAPADA ////
				if (b.isBombCatched()) {
					/* ENG: If there's no frenzy time left, catching a bomb means GAME OVER!
					 * In other words, isGameRunning = false, that will lead to the Game Over screen.
					 * ESO: Si no queda tiempo de frenesí, atrapar una bomba significará JUEGO TERMINADO!
					 * En otras palabras, isGameRunning = false, eso llevará a la pantalla de Game Over.
					 * */
					if (frenzyTime <= 0) {
						redApple.setSpawnRateInMillis(originalRedAppleSpawnRate);
						bomb.setSpawnRateInMillis(originalBombSpawnRate);
						spawnRateChanged = true;
						isGameRunning = false;
						gameOverMessage = "you catched a bomb! D:";
						gameOverTip = "bombs aren't tasty, they explode. Did you know?";
					}
					
					bomb.playSoundWhenCatched();
				}
				
				//// ENG: GREEN APPLE CATCHED ////
				//// ESP: MANZANA VERDE ATRAPADA ////
				if (b.isGreenAppleCatched()) {
					// ENG: A green apple will give you an extra life if your lives are under the max. //
					// ESP: Una manzana verde te dará una vida extra si tus vidas están por debajo del máximo. //
					if (lives < MAX_LIVES)
						increaseLivesIn(LIVES_FOR_CATCHING_GREEN_APPLE);
					
					// ENG: Also increases score and game speed. //
					// ESP: Además, incrementa la puntuación y la velocidad del juego. //
					increaseScoreIn(POINTS_FOR_CATCHING_GREEN_APPLE);
					increaseGameSpeedIn((GAME_SPEED_INCREASE_CONSTANT));
					
					greenApple.playSoundWhenCatched();
				}
				
				//// ENG: GOLDEN APPLE CATCHED ////
				//// ESP: MANZANA DORADA ATRAPADA ////
				if (b.isGoldenAppleCatched()) {
					restoreAllLives(); // restablecer todas las vidas perdidas
					increaseScoreIn(POINTS_FOR_CATCHING_GOLDEN_APPLE); // incrementar la puntuación
					increaseGameSpeedIn((GAME_SPEED_INCREASE_CONSTANT)); // incrementar la velocidad del juego
					
					goldenApple.playSoundWhenCatched();
				}
				
				//// ENG: RAINBOW APPLE CATCHED ////
				//// ESP: MANZANA ARCOÍRIS ATRAPADA ////
				if (b.isRainbowAppleCatched()) {
					/* ENG: Basically, the red apple spawn rate is boosted.
					 * The red apple spawn rate boost will remain while frenzy time isn't over.
					 * ESP: Basicamente, el tiempo de generación para manzanas rojas se incrementará.
					 * El tiempo incrementado de generación de manzanas rojas permanecerá mientras el tiempo de frenesí no se acabe.
					 * */
					redApple.setSpawnRateInMillis(100);
					// ENG: Constant value to calculate frenzy time. Don't touch it unless you know how this works!
					// ESP: Valor de constante para calcular el tiempo de frenesí. ¡No tocar a menos que sepas cómo funciona!
					frenzyTime = 60 * (int)(Math.ceil(gameSpeed * 10));
					gw.lblFrenzyTime.setForeground(Color.BLUE);
					
					rainbowApple.playSoundWhenCatched();
				}
				
				//// ENG: ROTTEN APPLE CATCHED ////
				//// ESP: MANZANA PODRIDA ATRAPADA ////
				if (b.isRottenAppleCatched()) {
					/* ENG: if there's no frenzy time left, you will lose lives and score will decrease.
					 * Obviously, if you have less lives than you are going to lose, this will lead to the Game Over screen.
					 * ESP: si no queda tiempo de frenesí, perderás vidas y la puntuación decrementará.
					 * Obviamente, si tienes menos vidas de las que vas a perder, esto te llevará a la pantalla de Game Over.
					 */
					if (frenzyTime <= 0) {
						increaseLivesIn(LIVES_FOR_CATCHING_ROTTEN_APPLE);
						increaseScoreIn(POINTS_FOR_CATCHING_ROTTEN_APPLE);
						if (lives <= 0)
							gameOverTip = "rotten apples aren't tasty at all! Eeugh! >_&#60";
					}
					
					rottenApple.playSoundWhenCatched();
				}
				
				//// ENG: SKULL APPLE CATCHED ////
				//// ESP: MANZANA ESQUELETO ATRAPADA ////
				if (b.isSkullAppleCatched()) {
					if (frenzyTime <= 0) {
						/* ENG: Basically, the bomb spawn rate is boosted.
						 * The bomb spawn rate boost will remain while curse time isn't over.
						 * ESP: Básicamente, el tiempo de generación de bombas se incrementará.
						 * El tiempo incrementado de generación de bombas permanecerá mientras el tiempo de maldición no se acabe.
						 */
						bomb.setSpawnRateInMillis(300);
						// ENG: Constant value to calculate curse time. Don't touch it unless you know how this works!
						// ESP: Valor constante para calcular el tiempo de maldición. ¡No tocar a menos que sepas cómo funciona!
						curseTime = 60 * (int)(Math.ceil(gameSpeed * 10)); 
						gw.lblCurseTime.setForeground(Color.RED);
					}
					
					skullApple.playSoundWhenCatched();
				}
				
				//// ENG: MAKE THE FALLING ENTITIES FALL ////
				//// ESP: HACER QUE LAS ENTIDADES QUE CAEN CAIGAN ////
				redApple.makeAllEntitiesOfThisTypeOnScreenFall();
				bomb.makeAllEntitiesOfThisTypeOnScreenFall();
				greenApple.makeAllEntitiesOfThisTypeOnScreenFall();
				goldenApple.makeAllEntitiesOfThisTypeOnScreenFall();
				rainbowApple.makeAllEntitiesOfThisTypeOnScreenFall();
				rottenApple.makeAllEntitiesOfThisTypeOnScreenFall();
				skullApple.makeAllEntitiesOfThisTypeOnScreenFall();
				
				// ENG: A LIVE IS LOST IF A RED APPLE HAS TOUCHED THE GROUND AND THERE'S NO FRENZY TIME LEFT //
				// ESP: UNA VIDA SE PIERDE SI UNA MANZANA ROJA HA TOCADO EL SUELO Y NO QUEDA TIEMPO DE FRENESÍ //
				if (redApple.isAnEntityTouchingTheGround()) {
					if (frenzyTime <= 0) {
						increaseLivesIn(-1);
						redAppleTouchedGround.playSound();
						if (lives <= 0)
							gameOverTip = "don't leak too many apples next time!";
					}
				}
				
				//// ENG: SPECIAL INSTRUCTIONS IF BASKET HAS SOME RED BLINK TIME ////
				//// ESP: INSTRUCCIONES ESPECIALES SI A LA CANASTA LE QUEDA TIEMPO DE PARPADEO ROJO ////
				
				// ENG: RED BLINK TIME FOR SCORE LABEL IN GAME PANE //
				// ESP: TIEMPO DE PARPADEO ROJO PARA EL LABEL DE PUNTUACIÓN EN EL PANEL DE JUEGO //
				if (redBlinkTimeForScore > 0) {
					drainRedBlinkTimeForScore();
					if (redBlinkTimeForScore > 0)
						gw.lblScore.setForeground(Color.RED);
					else
						gw.lblScore.setForeground(new Color(51, 51, 51));
				}
				// ENG: RED BLINK TIME FOR LIVES LEFT LABEL IN THE GAME WINDOW //
				// ESP: TIEMPO DE PARPADEO ROJO PARA EL LABEL DE VIDAS RESTANTES EN LA VENTANA DEL JUEGO //
				if (redBlinkTimeForLives > 0) {
					drainRedBlinkTimeForLives();
					if (redBlinkTimeForLives > 0)
						gw.lblLivesLeft.setForeground(Color.RED);
					else
						gw.lblLivesLeft.setForeground(new Color(51, 51, 51));
				}
				
				//// ENG: SPECIAL INSTRUCTIONS IF BASKET HAS SOME REMAINING FRENZY TIME ////
				//// ESP: INSTRUCCIONES ESPECIALES SI A LA CANASTA LA QUEDA TIEMPO DE FRENESÍ ////
				if (frenzyTime > 0) {
					drainFrenzyTime(); // disminuir en 1 el tiempo de frenesí.
					
					/* EENG: Red apple spawn rate will return to normal once the frenzy time is below 800.
					 * This is to avoid game over issues.
					 * ESP: El tiempo de generación de manzanas rojas volverá a la normalidad si el tiempo de frenesí está por debajo de 800.
					 */
					if (frenzyTime <= 800)
						redApple.setSpawnRateInMillis(redAppleLastSavedSpawnRate);
					if (frenzyTime <= 0)
						gw.lblFrenzyTime.setForeground(new Color(51, 51, 51));
				}
				
				//// ENG: SPECIAL INSTRUCTIONS IF BASKET HAS SOME REMAINING CURSE TIME ////
				//// ESP: INSTRUCCIONES ESPECIALES SI A LA CANASTA LE QUEDA TIEMPO DE MALDICIÓN ////
				if (curseTime > 0) {
					drainCurseTime(); // disminuir en 1 el tiempo de maldición.
					
					// ENG: Bomb spawn rate will return to normal once the curse time is over.
					// ESP: El tiempo de generación de bombas volverá a la normalidad una vez que se acabe el tiempo de maldición.
					if (curseTime <= 0) {
						bomb.setSpawnRateInMillis(bombLastSavedSpawnRate);
						gw.lblCurseTime.setForeground(new Color(51, 51, 51));
					}
				}
				
				// ENG: THE LIVES LEFT LABEL ON GAME WINDOW WILL CHANGE IT'S COLOR DEPENDING ON LIVES REMAINING.
				// few lives left means red letters! Otherwise, black letters will show.
				// ESP: EL LABEL DE LAS VIDAS RESTANTES EN LA VENTANA DEL JUEGO CAMBIARÁ SU COLOR DEPENDIENDO DE CUÁNTAS VIDAS QUEDEN.
				// ¡pocas vidas restantes significan letras rojas!. De lo contrario, letras negras se mostrarán.
				if (redBlinkTimeForLives <= 0)
					setLivesLeftLabelColorAccordingToLives();
			}
			
			// ENG: GAME OVER ACTIONS! //
			// ESP: ¡ACCIONES DE GAME OVER! //
			gett.interrupt(); // ENG: interrupt the game played clock. ESP: interrumpir el reloj de tiempo de juego.
			
			bgMusic1.stopSound();
			gameOverSound.playSound();
			
			// Si en la ventana de Game Over se selecciona "no", el juego finaliza
			if (gw.generateGameOverConfirmDialog() == JOptionPane.NO_OPTION) {
				playerWannaPlay = false;
				gameOverSound.stopSound();
			}
			else { // De lo contrario, el juego se reinicia.
				restartGame();
				isGameRunning = true;
				gameOverSound.stopSound();
				bgMusic1.playAndLoopSound();
			}
		}
		System.exit(0);
	}
	
	private void increaseScoreIn(int wonPoints) {
		score += wonPoints;
		
		if (wonPoints < 0)
			redBlinkTimeForScore = 30 * (int)Math.ceil(gameSpeed);
		
		if (score <= 0)
			score = 0;
		
		gw.lblScore.setText("Score: " + score);
	}
	
	private void increaseGameSpeedIn(float wonSpeed) {
		if (gameSpeed <= MAX_SPEED) {
			gameSpeed += wonSpeed;
			gw.lblSpeed.setText("Speed: " + toTwoDecimals.format(gameSpeed));
		}
		else
			gw.lblSpeed.setText("Speed: " + toTwoDecimals.format(gameSpeed) + " (MAX)");
	}
	
	private void increaseLivesIn(int wonLives) {
		lives += wonLives;
		if (wonLives < 0)
			redBlinkTimeForLives = 30 * (int)Math.ceil(gameSpeed);
		
		if (lives <= 0) {
			lives = 0;
			isGameRunning = false;
			gameOverMessage = "you ran out of lives :(";
		}
		
		gw.lblLivesLeft.setText("Lives left: " + lives);
		
		redApple.setAnEntityHasTouchedTheGround(false);
	}
	
	private void setLivesLeftLabelColorAccordingToLives() {
		if (lives > 3)
			gw.lblLivesLeft.setForeground(new Color(51, 51, 51));
		
		if (lives <= 3)
			gw.lblLivesLeft.setForeground(Color.RED);
	}
	
	private void restoreAllLives() {
		lives = MAX_LIVES;
		gw.lblLivesLeft.setText("Lives left: " + lives);
	}
	
	private void drainRedBlinkTimeForScore() {
		redBlinkTimeForScore--;
	}
	
	private void drainRedBlinkTimeForLives() {
		redBlinkTimeForLives--;
	}
	
	private void drainFrenzyTime() {
		frenzyTime--;
		gw.lblFrenzyTime.setText("Frenzy time: " + frenzyTime);
	}
	
	private void drainCurseTime() {
		curseTime--;
		gw.lblCurseTime.setText("Curse time: " + curseTime);
	}
	
	private void restartGame() {
		redApple.cleanEntitiesOfThisTypeOnScreen();
		bomb.cleanEntitiesOfThisTypeOnScreen();
		greenApple.cleanEntitiesOfThisTypeOnScreen();
		goldenApple.cleanEntitiesOfThisTypeOnScreen();
		rainbowApple.cleanEntitiesOfThisTypeOnScreen();
		rottenApple.cleanEntitiesOfThisTypeOnScreen();
		skullApple.cleanEntitiesOfThisTypeOnScreen();
		
		redApple.setSpawnRateInMillis(originalRedAppleSpawnRate);
		bomb.setSpawnRateInMillis(originalBombSpawnRate);
		
		redAppleLastSavedSpawnRate = originalRedAppleSpawnRate;
		bombLastSavedSpawnRate = originalBombSpawnRate;
		
		score = 0;
		gameSpeed = INITIAL_GAME_SPEED;
		lives = MAX_LIVES;
		
		setLivesLeftLabelColorAccordingToLives();
		
		redBlinkTimeForScore = 0;
		redBlinkTimeForLives = 0;
		frenzyTime = 0;
		curseTime = 0;
		
		gw.lblScore.setForeground(new Color(51, 51, 51));
		gw.lblFrenzyTime.setForeground(new Color(51, 51, 51));
		gw.lblCurseTime.setForeground(new Color(51, 51, 51));
		
		gw.lblScore.setText("Score: " + score);
		gw.lblSpeed.setText("Speed: " + INITIAL_GAME_SPEED);
		gw.lblLivesLeft.setText("Lives left: " + MAX_LIVES);
		
		gw.lblFrenzyTime.setText("Frenzy time: " + frenzyTime);
		gw.lblCurseTime.setText("Curse time: " + curseTime);
		
		gameOverMessage = "";
		gameOverTip = "";
		
		spawnRateChanged = true;
		
		gett.restartClock();
		gett.checkAccess();
	}
	
	// GETTERS & SETTERS //
	
	public boolean isGameRunning() {
		return isGameRunning;
	}

	public void setGameRunning(boolean isGameRunning) {
		this.isGameRunning = isGameRunning;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public float getGameSpeed() {
		return gameSpeed;
	}

	public void setGameSpeed(float gameSpeed) {
		this.gameSpeed = gameSpeed;
	}
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public int getFrenzyTime() {
		return frenzyTime;
	}

	public void setFrenzyTime(int frenzyTime) {
		this.frenzyTime = frenzyTime;
	}

	public int getCurseTime() {
		return curseTime;
	}

	public void setCurseTime(int curseTime) {
		this.curseTime = curseTime;
	}

	public String getGameOverMessage() {
		return gameOverMessage;
	}

	public void setGameOverMessage(String gameOverMessage) {
		this.gameOverMessage = gameOverMessage;
	}

	public String getGameOverTip() {
		return gameOverTip;
	}

	public void setGameOverTip(String gameOverTip) {
		this.gameOverTip = gameOverTip;
	}
}
