package logic.instrument;

/**
 * Interface implementing the function of an instrument
 * @param <Note> Notetype on which the instrument operates
 */
public interface Instrument<Note> {
    void pressNote(Note note);
    Note[] getPressedNotes();
    void playStrum();
    void reset();
}
