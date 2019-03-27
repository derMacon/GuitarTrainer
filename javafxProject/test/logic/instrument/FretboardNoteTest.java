package logic.instrument;

import logic.note.Prefix;
import logic.note.Tone;
import org.junit.Assert;
import org.junit.Test;

public class FretboardNoteTest {

    @Test
    public void testEquals() {
        FretboardNote note1 = new FretboardNote(Tone.G, Prefix.NEUTRAL, 1, true, new FretboardPos(2, 0));
        FretboardNote note2 = new FretboardNote(Tone.G, Prefix.SHARP, 1, true, new FretboardPos(2, 1));
        Assert.assertFalse(note1.equals(note2));
    }

}