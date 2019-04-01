package logic.note;

/**
 * Enum representing the possible prefixes a note can have
 */
public enum Prefix {
    FLAT, NEUTRAL, SHARP;

    /**
     * Translates a given String representation from one of the enum members to the appropriate member
     * @param str String to translate
     * @return the appropriate member
     */
    public static Prefix translate(String str) {
        str = str.split("_")[1];
        for (Prefix curr : values()) {
            if (curr.name().equals(str)) {
                return curr;
            }
        }
        return null;
    }
}
