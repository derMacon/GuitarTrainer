package logic.note;

import java.util.Arrays;
import java.util.List;

/**
 * Enum representing a Tone, used as a component from the Note class as well as the NoteCircle interface.
 */
public enum Tone {
    C(Prefix.NEUTRAL, Prefix.SHARP),
    D(Prefix.FLAT, Prefix.NEUTRAL, Prefix.SHARP),
    E(Prefix.FLAT, Prefix.NEUTRAL),
    F(Prefix.NEUTRAL, Prefix.SHARP),
    G(Prefix.FLAT, Prefix.NEUTRAL, Prefix.SHARP),
    A(Prefix.FLAT, Prefix.NEUTRAL, Prefix.SHARP),
    B(Prefix.FLAT, Prefix.NEUTRAL);

    private final List<Prefix> possiblePrefix;

    /**
     * Constructor setting the possible prefixes for the tone
     * @param possiblePrefix possible prefixes a Note with the given tone can have
     */
    Tone(Prefix... possiblePrefix) {
        this.possiblePrefix = Arrays.asList(possiblePrefix);
    }

    /**
     * Getter for the possible prefixes of a tone
     * @return possible prefixes of a tone
     */
    public List<Prefix> getPossiblePrefix() {
        return possiblePrefix;
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

}
