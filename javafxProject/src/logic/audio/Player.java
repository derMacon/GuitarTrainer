package logic.audio;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class to play a given audio file
 * <p>
 * https://www.codejava.net/coding/how-to-play-back-audio-in-java-with-examples
 */
public class Player implements LineListener {

    private static final int DELAY_TIME = 100000;
    private File audioFile;
    private boolean playCompleted = false;
    private String audioPath;

    /**
     * Constructor
     *
     * @param audioFile audio file to play
     */
    public Player(File audioFile) {
        this.audioFile = audioFile;
    }

//    public Player(String path) {
//        this.audioPath = path;
//    }

    /**
     * Initializes necessary components for playing a specific audio file
     */
    public void play() {
        playSound("/test.wav");
//        playSound("/audioFiles/nylon/test.wav");
//        try {
//            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
//            AudioFormat format = audioStream.getFormat();
//            DataLine.Info info = new DataLine.Info(Clip.class, format);
//            Clip audioClip = (Clip) AudioSystem.getLine(info);
//            audioClip.addLineListener(this);
//            audioClip.open(audioStream);
//            audioClip.start();

//            String path = "/audioFiles/" + "nylon/" + this.audioFile.getName();
//            String path = "/audioFiles/nylon/" + this.audioFile.getName();
//
//            InputStream is = getClass().getResourceAsStream(path);
//            AudioInputStream ais = AudioSystem.getAudioInputStream(is);
//            Clip audioClip = AudioSystem.getClip();
////            audioClip.addLineListener(this);
//            audioClip.open(ais);
//            audioClip.start();
//            System.out.println("audio started");


//            System.out.println(path);
//            URL url = this.getClass().getResource(path);
//            String urls = url.toString();
//            urls = urls.replaceFirst("file:/", "file:///");
//            AudioClip ac = Applet.newAudioClip(new URL(urls));
//            ac.play();
//            Thread.sleep(DELAY_TIME);
//            ac.stop();

//            while (!playCompleted) {
//                // wait for the playback completes
//                try {
//                    Thread.sleep(DELAY_TIME);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }

//        } catch (UnsupportedAudioFileException ex) {
//            System.out.println("The specified audio file is not supported.");
//            ex.printStackTrace();
//        } catch (LineUnavailableException ex) {
//            System.out.println("Audio line for playing back is unavailable.");
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            System.out.println("Error playing the audio file.");
//            ex.printStackTrace();
//        } catch (UnsupportedAudioFileException e) {
//            e.printStackTrace();
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//        }
    }

    public void playSound(String path) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
            clip.open(ais);
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.err.println("ERROR: Playing sound has failed");
            e.printStackTrace();
        }
        System.out.println("Path: " + path);
        InputStream in;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(
                    getClass().getClassLoader()
                            .getResource(path).getPath())));
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
