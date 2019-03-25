package logic.note;

import java.util.Comparator;

public class PrefixComparator implements Comparator<Prefix> {

    @Override
    public int compare(Prefix o1, Prefix o2) {
        assert null != o1 && null != o2;

        if(o1 == Prefix.FLAT) {
            return 1;
        }
        if(o2 == Prefix.FLAT) {
            return -1;
        }

        if(o1 == Prefix.SHARP) {
            return 1;
        }
        if(o2 == Prefix.SHARP) {
            return -1;
        }

        if(o1 == Prefix.NEUTRAL) {
            return 1;
        } else {
            return -1;
        }

    }
}
