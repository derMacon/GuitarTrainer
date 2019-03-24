package logic.guitar;

import gui.NotePrefix;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static gui.NotePrefix.*;

public enum NoteCircle {
    C(new Tone[]{Tone.C}, new NotePrefix[]{NEUTRAL}),
    C_SHARP(new Tone[]{Tone.C, Tone.D}, new NotePrefix[]{SHARP, FLAT}),
    D(new Tone[]{Tone.D}, new NotePrefix[]{NEUTRAL}),
    D_SHARP(new Tone[]{Tone.D, Tone.E}, new NotePrefix[]{SHARP, FLAT}),
    E(new Tone[]{Tone.E}, new NotePrefix[]{NEUTRAL}),
    F(new Tone[]{Tone.F}, new NotePrefix[]{NEUTRAL}),
    F_SHARP(new Tone[]{Tone.F, Tone.G}, new NotePrefix[]{SHARP, FLAT}),
    G(new Tone[]{Tone.G}, new NotePrefix[]{NEUTRAL}),
    G_SHARP(new Tone[]{Tone.G, Tone.A}, new NotePrefix[]{SHARP, FLAT}),
    A(new Tone[]{Tone.A}, new NotePrefix[]{NEUTRAL}),
    A_SHARP(new Tone[]{Tone.A, Tone.B}, new NotePrefix[]{SHARP, FLAT}),
    B(new Tone[]{Tone.B}, new NotePrefix[]{NEUTRAL});

    private Map<Tone, NotePrefix> notes;

    NoteCircle(Tone[] id, NotePrefix[] notePrefix) {
        assert id.length == notePrefix.length;
        this.notes = new LinkedHashMap<>();
        for (int i = 0; i < id.length; i++) {
            this.notes.put(id[i], notePrefix[i]);
        }
    }

    public Map<Tone, NotePrefix> getNotes() {
        return notes;
    }

    public Tone getPrimaryTone() {
        return getTones().get(0);
    }

    public List<Tone> getTones() {
        return new ArrayList<>(this.notes.keySet());
    }

    public NoteCircle nextSemiTone() {
        return values()[(ordinal() + 1) % values().length];
    }

    public NoteCircle nextMajorTone() {
        NoteCircle output = nextSemiTone();
//        while (this.getTones().equals(output.getTones())) {
        while(output.getTones().contains(this.getPrimaryTone())) {
            output = output.nextSemiTone();
        }
        return output;
    }


}
