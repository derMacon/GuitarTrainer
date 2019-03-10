package logic.audio;

import logic.guitar.Note;
import logic.guitar.NoteCircle;

import java.util.Collections;
import java.util.List;

public class AudioConverter {

    public static void playSingleNote(NoteCircle id) {
        System.out.println("Play " + id.name());
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
