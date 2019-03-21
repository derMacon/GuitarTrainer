package logic.guitar;

import gui.NotePrefix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static gui.NotePrefix.*;

public enum NoteCircle {
    C(new NoteId[] {NoteId.C}, new NotePrefix[] {NEUTRAL}),
    C_SHARP(new NoteId[] {NoteId.C, NoteId.D}, new NotePrefix[] {MAJOR, FLAT}),
    D(new NoteId[] {NoteId.D}, new NotePrefix[] {NEUTRAL}),
    D_SHARP(new NoteId[] {NoteId.D, NoteId.E}, new NotePrefix[] {MAJOR, FLAT}),
    E(new NoteId[] {NoteId.E}, new NotePrefix[] {NEUTRAL}),
    F(new NoteId[] {NoteId.F}, new NotePrefix[] {NEUTRAL}),
    F_SHARP(new NoteId[] {NoteId.F, NoteId.G}, new NotePrefix[] {MAJOR, FLAT}),
    G(new NoteId[] {NoteId.G}, new NotePrefix[] {NEUTRAL}),
    G_SHARP(new NoteId[] {NoteId.G, NoteId.A}, new NotePrefix[] {MAJOR, FLAT}),
    A(new NoteId[] {NoteId.A}, new NotePrefix[] {NEUTRAL}),
    A_SHARP(new NoteId[] {NoteId.A, NoteId.B}, new NotePrefix[] {MAJOR, FLAT}),
    B(new NoteId[] {NoteId.B}, new NotePrefix[] {NEUTRAL});

    private Map<NoteId, NotePrefix> notes;

    NoteCircle(NoteId[] id, NotePrefix[] notePrefix) {
        assert id.length == notePrefix.length;
        this.notes = new HashMap<>();
        for (int i = 0; i < id.length; i++) {
            this.notes.put(id[i], notePrefix[i]);
        }
    }

    public Map<NoteId, NotePrefix> getNotes() {
        return notes;
    }

    public NoteId getPrimaryNote() {
        return new ArrayList<>(this.notes.keySet()).get(0);
    }

    public NoteCircle nextSemiTone() {
        return values()[(getPrimaryNote().ordinal() + 1) % values().length];
    }

    public NoteCircle nextMajorTone() {
        // todo implementation
        System.out.println("todo next full note");
        return null;
    }

}
