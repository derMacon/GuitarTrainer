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
        NoteCircle[] notes = NoteCircle.values();
        String fileName;
        for (int i = 0; i < notes.length; i++) {
            this.audioFiles.add(new ArrayList<>());
            fileName = String.format(TEMPLATE_BLUEPRINT, prefix, notes[i].name());
            for (int j = 0; j < sPack.getHighestOctave(); j++) {
                File file = new File(sPack.getPath() + "\\" + fileName + j + ".wav");
                if (file.exists()) {
                    audioFiles.get(i).add(this.audioFiles.get(i).size(), file);
                } else {
                    this.audioFiles.get(i).add(new File(sPack.getPath() + "\\" + "empty.wav"));
                }
            }
        }
    }

    public void playSingleNote(Note note) {
        if(note.isPlayed()) {
            MusicRunner fstRunner = new MusicRunner(loadAudioFile(note));
            Thread fstThread = new Thread(fstRunner);
            fstThread.start();
        }
    }

    private File loadAudioFile(Note note) {
        return this.audioFiles.get(note.getId().ordinal()).get(note.getOctave());
    }

    public void playMultipleNotes(Note[] notes) {
        System.out.println("Play downstrum");
        for (int i = notes.length - 1; i >= 0; i--) {
            playSingleNote(notes[i]);
        }
    }
}
