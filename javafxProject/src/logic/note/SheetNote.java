package logic.note;

import java.util.Collections;
import java.util.List;

/**
 * Class implementing bahavior of a sheet note. In contrast to a instrument note this note does not have a fret position
 * and also is able to iterate it's prefix.
 */
public class SheetNote extends Note {

    /**
     * Constructor
     * @param tone tone of the note
     * @param prefix prefix of the note
     * @param octave octave of the note
     * @param isPlayed flag determining if the note is played or not
     */
    public SheetNote(Tone tone, Prefix prefix, int octave, boolean isPlayed) {
        super(tone, prefix, octave, isPlayed);
    }

    /**
     * Generates the offset measured in tones to the note new Note(Tone.E, Prefix.NEUTRAL, 0, true);
     * @return offset to the lowest note E with the octave 0
     */
    public int getOffsetToLowestE() {
        return (this.tone.ordinal() + this.octave * Tone.values().length) - Tone.E.ordinal();
    }

    /**
     * Generates the lowest possible note of a the notes tone
     * @return lowest possible note of a the notes tone
     */
    public SheetNote getLowestNoteOfTone() {
        Prefix lowestPrefix = Collections.min(this.tone.getPossiblePrefix());
        return new SheetNote(this.tone, lowestPrefix, this.octave, this.isPlayed);
    }

    /**
     * Iterates the prefix of the current note.
     * e.g. the prefix is:
     * NEUTRAL -> SHARP
     * SHARP -> FLAT
     * FLAT -> NEUTRAL
     * <p>
     * But only if it is possible for the note (e.g. for F, C, etc. it is not possible since there is no semitone in
     * between them -> not possible to flatten)
     * <p>
     * Used to make it possible for the user to iterate through the variations with only on button.
     *
     * @return new SheetNote instance
     */
    public SheetNote iteratePrefix() {
        if (!isPlayed) {
            this.isPlayed = true;
            return this;
        }

        List<Prefix> possiblePrefix = this.tone.getPossiblePrefix();
        Collections.sort(possiblePrefix, new PrefixComparator());
        int idxNewPrefix = possiblePrefix.indexOf(this.prefix) + 1;
        if (idxNewPrefix == possiblePrefix.size()) {
            return new SheetNote(this.tone, Prefix.NEUTRAL, this.octave, false);
        } else {
            return new SheetNote(this.tone, possiblePrefix.get(idxNewPrefix), this.octave, this.isPlayed);
        }
    }

}



