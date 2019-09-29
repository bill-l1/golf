import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundHandler {
	
	//AudioInputStream audioIn;
	//Clip clip;
	public SoundHandler() {
		/*
		try {
			//clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public void play(String sound, boolean loop) {
		AudioInputStream audioIn;
		try {
			/*
			InputStream fileIn = getClass().getResourceAsStream("sounds/"+sound);
			BufferedInputStream bufferedIn = new BufferedInputStream(fileIn);
			audioIn = AudioSystem.getAudioInputStream(bufferedIn);
			clip.open(audioIn);
			clip.start();
			*/
			audioIn = AudioSystem.getAudioInputStream(new File("sounds/"+sound).getAbsoluteFile()); 
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); 
			clip.start();
			if(loop)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
