package logic.guitar;

import java.util.Comparator;

public class Note implements Comparable {
    private NoteCircle id;
    private Pos pos;

    public Note(NoteCircle id, Pos pos) {
        this.id = id;
        this.pos = pos;
    }

    public NoteCircle getId() {
        return id;
    }

    public Pos getPos() {
        return pos;
    }

    public void setId(NoteCircle id) {
        this.id = id;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public Note incOctave() {
        // todo
        return null;
    }

    public Note decOctave() {
        // todo
        return null;
    }

    @Override
    public int compareTo(Object o) {
        assert null != o;
        assert o instanceof Note;
        Pos p1 = this.getPos();
        Pos p2 = ((Note) o).getPos();
        int diff = 0;
        return diff = p1.getGuitarString() - p2.getGuitarString() == 0 ? p1.getFret() - p2.getFret() : diff;
    }

    @Override
    public boolean equals(Object o) {
        if(null == o || !(o instanceof Note)) {
            return false;
        }
        Note other = (Note) o;
        return this.id == other.id && this.pos.equals(other.pos);
    }
}
