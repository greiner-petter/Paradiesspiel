package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

import java.util.Arrays;

public class Aufschwung extends Feld {
    public Aufschwung(Feld davor) {
        super(davor);
    }

    @Override
    public void ereignis() {
        Feld feld = this;
        Figur figur = figurenAufFeld.get(figurenAufFeld.size() - 1);
        if (Arrays.equals(figur.getWurf(), new int[]{6, 6})) {
            while (feld.getDanach() != null) {
                feld.figurNachVorneBewegen(figur);
                feld = feld.getDanach();
            }
        }
    }

    @Override
    public String toString() {
        return "Aufschwung";
    }
}
