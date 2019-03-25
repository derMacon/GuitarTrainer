package logic.instrument;

public interface Instrument<Note> {
    void pressNote(Note note);
    void playStrum();
    void reset();
}
