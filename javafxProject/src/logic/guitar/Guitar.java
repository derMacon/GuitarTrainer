package logic.guitar;

import java.util.HashMap;
import java.util.Map;

public class Guitar {
    private final NoteCircle[] openStrings = new NoteCircle[]{NoteCircle.E, NoteCircle.B, NoteCircle.G, NoteCircle.D, NoteCircle.A, NoteCircle.E};

    private Map<Pos, NoteCircle> pressedStrings;

    public Guitar() {
        this.pressedStrings = new HashMap<>();
        for (int i = 0; i < this.openStrings.length; i++) {
//            this.pressedStrings.put(new Pos(i, 0))
        }
    }

    public void pressNote(Pos pos) {
        assert null != pos;
        NoteCircle note = translate(pos);
        clearString(pos.getString());
        this.pressedStrings.put(pos, note);
    }

    private void clearString(int stringOrd) {
        for(Pos pos : this.pressedStrings.keySet()) {
            if(pos.getString() == stringOrd) {
                this.pressedStrings.remove(pos);
            }
        }
    }

    private NoteCircle translate(Pos pos) {
        NoteCircle[] noteCircle = NoteCircle.values();
        int ord = this.openStrings[pos.getString()].ordinal() + pos.getFret();
        return noteCircle[ord % noteCircle.length];
    }

    public void releaseNote(Pos pos) {
        assert null != pos;
        this.pressedStrings.remove(translate(pos));
    }

    public void playDownStrum() {
        // todo
    }

}
