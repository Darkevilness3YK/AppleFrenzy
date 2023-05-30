package classes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Basket extends Entity {
	// ENG: THE FALLING ENTITIES THAT WILL INTERACT WITH THE BASKET (THE PLAYER) //
	// ESP: LAS ENTIDADES CAYENTES QUE INTERACTUARÁN CON LA CANASTA (EL JUGADOR) //
	private FallingEntity redApple, bomb;
	private SpecialItem greenApple, goldenApple, rainbowApple, rottenApple, skullApple;
	
	public Basket(int[] entityCenter, int entityWidth, int entityHeight, Color entityColor, URL entityURLTexture, URL entityURLSound, boolean showEntityTexture, JFrame gameWindow, FallingEntity redApple, FallingEntity bomb, SpecialItem greenApple, SpecialItem goldenApple, SpecialItem rainbowApple, SpecialItem rottenApple, SpecialItem skullApple) {
		super(entityCenter, entityWidth, entityHeight, entityColor, entityURLTexture, entityURLSound, showEntityTexture, gameWindow);
		this.redApple = redApple;
		this.bomb = bomb;
		this.greenApple = greenApple;
		this.goldenApple = goldenApple;
		this.rainbowApple = rainbowApple;
		this.rottenApple = rottenApple;
		this.skullApple = skullApple;
	}
	
	@Override
	public void drawHitbox(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(topLeftCorner[X],
				topLeftCorner[Y],
				hitboxWidth,
				hitboxHeight);
	}
	
	@Override
	public void drawTexture(Graphics g) {
		if (!showEntityTexture)
			g.setColor(entityColor);
		try {
			if (!showEntityTexture) {
				g.fillRect(hitboxCenter[X] - (hitboxWidth / 2),
						hitboxCenter[Y] - (hitboxHeight / 2),
						hitboxWidth,
						hitboxHeight);
			}
			else {
				g.drawImage(new ImageIcon(entityURLTexture).getImage(),
						topLeftCorner[X],
						topLeftCorner[Y],
						hitboxWidth,
						hitboxHeight,
						null);
			}
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException caused by drawing an entity color/image in FallingEntity.drawEntitiesOnScreen()");
		}
	}
	
	/** ENG: the basket (player) control settings.<br>
	 * Basically, when a mouse movement is detected, the X position of the basket's center is updated according to the mouse arrow's pos..<br>
	 * Then, the basket's hitbox is updated since its center coordinates were updated too.<br><br>
	 * ESP: las opciones del control de la canasta (el jugador).<br>
	 * Básicamente, cuando un movimiento de ratón es detectado, la posición X del centro de la canasta se actualiza de acuerdo a la posición de la flecha del ratón.<br>
	 * Luego, la hitbox de la canasta se actualiza ya que las coordenadas de su centro se actualizaron también.
	 * */
	public MouseMotionAdapter changeBasketDirection() {
		/* ENG: the MouseMotionAdapter object saves the info of the mouse controller settings for this game.
		 * This can be asociated with the KeyAdapter method used for programming JButton ActionEvents.
		 * ESP: el objeto de tipo MouseMotionAdapter guarda la info del control del ratón para este juego.
		 * Esto puede asociarse con el KeyAdapter usado para programar ActionEvents de JButtons*/
		MouseMotionAdapter ma = new MouseMotionAdapter() {
			/** @param m <br>
			 * - ENG: the MouseEvent object where the mouse info is get.<br>
			 * - ESP: el objeto MouseEvent donde se obtiene la info del ratón.<br><br>
			 * ENG: this is when the mouse has been moved without pressing the main click button (left click for right-handeds, right click for left-handeds).<br><br>
			 * ESP: esto es para cuando el ratón ha sido movido sin presionar el botón del click principal (click izquierdo para diestros, click derecho para zurdos).*/
			@Override
			public void mouseMoved(MouseEvent m) {
				hitboxCenter[X] = m.getX(); // ENG: get X pos of the mouse. ESP: obtener posición X del ratón.
				updateHitbox(); // ENG: update the hitbox of the basket's center ESP: actualizar hitbox del centro de la canasta.
			}
			
			/** @param m <br>
			 * - ENG: the MouseEvent object where the mouse info is get.<br>
			 * - ESP: el objeto MouseEvent donde se obtiene la info del ratón.<br><br>
			 * ENG: this is when the mouse has been moved while pressing the main click button (left click for right-handeds, right click for left-handeds).<br><br>
			 * ESP: esto es para cuando el ratón ha sido movido mientras se presiona el botón del click principal (click izquierdo para diestros, click derecho para zurdos).*/
			@Override
			public void mouseDragged(MouseEvent m) {
				hitboxCenter[X] = m.getX(); // ENG: get X pos of the mouse. ESP: obtener posición X del ratón.
				updateHitbox(); // ENG: update the hitbox of the basket's center ESP: actualizar hitbox del centro de la canasta.
			}
		};
		return ma; // ENG: return the mouse controllers. ESP: devuelve los controles del ratón.
	}
	
	/* ENG: each time an item of the following is catched by the basket, it will return "true" to program actions and it will be removed from the entitiesOnScren list.
	 * Remember: the entitiesOnScreen list is from FallingEntity class and it saves the hitbox info of each entity that is showing on the game window!
	 * ESP: cada vez que un item de los siguientes se atrape por la canasta, devolverá "true" para programar acciones y se eliminará de la lista entitiesOnScreen.
	 * Recuerda: la lista entitiesOnScreen es de la clase FallingEntity y guarda la info de la hitbox de cada entidad que se muestre en la pantalla del juego!*/
	
	// MANZANA ROJA //
	public boolean isRedAppleCatched() {
		for (int redAppleIndex = 0; redAppleIndex < redApple.entitiesOnScreen.size(); redAppleIndex++) {
			if (redApple.entitiesOnScreen.get(redAppleIndex).isCollidingWith(this)) {
				redApple.entitiesOnScreen.remove(redAppleIndex);
				return true;
			}
		}
		return false;
	}
	
	// BOMBA //
	public boolean isBombCatched() {
		for (int bombIndex = 0; bombIndex < bomb.entitiesOnScreen.size(); bombIndex++) {
			if (bomb.entitiesOnScreen.get(bombIndex).isCollidingWith(this)) {
				bomb.entitiesOnScreen.remove(bombIndex);
				return true;
			}
		}
		return false;
	}
	
	// MANZANA VERDE //
	public boolean isGreenAppleCatched() {
		for (int greenAppleIndex = 0; greenAppleIndex < greenApple.entitiesOnScreen.size(); greenAppleIndex++) {
			if (greenApple.entitiesOnScreen.get(greenAppleIndex).isCollidingWith(this)) {
				greenApple.entitiesOnScreen.remove(greenAppleIndex);
				return true;
			}
		}
		return false;
	}
	
	// MANZANA DORADA //
	public boolean isGoldenAppleCatched() {
		for (int goldenAppleIndex = 0; goldenAppleIndex < goldenApple.entitiesOnScreen.size(); goldenAppleIndex++) {
			if (goldenApple.entitiesOnScreen.get(goldenAppleIndex).isCollidingWith(this)) {
				goldenApple.entitiesOnScreen.remove(goldenAppleIndex);
				return true;
			}
		}
		return false;
	}
	
	// MANZANA ARCOÍRIS //
	public boolean isRainbowAppleCatched() {
		for (int rainbowAppleIndex = 0; rainbowAppleIndex < rainbowApple.entitiesOnScreen.size(); rainbowAppleIndex++) {
			if (rainbowApple.entitiesOnScreen.get(rainbowAppleIndex).isCollidingWith(this)) {
				rainbowApple.entitiesOnScreen.remove(rainbowAppleIndex);
				return true;
			}
		}
		return false;
	}
	
	// MANZANA PODRIDA //
	public boolean isRottenAppleCatched() {
		for (int rottenAppleIndex = 0; rottenAppleIndex < rottenApple.entitiesOnScreen.size(); rottenAppleIndex++) {
			if (rottenApple.entitiesOnScreen.get(rottenAppleIndex).isCollidingWith(this)) {
				rottenApple.entitiesOnScreen.remove(rottenAppleIndex);
				return true;
			}
		}
		return false;
	}
	
	// MANZANA CALAVERA //
	public boolean isSkullAppleCatched() {
		for (int skullAppleIndex = 0; skullAppleIndex < skullApple.entitiesOnScreen.size(); skullAppleIndex++) {
			if (skullApple.entitiesOnScreen.get(skullAppleIndex).isCollidingWith(this)) {
				skullApple.entitiesOnScreen.remove(skullAppleIndex);
				return true;
			}
		}
		return false;
	}
}
