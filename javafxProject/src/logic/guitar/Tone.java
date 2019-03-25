package logic.guitar;

import gui.Prefix;

import java.util.Arrays;
import java.util.List;

public enum Tone {
    C(Prefix.NEUTRAL, Prefix.SHARP),
    D(Prefix.FLAT, Prefix.NEUTRAL, Prefix.SHARP),
    E(Prefix.FLAT, Prefix.NEUTRAL),
    F(Prefix.NEUTRAL, Prefix.SHARP),
    G(Prefix.FLAT, Prefix.NEUTRAL, Prefix.SHARP),
    A(Prefix.FLAT, Prefix.NEUTRAL, Prefix.SHARP),
    B(Prefix.FLAT, Prefix.NEUTRAL);

    private final List<Prefix> possiblePrefix;

    Tone(Prefix... possiblePrefix) {
        this.possiblePrefix = Arrays.asList(possiblePrefix);
    }

    public List<Prefix> getPossiblePrefix() {
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
