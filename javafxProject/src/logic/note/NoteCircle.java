package logic.note;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static logic.note.Prefix.*;

/**
 * Enum holding the information about the order of the notes in the chromatic circle. Especially useful when
 * determining the next semitone of a given combination of tone and prefix (needed when translating an instance of a
 * Fretboardpos instance to Note instance.
 */
public enum NoteCircle {
    C(new Tone[]{Tone.C}, new Prefix[]{NEUTRAL}),
    C_SHARP(new Tone[]{Tone.C, Tone.D}, new Prefix[]{SHARP, FLAT}),
    D(new Tone[]{Tone.D}, new Prefix[]{NEUTRAL}),
    D_SHARP(new Tone[]{Tone.D, Tone.E}, new Prefix[]{SHARP, FLAT}),
    E(new Tone[]{Tone.E}, new Prefix[]{NEUTRAL}),
    F(new Tone[]{Tone.F}, new Prefix[]{NEUTRAL}),
    F_SHARP(new Tone[]{Tone.F, Tone.G}, new Prefix[]{SHARP, FLAT}),
    G(new Tone[]{Tone.G}, new Prefix[]{NEUTRAL}),
    G_SHARP(new Tone[]{Tone.G, Tone.A}, new Prefix[]{SHARP, FLAT}),
    A(new Tone[]{Tone.A}, new Prefix[]{NEUTRAL}),
    A_SHARP(new Tone[]{Tone.A, Tone.B}, new Prefix[]{SHARP, FLAT}),
    B(new Tone[]{Tone.B}, new Prefix[]{NEUTRAL});

    /**
     * Map containg all possible tone / prefix combination for a semitone
     * <p>
     * e.g. C # will result in the following map:
     * Tone.C -> Prefix.SHARP
     * Tone.D -> Prefix.FLAT
     * <p>
     * since C_SHARP == D_FLAT
     */
    private Map<Tone, Prefix> notes;

    /**
     * Constructor translating two given arrays to one map, mapping each slot with the same index
     *
     * @param id       array of possible tones for this semitone
     * @param prefixes array of possible prefixes for this semitone
     */
    NoteCircle(Tone[] id, Prefix[] prefixes) {
        assert id.length == prefixes.length;
        this.notes = new LinkedHashMap<>();
        for (int i = 0; i < id.length; i++) {
            this.notes.put(id[i], prefixes[i]);
        }
    }

    /**
     * Getter for the the mapping of tone and prefix of this semitone
     *
     * @return the mapping of tone and prefix of this semitone
     */
    public Map<Tone, Prefix> getNotes() {
        return notes;
    }

    /**
     * Returns the primary tone of the semitone. This is always the tone with pefix == Prefix.SHARP or prefix ==
     * Pefix.NEUTRAL. e.g. C_SHARP
     * <p>
     * Tone.C -> Prefix.SHARP
     * Tone.D -> Prefix.FLAT
     * ==> C
     *
     * @return the primary tone of the semitone
     */
    public Tone getPrimaryTone() {
        return getTones().get(0);
    }

    /**
     * Getter for the keyset of the mapping
     *
     * @return the keyset of the mapping
     */
    public List<Tone> getTones() {
        return new ArrayList<>(this.notes.keySet());
    }

    /**
     * Generates the appropriate NoteCirle member for a given note. If multiple Ids are possible, the one with the
     * primary tone / without the prefix == Prefix.FLAT will be picked.
     *
     * @param note note to generate the id for
     * @return Notecirle member for the given note
     */
    public static NoteCircle getId(Note note) {
        return getId(note.getTone(), note.getPrefix());
    }

    /**
     * Generates the appropriate NoteCirle member for a given note. If multiple Ids are possible, the one with the
     * primary tone / without the prefix == Prefix.FLAT will be picked.
     *
     * @param tone tone to generate the id for
     * @param prefix prefix to generate the id for
     * @return the NoteCircle member appropriate to the given tone and prefix
     */
    public static NoteCircle getId(Tone tone, Prefix prefix) {
        NoteCircle[] circ = values();
        for (int i = 0; i < circ.length; i++) {
            if (circ[i].getNotes().get(tone) == prefix) {
                return circ[i];
            }
        }
        System.out.println("// todo thow some kind of exception");
        return null;
    }

}
