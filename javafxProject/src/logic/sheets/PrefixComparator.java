package logic.sheets;

import gui.NotePrefix;

import java.util.Comparator;

public class PrefixComparator implements Comparator<NotePrefix> {


    @Override
    public int compare(NotePrefix o1, NotePrefix o2) {
        assert null != o1 && null != o2;

        if(o1 == NotePrefix.FLAT) {
            return 1;
        }
        if(o2 == NotePrefix.FLAT) {
            return -1;
        }

        if(o1 == NotePrefix.SHARP) {
            return 1;
        }
        if(o2 == NotePrefix.SHARP) {
            return -1;
        }

        if(o1 == NotePrefix.NEUTRAL) {
            return 1;
        }
        if(o2 == NotePrefix.NEUTRAL) {
            return -1;
        }

        if(o1 == NotePrefix.MUTED) {
            return 1;
        } else {
            return -1;
        }
    }
}
