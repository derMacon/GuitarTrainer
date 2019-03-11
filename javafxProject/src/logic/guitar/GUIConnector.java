package logic.guitar;

import logic.guitar.Note;

public interface GUIConnector {
    void updateGui(Note currNote, Note prevNote);
    void initGui(Note[] openStrings);
    String getNoteName();
}
