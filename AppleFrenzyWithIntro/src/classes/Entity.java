package classes;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.JFrame;

public abstract class Entity extends Hitbox {
	// ENTITY ATTRIBUTES //
	/** Color de la entidad. */
	protected Color entityColor = null;
	/** Dirección URL de la textura de la entidad. */
	protected URL entityURLTexture = null;
	/** Dirección URL del sonido .WAV de la entidad. */
	protected URL entityURLSound = null;
	/** true para mostrar la textura de la entidad, false para solo mostrar el color.*/
	protected boolean showEntityTexture = false;
	
	/** ENG: WINDOW WHERE THIS ENTITY IS SHOWING<br>
	 * ESP: VENTANA DONDE LA ENTIDAD SE MUESTRA */
	protected JFrame window;
	
	/**
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
	 */
	public Entity(int[] entityCenter, int entityWidth, int entityHeight, Color entityColor, URL entityURLTexture, URL entityURLSound, boolean showEntityTexture, JFrame window) {
		super(entityCenter, entityWidth, entityHeight);
		this.entityColor = entityColor; // color de la entidad
		this.entityURLTexture = entityURLTexture; // dirección URL de la textura de la entidad
		this.entityURLSound = entityURLSound; // dirección URL del sonido de la entidad
		this.showEntityTexture = showEntityTexture; // true para mostrar la textura de la entidad, false para solo mostrar el color.
		this.window = window; // ventana donde la entidad se muestra
	}
	
	/* ENG: THE ABSTRACT METHODS WILL BE COMPLETED ON THE CHILDREN CLASSES: FallingEntity, Basket and SpecialItem.
	 * NOTE: the following methods are called by the GamePane class, since it's the drawing class of the window. */
	/* ESP: LOS MÉTODOS ABSTRACTOS SERÁN COMPLETADOS EN LAS CLASES HIJA: FallingEntity, Basket and SpecialItem.
	 * NOTA: los siguientes métodos son llamados por la clase GamePane, ya que es la clase que dibuja en la ventana.*/
	
	/** @param g <br>
	 * - ENG: the Graphics class object which is going to pick a color and draw.<br>
	 * - ESP: el objeto de la clase Graphics que seleccionará un color y dibujará.<br><br>
	 * ENG: draws the texture of the entity taking as starting point the top-left corner of the entity's hitbox and then the width and height of the same hitbox.<br>
	 * ESP: dibuja la textura de la entidad tomando como punto de partida la esquina superior izquierda de la hitbox de la entidad y luego el ancho y el alto de la misma hitbox.*/
	public abstract void drawHitbox(Graphics g);
	
	/** @param g <br>
	 * - ENG: the Graphics class object which is going to pick a color and draw.<br>
	 * - ESP: el objeto de la clase Graphics que seleccionará un color y dibujará.<br><br>
	 * ENG: draws the texture of the entity taking as starting point the top-left corner of the entity's hitbox and then the width and height of the same hitbox.<br>
	 * ESP: dibuja la textura de la entidad tomando como punto de partida la esquina superior izquierda de la hitbox de la entidad y luego el ancho y el alto de la misma hitbox.*/
	public abstract void drawTexture(Graphics g);
	
	/** ENG: each time this method is called, a Sound class object is created and then the sound is played with that object.<br>
	 * ESP: cada vez que se llama a este método, un objeto de la clase Sound es creado y luego el sonido se reproduce con ese objeto. */
	public void playSoundWhenCatched() {
		Sound pst = new Sound(entityURLSound);
		pst.playSound();
	}
}
