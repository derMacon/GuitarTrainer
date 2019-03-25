package logic.organization;

import logic.audio.AudioConverter;
import logic.excercise.GuitarTrainer;
import logic.excercise.Trainer;
import logic.guitar.FretboardPos;
import logic.guitar.Guitar;
import logic.sheets.SheetModel;

/**
 * Class distributes a simple allocation to the appropriate interface / class. Is necessary to make it possible to
 * switch the gui implementation. (maybe this app should also work headless / on android)
 */
public class FlowOrganizer implements Organized {

    private Guitar guitar;
    private Trainer trainer;
    private SheetModel sheets;
    private Mode mode;


    /**
     * Constructor setting all necessary attributes
     *
     * @param gui       gui attribute connecting the logic with the gui
     * @param audioConv audio converter making it possible to play given notes
     */
    public FlowOrganizer(GUIConnector gui, AudioConverter audioConv, Mode mode) {
        this.guitar = new Guitar(gui, audioConv);
        this.trainer = new GuitarTrainer(gui, audioConv);
        this.sheets = new SheetModel(gui, audioConv);
        this.mode = mode;
    }

    @Override
    public void interpretMode(Mode mode) {
        this.mode = mode;
        // todo maybe reavaluate if trainer needs mode...
        trainer.setMode(mode);
    }

    @Override
    public void playExcercise() {
        this.trainer.giveExcercise();
    }

    @Override
    public void playDownStrum() {
        this.guitar.playStrum();
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
        this.sheets.pressNote(offset);
    }


    @Override
    public void reset() {
        this.guitar.reset();
    }
}
