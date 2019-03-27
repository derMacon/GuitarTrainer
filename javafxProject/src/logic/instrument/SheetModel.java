package logic.instrument;

import logic.audio.AudioConnector;
import logic.note.NoteFactory;
import logic.note.SheetNote;
import logic.organization.GUIConnector;

/**
 * Class implements the sheet pages as an instrument where the user can select and play notes on.
 */
public class SheetModel implements Instrument<SheetNote> {

    private SheetNote[] sheetNotes;
    private final GUIConnector gui;
    private final AudioConnector audioConv;

    /**
     * Constructor
     * @param gui GuiConnector
     * @param audioConv audio converter to play audio files
     */
    public SheetModel(GUIConnector gui, AudioConnector audioConv) {
        this.gui = gui;
        this.audioConv = audioConv;
        initSheetNoteArray();
    }

    /**
     * Initializes sheet note array with muted notes
     */
    private void initSheetNoteArray() {
        SheetNote currNote;
        int noteCnt = 23;
        this.sheetNotes = new SheetNote[noteCnt];
        for (int i = 0; i < noteCnt; i++) {
            currNote = NoteFactory.createSheetNote(i);
            currNote.setPlayed(false);
            this.sheetNotes[i] = currNote;
        }
    }

    @Override
    public void pressNote(SheetNote note) {
        int noteOrd = note.getOffsetToLowestE();
        for (int i = 0; i < note.getPrefix().ordinal(); i++) {
            this.sheetNotes[noteOrd] = this.sheetNotes[noteOrd].iteratePrefix();
        }

        this.gui.updateSheetNotes(this.sheetNotes[noteOrd]);
    }

    @Override
    public SheetNote[] getPressedNotes() {
        return this.sheetNotes;
    }

    @Override
    public void playStrum() {
        System.out.println("todo strum");
    }

    @Override
    public void reset() {
        for (int i = 0; i < this.sheetNotes.length; i++) {
            if (this.sheetNotes[i].isPlayed()) {
                this.sheetNotes[i] = NoteFactory.createSheetNote(i);
                this.sheetNotes[i].setPlayed(false);
                this.gui.updateSheetNotes(this.sheetNotes[i]);
            }
        }
    }

}
