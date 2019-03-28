package logic.instrument;

/**
 * Interface implementing the function of an instrument
 * @param <Note> Notetype on which the instrument operates
 */
public interface Instrument<Note> {

    /**
     * Presses a note on the instrument and saves it in the internal datastructure
     * @param note note to be pressed on the instrument
     */
    void pressNote(Note note);

    /**
     * Getter for the pressed notes
     * @return array of pressed notes
     */
    Note[] getPressedNotes();

    /**
     * Plays a single specified note without saving it in the internal data structure
     * @param note note to be played
     */
    void playSingleNote(Note note);

    /**
     * Plays all pressed notes in one strum
     */
    void playStrum();

    /**
     * Resets / clears all pressed notes
     */
    void reset();
}
