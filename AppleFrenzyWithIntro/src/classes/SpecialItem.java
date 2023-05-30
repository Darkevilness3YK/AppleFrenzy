package classes;
import java.awt.Color;
import java.net.URL;

import javax.swing.JFrame;

/** ENG: this class is exactly the same as an FallingEntity class, except this one doesn't have a spawn rate for their items.
 * The spawn rate is controlled by the SpecialItemSpawnThread class.
 * ESP: esta clase es exáctamente la misma que la de FallingEntity, excepto que esta no tiene un tiempo de generación para sus items.
 * El tiempo de generación es controlado por la clase SpecialItemSpawnThread.*/
public class SpecialItem extends FallingEntity {	
	public SpecialItem(int[] entityCenter, int entityWidth, int entityHeight, Color entityColor, URL entityURLTexture, URL entityURLString, boolean showEntityTexture, JFrame gameWindow) {
		super(entityCenter, entityWidth, entityHeight, entityColor, entityURLTexture, entityURLString, showEntityTexture, gameWindow, 0);
	}
}

