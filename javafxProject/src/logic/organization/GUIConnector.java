package logic.organization;

import logic.guitar.Note;

public interface GUIConnector {
    void updateGui(Note currNote);
    void initGui();
}
