package logic.instrument;

import logic.note.FretboardNote;

/**
 * Interface implementing the function of an instrument
 * @param <Note> Notetype on which the instrument operates
 */
public interface Instrument<Note, Pos> {

    /**
     * Presses a note on the instrument and saves it in the internal datastructure
     * @param note note to be pressed on the instrument
     */
    Note pressNote(Note note);

    /**
     * Getter for the pressed notes
     * @return array of pressed notes
     */
    Note[] getPressedNotes();

    /**
     * Plays all pressed notes in one strum
     */
    void playStrum();

    /**
     * Resets / clears all pressed notes
     */
    void reset();
}
