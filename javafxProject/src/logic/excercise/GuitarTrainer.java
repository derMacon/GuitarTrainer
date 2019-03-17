package logic.excercise;

import logic.audio.AudioConverter;
import logic.dataPreservation.Logger;
import logic.organization.GUIConnector;

public class GuitarTrainer implements Trainer {

    private GUIConnector gui;
    private AudioConverter audioConv;

    public GuitarTrainer(GUIConnector gui, AudioConverter audioConverter) {
        this.gui = gui;
        this.audioConv = audioConverter;
    }

    @Override
    public void giveExcercise() {
        Logger.getInstance().printAndSafe("Trainer gives excercise");
    }

    @Override
    public void checkResult() {
        Logger.getInstance().printAndSafe("Trainer evaluates excercise");
    }
}
