package logic.organization;

import logic.audio.AudioConnector;
import logic.audio.AudioConverter;
import logic.dataPreservation.Logger;
import logic.excercise.GuitarTrainer;
import logic.excercise.Trainer;
import logic.note.FretboardNote;
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

    private final Instrument<FretboardNote, FretboardPos> guitar;
    private final Instrument<SheetNote, Integer> sheets;
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
        if(Mode.SHEET_FREEPLAY == this.mode) {
            prepareNoteBoards(offset);
            SheetNote updatedSheetNote = this.sheets.pressNote(NoteFactory.createSheetNote(offset));
            Logger.getInstance().printAndSafe("Sheet note selected: " + updatedSheetNote);
            this.audioConv.playSingleNote(updatedSheetNote);
            synchronize();
        }
    }

    private void prepareNoteBoards(int offset) {
        if (Mode.SHEET_FREEPLAY == this.mode) {
            this.guitar.reset();
            if (!checkIfToneAlreadySelected(offset)) {
                this.sheets.reset();
            }
        }
    }

    private boolean checkIfToneAlreadySelected(int offset) {
        SheetNote baseNote = NoteFactory.createSheetNote(offset);
        SheetNote[] pressedNotes = this.sheets.getPressedNotes();
        for(SheetNote currNote : pressedNotes) {
            if(currNote.getTone() == baseNote.getTone() && currNote.getOctave() == baseNote.getOctave()) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void pressNoteOnFretboard(FretboardPos fretboardPos) {
        if(Mode.GUITAR_FREEPLAY == this.mode) {
            FretboardNote updatedNote = this.guitar.pressNote(NoteFactory.createFretboardNote(fretboardPos));
            this.audioConv.playSingleNote(updatedNote);
            Logger.getInstance().printAndSafe("Fretboard note selected: " + updatedNote);
            synchronize();
        }
    }

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
        for (FretboardNote currNote : this.guitar.getPressedNotes()) {
            this.sheets.pressNote(NoteFactory.createSheetNote(currNote));
        }
    }

    /**
     * Synchronizes the guitar notes with the selected notes on the sheet page
     */
    private void syncGuitarWithSheet() {
        SheetNote[] selectedSheetNotes = this.sheets.getPressedNotes();
        if(1 == selectedSheetNotes.length) {
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
        if(this.mode == Mode.GUITAR_FREEPLAY) {
            for(FretboardNote note : Guitar.OPEN_STRINGS) {
                this.guitar.pressNote(note);
            }
        }
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
