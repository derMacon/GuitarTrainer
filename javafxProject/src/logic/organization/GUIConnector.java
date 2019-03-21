package logic.organization;

import logic.guitar.FretboardNote;
import logic.guitar.SheetNote;

public interface GUIConnector {
    void updateGuitar(FretboardNote currFretboardNote);
    void updateSheetNotes(SheetNote currFretboardNote);
    void setReplayButtonGrayedout(boolean isGrayedout);
    void initGui();
}
