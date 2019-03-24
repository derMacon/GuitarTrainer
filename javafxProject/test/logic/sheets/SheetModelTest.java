package logic.sheets;

import logic.guitar.FakeAudioConverter;
import logic.guitar.FakeGui;
import logic.guitar.Note;
import logic.guitar.NoteCircle;
import logic.guitar.SheetNote;
import logic.guitar.Tone;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SheetModelTest {

    private SheetNote[] initSheetNoteArray() {
        SheetNote currNote;
        int noteCnt = 23;
        SheetNote[] output = new SheetNote[noteCnt];
        for (int i = 0; i < noteCnt; i++) {
            currNote = new SheetNote(i);
            currNote.setPlayed(false);
            output[i] = currNote;
        }
        return output;
    }

    @Test
    public void testGenTraversablePrefix() {
        SheetModel model = new SheetModel(new FakeGui(), new FakeAudioConverter());
        List<NoteCircle> expOutput = Arrays.asList(new NoteCircle[] {NoteCircle.F, NoteCircle.F_SHARP, null});
        Assert.assertEquals(expOutput, model.genTraversablePrefix(1));

        expOutput = Arrays.asList(new NoteCircle[] {NoteCircle.E, NoteCircle.D_SHARP, null});
        Assert.assertEquals(expOutput, model.genTraversablePrefix(0));

        expOutput = Arrays.asList(new NoteCircle[] {NoteCircle.D, NoteCircle.D_SHARP, NoteCircle.C_SHARP, null});
        Assert.assertEquals(expOutput, model.genTraversablePrefix(6));
    }

//    // --- press note ---
//    @Test
//    public void testPressNote_NotTraversable() {
//        SheetModel sheets = new SheetModel(new FakeGui(), new FakeAudioConverter());
//        // first click
//        sheets.pressNote(0);
//        SheetNote[] actOutput = sheets.getSheetNotes();
//        SheetNote[] expOutput = initSheetNoteArray();
//        SheetNote pressedNote = new SheetNote(NoteCircle.E, 0, true);
//        expOutput[pressedNote.getOffsetToLowestE()] = pressedNote;
//        Assert.assertArrayEquals(expOutput, actOutput);
//
//        // todo second click not possible -> test / implement out of bound notes
//
////        // second click
////        sheets.pressNote(0);
////        actOutput = sheets.getSheetNotes();
////        expOutput = initSheetNoteArray();
////        pressedNote = new SheetNote(NoteCircle.D_SHARP, 0, true);
////        expOutput[pressedNote.getOffsetToLowestE()] = pressedNote;
////        Assert.assertArrayEquals(expOutput, actOutput);
//    }
//
//    @Test
//    public void testPressNote_FullyTraversable() {
//        SheetModel sheets = new SheetModel(new FakeGui(), new FakeAudioConverter());
//        // first click
//        sheets.pressNote(2);
//        SheetNote[] actOutput = sheets.getSheetNotes();
//        SheetNote[] expOutput = initSheetNoteArray();
//        SheetNote pressedNote = new SheetNote(NoteCircle.G, 0, true);
//        expOutput[pressedNote.getOffsetToLowestE()] = pressedNote;
//        Assert.assertArrayEquals(expOutput, actOutput);
//
//        // second click
//        sheets.pressNote(2);
//        actOutput = sheets.getSheetNotes();
//        expOutput = initSheetNoteArray();
//        pressedNote = new SheetNote(NoteCircle.G_SHARP, 0, true);
//        expOutput[pressedNote.getOffsetToLowestE()] = pressedNote;
//        Assert.assertArrayEquals(expOutput, actOutput);
//
//        // third click
//        sheets.pressNote(2);
//        actOutput = sheets.getSheetNotes();
//        expOutput = initSheetNoteArray();
//        pressedNote = new SheetNote(NoteCircle.F_SHARP, 0, true);
//        expOutput[pressedNote.getOffsetToLowestE() + 1] = pressedNote;
//        Assert.assertArrayEquals(expOutput, actOutput);
//
//        // fourth click
//        sheets.pressNote(2);
//        actOutput = sheets.getSheetNotes();
//        expOutput = initSheetNoteArray();
//        pressedNote = new SheetNote(NoteCircle.G, 0, false);
//        expOutput[pressedNote.getOffsetToLowestE()] = pressedNote;
//        Assert.assertArrayEquals(expOutput, actOutput);
//
//        // fifth click
//        sheets.pressNote(2);
//        actOutput = sheets.getSheetNotes();
//        expOutput = initSheetNoteArray();
//        pressedNote = new SheetNote(NoteCircle.G, 0, true);
//        expOutput[pressedNote.getOffsetToLowestE()] = pressedNote;
//        Assert.assertArrayEquals(expOutput, actOutput);
//    }

//    @Test
//    public void testPressNote_TraversableTwoTimes() {
//        SheetModel sheets = new SheetModel(new FakeGui(), new FakeAudioConverter());
//        // first click
//        sheets.pressNote();
//        Map<Tone, SheetNote> actOutput = sheets.getSheetNotes();
//        Map<Tone, SheetNote> expOutput = new HashMap<>();
//        expOutput.put(Tone.E, new SheetNote(NoteCircle.E, 0, true));
//        Assert.assertArrayEquals(expOutput.values().toArray(), actOutput.values().toArray());
//
//        // second click
//        sheets.pressNote(0);
//        actOutput = sheets.getSheetNotes();
//        expOutput.remove(Tone.E);
//        expOutput.put(Tone.E, new SheetNote(NoteCircle.E, 0, true));
//        Assert.assertArrayEquals(expOutput.values().toArray(), actOutput.values().toArray());
//    }

}