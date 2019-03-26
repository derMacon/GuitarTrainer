package logic.instrument;

import logic.audio.AudioConnector;
import logic.note.SheetNote;
import logic.note.NoteFactory;
import logic.organization.GUIConnector;

public class SheetModel implements Instrument<SheetNote> {

    private SheetNote[] sheetNotes;
    private final GUIConnector gui;
    private final AudioConnector audioConv;

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

    public SheetNote[] getSheetNotes() {
        return this.sheetNotes;
    }

    @Override
    public void pressNote(SheetNote note) {
        int noteOrd = note.getOffsetToLowestE();
        this.sheetNotes[noteOrd] = this.sheetNotes[noteOrd].iteratePrefix();
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
            if(this.sheetNotes[i].isPlayed()) {
               this.sheetNotes[i] = NoteFactory.createSheetNote(i);
               this.sheetNotes[i].setPlayed(false);
               this.gui.updateSheetNotes(this.sheetNotes[i]);
            }
        }
    }


    public void pressNote(int offset) {
        this.sheetNotes[offset] = this.sheetNotes[offset].iteratePrefix();
        this.gui.updateSheetNotes(this.sheetNotes[offset]);
    }

}
