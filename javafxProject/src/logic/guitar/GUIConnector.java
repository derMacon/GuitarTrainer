package logic.guitar;

public interface GUIConnector {
    void updateGui(Note currNote);
    void initGui();
    String getNoteName();
}
