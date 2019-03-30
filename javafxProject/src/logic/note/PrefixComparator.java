package logic.note;

import java.util.Comparator;

/**
 * Comparator used to sort a list of prefix in the following order:
 * NEUTRAL -> SHARP -> FLAT
 * <p>
 * Used when iterating the prefix on the gui
 */
public class PrefixComparator implements Comparator<Prefix> {

    @Override
    public int compare(Prefix o1, Prefix o2) {
        assert null != o1 && null != o2;

        if (o1 == Prefix.FLAT) {
            return 1;
        }
        if (o2 == Prefix.FLAT) {
            return -1;
        }

        if (o1 == Prefix.SHARP) {
            return 1;
        }
        if (o2 == Prefix.SHARP) {
            return -1;
        }

        if (o1 == Prefix.NEUTRAL) {
            return 1;
        } else {
            return -1;
        }
    }

}
