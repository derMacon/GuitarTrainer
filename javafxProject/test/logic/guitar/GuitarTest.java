package logic.guitar;

import org.junit.Assert;
import org.junit.Test;

public class GuitarTest {

    /*
    @Test
    public void testIncOctave_standardSituation() throws NotOnFretException {
        Guitar guitar = new Guitar(new FakeGui());
        Note note = new Note(NoteCircle.F, new Pos(1, 6));
        Note actOutput = guitar.incOctave(note);
        Note expOutput = new Note(NoteCircle.F, new Pos(0, 1));
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testIncOctave_highestBasePosition() throws NotOnFretException {
        Guitar guitar = new Guitar(new FakeGui());
        Note note = new Note(NoteCircle.E, new Pos(1, 5));
        Note actOutput = guitar.incOctave(note);
        Note expOutput = new Note(NoteCircle.E, new Pos(0, 0));
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testIncOctave_highestPossibleNum() throws NotOnFretException {
        Guitar guitar = new Guitar(new FakeGui());
        Note note = new Note(NoteCircle.E, new Pos(0, 0));
        Note actOutput = guitar.incOctave(note);
        Note expOutput = new Note(NoteCircle.E, new Pos(0, 12));
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test(expected = logic.guitar.NotOnFretException.class)
    public void testIncOctave_errorTooHigh() throws NotOnFretException {
        Guitar guitar = new Guitar(new FakeGui());
        Note note = new Note(NoteCircle.F, new Pos(0, 1));
        guitar.incOctave(note);
    }
    */

    // --- updateString ---

    @Test
    public void testUpdateString_clickingNonOpenString2x_sameStr() {
        Guitar guitar = new Guitar(new FakeGui());
        Note[] expOutput = guitar.openStrings.clone();
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new Pos(0, 1));
        expOutput[0] = new Note(NoteCircle.F, 2, new Pos(0,1));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new Pos(0, 1));
        expOutput = guitar.openStrings.clone();
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
    }

    @Test
    public void testUpdateString_clickingNonOpenString2x_notSameStr() {
        Guitar guitar = new Guitar(new FakeGui());
        Note[] expOutput = guitar.openStrings.clone();
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new Pos(0, 1));
        expOutput[0] = new Note(NoteCircle.F, 2, new Pos(0,1));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new Pos(0, 2));
        expOutput[0] = new Note(NoteCircle.F_SHARP, 2, new Pos(0,2));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
    }

    @Test
    public void testUpdateString_clickingOpenString2x() {
        Guitar guitar = new Guitar(new FakeGui());
        Note[] expOutput = guitar.openStrings.clone();
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new Pos(0, 0));
        Assert.assertFalse(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new Pos(0, 0));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
    }

    @Test
    public void testUpdateString_nonOpenString_openStr() {
        Guitar guitar = new Guitar(new FakeGui());
        Note[] expOutput = guitar.openStrings.clone();
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new Pos(0, 1));
        expOutput[0] = new Note(NoteCircle.F, 2, new Pos(0,1));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new Pos(0, 0));
        expOutput = guitar.openStrings;
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
    }

    @Test
    public void testUpdateString_openStr_nonOpenString() {
        Guitar guitar = new Guitar(new FakeGui());
        Note[] expOutput = guitar.openStrings.clone();
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new Pos(0, 0));
        expOutput = guitar.openStrings;
        Assert.assertFalse(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new Pos(0, 1));
        expOutput[0] = new Note(NoteCircle.F, 2, new Pos(0,1));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
    }


    // --- translate ---
    @Test
    public void testTranslate_openStrings() {
        Guitar guitar = new Guitar(new FakeGui());
        Assert.assertEquals(guitar.openStrings[0], guitar.translate(new Pos(0,0)));
        Assert.assertEquals(guitar.openStrings[1], guitar.translate(new Pos(1,0)));
        Assert.assertEquals(guitar.openStrings[2], guitar.translate(new Pos(2,0)));
        Assert.assertEquals(guitar.openStrings[3], guitar.translate(new Pos(3,0)));
        Assert.assertEquals(guitar.openStrings[4], guitar.translate(new Pos(4,0)));
        Assert.assertEquals(guitar.openStrings[5], guitar.translate(new Pos(5,0)));
    }

    @Test
    public void testTranslate_lowEString() {
        Guitar guitar = new Guitar(new FakeGui());

        // lowest possible val apart from open string
        Pos inputPos = new Pos(5, 1);
        Note expNote = new Note(NoteCircle.F, 0, inputPos);
        Note actNote = guitar.translate(inputPos);
        Assert.assertEquals(expNote, actNote);

        // changing octave
        inputPos = new Pos(5, 8);
        expNote = new Note(NoteCircle.C, 1, inputPos);
        actNote = guitar.translate(inputPos);
        Assert.assertEquals(expNote, actNote);

        // highest possible value
        inputPos = new Pos(5, 8);
        expNote = new Note(NoteCircle.C, 1, inputPos);
        actNote = guitar.translate(inputPos);
        Assert.assertEquals(expNote, actNote);
    }

    @Test
    public void testTranslate_highEString() {
        Guitar guitar = new Guitar(new FakeGui());

        Pos inputPos = new Pos(0, 7);
        Note expNote = new Note(NoteCircle.B, 2, inputPos);
        Note actNote = guitar.translate(inputPos);
        Assert.assertEquals(expNote, actNote);

        inputPos = new Pos(0, 8);
        expNote = new Note(NoteCircle.C, 3, inputPos);
        actNote = guitar.translate(inputPos);
        Assert.assertEquals(expNote, actNote);

        inputPos = new Pos(0, 12);
        expNote = new Note(NoteCircle.E, 3, inputPos);
        actNote = guitar.translate(inputPos);
        Assert.assertEquals(expNote, actNote);
    }


}