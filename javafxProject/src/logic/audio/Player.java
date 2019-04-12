package logic.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Class to play a given audio file
 * <p>
 * https://www.codejava.net/coding/how-to-play-back-audio-in-java-with-examples
 */
public class Player implements LineListener {

    private static final int DELAY_TIME = 1000;
    private File audioFile;
    private boolean playCompleted = false;

    /**
     * Constructor
     *
     * @param audioFile audio file to play
     */
    public Player(File audioFile) {
        this.audioFile = audioFile;
    }

    /**
     * Initializes necessary components for playing a specific audio file
     */
    public void play() {
        try {
            URL soundURL = this.getClass().getResource("/test.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
            audioClip.start();

            while (!playCompleted) {
                // wait for the playback completes
                try {
                    Thread.sleep(DELAY_TIME);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            audioClip.close();

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
    }

    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
//            System.out.println("Playback started.");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
//            System.out.println("Playback completed.");
        }

    }

}
