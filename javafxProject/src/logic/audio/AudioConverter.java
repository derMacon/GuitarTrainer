package logic.audio;

import logic.guitar.NoteCircle;
import logic.guitar.NoteComparator;
import logic.guitar.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AudioConverter {

    public static void playSingleNote(NoteCircle note) {
        System.out.println("Play " + note.name());
    }

    public static void playMultipleNotes(Map<Pos, NoteCircle> notes) {
        List<Map.Entry<Pos, NoteCircle>> orderedNotes = extractNotes(notes);

        // todo
        System.out.println("Play Board");
        for(Map.Entry<Pos, NoteCircle> e : orderedNotes) {
            System.out.println(e.toString());
        }
    }

    private static List<Map.Entry<Pos, NoteCircle>> extractNotes(Map<Pos, NoteCircle> notes) {
        List<Map.Entry<Pos, NoteCircle>> lst = new ArrayList<>(notes.entrySet());
        lst.sort(new NoteComparator());
        return lst;
    }
}
