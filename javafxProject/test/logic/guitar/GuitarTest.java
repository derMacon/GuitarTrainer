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

    @Test
    public void testUpdateString_clickingNonOpenString2x_sameStr() {
        Guitar guitar = new Guitar(new FakeGui());
        Note[] expOutput = guitar.openStrings.clone();
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new Pos(0, 1));
        expOutput[0] = new Note(NoteCircle.F, new Pos(0,1));
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
        expOutput[0] = new Note(NoteCircle.F, new Pos(0,1));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);

        guitar.pressNote(new Pos(0, 2));
        expOutput[0] = new Note(NoteCircle.F_SHARP, new Pos(0,2));
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
        expOutput[0] = new Note(NoteCircle.F, new Pos(0,1));
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
        expOutput[0] = new Note(NoteCircle.F, new Pos(0,1));
        Assert.assertTrue(guitar.pressedStrings[0].isPlayed());
        Assert.assertArrayEquals(guitar.pressedStrings, expOutput);
    }

}