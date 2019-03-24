package logic.guitar;

import gui.NotePrefix;

import java.util.Collections;
import java.util.List;

/**
 * Class implementing bahavior of a sheet note. In contrast to a guitar note this note does not have a fret position
 * and also is able to iterate it's prefix.
 */
public class SheetNote extends Note {


    public SheetNote(int offsetToLowerE) {
        super(generateId(offsetToLowerE), NotePrefix.NEUTRAL, generateOctave(offsetToLowerE), true);
    }

    public SheetNote(Tone tone, NotePrefix prefix, int octave, boolean isPlayed) {
        super(tone, prefix, octave, isPlayed);
    }

    private static Tone generateId(int offset) {
        return Tone.values()[offset % Tone.values().length];
    }

    private static int generateOctave(int offset) {
        return (offset + Tone.E.ordinal()) / Tone.values().length;
    }

    public int getOffsetToLowestE() {
        return this.tone.ordinal() + this.prefix.ordinal() * this.octave;
    }

    public SheetNote getLowestNoteOfTone() {
        NotePrefix lowestPrefix = Collections.min(this.tone.getPossiblePrefix());
        return new SheetNote(this.tone, lowestPrefix, this.octave, this.isPlayed);
    }

    public SheetNote nextSemiTone() {
        List<NotePrefix> possiblePrefix = this.tone.getPossiblePrefix();
        int idxNewPrefix = possiblePrefix.indexOf(this.prefix) + 1;
        if (idxNewPrefix != possiblePrefix.size()) {
            return new SheetNote(this.tone, possiblePrefix.get(idxNewPrefix), this.octave, this.isPlayed);
        }

        Tone newTone = this.tone.next();
        if (newTone.ordinal() == 0) {
            return new SheetNote(newTone, NotePrefix.NEUTRAL, this.octave + 1, this.isPlayed);
        } else {
            NotePrefix newPrefix = possiblePrefix.get(idxNewPrefix % possiblePrefix.size());
            return new SheetNote(newTone, newPrefix, this.octave, this.isPlayed);
        }
    }

    public SheetNote clearPrefix() {
        return new SheetNote(this.tone, NotePrefix.NEUTRAL, this.octave, this.isPlayed);
    }
}

//    public SheetNote nextMajorTone() {
//        NoteCircle note = this.getId().nextMajorTone();
//        return note == NoteCircle.values()[0] ?
//                new SheetNote(note, this.octave + 1, this.isPlayed)
//                : new SheetNote(note, this.octave, this.isPlayed);
//    }


