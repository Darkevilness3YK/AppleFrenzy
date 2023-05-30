package classes;
import java.util.Arrays;

public class Hitbox {
	// ENG: COORDINATES CONSTANTS. USED IN int[2] ARRAYS //
	// ESP: CONSTANTES DE COORDENADAS. USADAS EN ARREGLOS DE TIPO int[2] //
	protected final int X = 0;
	protected final int Y = 1;
	
	// ENG: HITBOX SIZE //
	// ESP: TAMAÑO DE LA HITBOX //
	/** Ancho de la hitbox. */
	protected int hitboxWidth = 0; // ancho de la hitbox
	/** Largo de la hitbox. */
	protected int hitboxHeight = 0; // alto de la hitbox
	
	// HITBOX CENTER //
	/** Centro de la hitbox. */
	protected int[] hitboxCenter = new int[2]; // centro de la hitbox
	
	// HITBOX CORNERS //
	/** Esquina superior izquierda.*/
	protected int[] topLeftCorner = new int[2]; // esquina superior izquierda
	/** Esquina superior derecha.*/
	protected int[] topRightCorner = new int[2]; // esquina superior derecha
	/** Esquina inferior derecha.*/
	protected int[] bottomRightCorner = new int[2]; // esquina inferior derecha
	/** Esquina inferior izquierda*/
	protected int[] bottomLeftCorner = new int[2]; // esquina inferior izquierda
	
	/**
	 * 
	 * @param entityCenter
	 * - the coordinates [X, Y] of the center of the hitbox
	 * @param entityWidth
	 * - the width of the hitbox
	 * @param entityHeight
	 * - the height of the hitbox
	 */
	public Hitbox(int[] entityCenter, int entityWidth, int entityHeight) {
		this.hitboxCenter = entityCenter; // centro de la hitbox
		this.hitboxWidth = entityWidth; // ancho de la hitbox
		this.hitboxHeight = entityHeight; // alto de la hitbox
		updateHitbox();
	}
	
	/**
	 * @param objectCenter
	 * an array of size 2 that saves the coords of the center of an object [x, y].
	 * @param hitboxWidth
	 * the width of the hitbox.
	 * @param hitboxHeight
	 * the height of the hitbox.<br><br>
	 * The hitbox is concentric: the center of the object determines the object hitbox's corners coords.<br>
	 * Every time the center of the object is moved, the hitbox also moves (size isn't changed), so the hitbox is always updated.*/
	public void updateHitbox() {
		//System.out.println("CENTER AT: " + Arrays.toString(HitboxCenter));
		
		// ENG: TOP-LEFT CORNER //
		// ESP: ESQUINA SUPERIOR IZQUIERDA //
		topLeftCorner[X] = hitboxCenter[X] - (hitboxWidth / 2);
		topLeftCorner[Y] = hitboxCenter[Y] - (hitboxHeight / 2);
		
		// ENG: TOP-RIGHT CORNER //
		// ESP: ESQUINA SUPERIOR DERECHA //
		topRightCorner[X] = hitboxCenter[X] + (hitboxWidth / 2);
		topRightCorner[Y] = hitboxCenter[Y] - (hitboxHeight / 2);
		
		// ENG: BOTTOM-RIGHT CORNER //
		// ESP: ESQUINA INFERIOR DERECHA //
		bottomRightCorner[X] = hitboxCenter[X] + (hitboxWidth / 2);
		bottomRightCorner[Y] = hitboxCenter[Y] + (hitboxHeight / 2);
		
		// ENG: BOTTOM-LEFT CORNER //
		// ESP: ESQUINA INTERIOR IZQUIERDA //
		bottomLeftCorner[X] = hitboxCenter[X] - (hitboxWidth / 2);
		bottomLeftCorner[Y] = hitboxCenter[Y] + (hitboxHeight / 2);
	}
	
	/** Prints the coordinates [X, Y] of the center of the hitbox;
	 */
	public void printHitboxCenter() {
		System.out.println("CENTER [X, Y]: " + Arrays.toString(hitboxCenter));
	}
	
	/** Prints all the 4 corners of the hitbox of the object (top-left, top-right, bottom-right and bottom-left);
	 */
	public void printHitboxCorners() {
		System.out.println("TOP-LEFT CORNER COORDS [X0, Y0]: " + Arrays.toString(topLeftCorner) + "\t");
		System.out.println("TOP-RIGHT CORNER COORDS [X1, Y1]: " + Arrays.toString(topRightCorner) + "\t");
		System.out.println("BOTTOM-RIGHT CORNER COORS [X2, Y2]: " + Arrays.toString(bottomRightCorner) + "\t");
		System.out.println("BOTTOM-LEFT CORNER COORDS [X3, Y3]: " + Arrays.toString(bottomLeftCorner) + "\t");
	}
	
	/**
	 * @param anotherEntityHitbox (hitbox de otra entidad) <br>
	 * - ENG: the hitbox of the collider object.<br>
	 * - ESP: la hitbox del objeto colisionador.<br><br>
	 * 
	 * 
	 * ENG: This method needs to be called by an collided object with bigger hitbox than the collider object of the parameter.<br>
	 * Otherwise, this method may not work as expected.<br>
	 * Basically, if at least any of the corners of the first object's hitbox (the calling object) is inside the corners of the second object's hitbox (the parameter object), a collision exists.<br><br>
	 * 
	 * On Apple Frenzy game, this method is only called by the basket entity.<br>
	 * The basket is the collided object and the entities like red apples, bombs, etc. are the collider objects.<br><br>
	 * 
	 * ESP: Este método necesita ser llamado por un objeto que va a ser colisionado con una hitbox más grande que el objeto que lo va a colisionar.<br>
	 * De otra forma, este método puede no funcionar como se espera.<br>
	 * Básicamente, si al menos alguna de las esquinas de la hitbox del primer objeto (el que llama al método) está dentro de las esquinas de la hitbox del segundo objeto (el del parámetro de este método), una colisión existe.<br><br>
	 * 
	 * En el juego Apple Frenzy, este método es solo llamado por la entidad "canasta".<br>
	 * La canasta es el objeto colisionado y las entidades como las manzanas rojas, bombas, etc. son los objetos colisionadores.
	 * 
	 * @return <br>
	 * ENG: true if a collision is detected, false if not.<br>
	 * ESP: true si una colisión es detectada, false si no.
	 * */
	public boolean isCollidingWith(Hitbox anotherEntityHitbox) {
		/* ENG: cornerCoords: coordinates of a corner. Which corner? Determined by the for.<br>
		 * ESP: cornerCoords: coordenadas de una esquina. ¿Cuál esquina? Determinada por el for.*/
		int[] cornerCoords = new int[2];
		
		for (int corner = 0; corner < 4; corner++) {
			switch (corner) {
				case 0:
					cornerCoords = topLeftCorner;
					break;
				case 1:
					cornerCoords = topRightCorner;
					break;
				case 2:
					cornerCoords = bottomRightCorner;
					break;
				case 3:
					cornerCoords = bottomLeftCorner;
					break;
				default:
					break;
			}
			
			/* ENG: Basically, if at least any of the corners of the collider object's hitbox is inside the corners of the collided object's hitbox, a collision exists.
			 * The collider object must be smaller than the collided object; otherwise, this method may not work as expected. */
			
			/* ESP: Básicamente, si al menos alguna de las esquinas de la hitbox del objeto colisionador está dentro de las esquinas del objeto colisionado, una colisión existe.
			 * El objeto colisionador debe ser más pequeño que el objeto colisionado; de otra forma, este método puede no funcionar como se espera. */
			if (
					(cornerCoords[X] >= anotherEntityHitbox.topLeftCorner[X]) && (cornerCoords[Y] >= anotherEntityHitbox.topLeftCorner[Y]) &&
					(cornerCoords[X] <= anotherEntityHitbox.bottomRightCorner[X]) && (cornerCoords[Y] <= anotherEntityHitbox.bottomRightCorner[Y])
			)
				return true;
		}
		
		return false;
	}
}
