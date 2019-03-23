package logic.guitar;

import org.junit.Assert;
import org.junit.Test;

public class SheetNoteTest {

    @Test
    public void testConstructor() {
        Assert.assertEquals(new SheetNote(NoteCircle.E, 0, true), new SheetNote(0));
        Assert.assertEquals(new SheetNote(NoteCircle.C, 1, true), new SheetNote(5));
    }

    @Test
    public void testGetLowestE() {
        int inputOffset = 0;
        SheetNote note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 1;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 2;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 3;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 4;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 5;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 6;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 7;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 8;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 9;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 10;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 11;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 12;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 13;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 14;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 15;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 16;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 17;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 18;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 19;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 20;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 21;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 22;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());

        inputOffset = 23;
        note = new SheetNote(inputOffset);
        Assert.assertEquals(inputOffset, note.getOffsetToLowestE());
    }

    @Test
    public void testNextMajorTone() {
        SheetNote input = new SheetNote(NoteCircle.B, 0, true);
        SheetNote expNote = new SheetNote(NoteCircle.C, 1, true);
        Assert.assertEquals(expNote, input.nextMajorTone());

        input = new SheetNote(NoteCircle.C, 1, true);
        expNote = new SheetNote(NoteCircle.D, 1, true);
        Assert.assertEquals(expNote, input.nextMajorTone());
    }

}