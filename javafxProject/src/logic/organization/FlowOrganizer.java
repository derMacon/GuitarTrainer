package logic.organization;

import logic.audio.AudioConverter;
import logic.excercise.GuitarTrainer;
import logic.excercise.Trainer;
import logic.guitar.FretboardPos;
import logic.guitar.Guitar;
import logic.guitar.SheetNote;

import java.util.HashMap;
import java.util.Map;

/**
 * Class distributes a simple allocation to the appropriate interface / class. Is necessary to make it possible to
 * switch the gui implementation. (maybe this app should also work headless / on android)
 */
public class FlowOrganizer implements Organized {

    private Guitar guitar;
    private Trainer trainer;
    private Map<Integer, SheetNote> sheetNotes;

    public FlowOrganizer(GUIConnector gui, AudioConverter audioConv) {
        this.guitar = new Guitar(gui, audioConv);
        this.trainer = new GuitarTrainer(gui, audioConv);
        this.sheetNotes = new HashMap<>();
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
    public void sheetNotePressed(int offset) {
        if (sheetNotes.containsKey(offset)) {
            sheetNotes.get(offset).iteratePrefix();
        } else {
            this.sheetNotes.put(offset, new SheetNote(offset));
        }
        this.trainer.userPressedSheetNote(this.sheetNotes.get(offset));
    }

    @Override
    public void reset() {
        this.guitar.reset();
    }
}
