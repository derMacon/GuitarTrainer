package logic.organization;

import gui.NotePrefix;
import logic.guitar.FretboardPos;

public interface Organized {
    void playExcercise();
    void checkInResult();
    void playDownStrum();
    void pressNoteOnFretboard(FretboardPos fretboardPos);
    void sheetNotePressed(int offset);

    /**
     * Refreshes the gui appropriately to the given Mode.
     * E.g. the replay button will be grayed out in freeplay mode.
     * @param mode
     */
    void interpretMode(Mode mode);
    void reset();
}
