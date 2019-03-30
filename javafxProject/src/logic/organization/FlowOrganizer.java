package logic.organization;

import logic.audio.AudioConnector;
import logic.audio.AudioConverter;
import logic.dataPreservation.Logger;
import logic.excercise.GuitarTrainer;
import logic.excercise.Trainer;
import logic.instrument.FretboardPos;
import logic.instrument.Guitar;
import logic.instrument.Instrument;
import logic.instrument.SheetModel;
import logic.note.FretboardNote;
import logic.note.NoteFactory;
import logic.note.SheetNote;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class distributes a simple allocation to the appropriate interface / class. Is necessary to make it possible to
 * switch the gui implementation. (maybe this app should also work headless / on android)
 */
public class FlowOrganizer implements Organized {

    private final Instrument<FretboardNote> guitar;
    private final Instrument<SheetNote> sheets;
    private final Trainer trainer;
    private final AudioConnector audioConv;
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
        this.audioConv = audioConv;
        interpretMode(mode);
    }

    @Override
    public void interpretMode(Mode mode) {
        this.mode = mode;
        trainer.setMode(mode);
        reset();
        synchronize();
    }

    @Override
    public void sheetNotePressed(int offset) {
        if (Mode.SHEET_FREEPLAY == this.mode) {
            prepareNoteBoards(offset);
            SheetNote updatedSheetNote = this.sheets.pressNote(NoteFactory.createSheetNote(offset));
            Logger.getInstance().printAndSafe("Sheet note selected: " + updatedSheetNote);
            this.audioConv.playSingleNote(updatedSheetNote);
            synchronize();
        }
    }

    /**
     * Resets guitar if the mode of the program equals the sheet free play mode.
     * If the note is not already selected on the sheet page the sheet page will be reset.
     * -> the prefix will be iterated later on so it's necessary to NOT reset if the previous note is on the same
     * position as the current selected note
     * @param offset offset to lowest E note
     */
    private void prepareNoteBoards(int offset) {
        if (Mode.SHEET_FREEPLAY == this.mode) {
            this.guitar.reset();
            if (!checkIfToneAlreadySelected(offset)) {
                this.sheets.reset();
            }
        }
    }

    /**
     * Checks if there is a already a note selected on the sheet page with the specified offset
     * @param offset offset to the lowest note E.
     * @return true if there is a already a note selected on the sheet page with the specified offset
     */
    private boolean checkIfToneAlreadySelected(int offset) {
        SheetNote baseNote = NoteFactory.createSheetNote(offset);
        SheetNote[] pressedNotes = this.sheets.getPressedNotes();
        for (SheetNote currNote : pressedNotes) {
            if (currNote.getTone() == baseNote.getTone() && currNote.getOctave() == baseNote.getOctave()) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void pressNoteOnFretboard(FretboardPos fretboardPos) {
        if (Mode.GUITAR_FREEPLAY == this.mode) {
            FretboardNote updatedNote = this.guitar.pressNote(NoteFactory.createFretboardNote(fretboardPos));
            this.audioConv.playSingleNote(updatedNote);
            Logger.getInstance().printAndSafe("Fretboard note selected: " + updatedNote);
            synchronize();
        }
    }

    /**
     * Calls the appropriate methods to synchronize the instruments according to the specified mode
     */
    private void synchronize() {
        switch (mode) {
            case GUITAR_FREEPLAY:
                syncSheetWithGuitar();
                break;
            case SHEET_FREEPLAY:
                syncGuitarWithSheet();
                break;
            default:
                System.out.println("not implemented yet [interpret mode - floworg]");
        }
    }

    /**
     * Synchronizes the sheet notes with the pressed notes on the guitar
     */
    private void syncSheetWithGuitar() {
        this.sheets.reset();
        Set<FretboardNote> uniqueNotes = new HashSet<>(Arrays.asList(this.guitar.getPressedNotes()));
        for (FretboardNote currNote : uniqueNotes) {
            this.sheets.pressNote(NoteFactory.createSheetNote(currNote));
        }
    }

    /**
     * Synchronizes the guitar notes with the selected notes on the sheet page
     */
    private void syncGuitarWithSheet() {
        SheetNote[] selectedSheetNotes = this.sheets.getPressedNotes();
        if (1 == selectedSheetNotes.length) {
            this.guitar.reset();
            SheetNote singlePressedNote = selectedSheetNotes[0];
            for (FretboardNote currPossiblity : NoteFactory.createFretboardNote(singlePressedNote)) {
                this.guitar.pressNote(currPossiblity);
            }
        }
    }

    @Override
    public void reset() {
        this.sheets.reset();
        this.guitar.reset();

        // Depending on the mode a reseted guitar may or may not have the open
        // strings selected / be completely muted
        if (this.mode == Mode.GUITAR_FREEPLAY) {
            for (FretboardNote note : Guitar.OPEN_STRINGS) {
                this.guitar.pressNote(note);
            }
        }

        synchronize();
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
}
