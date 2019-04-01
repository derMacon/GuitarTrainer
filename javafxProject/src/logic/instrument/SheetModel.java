package logic.instrument;

import logic.audio.AudioConnector;
import logic.dataPreservation.Logger;
import logic.note.NoteFactory;
import logic.note.SheetNote;
import logic.organization.GUIConnector;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implements the sheet pages as an instrument where the user can select and play notes on.
 */
public class SheetModel implements Instrument<SheetNote> {

    private static final int NOTE_COUNT = 23;
    private final GUIConnector gui;
    private final AudioConnector audioConv;
    private SheetNote[] sheetNotes;

    /**
     * Constructor
     *
     * @param gui       GuiConnector
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
        this.sheetNotes = new SheetNote[NOTE_COUNT];
        for (int i = 0; i < NOTE_COUNT; i++) {
            currNote = NoteFactory.createSheetNote(i);
            currNote = currNote.setPlayed(false);
            this.sheetNotes[i] = currNote;
        }
    }

    @Override
    public SheetNote pressNote(SheetNote note) {
        int noteOrd = note.getOffsetToLowestE();
        try {
            for (int i = 0; i < note.getPrefix().ordinal() && note.isPlayed(); i++) {
                this.sheetNotes[noteOrd] = this.sheetNotes[noteOrd].iteratePrefix();
            }
            this.gui.updateSheetNotes(this.sheetNotes[noteOrd]);
            return this.sheetNotes[noteOrd];
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.getInstance().printAndSafe("note [" + note + "] cannot be displayed on the sheet page");
        }
        return null;
    }

    @Override
    public SheetNote[] getPressedNotes() {
        List<SheetNote> outputLst = new ArrayList<>();
        for (SheetNote currNote : this.sheetNotes) {
            if (currNote.isPlayed()) {
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
