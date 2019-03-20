package logic.organization;

import gui.NotePrefix;
import logic.audio.AudioConverter;
import logic.excercise.GuitarTrainer;
import logic.excercise.Trainer;
import logic.guitar.Guitar;
import logic.guitar.FretboardPos;

/**
 * Class distributes a simple allocation to the appropriate interface / class. Is necessary to make it possible to
 * switch the gui implementation. (maybe this app should also work headless / on android)
 */
public class FlowOrganizer implements Organized {

    private Guitar guitar;
    private Trainer trainer;

    public FlowOrganizer(GUIConnector gui, AudioConverter audioConv) {
        this.guitar = new Guitar(gui, audioConv);
        this.trainer = new GuitarTrainer(gui, audioConv);
    }

    @Override
    public void interpretMode(Mode mode) {
        trainer.setMode(mode);
    }

    @Override
    public void playExcercise() {
        this.trainer.giveExcercise();
    }

    @Override
    public void playDownStrum() {
        this.guitar.playDownStrum();
    }

    @Override
    public void checkInResult() {
        this.trainer.checkResult();
    }

    @Override
    public void pressNoteOnFretboard(FretboardPos fretboardPos) {
        this.guitar.pressNote(fretboardPos);
    }

    @Override
    public void sheetPrefixPressed(int offset, NotePrefix prefix) {
        // todo
    }

    @Override
    public void sheetNotePressed(int offset, boolean isPlayed) {
        // todo
    }

    @Override
    public void reset() {
        this.guitar.reset();
    }
}
