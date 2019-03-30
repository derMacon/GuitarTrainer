package logic.note;

/**
 * Enum representing the possible prefixes a note can have
 */
public enum Prefix {
    FLAT, NEUTRAL, SHARP;

    public static Prefix translate(String str) {
        str = str.split("_")[1];
        for(Prefix curr : values()) {
            if (curr.name().equals(str)) {
                return curr;
            }
        }
        return null;
    }
}
