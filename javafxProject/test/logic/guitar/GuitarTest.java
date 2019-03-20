package logic.guitar;

import org.junit.Assert;
import org.junit.Test;

public class GuitarTest {

    /*
    @Test
    public void testIncOctave_standardSituation() throws NotOnFretException {
        Guitar guitar = new Guitar(new FakeGui());
        Note note = new Note(NoteCircle.F, new FretboardPos(1, 6));
        Note actOutput = guitar.incOctave(note);
        Note expOutput = new Note(NoteCircle.F, new FretboardPos(0, 1));
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testIncOctave_highestBasePosition() throws NotOnFretException {
        Guitar guitar = new Guitar(new FakeGui());
        Note note = new Note(NoteCircle.E, new FretboardPos(1, 5));
        Note actOutput = guitar.incOctave(note);
        Note expOutput = new Note(NoteCircle.E, new FretboardPos(0, 0));
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testIncOctave_highestPossibleNum() throws NotOnFretException {
        Guitar guitar = new Guitar(new FakeGui());
        Note note = new Note(NoteCircle.E, new FretboardPos(0, 0));
        Note actOutput = guitar.incOctave(note);
        Note expOutput = new Note(NoteCircle.E, new FretboardPos(0, 12));
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test(expected = logic.guitar.NotOnFretException.class)
    public void testIncOctave_errorTooHigh() throws NotOnFretException {
        Guitar guitar = new Guitar(new FakeGui());
        Note note = new Note(NoteCircle.F, new FretboardPos(0, 1));
        guitar.incOctave(note);
    }
    */

    // --- updateString ---

    @Test
    public void testUpdateString_clickingNonOpenString2x_sameStr() {
        Guitar guitar = new Guitar(new FakeGui());
        Note[] expOutput = guitar.OPEN_STRINGS.clone();
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new FretboardPos(0, 1));
        expOutput[0] = new Note(NoteCircle.F, 2, new FretboardPos(0,1));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new FretboardPos(0, 1));
        expOutput = guitar.OPEN_STRINGS.clone();
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
    }

    @Test
    public void testUpdateString_clickingNonOpenString2x_notSameStr() {
        Guitar guitar = new Guitar(new FakeGui());
        Note[] expOutput = guitar.OPEN_STRINGS.clone();
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new FretboardPos(0, 1));
        expOutput[0] = new Note(NoteCircle.F, 2, new FretboardPos(0,1));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new FretboardPos(0, 2));
        expOutput[0] = new Note(NoteCircle.F_SHARP, 2, new FretboardPos(0,2));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
    }

    @Test
    public void testUpdateString_clickingOpenString2x() {
        Guitar guitar = new Guitar(new FakeGui());
        Note[] expOutput = guitar.OPEN_STRINGS.clone();
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new FretboardPos(0, 0));
        Assert.assertFalse(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new FretboardPos(0, 0));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
    }

    @Test
    public void testUpdateString_nonOpenString_openStr() {
        Guitar guitar = new Guitar(new FakeGui());
        Note[] expOutput = guitar.OPEN_STRINGS.clone();
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new FretboardPos(0, 1));
        expOutput[0] = new Note(NoteCircle.F, 2, new FretboardPos(0,1));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new FretboardPos(0, 0));
        expOutput = guitar.OPEN_STRINGS;
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
    }

    @Test
    public void testUpdateString_openStr_nonOpenString() {
        Guitar guitar = new Guitar(new FakeGui());
        Note[] expOutput = guitar.OPEN_STRINGS.clone();
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new FretboardPos(0, 0));
        expOutput = guitar.OPEN_STRINGS;
        Assert.assertFalse(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new FretboardPos(0, 1));
        expOutput[0] = new Note(NoteCircle.F, 2, new FretboardPos(0,1));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
    }


    // --- translate ---
    @Test
    public void testTranslate_openStrings() {
        Guitar guitar = new Guitar(new FakeGui());
        Assert.assertEquals(guitar.OPEN_STRINGS[0], guitar.translate(new FretboardPos(0,0)));
        Assert.assertEquals(guitar.OPEN_STRINGS[1], guitar.translate(new FretboardPos(1,0)));
        Assert.assertEquals(guitar.OPEN_STRINGS[2], guitar.translate(new FretboardPos(2,0)));
        Assert.assertEquals(guitar.OPEN_STRINGS[3], guitar.translate(new FretboardPos(3,0)));
        Assert.assertEquals(guitar.OPEN_STRINGS[4], guitar.translate(new FretboardPos(4,0)));
        Assert.assertEquals(guitar.OPEN_STRINGS[5], guitar.translate(new FretboardPos(5,0)));
    }

    @Test
    public void testTranslate_lowEString() {
        Guitar guitar = new Guitar(new FakeGui());

        // lowest possible val apart from open string
        FretboardPos inputFretboardPos = new FretboardPos(5, 1);
        Note expNote = new Note(NoteCircle.F, 0, inputFretboardPos);
        Note actNote = guitar.translate(inputFretboardPos);
        Assert.assertEquals(expNote, actNote);

        // changing octave
        inputFretboardPos = new FretboardPos(5, 8);
        expNote = new Note(NoteCircle.C, 1, inputFretboardPos);
        actNote = guitar.translate(inputFretboardPos);
        Assert.assertEquals(expNote, actNote);

        // highest possible value
        inputFretboardPos = new FretboardPos(5, 8);
        expNote = new Note(NoteCircle.C, 1, inputFretboardPos);
        actNote = guitar.translate(inputFretboardPos);
        Assert.assertEquals(expNote, actNote);
    }

    @Test
    public void testTranslate_highEString() {
        Guitar guitar = new Guitar(new FakeGui());

        FretboardPos inputFretboardPos = new FretboardPos(0, 7);
        Note expNote = new Note(NoteCircle.B, 2, inputFretboardPos);
        Note actNote = guitar.translate(inputFretboardPos);
        Assert.assertEquals(expNote, actNote);

        inputFretboardPos = new FretboardPos(0, 8);
        expNote = new Note(NoteCircle.C, 3, inputFretboardPos);
        actNote = guitar.translate(inputFretboardPos);
        Assert.assertEquals(expNote, actNote);

        inputFretboardPos = new FretboardPos(0, 12);
        expNote = new Note(NoteCircle.E, 3, inputFretboardPos);
        actNote = guitar.translate(inputFretboardPos);
        Assert.assertEquals(expNote, actNote);
    }


}