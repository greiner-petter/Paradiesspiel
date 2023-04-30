package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

public class Paradies extends Feld {
    public Paradies(Feld davor, int position) {
        super(davor, position);
    }

    /**
     * Setzt für alle Figuren auf dem Feld, dass sie auf dem Paradiesfeld stehen auf true
     */
    @Override
    public void ereignis() {
        for (Figur figur : getFigurenAufFeld()) {
            figur.setParadies(true);
        }
    }

    @Override
    public String toString() {
        return "Paradies";
    }
}
