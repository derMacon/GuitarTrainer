package logic.organization;

import logic.instrument.FretboardPos;

/**
 * Interface giving the possibility to organize the logical steps following the gui interaction from the user.
 */
public interface Organized {

    /**
     * (re-)plays the exercise given by the trainer
     */
    void playExcercise();

    /**
     * Checks the solution the user checked in
     */
    void checkInResult();

    /**
     * Plays all selected notes in one strum
     */
    void playDownStrum();

    /**
     * User pressed a note with the given position on the fretboard, the organizer then evaluates the mode and handles
     * it appropriately.
     *
     * @param fretboardPos position of the selected note on the fretboard
     */
    void pressNoteOnFretboard(FretboardPos fretboardPos);

    /**
     * User pressed a note with the given offset, the organizer then evaluates the mode and handles it appropriately.
     *
     * @param offset offset of the selected note to the lowest note E with the octave 0
     */
    void sheetNotePressed(int offset);

    /**
     * Refreshes the gui appropriately to the given Mode.
     * E.g. the replay button will be grayed out in freeplay mode.
     *
     * @param mode mode of the game
     */
    void interpretMode(Mode mode);

    /**
     * Resets all instruments
     */
    void reset();
}
