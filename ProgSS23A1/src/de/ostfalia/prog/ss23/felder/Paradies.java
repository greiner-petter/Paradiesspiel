package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

public class Paradies extends Feld {
    public Paradies(Feld davor, int position) {
        super(davor, position);
    }

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
