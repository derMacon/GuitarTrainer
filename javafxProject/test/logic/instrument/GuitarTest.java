package logic.instrument;

import logic.note.NoteCircle;
import org.junit.Assert;
import org.junit.Test;

public class GuitarTest {

    /*
    @Test
    public void testIncOctave_standardSituation() throws NotOnFretException {
        Guitar instrument = new Guitar(new FakeGui());
        FretboardNote note = new FretboardNote(NoteCircle.F, new FretboardPos(1, 6));
        FretboardNote actOutput = instrument.incOctave(note);
        FretboardNote expOutput = new FretboardNote(NoteCircle.F, new FretboardPos(0, 1));
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testIncOctave_highestBasePosition() throws NotOnFretException {
        Guitar instrument = new Guitar(new FakeGui());
        FretboardNote note = new FretboardNote(NoteCircle.E, new FretboardPos(1, 5));
        FretboardNote actOutput = instrument.incOctave(note);
        FretboardNote expOutput = new FretboardNote(NoteCircle.E, new FretboardPos(0, 0));
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testIncOctave_highestPossibleNum() throws NotOnFretException {
        Guitar instrument = new Guitar(new FakeGui());
        FretboardNote note = new FretboardNote(NoteCircle.E, new FretboardPos(0, 0));
        FretboardNote actOutput = instrument.incOctave(note);
        FretboardNote expOutput = new FretboardNote(NoteCircle.E, new FretboardPos(0, 12));
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test(expected = logic.instrument.NotOnFretException.class)
    public void testIncOctave_errorTooHigh() throws NotOnFretException {
        Guitar instrument = new Guitar(new FakeGui());
        FretboardNote note = new FretboardNote(NoteCircle.F, new FretboardPos(0, 1));
        instrument.incOctave(note);
    }
    */

    // --- updateString ---

//    @Test
//    public void testUpdateString_clickingNonOpenString2x_sameStr() {
//        Guitar guitar = new Guitar(new FakeGui());
//        FretboardNote[] expOutput = guitar.OPEN_STRINGS.clone();
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//
//        guitar.updateNotes(new FretboardPos(0, 1));
//        expOutput[0] = new FretboardNote(NoteCircle.F, 2, new FretboardPos(0, 1));
//        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//
//        guitar.updateNotes(new FretboardPos(0, 1));
//        expOutput = guitar.OPEN_STRINGS.clone();
//        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//    }
//
//    @Test
//    public void testUpdateString_clickingNonOpenString2x_notSameStr() {
//        Guitar guitar = new Guitar(new FakeGui());
//        FretboardNote[] expOutput = guitar.OPEN_STRINGS.clone();
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//
//        guitar.updateNotes(new FretboardPos(0, 1));
//        expOutput[0] = new FretboardNote(NoteCircle.F, 2, new FretboardPos(0, 1));
//        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//
//        guitar.updateNotes(new FretboardPos(0, 2));
//        expOutput[0] = new FretboardNote(NoteCircle.F_SHARP, 2, new FretboardPos(0, 2));
//        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//    }
//
//    @Test
//    public void testUpdateString_clickingOpenString2x() {
//        Guitar guitar = new Guitar(new FakeGui());
//        FretboardNote[] expOutput = guitar.OPEN_STRINGS.clone();
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//
//        guitar.updateNotes(new FretboardPos(0, 0));
//        Assert.assertFalse(guitar.pressedStrings[0].isPlayed());
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//
//        guitar.updateNotes(new FretboardPos(0, 0));
//        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//    }
//
//    @Test
//    public void testUpdateString_nonOpenString_openStr() {
//        Guitar guitar = new Guitar(new FakeGui());
//        FretboardNote[] expOutput = guitar.OPEN_STRINGS.clone();
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//
//        guitar.updateNotes(new FretboardPos(0, 1));
//        expOutput[0] = new FretboardNote(NoteCircle.F, 2, new FretboardPos(0, 1));
//        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//
//        guitar.updateNotes(new FretboardPos(0, 0));
//        expOutput = guitar.OPEN_STRINGS;
//        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//    }
//
//    @Test
//    public void testUpdateString_openStr_nonOpenString() {
//        Guitar guitar = new Guitar(new FakeGui());
//        FretboardNote[] expOutput = guitar.OPEN_STRINGS.clone();
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//
//        guitar.updateNotes(new FretboardPos(0, 0));
//        expOutput = guitar.OPEN_STRINGS;
//        Assert.assertFalse(guitar.pressedStrings[0].isPlayed());
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//
//        guitar.updateNotes(new FretboardPos(0, 1));
//        expOutput[0] = new FretboardNote(NoteCircle.F, 2, new FretboardPos(0, 1));
//        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
//        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
//    }


//    // --- translate ---
//    @Test
//    public void testTranslate_openStrings() {
//        Guitar guitar = new Guitar(new FakeGui());
//        Assert.assertEquals(guitar.OPEN_STRINGS[0], guitar.translate(new FretboardPos(0, 0)));
//        Assert.assertEquals(guitar.OPEN_STRINGS[1], guitar.translate(new FretboardPos(1, 0)));
//        Assert.assertEquals(guitar.OPEN_STRINGS[2], guitar.translate(new FretboardPos(2, 0)));
//        Assert.assertEquals(guitar.OPEN_STRINGS[3], guitar.translate(new FretboardPos(3, 0)));
//        Assert.assertEquals(guitar.OPEN_STRINGS[4], guitar.translate(new FretboardPos(4, 0)));
//        Assert.assertEquals(guitar.OPEN_STRINGS[5], guitar.translate(new FretboardPos(5, 0)));
//    }
//
//    @Test
//    public void testTranslate_lowEString() {
//        Guitar guitar = new Guitar(new FakeGui());
//
//        // lowest possible val apart from open string
//        FretboardPos inputFretboardPos = new FretboardPos(5, 1);
//        FretboardNote expFretboardNote = new FretboardNote(NoteCircle.F, 0, inputFretboardPos);
//        FretboardNote actFretboardNote = guitar.translate(inputFretboardPos);
//        Assert.assertEquals(expFretboardNote, actFretboardNote);
//
//        // changing octave
//        inputFretboardPos = new FretboardPos(5, 8);
//        expFretboardNote = new FretboardNote(NoteCircle.C, 1, inputFretboardPos);
//        actFretboardNote = guitar.translate(inputFretboardPos);
//        Assert.assertEquals(expFretboardNote, actFretboardNote);
//
//        // highest possible value
//        inputFretboardPos = new FretboardPos(5, 8);
//        expFretboardNote = new FretboardNote(NoteCircle.C, 1, inputFretboardPos);
//        actFretboardNote = guitar.translate(inputFretboardPos);
//        Assert.assertEquals(expFretboardNote, actFretboardNote);
//    }
//
//    @Test
//    public void testTranslate_highEString() {
//        Guitar guitar = new Guitar(new FakeGui());
//
//        FretboardPos inputFretboardPos = new FretboardPos(0, 7);
//        FretboardNote expFretboardNote = new FretboardNote(NoteCircle.B, 2, inputFretboardPos);
//        FretboardNote actFretboardNote = guitar.translate(inputFretboardPos);
//        Assert.assertEquals(expFretboardNote, actFretboardNote);
//
//        inputFretboardPos = new FretboardPos(0, 8);
//        expFretboardNote = new FretboardNote(NoteCircle.C, 3, inputFretboardPos);
//        actFretboardNote = guitar.translate(inputFretboardPos);
//        Assert.assertEquals(expFretboardNote, actFretboardNote);
//
//        inputFretboardPos = new FretboardPos(0, 12);
//        expFretboardNote = new FretboardNote(NoteCircle.E, 3, inputFretboardPos);
//        actFretboardNote = guitar.translate(inputFretboardPos);
//        Assert.assertEquals(expFretboardNote, actFretboardNote);
//    }


}