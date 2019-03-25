package logic.guitar;

import gui.Prefix;
import logic.sheets.PrefixComparator;

import java.util.Collections;
import java.util.List;

/**
 * Class implementing bahavior of a sheet note. In contrast to a guitar note this note does not have a fret position
 * and also is able to iterate it's prefix.
 */
public class SheetNote extends Note {


    public SheetNote(int offsetToLowerE) {
        super(generateId(offsetToLowerE), Prefix.NEUTRAL, generateOctave(offsetToLowerE), true);
    }

    public SheetNote(Tone tone, Prefix prefix, int octave, boolean isPlayed) {
        super(tone, prefix, octave, isPlayed);
    }

    private static Tone generateId(int offset) {
        return Tone.values()[(Tone.E.ordinal() + offset) % Tone.values().length];
    }

    private static int generateOctave(int offset) {
        return (offset + Tone.E.ordinal()) / Tone.values().length;
    }

    public int getOffsetToLowestE() {
        return (this.tone.ordinal() + this.octave * Tone.values().length) - Tone.E.ordinal();
    }

    public SheetNote getLowestNoteOfTone() {
        Prefix lowestPrefix = Collections.min(this.tone.getPossiblePrefix());
        return new SheetNote(this.tone, lowestPrefix, this.octave, this.isPlayed);
    }

    public SheetNote nextSemiTone() {
        List<Prefix> possiblePrefix = this.tone.getPossiblePrefix();
        int idxNewPrefix = possiblePrefix.indexOf(this.prefix) + 1;
        if (idxNewPrefix != possiblePrefix.size()) {
            return new SheetNote(this.tone, possiblePrefix.get(idxNewPrefix), this.octave, this.isPlayed);
        }

        Tone newTone = this.tone.next();
        if (newTone.ordinal() == 0) {
            return new SheetNote(newTone, Prefix.NEUTRAL, this.octave + 1, this.isPlayed);
        } else {
            Prefix newPrefix = possiblePrefix.get(idxNewPrefix % possiblePrefix.size());
            return new SheetNote(newTone, newPrefix, this.octave, this.isPlayed);
        }
    }

    public SheetNote iteratePrefix() {
        if(!isPlayed) {
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

    public SheetNote clearPrefix() {
        return new SheetNote(this.tone, Prefix.NEUTRAL, this.octave, this.isPlayed);
    }
}



