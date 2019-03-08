import javax.sound.sampled.Mixer;
import java.io.File;

public class Main {

    public static Mixer mixer;

    public static void main(String[] args) {
        try {
            MusicRunner fstRunner = new MusicRunner(new File("res\\8381__speedy__clean-a-harm.wav"));
            MusicRunner sndRunner = new MusicRunner(new File("res\\8387__speedy__clean-d-harm.wav"));

            Thread fstThread = new Thread(fstRunner);
            Thread sndThread = new Thread(sndRunner);
            fstThread.start();
            Thread.sleep(500);
            sndThread.start();

            fstThread.join();
            sndThread.join();
            System.out.println("Finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
