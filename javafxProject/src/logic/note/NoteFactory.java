package logic.note;

import logic.instrument.FretboardPos;
import logic.instrument.Guitar;

import java.util.ArrayList;
import java.util.List;

/**
 * Static factory for the note class implementations
 */
public class NoteFactory {

    public static Note createNote(String str) {
        // todo validate with pattern matching / regex
        String[] components = str.substring(1, str.length() - 1).split(",");
        String[] data = getDataFromComponents(components);

        return new Note(Tone.translate(data[0]), Prefix.translate(data[0]),
                getOctave(data[1]), getIsPlayed(data[2]));
    }

    private static String[] getDataFromComponents(String[] components) {
        for (int i = 0; i < components.length; i++) {
            components[i] = components[i].split(" -> ")[1];
        }
        return components;
    }

    private static int getOctave(String octaveRepresentation) {
        return octaveRepresentation.charAt(octaveRepresentation.length() - 1) - '0';
    }

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
    public static List<FretboardNote> createFretboardNote(SheetNote note) {
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
    public static SheetNote createSheetNote(FretboardNote note) {
        return new SheetNote(note.getTone(), note.getPrefix(), note.getOctave(), note.isPlayed());
    }

}
