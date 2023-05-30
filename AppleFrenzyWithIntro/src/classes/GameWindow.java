package classes;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class GameWindow extends JFrame {
	// ENG: IDENTIFIER FOR GAMEWINDOW JFRAME CLASS. USED BY JAVA VIRTUAL MACHINE (JVM) //
	// ESP: IDENTIFICADOR PARA LA CLASE GAMEWINDOW QUE ES DE TIPO JFRAME. ES USADO POR LA JAVA VIRTUAL MACHINE (JVM) //
	private static final long serialVersionUID = 1L;
	
	// ENG: COORDINATES CONSTANTS //
	// ESP: CONSTANTES DE COORDENADAS. USADAS EN ARREGLOS DE TIPO INT[2] QUE GUARDEN COORDENADAS X y Y //
	// int[2], int[0] = X, int[1] = Y
	private final int X = 0;
	private final int Y = 1;
	
	// ENG: ELEMENTS IN GAME PANE //
	// ESP: ELEMENTOS EN EL PANEL DE JUEGO (LO QUE SE VA A MOSTRAR EN PANTALLA) //
	public JLabel lblScore;
	public JLabel lblSpeed;
	public JLabel lblLivesLeft;
	public JLabel lblFrenzyTime;
	public JLabel lblCurseTime;
	public JLabel lblTimeElapsed;
	public JLabel lblClock;
	
	// ENG: SIZE OF THIS WINDOW //
	// ESP: TAMAÑO DE LA VENTANA //
	private final int[] WINDOW_SIZE = {630, 600};
	
	/* Aquí se puede ver un ejemplo de las constantes X y Y declaradas anteriormente:
	 * Ancho de la ventana: WINDOW_SIZE[X] = 630
	 * Alto de la ventana: WINDOW_SIZE[Y] = 600
	*/
	
	// ENG: ENTITIES THAT WILL BE SHOWN ON THIS WINDOW //
	// ESP: ENTIDADES QUE SE MOSTRARÁN EN ESTA VENTANA //
	
	/* Ejemplo de creación de entidades:
	 * 
	 * FallingEntity entidad = new FallingEntity (
	 * 		coordenadas del centro int[X, Y],
	 * 		ancho,
	 * 		alto,
	 * 		Color (java.awt.Color),
	 * 		dirección (URL) de la textura,
	 * 		dirección (URL) del sonido,
	 * 		ver texturas (true para si, false para solo ver color),
	 * 		pasar un objeto de esta ventana (esta clase es una ventana),
	 * 		tiempo de generación.
	 * );
	 * 
	 * SpecialEntity entidadEspecial = new SpecialEntity (
	 * 		coordenadas del centro int[X, Y],
	 * 		ancho,
	 * 		alto,
	 * 		Color (java.awt.Color),
	 * 		dirección (URL) de la textura,
	 * 		dirección (URL) del sonido,
	 * 		ver texturas (true para si, false para solo ver color),
	 * 		pasar un objeto de esta ventana (esta clase es una ventana)
	 * );
	 * 
	 * Basket canasta = new Basket (
	 * 		coordenadas del centro int[X, Y],
	 * 		ancho,
	 * 		alto,
	 * 		Color (java.awt.Color),
	 * 		dirección (URL) de la textura,
	 * 		dirección (URL) del sonido,
	 * 		ver texturas (true para si, false para solo ver color),
	 * 		pasar un objeto de esta ventana (esta clase es una ventana),
	 * 		entidad 1,
	 * 		entidad 2,
	 * 		entidad 3,
	 * 		...
	 * 		entidad n.
	 * );
	 */
	
	/* 
	 * FallingEntity entidad = new FallingEntity (
	 * 		coordenadas del centro int[X, Y],
	 * 		ancho,
	 * 		alto,
	 * 		Color (java.awt.Color),
	 * 		dirección (URL) de la textura,
	 * 		dirección (URL) del sonido,
	 * 		ver texturas (true para si, false para solo ver color),
	 * 		pasar un objeto de esta ventana (esta clase es una ventana),
	 * 		tiempo de generación.
	 * );
	 */
	private FallingEntity redApple = new FallingEntity (
			new int[] {-20, -20},
			30,
			33,
			Color.RED,
			getSourceURL("/resources/red_apple.png"),
			getSourceURL("/resources/red_apple_sound.wav"),
			true,
			this,
			1000
	);
	private FallingEntity bomb = new FallingEntity (
			new int[] {-20, -20},
			50,
			50,
			Color.BLACK,
			getSourceURL("/resources/bomb.png"),
			getSourceURL("/resources/bomb_sound.wav"),
			true,
			this,
			2200
	);
	private SpecialItem greenApple = new SpecialItem (
			new int[] {-20, -20},
			30,
			33,
			Color.GREEN,
			getSourceURL("/resources/green_apple.png"),
			getSourceURL("/resources/green_apple_sound.wav"),
			true,
			this
	);
	private SpecialItem goldenApple = new SpecialItem (
			new int[] {-20, -20},
			30,
			33,
			Color.ORANGE,
			getSourceURL("/resources/golden_apple.png"),
			getSourceURL("/resources/golden_apple_sound.wav"),
			true,
			this
	);
	private SpecialItem rainbowApple = new SpecialItem (
			new int[] {-20, -20},
			30,
			33,
			Color.CYAN,
			getSourceURL("/resources/rainbow_apple.png"),
			getSourceURL("/resources/rainbow_apple_sound.wav"),
			true,
			this
	);
	private SpecialItem rottenApple = new SpecialItem (
			new int[] {-20, -20},
			30,
			33,
			Color.ORANGE.darker(),
			getSourceURL("/resources/rotten_apple.png"),
			getSourceURL("/resources/rotten_apple_sound.wav"),
			true,
			this
	);
	private SpecialItem skullApple = new SpecialItem (
			new int[] {-20, -20},
			30,
			33,
			Color.GRAY,
			getSourceURL("/resources/skull_apple.png"),
			getSourceURL("/resources/skull_apple_sound.wav"),
			true,
			this
	);
	private Basket b = new Basket (
			new int[] {WINDOW_SIZE[X] / 2, (WINDOW_SIZE[Y] - (55))},
			80,
			30,
			Color.ORANGE.darker().darker().darker(),
			getSourceURL("/resources/basket.png"),
			null,
			true,
			this,
			redApple,
			bomb,
			greenApple,
			goldenApple,
			rainbowApple,
			rottenApple,
			skullApple
	);
	
	// ENG: THE BACKGROUNDS OF THIS WINDOW //
	// ESP: LOS FONDOS DE ESTA VENTANA //
	private final URL backgroundURL = getSourceURL("/resources/whispy_woods_bg_alpha.png"); // Paisaje
	private final URL groundURL = getSourceURL("/resources/ground_by_side.png"); // Suelo
	private final Color background2Color = new Color(244, 241, 134); // Color de los labels de info del juego (vidas, velocidad, tiempo, etc).
	
	// ENG: ALL THE THINGS SET ON THE PARAMETERS OF THE GAMEPANE CLASS WILL BE DRAWN ON SCREEN //
	// ESP: TODA LO QUE ESTÉ EN LOS PARÁMETROS DE LA CLASE GAMEPANE SE DIBUJARÁ EN PANTALLA //
	public GameWindowContentPane gwcp = new GameWindowContentPane (
			this,
			false,
			backgroundURL,
			groundURL,
			redApple,
			bomb,
			b,
			greenApple,
			goldenApple,
			rainbowApple,
			rottenApple,
			skullApple
	);
	
	// ENG: GAME THREAD - MAIN THREAD OF THE GAME //
	// ESP: GAME THREAD - EL HILO PRINCIPAL DEL JUEGO. AQUÍ SE PROGRAMAN CONDICIONES DE COLISIÓN, SE REPRODUCEN LAS ANIMACIONES DE LAS COSAS QUE CAEN, ETC. //
	private MainThread mt = new MainThread (
			this,
			redApple,
			bomb,
			b,
			greenApple,
			goldenApple,
			rainbowApple,
			rottenApple,
			skullApple,
			getSourceURL("/resources/bg1_ghidorah_toilet_story_1.wav"),
			getSourceURL("/resources/red_apple_touched_ground_sound.wav"),
			getSourceURL("/resources/game_over_yeah.wav")
	);
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow frame = new GameWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	/**
	 * Create the frame.
	 */
	public GameWindow() {
		// game window (this class) settings //
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, WINDOW_SIZE[X], (WINDOW_SIZE[Y] + 80));
		//setBounds(0, 0, 780, 560);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(new ImageIcon(getSourceURL("/resources/red_apple.png")).getImage());
		setTitle("Apple Frenzy");
		
		// gameInfo panel settings //
		JPanel gameInfo = new JPanel();
		gameInfo.setOpaque(false);
		gameInfo.setLayout(new GridLayout(2, 4, 10, 1));
		gameInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
		gameInfo.setBackground(Color.LIGHT_GRAY);
		
		gameInfo.setBounds(-8, WINDOW_SIZE[Y] - 30, WINDOW_SIZE[X], 80);
		
		//gwcp.setBorder(new EmptyBorder(5, 5, 5, 5));
		gwcp.setLayout(null);
		setContentPane(gwcp);
		
		// lblScore settings //
		lblScore = new JLabel("Score: " + mt.getScore());
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setBounds(5, 5, 125, 14);
		lblScore.setBackground(background2Color);
		lblScore.setOpaque(true);
		gameInfo.add(lblScore);
		
		// lblSpeed settings //
		lblSpeed = new JLabel("Speed: " + mt.getGameSpeed());
		lblSpeed.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeed.setBounds(203, 5, 125, 14);
		lblSpeed.setBackground(background2Color);
		lblSpeed.setOpaque(true);
		gameInfo.add(lblSpeed);
		
		// lblLivesLeft settings //
		lblLivesLeft = new JLabel("Lives left: " + mt.getLives());
		lblLivesLeft.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLivesLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivesLeft.setBounds(370, 5, 125, 14);
		lblLivesLeft.setBackground(background2Color);
		lblLivesLeft.setOpaque(true);
		gameInfo.add(lblLivesLeft);
		
		// lblFrenzyTime settings //
		lblFrenzyTime = new JLabel("Frenzy time: " + mt.getFrenzyTime());
		lblFrenzyTime.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFrenzyTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrenzyTime.setBounds(5, 30, 250, 14);
		lblFrenzyTime.setBackground(background2Color);
		lblFrenzyTime.setOpaque(true);
		gameInfo.add(lblFrenzyTime);
		
		// lblCurseTime settings //
		lblCurseTime = new JLabel("Curse time: " + mt.getCurseTime());
		lblCurseTime.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurseTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurseTime.setBounds(203, 30, 250, 14);
		lblCurseTime.setBackground(background2Color);
		lblCurseTime.setOpaque(true);
		gameInfo.add(lblCurseTime);
		
		// lblTimeElapsed settings //
		lblTimeElapsed = new JLabel("Time elapsed:");
		lblTimeElapsed.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTimeElapsed.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeElapsed.setBounds(370, 26, 250, 18);
		lblTimeElapsed.setBackground(background2Color);
		lblTimeElapsed.setOpaque(true);
		gameInfo.add(lblTimeElapsed);
		
		// lblClock settings //
		lblClock = new JLabel("00:00:00");
		lblClock.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblClock.setHorizontalAlignment(SwingConstants.CENTER);
		lblClock.setBounds(490, 26, 250, 18);
		lblClock.setBackground(background2Color);
		lblClock.setOpaque(true);
		gameInfo.add(lblClock);
		
		gwcp.add(gameInfo);
		
		gwcp.addMouseMotionListener(b.changeBasketDirection());
		
		//gt.start();
		mt.start();
	}
	
	/** 
	 * @return
	 * A int[2] array, where:<br>
	 * int[0] = the width of this window.<br>
	 * int[1] = the height of this window.<br>
	 * <br>
	 * Un arreglo de tipo int[2], donde:<br>
	 * int[0] = el ancho de esta ventana.<br>
	 * int[1] = el alto de esta ventana.<br>
	 */
	public int[] getWindowSize() {
		return WINDOW_SIZE;
	}
	
	// ENG: get the complete URL from entityURLSound from the PC files. The sound file MUST BE .WAV!!!
	// ESP: obtiene la URL de la variable entityURLSound desde los archivos de la PC. EL ARCHIVO DE SONIDO DEBE SER .WAV!!!
	public URL getSourceURL(String sourcePath) {
		return getClass().getResource(sourcePath);
		// getResource() = C:\Users\Alan\Documents\EclipseProjects\PersonalProjects\AppleFrenzy\src
		// sourcePath = /resources/archivo
		// return C:\Users\Alan\Documents\EclipseProjects\PersonalProjects\AppleFrenzy\src\resources\archivo
	}
	
	/** Based on<br>
	 * {@link https://docs.oracle.com/javase/6/docs/api/javax/swing/JOptionPane.html}<br>
	 * Modified to make this method show a custom "game over" option pane for this game (Apple Frenzy).<br>
	 * It can be modified to make another custom option pane window.<br><br>
	 * NOTE: this window can't be closed by pressing the X!!! This is to prevent unexpected actions.<br>
	 * @return
	 * If the player presses "yes", JOptionPane.YES_OPTION is returned.<br>
	 * If the player presses "no", JOptionPane.NO_OPTION is returned.<br>*/
	public int generateGameOverConfirmDialog() {
		UIManager.put("OptionPane.yesButtonText", "Yes");
	    UIManager.put("OptionPane.noButtonText", "No");
		JOptionPane pane = new JOptionPane("<html><h3>Game Over YEAAAAAAAAAH!</h3><p>Reason of death: " + mt.getGameOverMessage() + "<p>Tip: " + mt.getGameOverTip() + "<p><p>Final score: " + mt.getScore() + "<p>Time survived: " + lblClock.getText() + "<p><p>Wanna try again?</html>");
		pane.setOptionType(JOptionPane.YES_NO_OPTION);
		pane.setMessageType(JOptionPane.ERROR_MESSAGE);
	    JDialog dialog = pane.createDialog(null, "LOL");
	    dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    dialog.setVisible(true);
	    Object selectedValue = pane.getValue();
	    if (selectedValue == JOptionPane.UNINITIALIZED_VALUE) {
	    	pane.setVisible(false);
	    	pane.setEnabled(false);
	    	dialog.setVisible(false);
	    	dialog.setEnabled(false);
	    	dialog.dispose();
	    	pane.setEnabled(false);
	    	selectedValue = generateGameOverConfirmDialog();
	    }
	    if (selectedValue == null)
	    	return JOptionPane.CLOSED_OPTION;
	    if (pane.getOptions() == null) {
	    	if (selectedValue instanceof Integer)
	    		return ((Integer)selectedValue).intValue();
	    	return JOptionPane.CLOSED_OPTION;
	    }
	    return JOptionPane.CLOSED_OPTION;
	}
}
