package logic.sheets;

import gui.NotePrefix;
import logic.audio.AudioConnector;
import logic.guitar.NoteCircle;
import logic.guitar.SheetNote;
import logic.guitar.Tone;
import logic.organization.GUIConnector;

import java.util.LinkedList;
import java.util.List;

public class SheetModel {

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
            currNote = new SheetNote(i);
            currNote.setPlayed(false);
            this.sheetNotes[i] = currNote;
        }
    }

    public SheetNote[] getSheetNotes() {
        return this.sheetNotes;
    }


    public void pressNote(int offset) {
        SheetNote prevNote = this.sheetNotes[offset];
        SheetNote currNote = prevNote;
        if(!prevNote.isPlayed()) {
            prevNote.setPlayed(true);
        } else {
            currNote = prevNote.nextSemiTone();
            if (prevNote.getTone() != currNote.getTone()) {
                currNote = currNote.clearPrefix();
                currNote.setPlayed(false);
                this.sheetNotes[offset] = currNote;
            }
        }
        this.gui.updateSheetNotes(currNote);
    }

    protected List<NoteCircle> genTraversablePrefix(int offset) {
        Tone baseTone = Tone.translateToTone(offset);
        LinkedList<NoteCircle> iteratingNotes = new LinkedList<>();
        for(NoteCircle note : NoteCircle.values()) {
            if(note.getTones().contains(baseTone)) {
                if(note.getNotes().get(baseTone) == NotePrefix.SHARP) {
                    iteratingNotes.add(1, note);
                } else {
                    iteratingNotes.add(0, note);
                }
            }
        }
        iteratingNotes.add(null);
        return iteratingNotes;
    }




//    private void iterate(int offset) {
//        Tone tone = Tone.translateToTone(offset);
//        SheetNote note = this.sheetNotes[offset];
//        note = note.nextSemiTone();
//        if(!note.getId().getTones().contains(tone)) {
//            note = new SheetNote(offset);
//            note.setPlayed(false);
//        }
//        this.sheetNotes[offset] = note;
//    }


//    public void pressNote(int offset) {
//        Tone baseTone = Tone.translateToTone(offset);
//        SheetNote updatedSheetNote;
//        if (containsTone(baseTone)) {
//            updatedSheetNote = iteratePrefix(baseTone);
//        } else {
//            updatedSheetNote = new SheetNote(offset);
//        }
//        this.sheetNotes.add(updatedSheetNote);
//        this.gui.updateSheetNotes(updatedSheetNote);
//    }
//
//    private boolean containsTone(Tone baseTone) {
//        for(SheetNote note : this.sheetNotes) {
//            if(note.getId().getTones().contains(baseTone)) {
//                return true;
//            }
//        }
//        return false;
//    }


    /**
     * Iterates the prefix of the current note.
     * e.g. the prefix is:
     * NEUTRAL -> SHARP
     * SHARP -> FLAT
     * FLAT -> NEUTRAL
     * <p>
     * But only if it is possible for the note (e.g. for F, C, etc. it is not possible since there is no semitone in
     * between them -> not possible to flatten)
     * <p>
     * Used to make it possible for the user to iterate through the variations with only on button.
     *
     * @return new SheetNote instance
     */
//    public SheetNote iteratePrefix(Tone baseTone) {
//        SheetNote note = this.sheetNotes.get(baseTone);
//        SheetNote output = incPrefix(baseTone);
//        if (!output.getId().getTones().contains(baseTone)) {
//            output = note.getLowestNoteOfTone();
//        }
//        this.sheetNotes.put(baseTone, output);
//        return output;
//    }
//
//    private SheetNote incPrefix(Tone baseTone) {
//        SheetNote note = this.sheetNotes.get(baseTone);
//        if (!note.isPlayed()) {
//            note.setPlayed(true);
//            return note;
//        }
//        note = new SheetNote(note.getId().nextSemiTone(), note.getOctave(), true);
//        if (!note.getId().getTones().contains(baseTone)) {
//            // mute note
//            note = new SheetNote(note.getId(), note.getOctave(), false);
//        }
////        this.sheetNotes.put(baseTone, note);
//        return note;
//    }
//
//    public void reset() {
//        this.sheetNotes.clear();
//    }

}
