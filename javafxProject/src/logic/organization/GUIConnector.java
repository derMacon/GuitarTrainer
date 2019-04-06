package logic.organization;

import logic.note.FretboardNote;
import logic.note.SheetNote;

/**
 * Interface connects logic with the gui
 */
public interface GUIConnector {

    /**
     * Updates the instrument on the gui with a given note
     *
     * @param currFretboardNote note to set on the instrument on the gui
     */
    void updateGuitar(FretboardNote currFretboardNote);

    /**
     * Updates sheet notes with a given note on the gui
     *
     * @param currFretboardNote note to update the sheet note component with
     */
    void updateSheetNotes(SheetNote currFretboardNote);

    /**
     * Makes replay button accessable / Dissables it
     *
     * @param isGrayedout flag to determine if it is dissabled or not
     */
    void setReplayButtonGrayedout(boolean isGrayedout);

    /**
     * Initializes gui
     */
    void initGui();

    /**
     * Shows a given message in a pop up window on the gui
     * @param title title of the message
     * @param message message to be displayed
     */
    void showMessage(String title, String message);
}
