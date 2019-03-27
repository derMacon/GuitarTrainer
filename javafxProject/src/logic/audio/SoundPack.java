package logic.audio;

public enum SoundPack {
    // todo check octaves from all soundpacks
    NYLON("Nylon", "res\\audioFiles\\nylon", 4),
    HARDROCK("Hard-Rock", "res\\hardRock", 4),
    OLDSCHOOL_E_GUITAR("Old school e-instrument", "res\\oldSchoolEGuitar", 4);

    private final String name;
    private final String path;
    private final int highestOctave;

    SoundPack(String name, String path, int highestOctave) {
        this.name = name;
        this.path = path;
        this.highestOctave = highestOctave;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getHighestOctave() {
        return highestOctave;
    }
}
