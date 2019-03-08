package logic.audio;

import java.io.File;

public class MusicRunner implements Runnable {

    Player player;

    public MusicRunner(File file) {
        this.player = new Player(file);
    }

    @Override
    public void run() {
        player.play();
    }
}
