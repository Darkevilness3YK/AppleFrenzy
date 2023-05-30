package classes;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameIntroWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// ENG: COORDINATES CONSTANTS //
	// ESP: CONSTANTES DE COORDENADAS. USADAS EN ARREGLOS DE TIPO INT[2] QUE GUARDEN COORDENADAS X y Y //
	// int[2], int[0] = X, int[1] = Y
	private final int X = 0;
	private final int Y = 1;
	
	// ENG: ELEMENTS IN GAME PANE //
	// ESP: ELEMENTOS EN EL PANEL DE JUEGO (LO QUE SE VA A MOSTRAR EN PANTALLA) //
	private JFXPanel contentPane;
	public JButton btnStartGame = new JButton("");
	public JButton btnInstructions = new JButton("");
	public JButton btnCredits = new JButton("");
	
	GameInstructionsDialog instructionsDialog; // Dialog (parecido a una ventana) que muestra las instrucciones del juego.
	Sound introTheme; // Canción de la intro
	
	// ENG: SIZE OF THIS WINDOW //
	// ESP: TAMAÑO DE LA VENTANA //
	private final int[] WINDOW_SIZE = {630, 600};
	
	private final int[] BUTTON_SIZE = {275, 72};
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameIntroWindow giw = new GameIntroWindow();
					giw.setVisible(true);
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
	public GameIntroWindow() {
		instructionsDialog = new GameInstructionsDialog(this);
		// IntroWindow (this class) settings //
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, WINDOW_SIZE[X], (WINDOW_SIZE[Y] + 80));
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(new ImageIcon(getSourceURL("/resources/red_apple.png")).getImage());
		setTitle("Apple Frenzy");
		
		// contentPane settings //
		contentPane = new JFXPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		startVideoThread(contentPane, new File(getClass().getResource("/resources/apple_frenzy_intro_final.mp4").getPath()));
		
		//gicp.setLayout(null);
		//setContentPane(gicp);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setOpaque(false);
		panelButtons.setBorder(new EmptyBorder(15, 170, 15, 175));
		panelButtons.setBounds(0, 317, 630, 280);
		contentPane.add(panelButtons);
		panelButtons.setLayout(new GridLayout(0, 1, 100, 10));
		
		// JButton btnStartGame = new JButton("START"); //
		btnStartGame.setFont(new Font("Segoe Script", Font.BOLD, 38));
		btnStartGame.setBackground(new Color(0, 0, 0, 0));
		btnStartGame.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/start_button.png")).getImage().getScaledInstance(BUTTON_SIZE[X], BUTTON_SIZE[Y], java.awt.Image.SCALE_SMOOTH)));
		// setPressedIcon establece el ícono que mostrará el botón cuando este es presionado con el mouse.
		btnStartGame.setPressedIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/start_button_pressed.png")).getImage().getScaledInstance(BUTTON_SIZE[X], BUTTON_SIZE[Y], java.awt.Image.SCALE_SMOOTH)));
		btnStartGame.setBorderPainted(false);
		btnStartGame.setSelected(false);
		btnStartGame.setVisible(false);
		btnStartGame.addMouseListener(new MouseAdapter() { // Acciones que hará botón si hay una acción en el mouse
			@Override
			public void mousePressed(MouseEvent me) { // Cuando se presione el click izq.
				Sound buttonClickedSound = new Sound(getSourceURL("/resources/wood_click.wav"));
				buttonClickedSound.playSound();
			}
			
			@Override
			public void mouseEntered(MouseEvent me) { // Cuando la flecha del mouse esté dentro del botón.
				Sound buttonHoveredSound = new Sound(getSourceURL("/resources/wood_tap.wav"));
				buttonHoveredSound.playSound();
				btnStartGame.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/start_button_hovered.png")).getImage().getScaledInstance(BUTTON_SIZE[X], BUTTON_SIZE[Y], java.awt.Image.SCALE_SMOOTH)));
			}
			
			@Override
			public void mouseExited(MouseEvent me) { // Cuando la flecha del mouse salga del botón.
				btnStartGame.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/start_button.png")).getImage().getScaledInstance(BUTTON_SIZE[X], BUTTON_SIZE[Y], java.awt.Image.SCALE_SMOOTH)));
			}
		});
		// Cuando se presione el botón "Start", iniciará un runnable que ejecutará la ventana
		// GameWindow, la cual iniciará el juego.
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								introTheme.stopSound();
								GameWindow gw = new GameWindow();
								gw.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}	
				});
				
				dispose();
			}
		});
		panelButtons.add(btnStartGame);
		
		// JButton btnInstructions = new JButton("INSTRUCTIONS"); //
		btnInstructions.setFont(new Font("Segoe Script", Font.BOLD, 37));
		btnInstructions.setBackground(new Color(0, 0, 0, 0));
		btnInstructions.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/instructions_button.png")).getImage().getScaledInstance(BUTTON_SIZE[X], BUTTON_SIZE[Y], java.awt.Image.SCALE_SMOOTH)));
		// setPressedIcon establece el ícono que mostrará el botón cuando este es presionado con el mouse.
		btnInstructions.setPressedIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/instructions_button_pressed.png")).getImage().getScaledInstance(BUTTON_SIZE[X], BUTTON_SIZE[Y], java.awt.Image.SCALE_SMOOTH)));
		btnInstructions.setBorderPainted(false);
		btnInstructions.setSelected(false);
		btnInstructions.setVisible(false);
		btnInstructions.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) { // Cuando se presione el click izq.
				Sound buttonClickedSound = new Sound(getSourceURL("/resources/wood_click.wav"));
				buttonClickedSound.playSound();
			}
			
			@Override
			public void mouseEntered(MouseEvent me) { // Cuando la flecha del mouse esté dentro del botón.
				Sound buttonHoveredSound = new Sound(getSourceURL("/resources/wood_tap.wav"));
				buttonHoveredSound.playSound();
				btnInstructions.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/instructions_button_hovered.png")).getImage().getScaledInstance(BUTTON_SIZE[X], BUTTON_SIZE[Y], java.awt.Image.SCALE_SMOOTH)));
			}
			
			@Override
			public void mouseExited(MouseEvent me) { // Cuando la flecha del mouse salga del botón.
				btnInstructions.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/instructions_button.png")).getImage().getScaledInstance(BUTTON_SIZE[X], BUTTON_SIZE[Y], java.awt.Image.SCALE_SMOOTH)));
			}
		});
		// Cuando se presione el botón "Instructions", se hará visible el cuadro de diálogo
		// de las instrucciones.
		// Este cuadro de diálogo fue creado por el usuario, por lo que este cuadro
		// tiene sus respectivos atributos y métodos en una clase.
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					disableIntroWindowButtons();
					instructionsDialog.setLocationRelativeTo(getGameIntroWindow());
					instructionsDialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		panelButtons.add(btnInstructions);
		
		// JButton btnCredits = new JButton("CREDITS"); //
		btnCredits.setFont(new Font("Segoe Script", Font.BOLD, 37));
		btnCredits.setBackground(new Color(0, 0, 0, 0));
		btnCredits.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/credits_button.png")).getImage().getScaledInstance(BUTTON_SIZE[X], BUTTON_SIZE[Y], java.awt.Image.SCALE_SMOOTH)));
		// setPressedIcon establece el ícono que mostrará el botón cuando este es presionado con el mouse.
		btnCredits.setPressedIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/credits_button_pressed.png")).getImage().getScaledInstance(BUTTON_SIZE[X], BUTTON_SIZE[Y], java.awt.Image.SCALE_SMOOTH)));
		btnCredits.setBorderPainted(false);
		btnCredits.setSelected(false);
		btnCredits.setVisible(false);
		btnCredits.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) { // Cuando se presione el click izq.
				Sound buttonClickedSound = new Sound(getSourceURL("/resources/wood_click.wav"));
				buttonClickedSound.playSound();
			}
			
			@Override
			public void mouseEntered(MouseEvent me) { // Cuando la flecha del mouse esté dentro del botón.
				Sound buttonHoveredSound = new Sound(getSourceURL("/resources/wood_tap.wav"));
				buttonHoveredSound.playSound();
				btnCredits.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/credits_button_hovered.png")).getImage().getScaledInstance(BUTTON_SIZE[X], BUTTON_SIZE[Y], java.awt.Image.SCALE_SMOOTH)));
			}
			
			@Override
			public void mouseExited(MouseEvent me) { // Cuando la flecha del mouse salga del botón.
				btnCredits.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/credits_button.png")).getImage().getScaledInstance(BUTTON_SIZE[X], BUTTON_SIZE[Y], java.awt.Image.SCALE_SMOOTH)));
			}
		});
		// Cuando se presione el botón "Credits", se mostrará un JOptionPane.showMessageDialog(),
		// el cual mostrará el nombre y el equipo de los integrantes que realizaron
		// este videojuego.
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(null, ""
						+ "<html>"
							+ "<h1>APPLE FRENZY STAFF</h1>"
							+ "<h2>GRAFICACIÓN 5SA - EQUIPO 2</h2>"
							+ "<ul>"
								+ "<li>Estrella Ortiz, Estefany Anahí"
								+ "<li>Flores Campos, Ana Teresa"
								+ "<li>Herrera Medina, Roger Diego"
								+ "<li>Marín Segura, Oscar Mario"
								+ "<li>Ordoñez Pool, Alan Jair"
							+ "</ul>"
						+ "</html>"
					);
				}
		});
		
		// JButton btnCredits = new JButton("INSTRUCTIONS"); //
		panelButtons.add(btnCredits);
	}
	
	// ENG: get the complete URL from entityURLSound from the PC files. The sound file MUST BE .WAV!!!
	// ESP: obtiene la URL de la variable entityURLSound desde los archivos de la PC. EL ARCHIVO DE SONIDO DEBE SER .WAV!!!
	public URL getSourceURL(String sourcePath) {
		return getClass().getResource(sourcePath);
		// getResource() = C:\Users\Alan\Documents\EclipseProjects\PersonalProjects\AppleFrenzy\src
		// sourcePath = /resources/archivo
		// return C:\Users\Alan\Documents\EclipseProjects\PersonalProjects\AppleFrenzy\src\resources\archivo
	}
	
	/* Deshabilitar los botones de la ventana de la intro */
	public void disableIntroWindowButtons() {
		btnStartGame.setEnabled(false);
		btnInstructions.setEnabled(false);
		btnCredits.setEnabled(false);
	}
	
	/* Habilitar los botones de la ventana de la intro */
	public void enableIntroWindowButtons() {
		btnStartGame.setEnabled(true);
		btnInstructions.setEnabled(true);
		btnCredits.setEnabled(true);
	}
	
	/* Devolver la ventana de intro (esta clase) */
	public GameIntroWindow getGameIntroWindow() {
		return this;
	}
	
	/* Hilo para la ejecución del video y audio para la intro.
	 * Se irá explicando poco a poco su funcionamiento. */
	public void startVideoThread(JFXPanel videoPanel, File videoPath) {
		introTheme = new Sound(getSourceURL("/resources/syphus_mias_song.wav"));
		
		// El objeto MediaPlayer llamado videoPlayer guarda el video que se va a reproducir en la intro.
		MediaPlayer videoPlayer = new MediaPlayer(new Media(videoPath.toURI().toString()));
		
		/* Variable que indica el comienzo del bucle del video.
		 * El bucle del video empieza cuando el logotipo de AppleFrenzy empieza a rebotar en la pantalla.
		 */
		final int loopAnchor = 4150;
		final double introThemeStartTime = 1900; 
		
		Thread threadForVideo = new Thread(new Runnable() {
			boolean videoIsPlaying = false; // False: el video no ha empezado. True: el video ya empezó
			boolean introThemeIsPlaying = false; // False: la canción no ha empezado. True: la canción ya empezó
			
			@Override
			public void run() {
				/* Platform.runLater es un hilo especial para ejecutar videos. */
				playVideo(videoPanel, videoPlayer, 0, false); // Reproducir el video desde el principio 1 vez.
				
				while (!videoIsPlaying) { // Se repetirá siempre y cuando el video no haya empezado.
					try {
						Thread.sleep(1); // Dormir 1 milisegundo. Esto es importante, porque si no, el hilo no funciona correctamente.
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					while (videoPlayer.getStatus() == MediaPlayer.Status.PLAYING) { // Se repetirá cuando el video empiece.
						videoIsPlaying = true; // Desactiva la bandera que permite que el primer while continúe (línea 195).
						System.out.println(videoPlayer.getCurrentTime()); // Ver el tiempo que lleva corriendo el video.
						
						if (videoPlayer.getCurrentTime().greaterThan(new Duration(introThemeStartTime - 1)) &&
							videoPlayer.getCurrentTime().lessThan(new Duration(introThemeStartTime + 1)) &&
							!introThemeIsPlaying) {
							introThemeIsPlaying = true;
							introTheme.playAndLoopSound();
						}
						
						/* El siguiente "if" detecta cuando el video ha finalizado. La condición dentro del if 
						 * únicamente es para detectar si el video ya está a punto de llegar a su fotograma final. */
						if (videoPlayer.getCurrentTime().greaterThan(new Duration(videoPlayer.getTotalDuration().toMillis() - 1)) &&
							videoPlayer.getCurrentTime().lessThan(new Duration(videoPlayer.getTotalDuration().toMillis() + 1))
						) {
							playVideo(videoPanel, videoPlayer, loopAnchor, true); /* Como hasta este punto el video está a punto de terminar, el siguiente playVideo
							 * reproducirá el video infinitamente desde el momento del bucle (loopAnchor).
							 */
							btnStartGame.setVisible(true);
							btnInstructions.setVisible(true);
							btnCredits.setVisible(true);
							break; /* Romper el ciclo que repite sus acciones cuando el video se está reproduciendo (línea 202).
							Ya no lo necesitamos más, es por eso que se rompe. */
						}
					}
				}
				System.out.println("END OF THREAD");
			}
		});
		threadForVideo.start(); // Iniciar el hilo programado.
	}
	
	/* Permite reproducir un video, pasando el JFXPanel donde se va a reproducir,
	 * el objeto MediaPlayer que ya guarda el video (hay que saber crearlo),
	 * un entero startTime que indica en qué tiempo inicia el video (si se pone 0,
	 * empezará desde el principio, pero si se pone un valor mayor a 0, el video
	 * empezará más adelante),
	 * y una variable booleana que, si se pone true, el video se reproducirá infinitamente,
	 * de lo contrario, si se pone false, solo se reproducirá una vez desde el tiempo de inicio
	 * hasta que acabe el video.*/
	public void playVideo(JFXPanel videoPanel, MediaPlayer videoPlayer, int startTime, boolean infiniteLoop) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				videoPanel.setScene(new Scene(new Group(new MediaView(videoPlayer)))); // Crear la lista de fotogramas que tiene el video.
				videoPlayer.setStartTime(new Duration(startTime)); // Establecer el tiempo de inicio del video indicado en el parámetro startTime.
				videoPlayer.setCycleCount((infiniteLoop ? MediaPlayer.INDEFINITE : 1));
				/* (infiniteLoop ? MediaPlayer.INDEFINITE : 1) es un "if" abreviado que permite
				 * devolver un valor dependiendo la condición programada.
				 * 
				 * (infiniteLoop ? MediaPlayer.INDEFINITE : 1) se lee así:
				 * Si infiniteLoop es igual a true, entonces devolver el valor MediaPlayer.INDEFINITE,
				 * el cual permite que el video se reproduzca infinitamente.
				 * De no cumplirse la condición (en este caso, que infiniteLoop sea igual a false),
				 * entonces el video solo se reproducirá una vez.
				 */
				videoPlayer.play(); // Reproducir el video desde el tiempo de inicio establecido en la línea 229.
			}
		});
	}
}
