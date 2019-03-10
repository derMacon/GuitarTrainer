package logic.guitar;

import org.junit.Assert;
import org.junit.Test;

public class GuitarTest {

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


}