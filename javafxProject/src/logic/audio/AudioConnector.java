package logic.audio;

import logic.guitar.Note;

public interface AudioConnector {
    void playSingleNote(Note note);
    void playMultipleNotes(Note[] notes);
}
