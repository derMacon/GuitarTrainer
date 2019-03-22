package logic.organization;

import logic.audio.AudioConverter;
import logic.excercise.GuitarTrainer;
import logic.excercise.Trainer;
import logic.guitar.FretboardPos;
import logic.guitar.Guitar;
import logic.guitar.NoteId;
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

    /**
     * Constructor setting all necessary attributes
     *
     * @param gui       gui attribute connecting the logic with the gui
     * @param audioConv audio converter making it possible to play given notes
     */
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
        NoteId newId = getPrimaryNote(offset);


        if (sheetNotes.containsKey(offset)) {
            sheetNotes.get(offset).iteratePrefix();
        } else {
            this.sheetNotes.put(offset, new SheetNote(offset));
        }
        this.trainer.userPressedSheetNote(this.sheetNotes.get(offset));
    }

    /**
     * Gets the primary note of a given note.
     * E.g. D_SHARP == E_FLAT the primary note is always the one with the SHARP prefix -> in this case D
     *
     * @param offset offset from the lower E note (octave == 0)
     * @return primary noteId with the given note offset
     */
    private NoteId getPrimaryNote(int offset) {
        return NoteId.values()[(offset + NoteId.E.ordinal()) % NoteId.values().length];
    }

    @Override
    public void reset() {
        this.guitar.reset();
    }
}
