package logic.audio;

/**
 * Enum holding all necessary information about the various sound packs available for this program
 */
public enum SoundPack {
    // todo check octaves from all soundpacks
    // todo create specified audio files
    NYLON("Nylon", "res\\audioFiles\\nylon", 4),
    HARDROCK("Hard-Rock", "res\\hardRock", 4),
    OLDSCHOOL_E_GUITAR("Old school e-instrument", "res\\oldSchoolEGuitar", 4);

    private final String name;
    private final String path;
    private final int highestOctave;

    /**
     * Constructor
     *
     * @param name          name of the soundpack
     * @param path          path to the audio files of the soundpack
     * @param highestOctave highest octave of the highest note from the soundpack
     */
    SoundPack(String name, String path, int highestOctave) {
        this.name = name;
        this.path = path;
        this.highestOctave = highestOctave;
    }

    /**
     * Getter for the name of the soundpack
     *
     * @return name of the soundpack
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the path of the audio files
     *
     * @return path of the audio files
     */
    public String getPath() {
        return path;
    }

    /**
     * Getter for the highest octave
     *
     * @return highest octave
     */
    public int getHighestOctave() {
        return highestOctave;
    }
}
