package logic.instrument;

import logic.note.Prefix;
import logic.note.NoteFactory;
import logic.note.SheetNote;
import logic.note.Tone;
import org.junit.Assert;
import org.junit.Test;

public class SheetNoteTest {

//    @Test
//    public void testConstructor() {
//        Assert.assertEquals(new SheetNote(NoteCircle.E, 0, true), new SheetNote(0));
//        Assert.assertEquals(new SheetNote(NoteCircle.C, 1, true), new SheetNote(5));
//    }

    @Test
    public void testGetLowestE() {
        int inputOffset = 0;
        SheetNote note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 1;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 2;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 3;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 4;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 5;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 6;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 7;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 8;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 9;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 10;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 11;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 12;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 13;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 14;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 15;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 16;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 17;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 18;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 19;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 20;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 21;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 22;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 23;
        note = NoteFactory.createSheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());
    }

//    @Test
//    public void testNextMajorTone() {
//        SheetNote input = new SheetNote(NoteCircle.B, 0, true);
//        SheetNote expNote = new SheetNote(NoteCircle.C, 1, true);
//        Assert.assertEquals(expNote, input.nextMajorTone());
//
//        input = new SheetNote(NoteCircle.C, 1, true);
//        expNote = new SheetNote(NoteCircle.D, 1, true);
//        Assert.assertEquals(expNote, input.nextMajorTone());
//    }
//
    @Test
    public void testGetLowestNoteOfTone() {
        SheetNote input = new SheetNote(Tone.D, Prefix.NEUTRAL, 0, true);
        Assert.assertEquals(new SheetNote(Tone.C, Prefix.SHARP, 0, true), input.getLowestNoteOfTone());

        input = new SheetNote(Tone.E, Prefix.NEUTRAL, 0, true);
        Assert.assertEquals(new SheetNote(Tone.D, Prefix.SHARP, 0, true), input.getLowestNoteOfTone());
    }


    @Test
    public void testIteratePrefix() {
        SheetNote note = new SheetNote(Tone.G, Prefix.NEUTRAL, 0, true);
        Assert.assertEquals(new SheetNote(Tone.G, Prefix.SHARP, 0, true), note);
        note = note.iteratePrefix();
        Assert.assertEquals(new SheetNote(Tone.G, Prefix.FLAT, 0, true), note);
        note = note.iteratePrefix();
        Assert.assertEquals(new SheetNote(Tone.G, Prefix.NEUTRAL, 0, false), note);
        note = note.iteratePrefix();
        Assert.assertEquals(new SheetNote(Tone.G, Prefix.SHARP, 0, true), note);
    }

}