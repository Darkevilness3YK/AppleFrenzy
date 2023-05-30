package classes;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sound {
	// ENG: create the Clip object and initializes it so it could play the entity sound.
	// ESP: crea el objeto Clip y lo inicializa para que pueda reproducir el sonido de la entidad.
	Clip sound;
	//AudioClip sonido;
	private URL soundURL;
	
	/** @param entityURLSound
	 * - the URL of the sound that is going to be played. */
	public Sound(URL soundURL) {
		this.soundURL = soundURL; // la dirección URL del sonido que se va a reproducir.
		//sonido = java.applet.Applet.newAudioClip(getClass().getResource(URLSound));
	}
	
	public void playSound() {
		try {
			sound = AudioSystem.getClip();
			sound.open(AudioSystem.getAudioInputStream(soundURL));
			sound.start(); // ENG: play the sound. ESP: reproducir el sonido.
			//sonido.play();
		}
		catch (Exception e) {
		}
	}
	
	public void stopSound() {
		if (sound.isRunning()) {
			sound.stop();
			sound.close();
			//sonido.stop();
		}
	}
	
	public void playAndLoopSound() {
		try {
			sound = AudioSystem.getClip();
			sound.open(AudioSystem.getAudioInputStream(soundURL));
			sound.loop(Clip.LOOP_CONTINUOUSLY);
			//sonido.loop();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
