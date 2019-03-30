package logic.audio;

import java.io.File;

/**
 * Runner class used to launch a playe instance in a new Thread and playing a specified note
 */
public class MusicRunner implements Runnable {

    /**
     * Player instance to be used to play a specified note / audio file in a new thread
     */
    private Player player;

    /**
     * Constructor setting up the audio file that will be played later on
     * @param file audio file that will be played later on
     */
    public MusicRunner(File file) {
        this.player = new Player(file);
    }

    @Override
    public void run() {
        player.play();
    }
}
