package logic.audio;

import logic.guitar.Note;
import logic.guitar.NoteCircle;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AudioConverter {

    private List<List<File>> audioFiles = new ArrayList<>();

    private static final String TEMPLATE_BLUEPRINT = "%s_%s_octave";

    public AudioConverter(SoundPack sPack) {
        String prefix = sPack.name().toLowerCase();
        String fileName = null;
        NoteCircle[] notes = NoteCircle.values();
        for (int i = 0; i < notes.length; i++) {
            this.audioFiles.add(new ArrayList<>());
            fileName = String.format(TEMPLATE_BLUEPRINT, prefix, notes[i].name());
            int j = 0;
            File file = new File(sPack.getPath() + "\\" + fileName + j + ".wav");
            while (file.exists()) {
                audioFiles.get(i).add(file);
                file = new File(sPack.getPath() + "\\" + fileName + ++j);
            }
        }
    }

    public void playSingleNote(Note note) {
        System.out.println("Play " + note);
        MusicRunner fstRunner = new MusicRunner(loadAudioFile(note));
        Thread fstThread = new Thread(fstRunner);
        fstThread.start();
    }

    private File loadAudioFile(Note note) {
        return this.audioFiles.get(note.getId().ordinal()).get(note.getOctave());
    }

    public void playMultipleNotes(List<Note> notes) {
        Collections.sort(notes);

        // todo
        System.out.println("Play chord");
        for (Note currNote : notes) {
            System.out.println(currNote.toString());
        }
    }

}
