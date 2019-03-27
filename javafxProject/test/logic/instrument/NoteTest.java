package logic.instrument;

import logic.note.Prefix;
import logic.note.Note;
import logic.note.Tone;
import org.junit.Assert;
import org.junit.Test;

public class NoteTest {

    @Test
    public void testNextSemiTone() {
        FretboardNote note = new FretboardNote(Tone.E, Prefix.NEUTRAL, 0, true, new FretboardPos(5, 0));
        Note expOutput= new FretboardNote(Tone.F, Prefix.NEUTRAL, 0, true, new FretboardPos(5, 0));
        Assert.assertEquals(expOutput, note.nextSemiTone());

        note = new FretboardNote(Tone.F, Prefix.NEUTRAL, 0, true, new FretboardPos(5, 1));
        expOutput = new FretboardNote(Tone.F, Prefix.SHARP, 0, true, new FretboardPos(5, 2));
        Assert.assertEquals(expOutput, note.nextSemiTone());

        note = new FretboardNote(Tone.F, Prefix.SHARP, 0, true, new FretboardPos(5, 2));
        expOutput = new FretboardNote(Tone.G, Prefix.NEUTRAL, 0, true, new FretboardPos(5, 3));
        Assert.assertEquals(expOutput, note.nextSemiTone());

        note = new FretboardNote(Tone.G, Prefix.FLAT, 0, true, new FretboardPos(5, 3));
        expOutput = new FretboardNote(Tone.G, Prefix.NEUTRAL, 0, true, new FretboardPos(5, 4));
        Assert.assertEquals(expOutput, note.nextSemiTone());

        note = new FretboardNote(Tone.G, Prefix.NEUTRAL, 0, true, new FretboardPos(5, 4));
        expOutput = new FretboardNote(Tone.G, Prefix.SHARP, 0, true, new FretboardPos(5, 5));
        Assert.assertEquals(expOutput, note.nextSemiTone());

        note = new FretboardNote(Tone.G, Prefix.SHARP, 0, true, new FretboardPos(5, 5));
        expOutput = new FretboardNote(Tone.A, Prefix.NEUTRAL, 0, true, new FretboardPos(5, 6));
        Assert.assertEquals(expOutput, note.nextSemiTone());

        note = new FretboardNote(Tone.A, Prefix.SHARP, 0, true, new FretboardPos(5, 6));
        expOutput = new FretboardNote(Tone.B, Prefix.NEUTRAL, 0, true, new FretboardPos(5, 7));
        Assert.assertEquals(expOutput, note.nextSemiTone());

        note = new FretboardNote(Tone.B, Prefix.NEUTRAL, 0, true, new FretboardPos(5, 7));
        expOutput = new FretboardNote(Tone.C, Prefix.NEUTRAL, 1, true, new FretboardPos(5, 8));
        Assert.assertEquals(expOutput, note.nextSemiTone());
    }

}