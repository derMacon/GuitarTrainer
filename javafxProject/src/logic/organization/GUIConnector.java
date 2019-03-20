package logic.organization;

import logic.guitar.Note;

public interface GUIConnector {
    void updateGuitar(Note currNote);
    void updateSheetNotes(Note currNote);
    void setReplayButtonGrayedout(boolean isGrayedout);
    void initGui();
}
