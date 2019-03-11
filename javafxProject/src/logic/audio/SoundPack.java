package logic.audio;

public enum SoundPack {
    NYLON("Nylon", "res\\audioFiles\\nylon"),
    HARDROCK("Hard-Rock", "res\\hardRock"),
    OLDSCHOOL_E_GUITAR("Old school e-guitar", "res\\oldSchoolEGuitar");

    private final String name;
    private final String path;

    SoundPack(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

}
