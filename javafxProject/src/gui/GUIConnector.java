package gui;

import logic.guitar.Pos;

public interface GUIConnector {
    void pressNote(Pos pos);
    void getNoteName();
}
