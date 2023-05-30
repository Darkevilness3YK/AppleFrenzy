package classes;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FallingEntity extends Entity {
	// ENG: FALLING ENTITY ATTRIBUTES //
	// ESP: ATRIBUTOS DE ESTA CLASE //
	private int spawnRateInMillis = 1; // tiempo de generación de una entidad en milisegundos
	private boolean anEntityHasTouchedTheGround = false; // true si una entidad ha tocado el suelo (desaparecido de la pantalla), false si no.
	
	// ENG: LIST OF ENTITIES THAT ARE ON SCREEN - EACH ONE WITH THEIR HITBOX INFO //
	// ESP: LISTA DE ENTIDADES EN PANTALLA - CADA UNA CON LA INFO DE SU HITBOX //
	protected LinkedList<Hitbox> entitiesOnScreen = new LinkedList<Hitbox>();
	
	/**
	 * Any object of this class will randomly spawn and fall from the top of the game window. <br>
	 * For example, if we create an object called "apple" like this:<br>
	 * <code>&emsp;FallingEntity apple = new FallingEntity(args);</code><br>
	 * apples with the set parameters will fall from the top of the screen. <br>
	 * 
	 * <pre>public FallingEntity(int[] entityCenter,
	 *                           int entityWidth,
	 *                           int entityHeight,
	 *                           Color entityColor,
	 *                           String entityURLTexture,
	 *                           String entityURLSound,
	 *                           boolean showEntityTexture,
	 *                           JFrame window,
	 *                           int spawnRateInMillis</pre>
	 * @param entityCenter
	 * - [X, Y] position of the entity center. NOTE: X and Y positions must be inside the game window size!
	 * @param entityWidth
	 * - the width of the entity
	 * @param entityHeight
	 * - the height of the entity
	 * @param entityColor
	 * - an java.awt.Color color.
	 * @param entityURLTexture
	 * - the URL of the entity texture. It's recommended the URL is on a package inside the Java project.
	 * @param entityURLSound
	 * - the URL of the entity sound. It's recommended the URL is on a package inside the Java project and the sound file is on .wav extension.
	 * @param showEntityTexture
	 * - true to show the entity texture (if a URL texture is given). false to show only the entity color
	 * @param window
	 * - the window where the entity is going to show.
	 * @param spawnRateInMillis
	 * - this entity will spawn each indicated seconds
	 **/
	public FallingEntity(int[] entityCenter, int entityWidth, int entityHeight, Color entityColor, URL entityURLTexture, URL entityURLSound, boolean showEntityTexture, JFrame window, int spawnRateInMillis) {
		super(entityCenter, entityWidth, entityHeight, entityColor, entityURLTexture, entityURLSound, showEntityTexture, window);
		this.spawnRateInMillis = spawnRateInMillis; // tiempo de generación de una entidad en milisegundos
	}
	
	/** ENG: To create an entity:<br>
	 * 1. Generate a random X pos for this entity's hitbox center in the window where the entity is going to generate.<br>
	 * 2. Put a starting Y pos for this entity's hitbox center, this is before the entity appears from the top of the window.<br>
	 * 3. In this case, updateHitbox() is to set the entity hitbox's corners for first time.<br>
	 * 4. Add the info of the hitbox previously filled into the entitiesOnScreen list.<br><br>
	 * ESP: Para crear una entidad:<br>
	 * 1. Generar una posición X aleatoria para el centro de la hitbox de esta entidad en la ventana en la que se generará la entidad.<br>
	 * 2. Poner una posición inicial Y para el centro de la hitbox de esta entidad. Esto es: antes de que aparezca desde la parte superior de la ventana.<br>
	 * 3. En este caso, updateHitbox() es para colocar las esquinas de la hitbox de la entidad por primera vez.<br>
	 * 3. Agregar la info de la hitbox previamente establecida en los 3 pasos anteriores en la lista entitiesOnScreen.*/
	public void createNewEntity() {
		hitboxCenter[X] = (int)(Math.random() * (window.getWidth() - 60) + 20); // generar posición X aleatoria
		hitboxCenter[Y] = (-(hitboxHeight) - 1); // colocar una posición inicial Y en una parte no visible de la ventana (arriba de esta)
		updateHitbox(); // crear la hitbox de la entidad
		entitiesOnScreen.add(new Hitbox(hitboxCenter.clone(), hitboxWidth, hitboxHeight)); // agregar la información de la hitbox a la lista entitiesOnScreen.
	}
	
	/** ENG: Each time this method is called, the Y pos of all entities on the list will increase by 1.<br>
	 * In other words, this is making each entity of the list fall by 1 pixel.<br>
	 * If an entity has reached the bottom of the window, the entity hitbox's info is removed from the list.<br><br>
	 * ESP: Cada vez que este método se llame, la posición Y de todas las entidades en la lista incrementarán en 1.<br>
	 * En otras palabras, esto es hacer que cada entidad de la lista caiga 1 pixel.<br>
	 * Si una entidad alcanza el fondo de la ventana, la info de la hitbox de la entidad es removida de la lista.*/
	public void makeAllEntitiesOfThisTypeOnScreenFall() {
		if (entitiesOnScreen.size() > 0) { // si la lista no está vacía:
			for (int entityIndex = 0; entityIndex < entitiesOnScreen.size(); entityIndex++) { // para cada entidad
				entitiesOnScreen.get(entityIndex).hitboxCenter[Y] += 1; // incrementar su posición Y en 1.
				
				// To check if the actual entity (indicated by the for loop) entity has reached the bottom of the window //
				if (entitiesOnScreen.get(entityIndex).hitboxCenter[Y] >= (window.getHeight())) { // si la entidad actual (indicada por el ciclo for) alcanza el fondo de la ventana
					anEntityHasTouchedTheGround = true; // se marca la respectiva bandera como true
					entitiesOnScreen.remove(entityIndex); // y se elimina la info de la hitbox de la entidad de la lista.
				}
				else // si la entidad actual no tocó el fondo de la ventana
					entitiesOnScreen.get(entityIndex).updateHitbox(); // su hitbox se actualiza (porque su centro se movió, por lo tanto, su hitbox también).
			}
		}
	}
	
	/** ENG: clear the entitiesOnScreen list.<br><br>
	 * ESP: dejar vacía la lista entitiesOnScreen.*/
	public void cleanEntitiesOfThisTypeOnScreen() {
		while (entitiesOnScreen.size() > 0) {
			entitiesOnScreen.pop();
		}
		anEntityHasTouchedTheGround = false;
	}
	
	/**
	 * ENG: For each hitbox in the entitiesOnScreen list, draws the hitbox taking as starting point the top-left corner of the entity's hitbox and then the width and height of the same hitbox..<br><br>
	 * ESP: Para cada hitbox en la lista entitiesOnScreen, dibuja su hitbox tomando como punto de partida la esquina superior izquierda de la hitbox de la entidad y luego el ancho y el alto de la misma hitbox.*/
	@Override
	public void drawHitbox(Graphics g) { // g es un objeto de la clase Graphics que agarrará un color y dibujará.
		g.setColor(Color.BLACK);
		for (int entityIndex = 0; entityIndex < entitiesOnScreen.size(); entityIndex++) {
			g.drawRect(entitiesOnScreen.get(entityIndex).topLeftCorner[X],
					   entitiesOnScreen.get(entityIndex).topLeftCorner[Y],
					   hitboxWidth,
					   hitboxHeight);
		}
	}
	
	/**
	 * ENG: For each hitbox in the entitiesOnScreen list, draws the texture taking as starting point the top-left corner of the entity's hitbox and then the width and height of the same hitbox.<br>
	 * NOTE: if the showEntityTexture flag is on false, only the color of the entity will be drawn.<br><br>
	 * ESP: Para cada hitbox en la lista entitiesOnScreen, dibuja su textura tomando como punto de partida la esquina superior izquierda de la hitbox de la entidad y luego el ancho y el alto de la misma hitbox.<br>
	 * NOTA: si la bandera showEntityTexture está en falso, solo el color de la entidad se dibujará.*/
	@Override
	public void drawTexture(Graphics g) { // g es un objeto de la clase Graphics que agarrará un color y dibujará.
		if (!showEntityTexture)
			g.setColor(entityColor);
		
		for (int entityIndex = 0; entityIndex < entitiesOnScreen.size(); entityIndex++) {
			try {
				if (!showEntityTexture) {
					g.fillRect(entitiesOnScreen.get(entityIndex).topLeftCorner[X],
							   entitiesOnScreen.get(entityIndex).topLeftCorner[Y],
							   hitboxWidth,
							   hitboxHeight);
				}
				
				else {
					g.drawImage(new ImageIcon(entityURLTexture).getImage(),
								entitiesOnScreen.get(entityIndex).topLeftCorner[X],
								entitiesOnScreen.get(entityIndex).topLeftCorner[Y],
								hitboxWidth,
								hitboxHeight,
								null);
				}
			}
			catch (IndexOutOfBoundsException e) {
				System.out.println("IndexOutOfBoundsException caused by drawing an entity color/image in FallingEntity.drawTexture()");
			}
		}
	}
	
	// GETTERS AND SETTERS // 
	
	public int getSpawnRateInMillis() {
		return spawnRateInMillis;
	}

	public void setSpawnRateInMillis(int spawnRateInMillis) {
		this.spawnRateInMillis = spawnRateInMillis;
	}

	public boolean isAnEntityTouchingTheGround() {
		return anEntityHasTouchedTheGround;
	}

	public void setAnEntityHasTouchedTheGround(boolean anEntityHasTouchedTheGround) {
		this.anEntityHasTouchedTheGround = anEntityHasTouchedTheGround;
	}
}
