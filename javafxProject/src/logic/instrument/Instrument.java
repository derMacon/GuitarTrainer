package logic.instrument;

public interface Instrument<Note> {
    void pressNote(Note note);
    Note[] getPressedNotes();
    void playStrum();
    void reset();
}
