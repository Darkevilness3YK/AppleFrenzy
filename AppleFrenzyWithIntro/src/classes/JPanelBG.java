package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelBG extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private URL backgroundURL; // Fondo que se va a poner en el JPanel
	private int width, height; // Ancho y Largo de la imagen, respectivamente.
	
	public JPanelBG(int width, int height, URL backgroundURL) { // Inicializar los atributos de la clase.
		this.width = width;
		this.height = height;
		this.backgroundURL = backgroundURL;
	}
	
	@Override
	public void paintComponent(Graphics g) { // Dibujar el fondo en pantalla con el ancho y largo indicados.
		super.paintComponent(g);
		Image bg = new ImageIcon(backgroundURL).getImage();
		g.drawImage(bg, 0, 0, width, height, Color.GREEN.brighter().brighter(), this);
	}
}
