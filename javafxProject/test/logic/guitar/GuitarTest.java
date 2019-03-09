package logic.guitar;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuitarTest {

    @Test
    public void testIncOctave_standardSituation() {
        Guitar guitar = new Guitar(new FakeGui());
        Note note = new Note(NoteCircle.F, new Pos(1, 6));
        Note actOutput = guitar.incOctave(note);
        Note expOutput = new Note(NoteCircle.F, new Pos(0, 1));
        Assert.assertEquals(expOutput, actOutput);
    }

}