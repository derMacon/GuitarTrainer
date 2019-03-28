package logic.audio;

import logic.note.Note;
import logic.note.NoteCircle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implementing all audio related functions
 */
public class AudioConverter implements AudioConnector {

    /**
     * Format string serving as blueprint for the file name.
     * Needed when loading up the audiofile into the list attribut
     * Needed when loading up the audiofile into the list attribute
     */
    private static final String TEMPLATE_BLUEPRINT = "%s_%s_octave";

    /**
     * List of list of audio files used to play single notes.
     * - octave: first dimension
     * - Tone: second dimension
     * e.g. A C in the second octave would be available with the following method call:
     * audioFiles.get(2).get(0)
     */
    private List<List<File>> audioFiles = new ArrayList<>();

    /**
     * Default constructor setting the nylon sound pack as the default
     */
    public AudioConverter() {
        this(SoundPack.NYLON);
    }

    /**
     * Constructor loading up audio files from a given sound pack
     *
     * @param sPack sound pack to load audio files from
     */
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

    @Override
    public void playSingleNote(Note note) {
        if (null != note && note.isPlayed()) {
            MusicRunner fstRunner = new MusicRunner(loadAudioFile(note));
            Thread fstThread = new Thread(fstRunner);
            fstThread.start();
        }
    }

    @Override
    public void playMultipleNotes(Note[] notes) {
        System.out.println("Play downstrum");
        for (int i = notes.length - 1; i >= 0; i--) {
            playSingleNote(notes[i]);
        }
    }

    /**
     * loads up specific audio file corresponding to a given note
     *
     * @param note note to load
     * @return specific audio file corresponding to a given note
     */
    private File loadAudioFile(Note note) {
        return this.audioFiles.get(NoteCircle.getId(note).ordinal()).get(note.getOctave());
    }
}
