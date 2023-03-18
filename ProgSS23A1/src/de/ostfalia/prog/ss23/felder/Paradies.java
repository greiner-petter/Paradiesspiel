package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

public class Paradies extends Feld {
    public Paradies(Feld davor) {
        super(davor);
    }

    @Override
    public void ereignis() {
        for (Figur figur : figurenAufFeld) {
            figur.setParadies(true);
        }
    }

    @Override
    public String toString() {
        return "Paradies";
    }
}
