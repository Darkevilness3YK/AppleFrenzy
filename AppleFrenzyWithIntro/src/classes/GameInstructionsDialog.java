package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GameInstructionsDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanelBG contentPane;
	//private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			GameInstructionsDialog dialog = new GameInstructionsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	/**
	 * Create the dialog.
	 */
	
	public GameInstructionsDialog(GameIntroWindow giw) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent we) {
				giw.enableIntroWindowButtons();
				setVisible(false);
			}
		});
		// GameInstructionDialog (this class) settings //
		setBounds(100, 100, 700, 650);
		setFocusableWindowState(true);
		setResizable(false);
		setIconImage(new ImageIcon(getSourceURL("/resources/red_apple.png")).getImage());
		setTitle("Apple Frenzy - Instructions");
		
		contentPane = new JPanelBG(this.getWidth(), this.getHeight(), getSourceURL("/resources/wood_bg_alpha.png"));
		//contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0x57D6FF));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel instructionsPane = new JPanel();
		instructionsPane.setOpaque(false);
		contentPane.add(instructionsPane);
		instructionsPane.setLayout(null);
		
		JEditorPane dtrpnInstruccionGeneral = new JEditorPane("text/html", "");
		dtrpnInstruccionGeneral.setBorder(new LineBorder(new Color(0, 0, 0)));
		dtrpnInstruccionGeneral.setOpaque(false);
		dtrpnInstruccionGeneral.setBounds(10, 3, 660, 44);
		instructionsPane.add(dtrpnInstruccionGeneral);
		dtrpnInstruccionGeneral.setEditable(false);
		dtrpnInstruccionGeneral.setText("<strong>El objetivo principal es sobrevivir el mayor tiempo posible con la mayor puntuación posible.<br>¿Cuánto sobrevivirás?</strong>");
		
		JEditorPane dtrpnItemsBuenos = new JEditorPane("text/html", "");
		dtrpnItemsBuenos.setBorder(new LineBorder(new Color(0, 0, 0)));
		dtrpnItemsBuenos.setOpaque(false);
		dtrpnItemsBuenos.setBounds(10, 50, 660, 25);
		instructionsPane.add(dtrpnItemsBuenos);
		dtrpnItemsBuenos.setEditable(false);
		dtrpnItemsBuenos.setText("<strong>ITEMS BUENOS:</strong> Puedes recoger los siguientes items sin problemas:");
		
		JEditorPane dtrpnManzanaRoja = new JEditorPane("text/html", "");
		dtrpnManzanaRoja.setBorder(new LineBorder(new Color(0, 0, 0)));
		dtrpnManzanaRoja.setOpaque(false);
		dtrpnManzanaRoja.setBounds(70, 78, 600, 50);
		instructionsPane.add(dtrpnManzanaRoja);
		dtrpnManzanaRoja.setEditable(false);
		dtrpnManzanaRoja.setText("<strong>- Manzana roja:</strong> te da 1 punto. Si no se recoge 1 manzana roja, se pierde 1 vida.");
		
		JEditorPane dtrpnManzanaVerde = new JEditorPane("text/html", "");
		dtrpnManzanaVerde.setBorder(new LineBorder(new Color(0, 0, 0)));
		dtrpnManzanaVerde.setOpaque(false);
		dtrpnManzanaVerde.setBounds(70, 131, 600, 50);
		instructionsPane.add(dtrpnManzanaVerde);
		dtrpnManzanaVerde.setEditable(false);
		dtrpnManzanaVerde.setText("<strong>- Manzana verde:</strong> te da 2 puntos. Te recupera 1 vida siempre que estén por debajo del máximo, que es 10.");
		
		JEditorPane dtrpnManzanaDorada = new JEditorPane("text/html", "");
		dtrpnManzanaDorada.setBorder(new LineBorder(new Color(0, 0, 0)));
		dtrpnManzanaDorada.setOpaque(false);
		dtrpnManzanaDorada.setBounds(70, 184, 600, 50);
		instructionsPane.add(dtrpnManzanaDorada);
		dtrpnManzanaDorada.setEditable(false);
		dtrpnManzanaDorada.setText("<strong>- Manzana dorada:</strong> te da 5 puntos. Te recupera todas las vidas perdidas, es decir, te deja con 10 vidas.");
		
		JEditorPane dtrpnManzanaArcoiris = new JEditorPane("text/html", "");
		dtrpnManzanaArcoiris.setBorder(new LineBorder(new Color(0, 0, 0)));
		dtrpnManzanaArcoiris.setOpaque(false);
		dtrpnManzanaArcoiris.setBounds(70, 237, 600, 66);
		instructionsPane.add(dtrpnManzanaArcoiris);
		dtrpnManzanaArcoiris.setEditable(false);
		dtrpnManzanaArcoiris.setText("<strong>- Manzana arcoíris:</strong> da un tiempo de frenesí. Durante el tiempo de frenesí, eres inmune a todos los items malos y a perder vidas por dejar caer manzanas rojas. Además, las manzanas rojas caerán muy rápidamente, lo que te permitirá sumar muchos puntos.");
		
		JEditorPane dtrpnItemsMalos = new JEditorPane("text/html", "");
		dtrpnItemsMalos.setBorder(new LineBorder(new Color(0, 0, 0)));
		dtrpnItemsMalos.setOpaque(false);
		dtrpnItemsMalos.setBounds(10, 306, 660, 44);
		instructionsPane.add(dtrpnItemsMalos);
		dtrpnItemsMalos.setEditable(false);
		dtrpnItemsMalos.setText("<strong>ITEMS MALOS:</strong> Necesitas evitar a toda costa los siguientes items (si tienes tiempo de frenes\u00ED no te pasar\u00E1 nada si los recoges):");
		
		JEditorPane dtrpnManzanaPodrida = new JEditorPane("text/html", "");
		dtrpnManzanaPodrida.setBorder(new LineBorder(new Color(0, 0, 0)));
		dtrpnManzanaPodrida.setOpaque(false);
		dtrpnManzanaPodrida.setBounds(70, 353, 600, 50);
		instructionsPane.add(dtrpnManzanaPodrida);
		dtrpnManzanaPodrida.setEditable(false);
		dtrpnManzanaPodrida.setText("<strong>- Manzana podrida:</strong> pierdes 10 puntos. Si tienes menos de 10 puntos, te quedar\u00E1s con 0 puntos. Tambi\u00E9n te hace perder 2 vidas.");
		
		JEditorPane dtrpnBomba = new JEditorPane("text/html", "");
		dtrpnBomba.setBorder(new LineBorder(new Color(0, 0, 0)));
		dtrpnBomba.setOpaque(false);
		dtrpnBomba.setBounds(70, 406, 600, 50);
		instructionsPane.add(dtrpnBomba);
		dtrpnBomba.setEditable(false);
		dtrpnBomba.setText("<strong>- Bomba:</strong> si la recoges y no hay tiempo de frenes\u00ED, se acaba el juego.");
		
		JEditorPane dtrpnManzanaEsqueleto = new JEditorPane("text/html", "");
		dtrpnManzanaEsqueleto.setBorder(new LineBorder(new Color(0, 0, 0)));
		dtrpnManzanaEsqueleto.setOpaque(false);
		dtrpnManzanaEsqueleto.setBounds(70, 459, 600, 50);
		instructionsPane.add(dtrpnManzanaEsqueleto);
		dtrpnManzanaEsqueleto.setEditable(false);
		dtrpnManzanaEsqueleto.setText("<strong>- Manzana esqueleto:</strong> da un tiempo de maldici\u00F3n. Durante el tiempo de maldici\u00F3n, las bombas van a caer muy r\u00E1pido. \u00A1Ev\u00EDtalas!");
		
		JEditorPane dtrpnDiviertete = new JEditorPane();
		dtrpnDiviertete.setBorder(new LineBorder(new Color(0, 0, 0)));
		dtrpnDiviertete.setOpaque(false);
		dtrpnDiviertete.setBounds(288, 512, 108, 25);
		dtrpnDiviertete.setContentType("text/html");
		dtrpnDiviertete.setText("\u00A1DIVI\u00C9RTETE! :]");
		instructionsPane.add(dtrpnDiviertete);
		
		JLabel lblManzanaRoja = new JLabel("");
		lblManzanaRoja.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblManzanaRoja.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/red_apple.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		lblManzanaRoja.setBounds(10, 78, 50, 50);
		instructionsPane.add(lblManzanaRoja);
		
		JLabel lblManzanaVerde = new JLabel("");
		lblManzanaVerde.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblManzanaVerde.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/green_apple.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		lblManzanaVerde.setBounds(10, 131, 50, 50);
		instructionsPane.add(lblManzanaVerde);
		
		JLabel lblManzanaDorada = new JLabel("");
		lblManzanaDorada.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblManzanaDorada.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/golden_apple.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		lblManzanaDorada.setBounds(10, 184, 50, 50);
		instructionsPane.add(lblManzanaDorada);
		
		JLabel lblManzanaArcoiris = new JLabel("");
		lblManzanaArcoiris.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblManzanaArcoiris.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/rainbow_apple.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		lblManzanaArcoiris.setBounds(10, 237, 50, 66);
		instructionsPane.add(lblManzanaArcoiris);
		
		JLabel lblManzanaPodrida = new JLabel("");
		lblManzanaPodrida.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblManzanaPodrida.setBounds(10, 353, 50, 50);
		instructionsPane.add(lblManzanaPodrida);
		lblManzanaPodrida.setHorizontalAlignment(SwingConstants.CENTER);
		lblManzanaPodrida.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/rotten_apple.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		
		JLabel lblBomba = new JLabel("");
		lblBomba.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblBomba.setBounds(10, 406, 50, 50);
		instructionsPane.add(lblBomba);
		lblBomba.setHorizontalAlignment(SwingConstants.CENTER);
		lblBomba.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/bomb.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		
		JLabel lblManzanaEsqueleto = new JLabel("");
		lblManzanaEsqueleto.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblManzanaEsqueleto.setBounds(10, 460, 50, 50);
		instructionsPane.add(lblManzanaEsqueleto);
		lblManzanaEsqueleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblManzanaEsqueleto.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/skull_apple.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.setOpaque(false);
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				giw.enableIntroWindowButtons();
				setVisible(false);
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JLabel lblInstrucciones = new JLabel("INSTRUCCIONES");
		lblInstrucciones.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblInstrucciones, BorderLayout.NORTH);
	}
	
	// ENG: get the complete URL from entityURLSound from the PC files. The sound file MUST BE .WAV!!!
	// ESP: obtiene la URL de la variable entityURLSound desde los archivos de la PC. EL ARCHIVO DE SONIDO DEBE SER .WAV!!!
	public URL getSourceURL(String sourcePath) {
		return getClass().getResource(sourcePath);
		// getResource() = C:\Users\Alan\Documents\EclipseProjects\PersonalProjects\AppleFrenzy\src
		// sourcePath = /resources/archivo
		// return C:\Users\Alan\Documents\EclipseProjects\PersonalProjects\AppleFrenzy\src\resources\archivo
	}
	
	/* Devolver el Dialog de instrucciones (esta clase) */
	public GameInstructionsDialog getGameInstructionsDialog() {
		return this;
	}
}
