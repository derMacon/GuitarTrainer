package logic.guitar;

import gui.NotePrefix;

import java.util.Arrays;
import java.util.List;

public enum Tone {
    C(NotePrefix.NEUTRAL, NotePrefix.SHARP),
    D(NotePrefix.FLAT, NotePrefix.NEUTRAL, NotePrefix.SHARP),
    E(NotePrefix.FLAT, NotePrefix.NEUTRAL),
    F(NotePrefix.NEUTRAL, NotePrefix.SHARP),
    G(NotePrefix.FLAT, NotePrefix.NEUTRAL, NotePrefix.SHARP),
    A(NotePrefix.FLAT, NotePrefix.NEUTRAL, NotePrefix.SHARP),
    B(NotePrefix.FLAT, NotePrefix.NEUTRAL);

    private final List<NotePrefix> possiblePrefix;

    Tone(NotePrefix... possiblePrefix) {
        this.possiblePrefix = Arrays.asList(possiblePrefix);
    }

    public List<NotePrefix> getPossiblePrefix() {
        return possiblePrefix;
    }

    public Tone next() {
        return values()[(ordinal() + 1) % values().length];
    }


    /**
     * Gets the primary note of a given note.
     * E.g. D_SHARP == E_FLAT the primary note is always the one with the SHARP prefix -> in this case D
     *
     * @param offset offset from the lower E note (octave == 0)
     * @return primary noteId with the given note offset
     */
    public static Tone translateToTone(int offset) {
        return values()[(E.ordinal() + offset) % values().length];
    }

    public static int translateToOffset(Tone tone) {
        int offset = tone.ordinal() - E.ordinal();
        return offset < 0 ? offset + values().length : offset;
    }
}
