package logic.audio;

import logic.guitar.Note;
import logic.guitar.NoteCircle;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class AudioConverter {

    private final String path;

    public AudioConverter(String path) {
        this.path = path;
    }

    public void playSingleNote(Note note) {
        System.out.println("Play " + note);

        MusicRunner fstRunner = new MusicRunner(new File(this.path + "\\8381__speedy__clean-a-harm.wav"));
        Thread fstThread = new Thread(fstRunner);
        fstThread.start();

        System.out.println("Finished");
    }

    public void playMultipleNotes(List<Note> notes) {
        Collections.sort(notes);

        // todo
        System.out.println("Play chord");
        for(Note currNote : notes) {
            System.out.println(currNote.toString());
        }
    }

}
