package logic.excercise;

import logic.audio.AudioConnector;
import logic.audio.AudioConverter;
import logic.dataPreservation.Logger;
import logic.guitar.SheetNote;
import logic.organization.GUIConnector;
import logic.organization.Mode;

public class GuitarTrainer implements Trainer {

    private GUIConnector gui;
    private AudioConnector audioConv;
    private Mode mode;

    public GuitarTrainer(GUIConnector gui, AudioConverter audioConverter) {
        this.gui = gui;
        this.audioConv = audioConverter;
        setMode(Mode.values()[0]);
    }

    @Override
    public void userPressedSheetNote(SheetNote sheetNote) {
        this.gui.updateSheetNotes(sheetNote);
    }

    @Override
    public void setMode(Mode mode) {
        this.mode = mode;
        gui.setReplayButtonGrayedout(this.mode == Mode.FREEPLAY);
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
