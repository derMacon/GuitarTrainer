package logic.instrument;

/**
 * Interface implementing the function of an instrument
 * @param <Note> Notetype on which the instrument operates
 */
public interface Instrument<Note> {

    /**
     * Presses a note on the instrument and saves it in the internal datastructure
     * @param note note to be pressed on the instrument
     * @return updated note that was actually saved in the internal data structure, this note may differ from the
     * input when the note was already selected in a previous turn, it is up to the implementation how this is handled.
     * e.g. the guitar muted the note and the sheet page iterates its prefix
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
