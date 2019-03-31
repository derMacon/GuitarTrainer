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
     *
     * @param possiblePrefix possible prefixes a Note with the given tone can have
     */
    Tone(Prefix... possiblePrefix) {
        this.possiblePrefix = Arrays.asList(possiblePrefix);
    }

    /**
     * Parses the string to a tone
     *
     * @param str string to parse to a tone
     * @return tone that equals the string description
     */
    public static Tone translate(String str) {
        for (Tone curr : values()) {
            if (curr.name().charAt(0) == str.charAt(0)) {
                return curr;
            }
        }
        return null;
    }

    /**
     * Getter for the possible prefixes of a tone
     *
     * @return possible prefixes of a tone
     */
    public List<Prefix> getPossiblePrefix() {
        return possiblePrefix;
    }

}
