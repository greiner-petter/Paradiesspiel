package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

import java.util.Arrays;

public class Aufschwung extends Feld {
    public Aufschwung(Feld davor, int position) {
        super(davor, position);
    }

    /**
     * Wenn das Feld mit einem doppel 6 Wurf erreicht wurde, wird die Figur in das Paradies bewegt
     */
    @Override
    public void ereignis() {
        Feld feld = this;
        Figur figur = getFigurenAufFeld().get(getFigurenAufFeld().size() - 1);
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
