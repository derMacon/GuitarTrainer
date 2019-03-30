package logic.instrument;

import logic.audio.AudioConnector;
import logic.note.FretboardNote;
import logic.note.NoteFactory;
import logic.note.SheetNote;
import logic.organization.GUIConnector;

import java.util.ArrayList;
import java.util.List;

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
            currNote = currNote.setPlayed(false);
            this.sheetNotes[i] = currNote;
        }
    }

    @Override
    public SheetNote pressNote(SheetNote note) {
        int noteOrd = note.getOffsetToLowestE();
        for (int i = 0; i < note.getPrefix().ordinal(); i++) {
            this.sheetNotes[noteOrd] = this.sheetNotes[noteOrd].iteratePrefix();
        }

        this.gui.updateSheetNotes(this.sheetNotes[noteOrd]);
        return this.sheetNotes[noteOrd];
    }

    @Override
    public SheetNote[] getPressedNotes() {
         List<SheetNote> outputLst = new ArrayList<>();
         for(SheetNote currNote : this.sheetNotes) {
             if(currNote.isPlayed()) {
                 outputLst.add(currNote);
             }
         }
         SheetNote[] outputArr = new SheetNote[0];
         return outputLst.size() == 0 ? outputArr : outputLst.toArray(outputArr);
    }

    @Override
    public void playStrum() {
        System.out.println("todo strum");
    }

    @Override
    public void reset() {
        for (int i = 0; i < this.sheetNotes.length; i++) {
            if (this.sheetNotes[i].isPlayed()) {
                this.sheetNotes[i] = NoteFactory.createSheetNote(i).setPlayed(false);
                this.gui.updateSheetNotes(this.sheetNotes[i]);
            }
        }
    }

}
