package classes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindowContentPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ENG: GAME WINDOW - THIS IS TO GET THE WINDOW SIZE //
	// ESP: VENTANA DEL JUEGO - ESTO ES PARA OBTENER EL TAMAÑO DE LA VENTANA //
	private JFrame gameWindow;
	
	// ENG: THE BACKGROUNDS OF THE GAME WINDOW //
	// ESP: LOS FONDOS DE LA VENTANA DE JUEGO //
	private URL backgroundURL;
	private URL groundURL;
	
	// ENG: ENTITIES AND ANOTHER OBJECTS THAT WILL BE DRAWN ON SCREEN //
	// ESP: ENTIDADES Y OTROS OBJETOS QUE SE DIBUJARÁN EN PANTALLA //
	private FallingEntity redApple, bomb;
	private Basket b;
	
	// ENG: SPECIAL ITEMS OBJECTS //
	// ESP: OBJETOS DE ITEMS ESPECIALES //
	private SpecialItem greenApple, goldenApple, rainbowApple, rottenApple, skullApple;
	
	// ENG: BOOLEAN VARIABLE THAT ALLOWS TO MAKE THE ENTITIES' HITBOX VISIBLE IF IT'S IN TRUE //
	// ESP: VARIABLE BOOLEANA QUE PERMITE QUE LA HITBOX DE LAS ENTIDADES SE VEAN SI ESTÁ EN TRUE //
	private boolean showEntitiesHitbox;
	
	public GameWindowContentPane(JFrame gameWindow, boolean showEntitiesHitbox, URL backgroundURL, URL groundURL, FallingEntity redApple, FallingEntity bomb, Basket b, SpecialItem greenApple, SpecialItem goldenApple, SpecialItem rainbowApple, SpecialItem rottenApple, SpecialItem skullApple) {
		this.gameWindow = gameWindow;
		this.showEntitiesHitbox = showEntitiesHitbox;
		this.backgroundURL = backgroundURL;
		this.groundURL = groundURL;
		this.redApple = redApple;
		this.bomb = bomb;
		this.b = b;
		this.greenApple = greenApple;
		this.goldenApple = goldenApple;
		this.rainbowApple = rainbowApple;
		this.rottenApple = rottenApple;
		this.skullApple = skullApple;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		/* ENG: THE ORDER OF DRAWING IS IMPORTANT!!!
		 * The order define the layering of the drawn objects. In other words:
		 * - The objects that are drawn first are more away on Z-Axis.
		 * - The objects that are drawn last are more close on Z-Axis.
		 * ESP: ¡¡¡EL ORDEN DE DIBUJADO ES IMPORTANTE!!!
		 * El orden define la capa de los objetos dibujados. En otras palabras:
		 * - Los objetos que se dibujen primero, estarán más lejos en el eje Z.
		 * - Los objetos que se dibujen de último, estarán más cerca en el eje Z.
		 */
		super.paintComponent(g);
		drawBackground(g); // dibujar el fondo
		
		// ENG: draw the entities' hitbox if the flag is in true.
		// ESP: dibuja la hitbox de las entidades si la bandera está en true.
		if (showEntitiesHitbox) {
			redApple.drawHitbox(g);
			bomb.drawHitbox(g);
			greenApple.drawHitbox(g);
			goldenApple.drawHitbox(g);
			rainbowApple.drawHitbox(g);
			rottenApple.drawHitbox(g);
			skullApple.drawHitbox(g);
			b.drawHitbox(g);
		}
		
		// ENG: draw the entities' texture.
		// ESP: dibuja las texturas de las entidades.
		redApple.drawTexture(g);
		bomb.drawTexture(g);
		greenApple.drawTexture(g);
		goldenApple.drawTexture(g);
		rainbowApple.drawTexture(g);
		rottenApple.drawTexture(g);
		skullApple.drawTexture(g);
		
		drawGround(g); // dibujar el suelo
		
		b.drawTexture(g);
		
		repaint();
	}
	
	private void drawBackground(Graphics g) {
		Image bg = new ImageIcon(backgroundURL).getImage();
		g.drawImage(bg, 0, 0, gameWindow.getWidth(), (gameWindow.getHeight() - 80), Color.GREEN.brighter().brighter(), this);
	}
	
	private void drawGround(Graphics g) {
		Image bg = new ImageIcon(groundURL).getImage();
		g.drawImage(bg, 0, (gameWindow.getHeight() - 30 - 80), gameWindow.getWidth(), 80, Color.GREEN.brighter().brighter(), this);
	}
}
