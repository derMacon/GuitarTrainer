package logic.audio;

import logic.guitar.Note;
import logic.guitar.NoteCircle;
import logic.guitar.NoteComparator;
import logic.guitar.Pos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AudioConverter {

    public static void playSingleNote(NoteCircle note) {
        System.out.println("Play " + note.name());
    }

    public static void playMultipleNotes(List<Note> notes) {
        Collections.sort(notes);

        // todo
        System.out.println("Play chord");
        for(Note currNote : notes) {
            System.out.println(currNote.toString());
        }
    }

}
