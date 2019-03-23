package logic.sheets;

import logic.guitar.FakeAudioConverter;
import logic.guitar.FakeGui;
import logic.guitar.NoteCircle;
import logic.guitar.SheetNote;
import logic.guitar.Tone;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SheetModelTest {

    // --- press note ---
//    @Test
//    public void testPressNote_NotTraversable() {
//        SheetModel sheets = new SheetModel(new FakeGui(), new FakeAudioConverter());
//        // first click
//        sheets.pressNote(0);
//        Map<Tone, SheetNote> actOutput = sheets.getSheetNotes();
//        Map<Tone, SheetNote> expOutput = new HashMap<>();
//        expOutput.put(Tone.E, new SheetNote(NoteCircle.E, 0, true));
//        Assert.assertArrayEquals(expOutput.values().toArray(), actOutput.values().toArray());
//
//        // second click
//        sheets.pressNote(0);
//        actOutput = sheets.getSheetNotes();
//        expOutput.remove(Tone.E);
//        expOutput.put(Tone.E, new SheetNote(NoteCircle.D_SHARP, 0, true));
//        Assert.assertArrayEquals(expOutput.values().toArray(), actOutput.values().toArray());
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