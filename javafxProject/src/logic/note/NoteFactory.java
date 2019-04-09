package logic.note;

import logic.instrument.FretboardPos;
import logic.instrument.Guitar;

import java.util.ArrayList;
import java.util.List;

/**
 * Static factory for the note class implementations
 */
public class NoteFactory {

    /**
     * Parses the a given line from a text file to a note instance
     *
     * @param line line to parse`
     * @return a given line from a text file to a note instance
     */
    public static Note createNote(String line) {
        // todo validate with pattern matching / regex
        String[] components = line.substring(1, line.length() - 1).split(",");
        String[] data = getDataFromComponents(components);
        return new Note(Tone.translate(data[0]), Prefix.translate(data[0]), getOctave(data[1]));
    }

    /**
     * Generates the data blocks containing the string representation as well as a description of the various
     * components of the notes
     *
     * @param components Components of a note
     * @return data blocks containing the string representation as well as a description of the various
     * components of the notes
     */
    private static String[] getDataFromComponents(String[] components) {
        for (int i = 0; i < components.length; i++) {
            components[i] = components[i].split(" -> ")[1].trim();
        }
        return components;
    }

    /**
     * Parses the octave from a given string
     *
     * @param octaveRepresentation string representing the octave of a note
     * @return int representing the octave of a note
     */
    private static int getOctave(String octaveRepresentation) {
        return octaveRepresentation.charAt(octaveRepresentation.length() - 1) - '0';
    }

    /**
     * Parses the flag determining if a note is muted or not
     *
     * @param str string representing a the muted flag of a note
     * @return the flag determining if a note is muted or not
     */
    private static boolean getIsPlayed(String str) {
        return str.matches(".*true");
    }

    /**
     * Translates a given position to a note
     *
     * @param pos position of the note on the fretboard
     * @return note at the specified position
     */
    public static FretboardNote createFretboardNote(FretboardPos pos) {
        FretboardNote note = Guitar.OPEN_STRINGS[pos.getGuitarString()];
        for (int i = 0; i < pos.getFret(); i++) {
            note = note.nextSemiTone();
        }
        return note;
    }

    /**
     * Translates a given SheetNote instance to a FretboardNote instance
     *
     * @param note SheetNote instance to translate
     * @return Corresponding FrerboardNote instance
     */
    public static List<FretboardNote> createFretboardNote(Note note) {
        List<FretboardNote> output = new ArrayList<>();
        FretboardNote currNote;
        for (FretboardNote currOpenString : Guitar.OPEN_STRINGS) {
            currNote = currOpenString;
            for (int i = 0; i < Guitar.FRET_CNT - 1; i++) {
                if (note.equals(currNote)) {
                    output.add(currNote);
                }
                currNote = currNote.nextSemiTone();
            }
        }
        return output;
    }

    /**
     * Creates a SheetNote from the given offset
     *
     * @param offset offset of the note to the lowest Note E
     * @return SheetNote with the given offset to the lowest Note e
     */
    public static SheetNote createSheetNote(int offset) {
        Tone tone = Tone.values()[(Tone.E.ordinal() + offset) % Tone.values().length];
        Prefix prefix = Prefix.NEUTRAL;
        int octave = (offset + Tone.E.ordinal()) / Tone.values().length;
        boolean isPlayed = true;

        return new SheetNote(tone, prefix, octave, isPlayed);
    }

    /**
     * Translates a given FretboardNote instance to a SheetNote instance
     *
     * @param note FretboardNote to translate
     * @return Corresponding SheetNote instance
     */
    public static SheetNote createSheetNote(Note note) {
        return new SheetNote(note.getTone(), note.getPrefix(), note.getOctave(), note.isPlayed());
    }

}
