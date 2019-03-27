package logic.organization;

import logic.audio.AudioConverter;
import logic.excercise.GuitarTrainer;
import logic.excercise.Trainer;
import logic.instrument.FretboardNote;
import logic.instrument.FretboardPos;
import logic.instrument.Guitar;
import logic.instrument.Instrument;
import logic.instrument.SheetModel;
import logic.note.NoteFactory;
import logic.note.SheetNote;

/**
 * Class distributes a simple allocation to the appropriate interface / class. Is necessary to make it possible to
 * switch the gui implementation. (maybe this app should also work headless / on android)
 */
public class FlowOrganizer implements Organized {

    private Instrument<FretboardNote> guitar;
    private Instrument<SheetNote> sheets;
    private Trainer trainer;
    private Mode mode;


    /**
     * Constructor setting all necessary attributes
     *
     * @param gui       gui attribute connecting the logic with the gui
     * @param audioConv audio converter making it possible to play given notes
     * @param mode      current state mode of the program / excercise
     */
    public FlowOrganizer(GUIConnector gui, AudioConverter audioConv, Mode mode) {
        this.guitar = new Guitar(gui, audioConv);
        this.trainer = new GuitarTrainer(gui, audioConv);
        this.sheets = new SheetModel(gui, audioConv);
        this.mode = mode;

        if (this.mode == Mode.GUITAR_FREEPLAY) {
            syncSheetWithGuitar();
        }
    }

    @Override
    public void interpretMode(Mode mode) {
        this.mode = mode;
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
        this.guitar.pressNote(NoteFactory.createFretboardNote(fretboardPos));
        if (this.mode == Mode.GUITAR_FREEPLAY) {
            syncSheetWithGuitar();
        }
    }

    /**
     * Synchronizes the sheet notes with the pressed notes on the guitar
     */
    private void syncSheetWithGuitar() {
        this.sheets.reset();
        for (FretboardNote currNote : this.guitar.getPressedNotes()) {
            this.sheets.pressNote(NoteFactory.createSheetNote(currNote));
        }
    }

    @Override
    public void sheetNotePressed(int offset) {
        this.sheets.pressNote(NoteFactory.createSheetNote(offset));
        if (this.mode == Mode.SHEET_FREEPLAY) {
            syncGuitarWithSheet();
        }
    }

    /**
     * Synchronizes the guitar notes with the selected notes on the sheet page
     */
    private void syncGuitarWithSheet() {
        assert 0 == this.sheets.getPressedNotes().length;
        this.guitar.reset();
        SheetNote singlePressedNote = this.sheets.getPressedNotes()[0];
        for (FretboardNote currPossiblity : NoteFactory.createFretboardNote(singlePressedNote)) {
            this.guitar.pressNote(currPossiblity);
        }
    }

    @Override
    public void reset() {
        this.guitar.reset();
        this.sheets.reset();
    }
}
