package logic.guitar;

import java.util.Comparator;
import java.util.Map;

public class NoteComparator implements Comparator<Map.Entry<Pos, NoteCircle>> {

    @Override
    public int compare(Map.Entry<Pos, NoteCircle> o1, Map.Entry<Pos, NoteCircle> o2) {
        return o1.getKey().getString() - o2.getKey().getString();
    }

}
